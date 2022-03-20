package lesson06;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.*;
import java.util.Properties;

public class YandexWeather {
    static Properties prop = new Properties();

    public static void main(String[] args) throws IOException {

        loadProperties();
        OkHttpClient client = new OkHttpClient();

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

        Request requestHTTP = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(url)
                .addHeader("X-Yandex-API-Key", prop.getProperty("API_KEY"))
                .build();

        String jsonResponse = client.newCall(requestHTTP).execute().body().string();
        System.out.println(jsonResponse);
    }

    private static void loadProperties() throws IOException {
        try(FileInputStream configFile = new FileInputStream("src/lesson06/yandexweather.properties")){
            prop.load(configFile);
        }
    }
}
