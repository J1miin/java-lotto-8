package model;

import camp.nextstep.edu.missionutils.Randoms;
import constants.LottoConstants;
import java.util.List;

public class ConcreteRandomNumberExtractor implements RandomNumberExtractor {
    @Override
    public List<Integer> getRandomNumbers() {
        return Randoms.pickUniqueNumbersInRange(LottoConstants.MIN_NUMBER,LottoConstants.MAX_NUMBER,LottoConstants.LOTTO_SIZE);
    }
}
