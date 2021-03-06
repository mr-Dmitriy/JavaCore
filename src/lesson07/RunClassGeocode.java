package lesson07;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.Objects;

public class RunClassGeocode {


    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("geocode-maps.yandex.ru")
                .addPathSegment("1.x")
                .addQueryParameter("apikey", ApplicationGlobalState.getInstance().getApiKeyForCity())
                .addQueryParameter("geocode", "Moscow")
                .build();

        System.out.println(url);

        Request requestHTTP = new Request.Builder()
                .url(url)
                .build();

        String jsonResponse = Objects.requireNonNull(client.newCall(requestHTTP).execute().body()).string();
        System.out.println(jsonResponse);
    }
}
