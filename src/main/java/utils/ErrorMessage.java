package utils;

import dto.LottoConstants;

public enum ErrorMessage {
    INVALID_LOTTO_NUMBER_SIZE("로또 번호는 6개여야 합니다."),
    DUPLICATED_LOTTO_NUMBER("로또 번호는 중복될 수 없습니다."),
    INVALID_LOTTO_RANGE("로또 번호는 "+ LottoConstants.MIN_NUMBER + "부터 "+ LottoConstants.MAX_NUMBER +"사이여야 합니다."),
    INVALID_PRICE_RANGE("가격은 0원 이상이어야 합니다."),
    INVALID_PRICE_INPUT("가격은 천원 단위로 입력되어야 합니다."),
    DUPLICATED_BONUS_NUMBER("보너스 번호는 당첨 번호와 중복될 수 없습니다."),
    INVALID_INPUT("숫자가 아닌 값이 입력됐습니다."),
    NEGATIVE_NUMBER_INPUT("0보다 큰 값이 입력되어야 합니다."),
    INVALID_DELIMITER("구분자에 문제가 있습니다."),
    INPUT_IS_NULL("값이 입력되지 않았습니다.");

    private static final String ERROR_PREFIX = "[ERROR]";
    private static final String SPACE = " ";
    private final String message;

    ErrorMessage(String message) {
        this.message = ERROR_PREFIX + SPACE + message;
    }

    public String getMessage() {
        return message;
    }
}
