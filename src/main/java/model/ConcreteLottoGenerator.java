package model;

import domain.Lotto;
import domain.Lottos;
import constants.LottoConstants;
import domain.Price;

public class ConcreteLottoGenerator implements LottoGenerator {
    private int totalLottoAmount;
    private final Lottos lottos;
    private final RandomNumberExtractor randomNumberExtractor;

    public ConcreteLottoGenerator(Lottos lottos, RandomNumberExtractor randomNumberExtractor) {
        this.lottos = lottos;
        this.randomNumberExtractor = randomNumberExtractor;
    }

    @Override
    public void createLottoSet(Price price){
        findTotalLottoAmount(price);

        for (int i = 0 ; i < totalLottoAmount; ++i){
            Lotto lotto = createLottoNumber();
            lottos.addLotto(lotto);
        }
    }

    @Override
    public void findTotalLottoAmount(Price price) {
        this.totalLottoAmount = price.getPrice() / LottoConstants.LOTTO_PRICE_UNIT ;
    }

    @Override
    public int getTotalLottoAmount(){
        return this.totalLottoAmount;
    }

    @Override
    public Lottos getLottos(){
        return this.lottos;
    }

    public Lotto createLottoNumber(){
        return new Lotto(randomNumberExtractor.getRandomNumbers());
    }
}