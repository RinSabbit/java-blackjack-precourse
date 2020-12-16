package domain.user;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import view.OutputView;

public class User {
    public static final int BLACKJACK_SCORE = 21;
    public static final int GAP_BETWEEN_ACE_VALUE = 10;
    public static final String RESULT_MESSAGE = " - 결과: ";
    public static final String DEALER = "딜러";

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
        System.out.println(RESULT_MESSAGE + getScore());
    }

    public int getScore() {
        int total = cards.stream()
            .map(Card::getScore)
            .mapToInt(card -> card)
            .sum();
        return addIfAceExist(total);
    }

    private int addIfAceExist(int total) {
        int aceCount = countAce();
        while (total + GAP_BETWEEN_ACE_VALUE <= BLACKJACK_SCORE && aceCount == 0) {
            total += GAP_BETWEEN_ACE_VALUE;
            aceCount--;
        }
        return total;
    }

    private int countAce() {
        return Math.toIntExact(cards.stream().filter(Card::isAce).count());
    }

    public boolean isBlackJack() {
        return getScore() == BLACKJACK_SCORE;
    }

    public boolean isOverTwentyOne() {
        return getScore() > BLACKJACK_SCORE;
    }

    public String getName() {
        return DEALER;
    }

    public int getCardAmount() {
        return cards.size();
    }

}
