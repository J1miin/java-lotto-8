package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {
    private final String DELIMITER = ",";

    public int parseIntoInteger(String input){
        return Integer.parseInt(input);
    }

    public List<String> parseByDelimiter(String input) {
        if (!input.matches("^[^,]+(,[^,]+)*$")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DELIMITER.getMessage());
        }

        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
