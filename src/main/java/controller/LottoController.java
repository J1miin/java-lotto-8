package controller;

import domain.Lotto;
import domain.LottoResult;
import domain.Lottos;
import domain.WinningLotto;
import java.util.List;
import model.LottoGenerator;
import model.LottoMatcher;
import utils.InputHandler;
import utils.LottoValidator;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputHandler inputHandler;
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;
    private final LottoMatcher lottoMatcher;

    public LottoController(InputHandler inputHandler, InputView inputView,
                           OutputView outputView, LottoGenerator lottoGenerator, LottoMatcher lottoMatcher
                           ) {
        this.inputHandler = inputHandler;
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
        this.lottoMatcher = lottoMatcher;
    }

    public void run() {
        int price = askPrice();
        Lottos lottos = createLottos(price);

        outputView.showTotalAmount(lottoGenerator.getTotalLottoAmount());
        outputView.showLottos(lottos);

        WinningLotto winningLotto = createWinningLotto();

        LottoResult result = lottoMatcher.match(lottos.getLottos(), winningLotto);
        outputView.showResult(result);
        outputView.showProfitRate(result,price);
    }

    public int askPrice(){
        String input = inputView.askPrice();
        int price = inputHandler.checkPrice(input);
        LottoValidator.validatePrice(price);
        return price;
    }

    public Lottos createLottos(int price){
        lottoGenerator.createLottoSet(price);
        return lottoGenerator.getLottos();
    }

    public WinningLotto createWinningLotto(){
        List<Integer> winningNumbers = askWinningNumber();
        int bonusNumber = askBonusNumber(winningNumbers);

        Lotto winningLotto = new Lotto(winningNumbers);
        return new WinningLotto(winningLotto, bonusNumber);
    }

    private List<Integer> askWinningNumber() {
        String input = inputView.askWinningNumber();
        return inputHandler.checkWinningNumberInput(input);
    }

    private int askBonusNumber(List<Integer> winningNumbers) {
        String input = inputView.askBonusNumber();
        return inputHandler.checkBonusNumber(input);
    }
}
