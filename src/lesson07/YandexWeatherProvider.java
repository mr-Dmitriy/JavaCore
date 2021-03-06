package lesson07;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lesson07.enums.Periods;
import lesson07.yandexWeather.Forecast;
import lesson07.yandexWeather.WeatherResponse;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;
import java.util.Properties;


public class YandexWeatherProvider implements WeatherProvider {

    private static final String BASE_HOST = "api.weather.yandex.ru";
    private static final String API_VERSION = "v2";
    private static final String FORECAST = "forecast";
    private static final String LANG = "ru_RU";
    static Properties prop = new Properties();





    public void getWeather(Periods periods) throws IOException {

        OkHttpClient client = new OkHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        String[] valLatLon = detectLatLon();
        String latValue = valLatLon[0];
        String lonValue = valLatLon[1];
        loadProperties();

        HttpUrl url = null;

        if (periods.equals(Periods.NOW)) {
            url = new HttpUrl.Builder()
                    .scheme("https")
                    .host(BASE_HOST)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(FORECAST)
                    .addQueryParameter("lat", latValue)
                    .addQueryParameter("lon", lonValue)
                    .addQueryParameter("lang", LANG)
                    .addQueryParameter("limit", "1")
                    .build();
        }

        if (periods.equals(Periods.FIVE_DAYS)) {
            url = new HttpUrl.Builder()
                    .scheme("https")
                    .host(BASE_HOST)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(FORECAST)
                    .addQueryParameter("lat", latValue)
                    .addQueryParameter("lon", lonValue)
                    .addQueryParameter("lang", LANG)
                    .addQueryParameter("limit", "5")
                    .build();
        }

        assert url != null;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-Yandex-API-Key", prop.getProperty("API_KEY"))
                .build();

        String jsonResponse = Objects.requireNonNull(client.newCall(request).execute().body()).string();
        System.out.println(jsonResponse);

        StringReader reader = new StringReader(jsonResponse);

        WeatherResponse weatherResponse = mapper.readValue(reader, WeatherResponse.class);

        String city = mapper
                .readTree(jsonResponse)
                .at("/geo_object/locality/name")
                .asText();

        for (Forecast forecast : weatherResponse.getForecasts()) {
            if (periods.equals(Periods.NOW)) {
                System.out.println(
                        "?? ???????????? " + city +
                                " ?????????????? (" + forecast.getDate() +
                                ") " + forecast.getParts().getDay_short().getCondition() +
                                ", ?????????????????????? " + forecast.getParts().getDay_short().getTemp()
                );
            }
            if (periods.equals(Periods.FIVE_DAYS)) {
                System.out.println(
                        "?? ???????????? " + city +
                                " ???? ???????? " + forecast.getDate() +
                                " ?????????????????? " + forecast.getParts().getDay_short().getCondition() +
                                ", ?????????????????????? " + forecast.getParts().getDay_short().getTemp()
                );
            }
        }

    }

    public String[] detectLatLon() throws IOException {

        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();
        String apiKeyForCity = ApplicationGlobalState.getInstance().getApiKeyForCity();

        System.out.println("?????????? ???????????? " + selectedCity + "...");

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
            throw new IOException("???????????????????? ???????????????? ???????????????????? ?? ????????????. " +
                    "?????? ???????????? ?????????????? = " + response.code() + " ???????? ???????????? = " + Objects.requireNonNull(response.body()).string());
        }

        String jsonResponse = Objects.requireNonNull(response.body()).string();

        if (objectMapper.readTree(jsonResponse).size() == 0) {
            throw new IOException("Server returns 0 cities");
        }

        for (JsonNode res : objectMapper.readTree(jsonResponse)) {
            for (JsonNode loc : res.at("/items")) {
                String lat = loc.at("/point/lat").asText();
                String lon = loc.at("/point/lon").asText();
                System.out.println("?????????? " + selectedCity + " ???????????? ???? ???????????? " + lat + " ?? ?????????????? " + lon);
                return new String[]{lat, lon};
            }
        }

        throw new IOException("Server returns 0 cities");
    }

    private static void loadProperties() throws IOException {
        try(FileInputStream configFile = new FileInputStream("src/lesson06/yandexweather.properties")){
            prop.load(configFile);
        }
    }

}
