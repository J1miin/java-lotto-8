package utils;

import dto.LottoConstants;

public class LottoValidator {
    public static void validatePrice(int price){
        if (price < 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PRICE_RANGE.getMessage());
        }
        if (price % LottoConstants.LOTTO_PRICE_UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PRICE_INPUT.getMessage());
        }
    }
}