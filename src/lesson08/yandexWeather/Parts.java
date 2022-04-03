package lesson08.yandexWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "morning",
        "evening",
        "night_short",
        "night",
        "day",
        "day_short"
})

public class Parts {

    private Day morning;
    private Day evening;
    private Day night_short;
    private Day night;
    private Day day;
    private Day day_short;

    public Day getMorning() { return morning; }
    public void setMorning(Day value) { this.morning = value; }

    public Day getEvening() { return evening; }
    public void setEvening(Day value) { this.evening = value; }

    public Day getNight_short() { return night_short; }
    public void setNight_short(Day value) { this.night_short = value; }

    public Day getNight() { return night; }
    public void setNight(Day value) { this.night = value; }

    public Day getDay() { return day; }
    public void setDay(Day value) { this.day = value; }

    public Day getDay_short() { return day_short; }
    public void setDay_short(Day value) { this.day_short = value; }
}
