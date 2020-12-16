package controller;

import domain.user.Player;
import exception.BlackJackException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.ValidateUtils;
import view.InputView;
import view.OutputView;

public class MainController {

    private final List<Player> players;

    public MainController() {
        players = new ArrayList<>();
    }

    public void play() {
        inputCondition();
        blackJack();
    }

    private void inputCondition() {
        String[] players = inputPlayers();
        Arrays.stream(players).forEach(this::bettingMoney);
    }

    private String[] inputPlayers() {
        OutputView.guideInputPlayers();
        String[] players = InputView.inputPlayers();
        try{
            ValidateUtils.isValidNames(players);
        } catch (BlackJackException exception){
            OutputView.showErrorMessage(exception);
            inputCondition();
        }
        return players;
    }

    private void bettingMoney(String player){
        OutputView.guideBettingMoney(player);
        try{
            int money = ValidateUtils.isValidMoney(InputView.inputValue());
            players.add(new Player(player,money));
        } catch (BlackJackException exception){
            OutputView.showErrorMessage(exception);
            bettingMoney(player);
        }
    }

    private void blackJack() {
        BlackJackController blackJackController = new BlackJackController(players);
        blackJackController.startGame();
    }
}
