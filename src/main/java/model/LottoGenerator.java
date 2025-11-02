package model;

import domain.Lottos;

public interface LottoGenerator {
    public void findTotalLottoAmount(int price);
    public void createLottoSet(int price);
    public int getTotalLottoAmount();
    public Lottos getLottos();
}
