package exception;

public class BlackJackException extends IllegalArgumentException {

    public BlackJackException(String message) {
        super("[ERROR] " + message);
    }

}
