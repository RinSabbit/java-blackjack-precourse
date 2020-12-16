package controller;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import exception.BlackJackException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.ValidateUtils;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    List<Player> players;
    private final Dealer dealer;
    private final ArrayList<Card> cards;

    public BlackJackController(List<Player> players) {
        this.players = players;
        dealer = new Dealer();
        List<Card> initialCards = CardFactory.create();
        cards = new ArrayList<>();
        cards.addAll(initialCards);
    }

    public void startGame() {
        handOutCard();
        deal();
        dealerGetCard();
        showCardResult();
        showFinalProfit();
    }

    private void handOutCard() {
        dealer.addCard(giveCard());
        dealer.addCard(giveCard());
        players.forEach(player -> player.addCard(giveCard()));
        players.forEach(player -> player.addCard(giveCard()));
        blackJackWhileHandOuting();
    }

    private void blackJackWhileHandOuting(){

    }

    private void deal() {
        players.forEach(this::dealWithPlayer);
    }

    private void dealWithPlayer(Player player) {
        OutputView.askGetMoreCard(player);
        try {
            wantCard(player);
        } catch (BlackJackException exception){
            OutputView.showErrorMessage(exception);
            dealWithPlayer(player);
        }
    }

    private void wantCard(Player player) {
        if(ValidateUtils.isValidChoice(InputView.inputValue()) && !player.isOverTwentyOne()){
            player.addCard(giveCard());
            dealWithPlayer(player);
        }
    }

    private void dealerGetCard() {
        if (dealer.calculateScore() < 17) {
            OutputView.addDealerCard();
            dealer.addCard(giveCard());
        }
    }

    private void showCardResult() {
    }

    private void showFinalProfit() {
        OutputView.showResult();
        if(dealer.isOverTwentyOne()){
            double sumProfit = players.stream().mapToDouble(Player::getBettingMoney).sum();
            System.out.println("딜러: -" + sumProfit);
           players.forEach(OutputView::showProfit);
        }

    }


    private Card giveCard() {
        if (!cards.isEmpty()) {
            Collections.shuffle(cards);
            Card card = cards.stream()
                .findFirst()
                .orElseThrow(() -> new BlackJackException("더 뽑을 카드가 없습니다."));
            cards.remove(1);
            return card;
        }
        throw new BlackJackException("더 뽑을 카드가 없습니다.");
    }
}
