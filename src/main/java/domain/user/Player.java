package domain.user;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {
    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    @Override
    public void showCards() {
        System.out.print(name + "카드 :");
        super.showCards();
    }

    @Override
    public void showCardsWithScore() {
        System.out.print(name + "카드 :");
        super.showCardsWithScore();
    }

    @Override
    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }

}
