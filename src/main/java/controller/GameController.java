package controller;

import domain.user.Player;
import domain.user.User;
import exception.BlackJackException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import utils.ValidateUtils;
import view.InputView;
import view.OutputView;

public class GameController {

    private final InputView inputView;
    private final List<User> users;

    public GameController(Scanner scanner) {
        inputView = new InputView(scanner);
        users = new ArrayList<>();
    }

    public void play() {
        inputCondition();
        blackJack();
        gameOver();
    }

    private void inputCondition() {
        String[] players = inputPlayers();
        Arrays.stream(players).forEach(this::bettingMoney);
    }

    private String[] inputPlayers() {
        OutputView.guideInputPlayers();
        String[] players = inputView.inputPlayers();
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
            int money = ValidateUtils.isValidMoney(inputView.inputValue());
            users.add(new Player(player,money));
        } catch (BlackJackException exception){
            OutputView.showErrorMessage(exception);
            bettingMoney(player);
        }
    }

    private void blackJack() {
        OutputView.addDealerCard();
        //OutputView.askGetMoreCard();
        OutputView.showWhichCardUserHas();
    }

    private void gameOver() {
        OutputView.showResult();

    }
}
