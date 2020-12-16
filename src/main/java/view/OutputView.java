package view;

import domain.card.Card;
import domain.user.Player;
import domain.user.User;
import exception.BlackJackException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String CARDS_DELIMITER = ", ";
    private static final String RESULT_DELIMITER = ": ";
    private static final String INPUT_PLAYERS_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_PLAYERS_BETTING_MONEY_MESSAGE = "의 배팅 금액은?";
    private static final String GET_MORE_CARD_MESSAGE = "는(은) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String DEALER_ADD_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String GAME_RESULT_MONEY_MESSAGE = "## 최종 수익";

    public static void showErrorMessage(BlackJackException exception) {
        System.out.println(exception.getMessage());
    }

    public static void showResult() {
        System.out.println(GAME_RESULT_MONEY_MESSAGE);
    }

    public static void addDealerCard() {
        System.out.println(DEALER_ADD_CARD_MESSAGE);
    }

    public static void askGetMoreCard(User player) {
        System.out.println(((Player) player).getName() + GET_MORE_CARD_MESSAGE);
    }

    public static void guideInputPlayers() {
        System.out.println(INPUT_PLAYERS_NAME_MESSAGE);
    }

    public static void guideBettingMoney(String player) {
        System.out.println(player + INPUT_PLAYERS_BETTING_MONEY_MESSAGE);
    }

    public static void showCards(List<Card> cards) {
        System.out.print(cards.stream().map(Card::getCard).collect(Collectors.joining(
            CARDS_DELIMITER)));
    }

    public static void showProfits(Map<User, Double> profits) {
        profits.forEach(
            (user, profit) -> System.out.println(user.getName() + RESULT_DELIMITER + Math.round(profit)));
    }
}
