package lotto;

import domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import utils.ErrorMessage;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("6개보다 적은 숫자일 때")
    void lottoHasLessThanSixNumbers() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_LOTTO_NUMBER_SIZE.getMessage());
    }

    @Test
    @DisplayName("로또 숫자가 1-45 범위를 벗어난 경우")
    void lottoNumberIsOutOfRange() {
        List<Integer> invalidNumbers = List.of(1, 2, 3, 30, 400, -5);
        assertThatThrownBy(() -> new Lotto(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_LOTTO_RANGE.getMessage());
    }

    @Test
    @DisplayName("로또 오름차순 정렬")
    void sortLottoNumbers() {
        Lotto lotto = new Lotto(List.of(6, 3, 1, 5, 2, 4));
        Lotto sorted = lotto.sorted();

        assertThat(sorted.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }
}
