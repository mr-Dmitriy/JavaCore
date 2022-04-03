package lesson08;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lesson08.enums.Periods;
import lesson08.yandexWeather.Forecast;
import lesson08.yandexWeather.WeatherResponse;
import lesson08.entity.WeatherData;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.sql.SQLException;


public class YandexWeatherProvider implements WeatherProvider {
    private static final String BASE_HOST = "api.weather.yandex.ru";
    private static final String API_VERSION = "v2";
    private static final String FORECAST = "forecast";
    private static final String LANG = "en_US";

    static Properties prop = new Properties();

    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final DatabaseRepositorySQLiteImpl databaseRepositorySQLite = new DatabaseRepositorySQLiteImpl();

    @Override
    public void getWeather(Periods periods) throws IOException, SQLException  {

        loadProperties();

        if (periods.equals(Periods.NOW) || periods.equals(Periods.FIVE_DAYS)) {

            String[] valLatLon = detectLatLon();
            String latValue = valLatLon[0];
            String lonValue = valLatLon[1];

            HttpUrl url;
            url = new HttpUrl.Builder()
                             .scheme("https")
                             .host(BASE_HOST)
                             .addPathSegment(API_VERSION)
                             .addPathSegment(FORECAST)
                             .addQueryParameter("lat", latValue)
                             .addQueryParameter("lon", lonValue)
                             .addQueryParameter("lang", LANG)
                             .addQueryParameter("limit", periods.equals(Periods.NOW)?"1":prop.getProperty("LIMIT"))
                             .build();

            Request request = new Request.Builder()
                                         .url(url)
                                         .addHeader("X-Yandex-API-Key", API_KEY)
                                         .build();

            String responseList = Objects.requireNonNull(client.newCall(request).execute().body()).string();

            StringReader reader = new StringReader(responseList);

            WeatherResponse weatherResponse = objectMapper.readValue(reader, WeatherResponse.class);

            String city = objectMapper
                                      .readTree(responseList)
                                      .at("/geo_object/locality/name")
                                      .asText();

            for (Forecast forecast : weatherResponse.getForecasts()) {

                String valveRes = "In town " + city +
                                  (periods.equals(Periods.NOW) ? " today (" : " on date ") +
                                  forecast.getDate() + (periods.equals(Periods.NOW) ? ") " : " awaits ") +
                                  forecast.getParts().getDay_short().getCondition() + ", temperature " +
                                  forecast.getParts().getDay_short().getTemp();
                System.out.println(valveRes);

                WeatherData weatherData = new WeatherData(
                                                          city,
                                                          forecast.getDate(),
                                                          forecast.getParts().getDay_short().getCondition(),
                                                          forecast.getParts().getDay_short().getTemp()
                );

                databaseRepositorySQLite.createTableIfNotExists();
                databaseRepositorySQLite.saveWeatherData(weatherData);
            }
        }

        if (periods.equals(Periods.WEATHER_DATE_BY_SELECTED_DATE)) {
            getDataFromDbBySelectingDate();
        }

        if (periods.equals(Periods.ALL_DATE)) {
            getAllFromDb();
        }

        if (periods.equals(Periods.CLEAR)) {
            databaseRepositorySQLite.performDropTable();
            System.out.println("The DateBase is cleared");
        }

    }

    @Override
    public List<WeatherData> getAllFromDb() throws SQLException {
        List<WeatherData> weatherDataList = databaseRepositorySQLite.getAllSavedData();
        printlnResult(weatherDataList);
        return weatherDataList;
    }

    public void getDataFromDbBySelectingDate() throws SQLException {
        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();
        String selectedDate = ApplicationGlobalState.getInstance().getSelectedDate();
        List<WeatherData> weatherDataList = databaseRepositorySQLite.getDataBySelectingCityAndDate(selectedCity,selectedDate);
        printlnResult(weatherDataList);
    }

    public void printlnResult(List<WeatherData> weatherDataList) {
        if (weatherDataList.size() == 0){
            System.out.println("There is no such date");
        }else {
            for (WeatherData weatherData : weatherDataList) {
                String valveRes = "In town " + weatherData.getCity() + " on date " +  weatherData.getLocalDate() +
                        " awaits " + weatherData.getText() + ", temperature " + weatherData.getTemperature();
                System.out.println(valveRes);
            }
        }
    }

    public String[] detectLatLon() throws IOException {

        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();
        String apiKeyForCity = ApplicationGlobalState.getInstance().getApiKeyForCity();

        System.out.println("Поиск города " + selectedCity + "...");

        HttpUrl detectLocationURL = new HttpUrl.Builder()
                                               .scheme("https")
                                               .host("catalog.api.2gis.com")
                                               .addPathSegment("3.0")
                                               .addPathSegment("items")
                                               .addPathSegment("geocode")
                                               .addQueryParameter("q", selectedCity)
                                               .addQueryParameter("fields", "items.point")
                                               .addQueryParameter("key", apiKeyForCity)
                                               .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(detectLocationURL)
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("unable to read city information. " +
                    "Server response code = " + response.code() + " response body = " + Objects.requireNonNull(response.body()).string());
        }

        String jsonResponse = Objects.requireNonNull(response.body()).string();

        if (objectMapper.readTree(jsonResponse).size() == 0) {
            throw new IOException("Server returns 0 cities");
        }

        for (JsonNode res : objectMapper.readTree(jsonResponse)) {
            for (JsonNode loc : res.at("/items")) {
                String lat = loc.at("/point/lat").asText();
                String lon = loc.at("/point/lon").asText();
                System.out.println("The town " + selectedCity + " found on " + lat + " , " + lon);
                return new String[]{lat, lon};
            }
        }

        throw new IOException("Server returns 0 cities");
    }

    private static void loadProperties() throws IOException {
        try(FileInputStream configFile = new FileInputStream("src/lesson08/yandexWeather/yandexweather.properties")){
            prop.load(configFile);
        }
    }

}
