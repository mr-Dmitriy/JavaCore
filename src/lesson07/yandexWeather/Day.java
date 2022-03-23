package lesson07.yandexWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_source",
        "temp",
        "temp_min",
        "temp_avg",
        "temp_max",
        "temp_water",
        "wind_speed",
        "wind_gust",
        "wind_dir",
        "pressure_mm",
        "pressure_pa",
        "humidity",
        "soil_temp",
        "soil_moisture",
        "prec_mm",
        "prec_prob",
        "prec_period",
        "cloudness",
        "prec_type",
        "prec_strength",
        "icon",
        "condition",
        "uvIndex",
        "feels_like",
        "daytime",
        "polar",
        "fresh_snow_mm",

})

public class Day {

    @JsonProperty("_source")
    private String source;
    private Long temp_min;
    private Long temp_avg;
    private Long temp_max;
    private long temp_water;
    private double wind_speed;
    private double wind_gust;
    private String wind_dir;
    private long pressure_mm;
    private long pressure_pa;
    private long humidity;
    private long soilTemp;
    private double soil_moisture;
    private long prec_mm;
    private long prec_prob;
    private long prec_period;
    private double cloudness;
    private long prec_type;
    private long prec_strength;
    private String icon;
    private String condition;
    private Long uv_index;
    private long feels_like;
    private String daytime;
    private boolean polar;
    private Long fresh_snowMm;
    private Long temp;

    @JsonProperty("_source")
    public String getSource() { return source; }
    @JsonProperty("_source")
    public void setSource(String value) { this.source = value; }

    public Long getTemp_min() { return temp_min; }
    public void setTemp_min(Long value) { this.temp_min = value; }

    public Long getTemp_avg() { return temp_avg; }
    public void setTemp_avg(Long value) { this.temp_avg = value; }

    public Long getTemp_max() { return temp_max; }
    public void setTemp_max(Long value) { this.temp_max = value; }

    public long getTemp_water() { return temp_water; }
    public void setTemp_water(long value) { this.temp_water = value; }

    public double getWind_speed() { return wind_speed; }
    public void setWind_speed(double value) { this.wind_speed = value; }

    public double getWind_gust() { return wind_gust; }
    public void setWind_gust(double value) { this.wind_gust = value; }

    public String getWind_dir() { return wind_dir; }
    public void setWind_dir(String value) { this.wind_dir = value; }

    public long getPressure_mm() { return pressure_mm; }
    public void setPressure_mm(long value) { this.pressure_mm = value; }

    public long getPressure_pa() { return pressure_pa; }
    public void setPressure_pa(long value) { this.pressure_pa = value; }

    public long getHumidity() { return humidity; }
    public void setHumidity(long value) { this.humidity = value; }

    public long getSoil_temp() { return soilTemp; }
    public void setSoil_temp(long value) { this.soilTemp = value; }

    public double getSoil_moisture() { return soil_moisture; }
    public void setSoil_moisture(double value) { this.soil_moisture = value; }

    public long getPrec_mm() { return prec_mm; }
    public void setPrec_mm(long value) { this.prec_mm = value; }

    public long getPrec_prob() { return prec_prob; }
    public void setPrec_prob(long value) { this.prec_prob = value; }

    public long getPrec_period() { return prec_period; }
    public void setPrec_period(long value) { this.prec_period = value; }

    public double getCloudness() { return cloudness; }
    public void setCloudness(double value) { this.cloudness = value; }

    public long getPrec_type() { return prec_type; }
    public void setPrec_type(long value) { this.prec_type = value; }

    public long getPrec_strength() { return prec_strength; }
    public void setPrec_strength(long value) { this.prec_strength = value; }

    public String getIcon() { return icon; }
    public void setIcon(String value) { this.icon = value; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public Long getUv_index() { return uv_index; }
    public void setUv_index(Long value) { this.uv_index = value; }

    public long getFeels_like() { return feels_like; }
    public void setFeels_like(long value) { this.feels_like = value; }

    public String getDaytime() { return daytime; }
    public void setDaytime(String value) { this.daytime = value; }

    public boolean getPolar() { return polar; }
    public void setPolar(boolean value) { this.polar = value; }

    public Long getFresh_snow_mm() { return fresh_snowMm; }
    public void setFresh_snow_mm(Long value) { this.fresh_snowMm = value; }

    public Long getTemp() { return temp; }
    public void setTemp(Long value) { this.temp = value; }

}
