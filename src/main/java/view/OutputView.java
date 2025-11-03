package view;

import domain.Lotto;
import domain.LottoResult;
import domain.Lottos;
import domain.Rank;

public class OutputView {
    private static String ANSWER_THE_LOTTO_AMOUNT = "개를 구매했습니다.";
    private static String SHOW_STATISTICS = "당첨 통계";
    private static String DASH = "-";
    private static Integer DASH_REPEAT_THREE = 3;
    private static String WINNING_UNIT = "개";
    private static String SPACE = " ";

    public void showTotalAmount(int lottoAmount){
        System.out.println("\n" + lottoAmount + ANSWER_THE_LOTTO_AMOUNT);
    }

    public void showLottos(Lottos lottos){
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.sorted().getNumbers());
        }
    }

    public void showResult(LottoResult result) {
        System.out.println("\n" + SHOW_STATISTICS);
        System.out.println(DASH.repeat(DASH_REPEAT_THREE));

        for (Rank rank : Rank.values()) {
            if (rank != Rank.NONE) {
                System.out.println(rank.getResultMessage() + SPACE + DASH + SPACE
                        + result.getCount(rank) + WINNING_UNIT);
            }
        }
    }

    public void showProfitRate(LottoResult result, int purchaseAmount) {
        double profitRate = result.calculateProfitRate(purchaseAmount);
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate * 100);
    }

    public static void printErrorMessage(String errorMessage){
        System.out.println(errorMessage);
    }

}
