package utils;

import domain.Status;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import view.OutputView;

public class ProfitCalculator {

    private static final double LOSE_RADIO = -1;
    private static final double WINNING_RATIO = 1;
    private static final double DRAW_RATIO = 0;
    private static final double HANDOUT_BLACKJACK_RATIO = 0.5;
    private final Dealer dealer;
    private final List<User> users;
    private final Map<User, Double> profit;
    private final Map<User, Status> userStatus;
    private double dealerProfit;

    public ProfitCalculator(Dealer dealer, List<User> users) {
        this.dealer = dealer;
        this.users = users;
        dealerProfit = 0;
        profit = new HashMap<>();
        userStatus = new HashMap<>();
    }


    public void checkStatus() {
        if (dealer.isOverTwentyOne()) {
            users.forEach(user -> userStatus.put(user, Status.WIN));
            return;
        }
        users.forEach(this::checkEachStatus);
    }

    private void checkEachStatus(User user) {
        if (dealer.getScore() > user.getScore() || user.isOverTwentyOne()) {
            userStatus.put(user, Status.LOSE);
            return;
        }
        if (dealer.getScore() == user.getScore()) {
            userStatus.put(user, Status.DRAW);
            return;
        }
        userStatus.put(user, Status.WIN);
    }

    public void calculateProfit(User user) {
        if (user.isBlackJack() && user.getCardAmount() == 2) {
            earnMoney(user, HANDOUT_BLACKJACK_RATIO);
            return;
        }
        if (userStatus.get(user) == Status.WIN) {
            earnMoney(user, WINNING_RATIO);
            return;
        }
        if (userStatus.get(user) == Status.DRAW) {
            earnMoney(user, DRAW_RATIO);
            return;
        }
        if (userStatus.get(user) == Status.LOSE) {
            earnMoney(user, LOSE_RADIO);
        }
    }

    public void earnMoney(User user, double moneyRatio) {
        Player player = (Player) user;
        profit.put(player, player.getBettingMoney() * moneyRatio);
        dealerProfit -= player.getBettingMoney() * moneyRatio;
    }

    public void showProfit() {
        checkStatus();
        users.forEach(this::calculateProfit);
        OutputView.showResult();
        System.out.println(dealer.getName() + ": " + Math.round(dealerProfit));
        OutputView.showProfits(profit);
    }
}
