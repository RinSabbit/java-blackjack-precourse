package view;

import java.util.Scanner;

public class InputView {

    private static final String DELIMITER = ",";
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String inputValue(){
        return scanner.nextLine();
    }

    public String[] inputPlayers() {
        String players = scanner.nextLine();
        return players.split(DELIMITER);
    }
}
