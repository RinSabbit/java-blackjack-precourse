package domain.user;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer extends User {

    public Dealer() {
    }

    @Override
    public void showCards() {
        System.out.print("딜러 카드: ");
        super.showCards();
    }

    @Override
    public void showCardsWithScore() {
        System.out.print("딜러 카드: ");
        super.showCardsWithScore();
    }
}
