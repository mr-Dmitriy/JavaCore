package lesson07.yandexWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "now",
        "now_dt",
        "forecasts"
})

public class WeatherResponse {

    private float now;
    private String now_dt;
    private Forecast[] forecasts;

    public float getNow() {
        return now;
    }
    public void setNow(float now) {
        this.now = now;
    }

    public String getNow_dt() {
        return now_dt;
    }
    public void setNow_dt(String now_dt) {
        this.now_dt = now_dt;
    }

    public Forecast[] getForecasts() { return forecasts; }
    public void setForecasts(Forecast[] forecasts) { this.forecasts = forecasts; }
}
