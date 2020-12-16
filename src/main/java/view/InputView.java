package view;

import java.util.Scanner;

public class InputView {

    private static final String DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputValue() {
        return scanner.nextLine();
    }

    public static String[] inputPlayers() {
        String players = scanner.nextLine();
        return players.split(DELIMITER);
    }
}
