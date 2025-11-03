package controller;

import domain.*;
import model.ConcreteLottoGenerator;
import model.ConcreteRandomNumberExtractor;
import model.LottoGenerator;
import model.LottoMatcher;
import org.junit.jupiter.api.Test;
import utils.InputHandler;
import utils.InputParser;
import view.InputView;
import view.OutputView;

import java.io.ByteArrayInputStream;

class LottoControllerTest {

    @Test
    void runTest() {
        String input = "5000\n1,2,3,4,5,6\n7\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputHandler inputHandler = new InputHandler(new InputParser());
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoGenerator lottoGenerator = new ConcreteLottoGenerator(new Lottos(),new ConcreteRandomNumberExtractor());
        LottoMatcher lottoMatcher = new LottoMatcher();

        LottoController controller = new LottoController(
                inputHandler, inputView, outputView, lottoGenerator, lottoMatcher
        );

        controller.run();
    }
}