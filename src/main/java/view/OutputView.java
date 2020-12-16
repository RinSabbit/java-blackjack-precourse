package view;

import domain.card.Card;
import domain.user.Player;
import domain.user.User;
import exception.BlackJackException;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String INPUT_PLAYERS_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_PLAYERS_BETTING_MONEY_MESSAGE = "의 배팅 금액은?";
    private static final String GET_MORE_CARD_MESSAGE = "는(은) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String DEALER_ADD_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String GAME_RESULT_MONEY_MESSAGE = "## 최종 수익";
    private static final String CARD_USER_HAVE_MESSAGE = "카드: ";

    public static void showErrorMessage(BlackJackException exception){
        System.out.println(exception.getMessage());
    }

    public static void showWhichCardUserHas() {
        System.out.println(CARD_USER_HAVE_MESSAGE);
    }

    public static void showResult(){
        System.out.println(GAME_RESULT_MONEY_MESSAGE);
    }

    public static void addDealerCard(){
        System.out.println(DEALER_ADD_CARD_MESSAGE);
    }

    public static void askGetMoreCard(Player player){
        System.out.println(player.getName() + GET_MORE_CARD_MESSAGE);
    }

    public static void guideInputPlayers(){
        System.out.println(INPUT_PLAYERS_NAME_MESSAGE);
    }

    public static void guideBettingMoney(String player){
        System.out.println(player + INPUT_PLAYERS_BETTING_MONEY_MESSAGE);
    }

    public static void showCards(List<Card> cards){
        StringBuilder stringBuilder = new StringBuilder();

    }


}
