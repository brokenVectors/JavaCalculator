public class DefaultCalculatorLogicAdapter implements CalculatorLogicAdapter {
    public CalculatorLogic calculatorLogic;
    public DefaultCalculatorLogicAdapter(CalculatorLogic calculatorLogic) {
        this.calculatorLogic = calculatorLogic;
    }
    @Override
    public String performAction(String actionName) {
        CalculatorAction action = null;
        switch(actionName) {
            case "clear":
                action = CalculatorAction.CLEAR;
                break;
            case "sign":
                action = CalculatorAction.SIGN;
                break;
            case "mod":
                action = CalculatorAction.MOD;
                break;
            case "add":
                action = CalculatorAction.ADD;
                break;
            case "sub":
                action = CalculatorAction.SUB;
                break;
            case "mult":
                action = CalculatorAction.MULT;
                break;
            case "div":
                action = CalculatorAction.DIV;
                break;
            case ".":
                action = CalculatorAction.DOT;
                break;
            case "eq":
                action = CalculatorAction.EQ;
                break;
            case "1":
                action = CalculatorAction.ONE;
                break;
            case "2":
                action = CalculatorAction.TWO;
                break;
            case "3":
                action = CalculatorAction.THREE;
                break;
            case "4":
                action = CalculatorAction.FOUR;
                break;
            case "5":
                action = CalculatorAction.FIVE;
                break;
            case "6":
                action = CalculatorAction.SIX;
                break;
            case "7":
                action = CalculatorAction.SEVEN;
                break;
            case "8":
                action = CalculatorAction.EIGHT;
                break;
            case "9":
                action = CalculatorAction.NINE;
                break;
            case "0":
                action = CalculatorAction.ZERO;
                break;
        }
        this.calculatorLogic.performAction(action);
        return this.calculatorLogic.getDisplay();
    }
}
