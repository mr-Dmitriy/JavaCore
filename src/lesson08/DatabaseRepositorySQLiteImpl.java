package lesson08;

import lesson08.entity.WeatherData;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class DatabaseRepositorySQLiteImpl implements DatabaseRepository {

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    String filename = null;
    String createTableQuery = "CREATE TABLE IF NOT EXISTS weather (\n" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "city TEXT NOT NULL,\n" +
            "date_time TEXT NOT NULL,\n" +
            "weather_text TEXT NOT NULL,\n" +
            "temperature REAL NOT NULL\n" +
            ");";
    String insertWeatherQuery = "INSERT INTO weather (city, date_time, weather_text, temperature) VALUES (?,?,?,?)";

    public DatabaseRepositorySQLiteImpl() {
        filename = ApplicationGlobalState.getInstance().getDbFileName();
    }

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
        return connection;
    }

    public Statement getStatement() throws SQLException {
        Statement statement = getConnection().createStatement();
        return statement;
    }

    public PreparedStatement getPreparedStatement() throws SQLException{
        PreparedStatement preparedStatement = getConnection().prepareStatement(insertWeatherQuery);
        return preparedStatement;
    }

    public void createTableIfNotExists() {
        try (Connection connection = getConnection()) {
            connection.createStatement().execute(createTableQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean saveWeatherData(WeatherData weatherData) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement saveWeather = connection.prepareStatement(insertWeatherQuery)) {
            saveWeather.setString(1, weatherData.getCity());
            saveWeather.setString(2, weatherData.getLocalDate());
            saveWeather.setString(3, weatherData.getText());
            saveWeather.setLong(4, weatherData.getTemperature());
            return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new SQLException("Failure on saving weather object");
    }

    @Override
    public List<WeatherData> getAllSavedData() throws SQLException {
        ResultSet resultSet = getConnection().createStatement().executeQuery("SELECT * FROM weather");
        List<WeatherData> weatherDataList = new ArrayList<>();
        while (resultSet.next()) {
            weatherDataList.add(new WeatherData(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getLong(5)));
        }
        return weatherDataList;
    }

    public void closeConnection (){
        try {
            getConnection().close();
            getStatement().close();
            getPreparedStatement().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void performDropTable() throws SQLException {
        try {
            getStatement().executeUpdate("DROP TABLE IF EXISTS weather");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<WeatherData> getDataBySelectingCityAndDate(String city, String date) throws SQLException {

        PreparedStatement selections = getConnection().prepareStatement("SELECT * FROM weather WHERE city = ? AND date_time = ?");
        selections.setString(1, city);
        selections.setString(2, date);
        ResultSet resultSet = selections.executeQuery();

        List<WeatherData> weatherDataList = new ArrayList<>();
        while (resultSet.next()) {
            weatherDataList.add(new WeatherData(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getLong(5)));
        }
        return weatherDataList;
    }
}
