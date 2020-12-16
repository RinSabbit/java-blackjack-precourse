package view;

import java.util.Scanner;
import java.util.stream.IntStream;

public class InputView {
    private static final String DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputValue() {
        return scanner.nextLine();
    }

    public static String[] inputPlayers() {
        String[] players = scanner.nextLine()
            .split(DELIMITER);
        IntStream.range(0,players.length)
            .forEach(value -> players[value] = players[value].trim());
        return players;
    }
}
