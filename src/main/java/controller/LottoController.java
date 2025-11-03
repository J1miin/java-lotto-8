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
        int priceValue = inputHandler.checkPrice(input);
        Price price = new Price(priceValue);
        return price.getPrice();
    }

    public Lottos createLottos(int price){
        lottoGenerator.createLottoSet(price);
        return lottoGenerator.getLottos();
    }

    public WinningLotto createWinningLotto(){
        List<Integer> winningNumbers = askWinningNumber();
        Lotto winningLotto = new Lotto(winningNumbers);

        int bonusNumber = askBonusNumber();
        return new WinningLotto(winningLotto, bonusNumber);
    }

    private List<Integer> askWinningNumber() {
        String input = inputView.askWinningNumber();
        return inputHandler.checkWinningNumberInput(input);
    }

    private int askBonusNumber() {
        String input = inputView.askBonusNumber();
        return inputHandler.checkBonusNumber(input);
    }
}
