package model;

import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMatcherTest {
    private final LottoMatcher lottoMatcher = new LottoMatcher();

    @Test
    @DisplayName("6개가 다 맞는 경우")
    void sixnumberHasMatched() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        LottoResult result = lottoMatcher.match(List.of(lotto), winningLotto);
        assertThat(result.getCount(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    @DisplayName("5개 + 보너스가 맞는 경우")
    void fiveNumberAndBonusNumnerHasMatched() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        LottoResult result = lottoMatcher.match(List.of(lotto), winningLotto);

        assertThat(result.getCount(Rank.SECOND)).isEqualTo(1);
    }

    @Test
    @DisplayName("여러 개의 로또를 샀고, 여러 로또의 당첨 여부를 확인하는 상황")
    void checkLottosMatchResult() {
        List<Lotto> lottos = List.of(
                //3개 맞는 경우(1)
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),
                //3개 맞는 경우(2)
                new Lotto(List.of(4, 5, 6, 13, 14, 15)),
                //6개 다 맞는 경우
                new Lotto(List.of(1, 2, 3, 4, 5, 6))
        );
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        LottoResult result = lottoMatcher.match(lottos, winningLotto);

        assertThat(result.getCount(Rank.FIFTH)).isEqualTo(2);
        assertThat(result.getCount(Rank.FIRST)).isEqualTo(1);
    }
}