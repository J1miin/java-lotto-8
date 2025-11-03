package model;

import domain.Lotto;
import domain.LottoResult;
import domain.WinningLotto;
import domain.Rank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoMatcher {
    private Map<Rank, Integer> results = new HashMap<>();

    public LottoResult match(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<Rank, Integer> results = new HashMap<>();

        for (Lotto lotto : lottos) {
            Rank rank = determineRank(lotto, winningLotto);
            results.put(rank, results.getOrDefault(rank, 0) + 1);
        }

        return new LottoResult(results);
    }

    private Rank determineRank(Lotto lotto, WinningLotto winningLotto) {
        int matchCount = countMatch(lotto, winningLotto);
        boolean hasBonus = hasBonus(lotto, winningLotto);
        return Rank.of(matchCount, hasBonus);
    }

    private int countMatch(Lotto lotto, WinningLotto winningLotto) {
        List<Integer> winningNumbers = winningLotto.getLotto().getNumbers();
        List<Integer> lottoNumbers = lotto.getNumbers();

        return (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private boolean hasBonus(Lotto lotto, WinningLotto winningLotto) {
        return lotto.getNumbers().contains(winningLotto.getBonusNumber());
    }
}

