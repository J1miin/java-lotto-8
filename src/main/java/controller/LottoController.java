package controller;

import domain.Lotto;
import domain.LottoResult;
import domain.Lottos;
import domain.Price;
import domain.WinningLotto;
import java.util.List;
import model.LottoGenerator;
import model.LottoMatcher;
import utils.InputHandler;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputHandler inputHandler;
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;
    private final LottoMatcher lottoMatcher;

    public LottoController(InputHandler inputHandler, InputView inputView,
                           OutputView outputView, LottoGenerator lottoGenerator,
                           LottoMatcher lottoMatcher) {
        this.inputHandler = inputHandler;
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
        this.lottoMatcher = lottoMatcher;
    }

    public void run() {
        Price price = askPrice();
        Lottos lottos = createLottos(price);
        showPurchasedLottos(lottos);
        WinningLotto winningLotto = createWinningLotto();
        showResult(price, lottos, winningLotto);
    }

    private Price askPrice(){
        String input = inputView.askPrice();
        int priceValue = inputHandler.validateInteger(input);
        return new Price(priceValue);
    }

    private Lottos createLottos(Price price){
        lottoGenerator.createLottoSet(price);
        return lottoGenerator.getLottos();
    }

    private void showPurchasedLottos(Lottos lottos) {
        outputView.showTotalAmount(lottoGenerator.getTotalLottoAmount());
        outputView.showLottos(lottos);
    }

    private WinningLotto createWinningLotto(){
        List<Integer> winningNumbers = askWinningNumber();
        Lotto winningLotto = new Lotto(winningNumbers);

        int bonusNumber = askBonusNumber();
        return new WinningLotto(winningLotto, bonusNumber);
    }

    private List<Integer> askWinningNumber() {
        String input = inputView.askWinningNumber();
        return inputHandler.validateIntegers(input);
    }

    private int askBonusNumber() {
        String input = inputView.askBonusNumber();
        return inputHandler.validateInteger(input);
    }

    private void showResult(Price price, Lottos lottos, WinningLotto winningLotto) {
        LottoResult result = lottoMatcher.match(lottos.getLottos(), winningLotto);
        outputView.showResult(result);
        outputView.showProfitRate(result,price.getPrice());
    }
}
