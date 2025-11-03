package domain;

import constants.LottoConstants;
import utils.ErrorMessage;

public class Price {
    private final int price;

    public Price(int value) {
        validate(value);
        this.price = value;
    }

    private void validate(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PRICE_RANGE.getMessage());
        }
        if (value % LottoConstants.LOTTO_PRICE_UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PRICE_INPUT.getMessage());
        }
    }

    public int calculateLottoCount() {
        return price / LottoConstants.LOTTO_PRICE_UNIT;
    }

    public int getPrice() {
        return price;
    }
}
