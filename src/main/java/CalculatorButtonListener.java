import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorButtonListener implements ActionListener {
    private final Calculator calculator;
    private final String actionName;
    public CalculatorButtonListener(Calculator calculator, String actionName) {
        this.calculator = calculator;
        this.actionName = actionName;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        calculator.handleButtonClick(this.actionName);
    }
}
