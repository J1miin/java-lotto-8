package model;

import domain.Lottos;
import domain.Price;

public interface LottoGenerator {
    public void findTotalLottoAmount(Price price);
    public void createLottoSet(Price price);
    public int getTotalLottoAmount();
    public Lottos getLottos();
}
