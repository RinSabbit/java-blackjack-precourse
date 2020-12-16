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

    public void showCards(){
        OutputView.showCards(cards);
    }


    public int calculateScore(){
        return cards.stream().map(Card::getScore).mapToInt(card -> card).sum();
    }

}
