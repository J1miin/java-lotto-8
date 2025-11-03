package utils;

import java.util.ArrayList;
import java.util.List;
import view.OutputView;

public class InputHandler {
    private final InputParser inputParser;

    public InputHandler(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public int checkPrice(String input){
        isInputEmpty(input);
        return checkIntegerInput(input);
    }

    public int checkBonusNumber(String input){
        isInputEmpty(input);
        return checkIntegerInput(input);
    }

    public List<Integer> checkWinningNumberInput(String input){
        isInputEmpty(input);
        List<String> winningNumberInput = parseByDelimiter(input);
        return changeIntoInteger(winningNumberInput);
    }

    public int checkIntegerInput(String input){
        return changeIntoInteger(input);
    }

    public void isInputEmpty(String input){
        if (input==null || input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_IS_NULL.getMessage());
        }
    }

    public int changeIntoInteger(String input){
        try {
            return inputParser.parseIntoInteger(input);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }

    public List<String> parseByDelimiter(String input){
        return inputParser.parseByDelimiter(input);
    }

    public List<Integer> changeIntoInteger(List<String> input){
        try {
            List<Integer> winningNumber = new ArrayList<>();
            for (String value : input){
                winningNumber.add(inputParser.parseIntoInteger(value));
            }
            return winningNumber;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }
}
