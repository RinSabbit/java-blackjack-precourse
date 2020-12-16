import controller.GameController;
import java.util.Scanner;

public class Application {

    public void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController(scanner);
        gameController.play();
    }

}
