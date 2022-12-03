public interface CalculatorLogicAdapter {
    // performAction takes in an action name("sub","mult","1", etc.) and returns the number display.
    // Acts as a bridge between the Calculator class(UI) and the CalculatorLogic class(purely logic).
    public String performAction(String actionName);
}
