package lesson08;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.Objects;

public class RunClassGeocode {


    public static void main(String[] args) throws IOException {

        String apiKeyForCity = ApplicationGlobalState.getInstance().getApiKeyForCity();

        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("catalog.api.2gis.com")
                .addPathSegment("3.0")
                .addPathSegment("items")
                .addPathSegment("geocode")
                .addQueryParameter("q", "Moscow")
                .addQueryParameter("fields", "items.point")
                .addQueryParameter("key", apiKeyForCity)
                .build();

        System.out.println(url);

        Request requestHTTP = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(url)
                .build();

        String jsonResponse = Objects.requireNonNull(client.newCall(requestHTTP).execute().body()).string();
        System.out.println(jsonResponse);
    }
}
