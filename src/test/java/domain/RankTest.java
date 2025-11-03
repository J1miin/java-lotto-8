package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {
    @Test
    @DisplayName("1등 당첨자인 경우")
    void firstWinner() {
        Rank rank = Rank.of(6, false);
        assertThat(rank).isEqualTo(Rank.FIRST);
        assertThat(rank.getPrizeMoney()).isEqualTo(2_000_000_000);
    }

    @Test
    @DisplayName("2등 당첨인 경우: 5개 번호 & 보너스 번호")
    void secondWinner() {
        Rank rank = Rank.of(5, true);
        assertThat(rank).isEqualTo(Rank.SECOND);
        assertThat(rank.getPrizeMoney()).isEqualTo(30_000_000);
    }

    @Test
    @DisplayName("3등 당첨자인 경우")
    void thirdWinner() {
        Rank rank = Rank.of(5, false);
        assertThat(rank).isEqualTo(Rank.THIRD);
        assertThat(rank.getPrizeMoney()).isEqualTo(1_500_000);
    }

    @Test
    @DisplayName("4등 당첨자인 경우")
    void fourthWinner() {
        Rank rank = Rank.of(4, false);
        assertThat(rank).isEqualTo(Rank.FOURTH);
        assertThat(rank.getPrizeMoney()).isEqualTo(50_000);
    }

    @Test
    @DisplayName("5등 당첨자인 경우")
    void fifthWinner() {
        Rank rank = Rank.of(3, false);
        assertThat(rank).isEqualTo(Rank.FIFTH);
        assertThat(rank.getPrizeMoney()).isEqualTo(5_000);
    }

    @Test
    @DisplayName("2개 이하 당첨된 경우")
    void notAwinner() {
        Rank rank = Rank.of(2, false);
        assertThat(rank).isEqualTo(Rank.NONE);
        assertThat(rank.getPrizeMoney()).isEqualTo(0);
    }

    @Test
    @DisplayName("결과 출력이 잘 되는지 문구 확인")
    void resultMessageCheck() {
        assertThat(Rank.FIRST.getResultMessage()).contains("6개 일치", "2,000,000,000원");
        assertThat(Rank.FIFTH.getResultMessage()).contains("3개 일치", "5,000원");
    }
}
