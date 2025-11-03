package utils;

import java.util.List;

public class InputHandler {
    private final InputParser inputParser;

    public InputHandler(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public int validateInteger(String input){
        validateEmptyOrNot(input);
        return parseIntoIntegers(input);
    }

    public List<Integer> validateIntegers(String input){
        validateEmptyOrNot(input);
        List<String> winningNumberInput = parseByDelimiter(input);
        return parseIntoIntegers(winningNumberInput);
    }

    public void validateEmptyOrNot(String input){
        if (input==null || input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_IS_NULL.getMessage());
        }
    }

    public int parseIntoIntegers(String input){
        try {
            return inputParser.parseIntoInteger(input);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }

    public List<String> parseByDelimiter(String input){
        return inputParser.parseByDelimiter(input);
    }

    public List<Integer> parseIntoIntegers(List<String> input){
        try {
            return input.stream()
                    .map(inputParser::parseIntoInteger)
                    .toList();
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }
}