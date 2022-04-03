package lesson08;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UserInterface {

    private final Controller controller = new Controller();

    public void runApplication() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print(
                    "\n1 - Show and add current weather to database\n" +
                    "2 - Show and add the weather for the next 5 days to the database\n" +
                    "3 - Get data from the database by city name and date\n" +
                    "4 - Get all data from the database\n" +
                    "5 - Clear the database\n" +
                    "Exit or exit - finish work\n" +
                    "Enter your answer: "
            );
            String result = scanner.nextLine();

            if (result.equals("1") || result.equals("2")) {

                System.out.print("Enter city name in English (exit or Exit - finish work): ");
                String city = scanner.nextLine();
                checkIsExit(city);
                setGlobalCity(city);
            }

            if (result.equals("3")) {

                System.out.print("Enter city name in English (exit or Exit - завершить работу): ");
                String city = scanner.nextLine();
                checkIsExit(city);
                setGlobalCity(city);

                System.out.print("Enter date in YYYY-MM-DD format (exit or Exit - finish work): ");
                String selectedDate = scanner.nextLine();
                checkIsExit(selectedDate);
                setDate(selectedDate);

            }

            checkIsExit(result);

            try {
                validateUserInput(result);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

            try {
                notifyController(result);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

            checkIsExit(result);
        }
    }

    private void checkIsExit(String result) {
        if (result.equals("Exit") || result.equals("exit")) {
            System.out.println("Finish work");
            System.exit(0);
        }
    }

    private void setGlobalCity(String result) {
        ApplicationGlobalState.getInstance().setSelectedCity(result);
    }

    private void setDate(String result) {
        ApplicationGlobalState.getInstance().setSelectedDate(result);
    }

    private void validateUserInput(String userInput) throws IOException {
        if (userInput == null || userInput.length() != 1) {
            throw new IOException("Incorrect user input: expected one digit as answer, but actually get " + userInput);
        }
        int answer = 0;
        try {
            answer = Integer.parseInt(userInput);
            if (answer > 5){
                throw new IOException("Incorrect user input: character must be less then 5!");
            }
        } catch (NumberFormatException e) {
            throw new IOException("Incorrect user input: character is not numeric!");
        }
    }

    private void notifyController(String input) throws IOException, SQLException {
        controller.onUserInput(input);
    }

}
