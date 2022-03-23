package lesson07.yandexWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date",
        "date_ts",
        "week",
        "sunrise",
        "sunset",
        "rise_begin",
        "set_end",
        "moon_code",
        "moon_text",
        "parts",

})

public class Forecast {

    private String date;
    private long date_ts;
    private long week;
    private String sunrise;
    private String sunset;
    private String rise_begin;
    private String set_end;
    private long moon_code;
    private String moon_text;
    private Parts parts;


    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public long getDate_ts() { return date_ts; }
    public void setDate_ts(long date_ts) { this.date_ts = date_ts; }

    public long getWeek() { return week; }
    public void setWeek(long week) { this.week = week; }

    public String getSunrise() { return sunrise; }
    public void setSunrise(String sunrise) { this.sunrise = sunrise; }

    public String getSunset() { return sunset; }
    public void setSunset(String sunset) { this.sunset = sunset; }

    public String getRise_begin() { return rise_begin; }
    public void setRise_begin(String rise_begin) { this.rise_begin = rise_begin; }

    public String getSet_end() { return set_end; }
    public void setSet_end(String setEnd) { this.set_end = setEnd; }

    public long getMoon_code() { return moon_code; }
    public void setMoon_code(long moonCode) { this.moon_code = moonCode; }

    public String getMoon_text() { return moon_text; }
    public void setMoon_text(String moonText) { this.moon_text = moonText; }

    public Parts getParts() { return parts; }
    public void setParts(Parts parts) { this.parts = parts; }

}
