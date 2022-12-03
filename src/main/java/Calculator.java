import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Calculator extends JFrame {
    private JPanel mainPanel;
    private JLabel numberDisplay;
    private JPanel buttonPanel;
    private final DefaultCalculatorLogicAdapter logicAdapter;
    private final HashMap<String, JButton> buttons;

    public Calculator(String title, int width, int height) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);
        this.setSize(new Dimension(width, height));
        this.logicAdapter = new DefaultCalculatorLogicAdapter(new CalculatorLogic());
        this.buttons = new HashMap<>();
        this.initLayout();
        this.initButtons();
    }

    public void handleButtonClick(String actionName) {
        // Handle button click.
        String display = this.logicAdapter.performAction(actionName);
        numberDisplay.setText(display);
    }

    public void setDisplayText(String text) {
        numberDisplay.setText(text);
    }

    private void initLayout() {
        GridLayout gridLayout = new GridLayout(5, 4);
        buttonPanel.setLayout(gridLayout);
        buttonPanel.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }

    private void initButtons() {
        addButton("clear", "AC");
        addButton("sign", "+/-");
        addButton("mod", "%");
        addButton("div", "/");

        addButton("7", "7");
        addButton("8", "8");
        addButton("9", "9");
        addButton("mult", "*");

        addButton("4", "4");
        addButton("5", "5");
        addButton("6", "6");
        addButton("sub", "-");

        addButton("1", "1");
        addButton("2", "2");
        addButton("3", "3");
        addButton("add", "+");

        addButton("0", "0");
        addButton(".", ".");
        addButton("eq", "=");
    }

    private void addButton(String name, String text) {
        JButton button = new JButton(text);
        button.addActionListener(new CalculatorButtonListener(this, name));
        buttons.put(name, button);
        buttonPanel.add(button);
    }
}
