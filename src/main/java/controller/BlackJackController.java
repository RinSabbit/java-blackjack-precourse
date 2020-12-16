package controller;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import exception.BlackJackException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.ValidateUtils;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final List<Player> players;
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
        if (!blackJackWhileHandOuting()) {
            deal();
            supplyDealerCard();
        }
        showCardsWithScore();
        showFinalProfit();
    }

    private void handOutCard() {
        dealer.addCard(giveCard());
        dealer.addCard(giveCard());
        players.forEach(player -> player.addCard(giveCard()));
        players.forEach(player -> player.addCard(giveCard()));
        showCards();
    }

    private boolean blackJackWhileHandOuting() {
        return players.stream().anyMatch(Player::isBlackJack);
    }

    private void deal() {
        players.forEach(this::dealWithPlayer);
    }

    private void dealWithPlayer(Player player) {
        try {
            checkCardScore(player);
        } catch (BlackJackException exception) {
            OutputView.showErrorMessage(exception);
            dealWithPlayer(player);
        }
    }

    private void checkCardScore(Player player) {
        if (!player.isOverTwentyOne()) {
            OutputView.askGetMoreCard(player);
            wantCard(player);
        }
    }

    private void wantCard(Player player) {
        if(ValidateUtils.isValidChoice(InputView.inputValue())){
            player.addCard(giveCard());
            player.showCards();
            dealWithPlayer(player);
        }
    }

    private void supplyDealerCard() {
        if (dealer.calculateScore() < 17) {
            OutputView.addDealerCard();
            dealer.addCard(giveCard());
        }
    }

    private void showCards() {
        dealer.showCards();
        players.forEach(Player::showCards);
    }

    private void showCardsWithScore() {
        dealer.showCardsWithScore();
        players.forEach(Player::showCardsWithScore);
    }

    private void showFinalProfit() {
        double dealerProfit = 0;
        int max = findMaxScore();

        OutputView.showResult();
        if (dealer.isOverTwentyOne()) {
            dealerProfit = players.stream().mapToDouble(Player::getBettingMoney).sum();
            System.out.println("딜러: -" + dealerProfit);
            players.forEach(OutputView::showProfit);
            return;
        }
        if (blackJackWhileHandOuting() && dealer.isBlackJack()) {
            System.out.println("딜러: " + dealerProfit);
            players.forEach(OutputView::showProfit);
            return;
        }
        if (blackJackWhileHandOuting() && !dealer.isBlackJack()) { // 첫 드롭에서 플레이어 블랙잭

            return;
        }
        if (dealer.isOn(max)) { // 딜러가 이김

        }
    }

    private int findMaxScore() {
        List<Integer> scores = new ArrayList<>();
        scores.add(dealer.calculateScore());
        players.forEach(player -> scores.add(player.calculateScore()));
        return Collections.max(scores);
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
