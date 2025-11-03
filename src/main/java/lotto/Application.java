package lotto;

import controller.LottoController;
import domain.Lottos;
import model.ConcreteLottoGenerator;
import model.ConcreteRandomNumberExtractor;
import model.LottoMatcher;
import utils.InputHandler;
import utils.InputParser;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {

        try {
            LottoController lottoController = new LottoController(
                    new InputHandler(new InputParser()),
                    new InputView(),
                    new OutputView(),
                    new ConcreteLottoGenerator(new Lottos(), new ConcreteRandomNumberExtractor()),
                    new LottoMatcher());

            lottoController.run();

        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }
}
