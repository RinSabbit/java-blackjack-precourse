package domain.user;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import view.OutputView;

public class User {


    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void showCards() {
        OutputView.showCards(cards);
        System.out.print(System.lineSeparator());
    }

    public void showCardsWithScore() {
        OutputView.showCards(cards);
        System.out.println(" - 결과: " + calculateScore());
    }

    public int calculateScore() {
        return cards.stream().map(Card::getScore).mapToInt(card -> card).sum();
    }

    public boolean isBlackJack() {
        return calculateScore() == 21;
    }

    public boolean isOverTwentyOne() {
        return calculateScore() > 21;
    }

    public boolean isOn(int max) {
        return calculateScore() == max;
    }
}
