package lesson08;

import lesson08.enums.Functionality;
import lesson08.enums.Periods;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;

public class Controller {

    WeatherProvider weatherProvider  = new YandexWeatherProvider();
    Map<Integer, Functionality> variantResult = new HashMap();

    public Controller() {
        variantResult.put(1, Functionality.GET_CURRENT_WEATHER);
        variantResult.put(2, Functionality.GET_WEATHER_IN_NEXT_5_DAYS);
        variantResult.put(3, Functionality.GET_WEATHER_DATA_BY_SELECTING_DATE_FROM_BD);
        variantResult.put(4, Functionality.GET_ALL_WEATHER_DATE_FROM_BD);
        variantResult.put(5, Functionality.GET_CLEAR_BASE);
    }

    public void onUserInput(String input) throws IOException, SQLException {
        int command = Integer.parseInt(input);
        if (!variantResult.containsKey(command)) {
            throw new IOException("There is no command for command-key " + command);
        }

        switch (variantResult.get(command)) {
            case GET_CURRENT_WEATHER:
                getCurrentWeather();
                break;
            case GET_WEATHER_IN_NEXT_5_DAYS:
                getWeatherIn5Days();
                break;
            case GET_WEATHER_DATA_BY_SELECTING_DATE_FROM_BD:
                getWeatherFromBdByDate();
                break;
            case GET_ALL_WEATHER_DATE_FROM_BD:
                getWeatherFromBd();
                break;
            case GET_CLEAR_BASE:
                getClearDB();
                break;
        }
    }

    public void getCurrentWeather() throws IOException, SQLException {
        weatherProvider.getWeather(Periods.NOW);
    }

    public void getWeatherIn5Days() throws IOException, SQLException {
        weatherProvider.getWeather(Periods.FIVE_DAYS);
    }

    public void getWeatherFromBdByDate() throws IOException, SQLException {
        weatherProvider.getWeather(Periods.WEATHER_DATE_BY_SELECTED_DATE);
    }

    public void getWeatherFromBd() throws IOException, SQLException {
        weatherProvider.getWeather(Periods.ALL_DATE);
    }

    public void getClearDB() throws IOException, SQLException {
        weatherProvider.getWeather(Periods.CLEAR);
    }
}
