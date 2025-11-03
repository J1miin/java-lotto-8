package model;

import domain.Lotto;
import domain.Lottos;
import constants.LottoConstants;
import domain.Price;
import java.util.stream.IntStream;

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
        IntStream.range(0, totalLottoAmount).mapToObj(i -> createLottoNumber()).forEach(lottos::addLotto);
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