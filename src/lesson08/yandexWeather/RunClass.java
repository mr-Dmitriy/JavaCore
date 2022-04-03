package lesson08.yandexWeather;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;
import java.util.Properties;


public class RunClass {

    static Properties prop = new Properties();

    public static void main(String[] args) throws IOException {

        loadProperties();
        OkHttpClient client = new OkHttpClient();

        // Сегментированное построение URL
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(prop.getProperty("BASE_HOST"))
                .addPathSegment(prop.getProperty("API_VERSION"))
                .addPathSegment(prop.getProperty("FORECAST"))
                .addQueryParameter("lat", prop.getProperty("LAT"))
                .addQueryParameter("lon", prop.getProperty("LON"))
                .addQueryParameter("lang", prop.getProperty("LANG"))
                .addQueryParameter("limit", prop.getProperty("LIMIT"))
                .build();

        System.out.println(url);

        // Указание заголовка
        Request requestHTTP = new Request.Builder()
                .url(url)
                .addHeader("X-Yandex-API-Key", prop.getProperty("API_KEY"))
                .build();

        String jsonResponse = Objects.requireNonNull(client.newCall(requestHTTP).execute().body()).string();
        System.out.println(jsonResponse);

        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(jsonResponse);

        WeatherResponse weatherResponse = mapper.readValue(reader, WeatherResponse.class);

        String city = mapper
                .readTree(jsonResponse)
                .at("/geo_object/locality/name")
                .asText();

        for (Forecast forecast : weatherResponse.getForecasts()) {
            System.out.println(
                    "В городе " + city +
                            " на дату " + forecast.getDate() +
                            " ожидается " + forecast.getParts().getDay_short().getCondition() +
                            ", температура " + forecast.getParts().getDay_short().getTemp()
            );
        }
    }

    private static void loadProperties() throws IOException {
        try(FileInputStream configFile = new FileInputStream("src/lesson07/weatherYandex/yandexweather.properties")){
            prop.load(configFile);
        }
    }
}
