package view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static String ASK_PRICE = "구입금액을 입력해주세요";
    private static String ASK_WINNING_NUMBER = "당첨 번호를 입력해주세요.";
    private static String ASK_BONUS_NUMBER = "보너스 번호를 입력해주세요.";

    public String askPrice(){
        System.out.println(ASK_PRICE);
        return Console.readLine();
    }

    public String askWinningNumber(){
        System.out.println("\n" + ASK_WINNING_NUMBER);
        return Console.readLine();
    }

    public String askBonusNumber() {
        System.out.println("\n" + ASK_BONUS_NUMBER);
        return Console.readLine();
    }
}
