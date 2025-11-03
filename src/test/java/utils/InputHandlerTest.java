package utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputHandlerTest {
    private final InputHandler inputHandler = new InputHandler(new InputParser());

    @Test
    @DisplayName("정수 입력이 잘 된 경우")
    void correctInteger(){
        int result = inputHandler.validateInteger("8000");
        assertThat(result).isEqualTo(8000);
    }

    @Test
    @DisplayName("빈 입력이 들어온 경우")
    void emptyInput(){
        assertThatThrownBy(()-> inputHandler.validateInteger(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INPUT_IS_NULL.getMessage());
    }

    @Test
    @DisplayName("null 입력이 들어온 경우")
    void nullInput(){
        assertThatThrownBy(()-> inputHandler.validateInteger(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INPUT_IS_NULL.getMessage());
    }

    @Test
    @DisplayName("숫자가 아닌 입력이 들어온 경우")
    void invalidInputWithCharacter(){
        assertThatThrownBy(()-> inputHandler.validateInteger("123a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_INPUT.getMessage());
    }

    @Test
    @DisplayName("공백이 포함된 입력이 들어온 경우")
    void invalidInputWithSpace(){
        assertThatThrownBy(()-> inputHandler.validateInteger("10 01"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_INPUT.getMessage());
    }

    @Test
    @DisplayName("여러 정수를 제대로 파싱하는 경우")
    void parseIntegersWell(){
        List<Integer> numbers = inputHandler.validateIntegers("1,2,3,4,5,6");
        assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("여러 정수 중 하나라도 숫자가 아닌 경우")
    void notAllIntegers() {
        assertThatThrownBy(() -> inputHandler.validateIntegers("1,2,abc,4,5,6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_INPUT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {",,3,4" , "1,,2,3,4", "3,,45,6"})
    @DisplayName("잘못된 구분자가 들어온 경우")
    void invalidDelimiter(String input) {
        assertThatThrownBy(() -> inputHandler.validateIntegers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_DELIMITER.getMessage());
    }

}
