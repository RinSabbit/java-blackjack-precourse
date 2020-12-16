package controller;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.User;
import exception.BlackJackException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.ProfitCalculator;
import utils.ValidateUtils;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final List<User> players;
    private final Dealer dealer;
    private final ArrayList<Card> cards;

    public BlackJackController(List<User> players) {
        this.players = players;
        dealer = new Dealer();
        List<Card> initialCards = CardFactory.create();
        cards = new ArrayList<>(initialCards);
        Collections.shuffle(cards);
    }

    public void startGame() {
        handOutCard();
        deal();
        supplyDealerCard();
        showCardsWithScore();
        ProfitCalculator profitCalculator = new ProfitCalculator(dealer, players);
        profitCalculator.showProfit();
    }

    private void handOutCard() {
        dealer.addCard(giveCard());
        dealer.addCard(giveCard());
        players.forEach(player -> player.addCard(giveCard()));
        players.forEach(player -> player.addCard(giveCard()));
        showCards();
    }

    private void deal() {
        players.forEach(this::dealWithPlayer);
    }

    private void dealWithPlayer(User player) {
        try {
            checkCardScore(player);
        } catch (BlackJackException exception) {
            OutputView.showErrorMessage(exception);
            dealWithPlayer(player);
        }
    }

    private void checkCardScore(User player) {
        if (!player.isOverTwentyOne() && !player.isBlackJack()) {
            OutputView.askGetMoreCard(player);
            wantCard(player);
        }
    }

    private void wantCard(User player) {
        if (ValidateUtils.isValidChoice(InputView.inputValue())) {
            player.addCard(giveCard());
            player.showCards();
            dealWithPlayer(player);
        }
    }

    private void supplyDealerCard() {
        if (dealer.getScore() < 17) {
            OutputView.addDealerCard();
            dealer.addCard(giveCard());
        }
    }

    private void showCards() {
        dealer.showCards();
        players.forEach(User::showCards);
    }

    private void showCardsWithScore() {
        dealer.showCardsWithScore();
        players.forEach(User::showCardsWithScore);
    }

    private Card giveCard() {
        if (!cards.isEmpty()) {
            Card card = cards.stream()
                .findFirst()
                .orElseThrow(() -> new BlackJackException("더 뽑을 카드가 없습니다."));
            cards.remove(0);
            return card;
        }
        throw new BlackJackException("더 뽑을 카드가 없습니다.");
    }
}
