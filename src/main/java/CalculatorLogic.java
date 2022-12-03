import java.text.DecimalFormat;
import java.util.*;

public class CalculatorLogic {

    public ArrayList<CalculatorAction> actions;
    private final HashMap<String, Integer> getPrecedence;
    public StringBuilder expressionBuilder;
    public DecimalFormat decimalFormat;
    public CalculatorLogic() {
        this.actions = new ArrayList<>();
        this.expressionBuilder = new StringBuilder();
        this.getPrecedence = new HashMap<>();
        // PEMDAS
        this.getPrecedence.put("+", 0);
        this.getPrecedence.put("-", 0);
        this.getPrecedence.put("*", 1);
        this.getPrecedence.put("/", 1);
        this.getPrecedence.put("%", 1);
        this.decimalFormat = new DecimalFormat("0.#");
    }

    public void performAction(CalculatorAction action) {
        if(action == CalculatorAction.CLEAR) {
            this.actions.clear();
        }
        // Use the shunting-yard algorithm to convert in-fix notation to RPN(Reverse Polish Notation). [ STEP 2 ]
        // Evaluate the RPN result. [ STEP 1 ]
        // [FIVE, SIX, MULT, SEVEN, ADD]
        // [5, 6, *, 7, +] (5 * 6 + 7)
        // [30, 7, +]
        // [37]

        // Special actions
        switch(action) {
            case SIGN:
                // Invert sign if expression consists of just one number
                // Can't be bothered to implement!
                return; // placeholder
            case CLEAR:
                expressionBuilder.setLength(0);
                return;
            case EQ:
                // Make things a little easier
                double result = this.evaluate();
                this.actions.clear();
                this.expressionBuilder.setLength(0);
                this.expressionBuilder.append(this.decimalFormat.format(result));
                return;
            default:
                break;
        }
        boolean canPerform = true;
        // Basic syntax validator
        if(this.actions.size() > 0) {
            CalculatorAction lastAction = this.actions.get(this.actions.size()-1);
            if(lastAction.isOperation() && action.isOperation()) canPerform = false;
            if(lastAction.isOperation() && action.isDot()) canPerform = false;
            if(lastAction.isDot() && action.isOperation()) canPerform = false;
        }
        else if(action.isOperation() && this.expressionBuilder.length() == 0) canPerform = false;

        if(canPerform) {
            expressionBuilder.append(action.actionToString());
            this.actions.add(action);
        }
        else {
            System.out.println("Invalid syntax");
        }

        //System.out.println(expressionBuilder.toString());
    }
    public Integer getPrecedence(String operation) {
        return this.getPrecedence.get(operation);
    }
    public ArrayList<String> infixToPostfix(ArrayList<String> tokens) {
        // Convert in-fix notation to Reverse Polish notation.
        ArrayList<String> outputQueue = new ArrayList<>();
        Stack<String> operatorStack = new Stack<>();
        // While there are tokens to be read, read a token.
        for(String token: tokens) {
            boolean isOperation = token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("%");
            if(isOperation) {
                // While there's an operator on the top of the stack with greater precedence, pop operators from stack onto the output queue.
                while(operatorStack.size() > 0 && (this.getPrecedence(operatorStack.peek()) > this.getPrecedence(token))) {
                    outputQueue.add(operatorStack.pop());
                    System.out.println("pop");
                }
                // Push current operator onto stack.
                operatorStack.push(token);
            }
            else {
                // Add to queue.
                outputQueue.add(token);
            }
        }
        // While there are operators on the stack, pop them to the queue
        while(operatorStack.size() > 0) {
            outputQueue.add(operatorStack.pop());
        }
        return outputQueue;
    }
    public double evaluate() {
        // Turn the string into an array of tokens to bunch up multiple digits together into one number.
        ArrayList<String> tokens = new ArrayList<>();
        char[] chars = this.expressionBuilder.toString().toCharArray();
        for(int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];
            if(i > 0) {
                char prevChar = chars[i-1];
                if( (Character.isDigit(prevChar) && Character.isDigit(currentChar)) || (prevChar == '.' && Character.isDigit(currentChar)) || (Character.isDigit(prevChar) && currentChar == '.')) {
                    String lastElement = tokens.get(tokens.size()-1);
                    lastElement += currentChar;
                    tokens.set(tokens.size()-1, lastElement);
                }
                else tokens.add(String.valueOf(currentChar));
            }
            else tokens.add(String.valueOf(currentChar));
        }
        tokens = infixToPostfix(tokens); // Use the shunting yard algorithm to turn infix notation to postfix(RPN).
        // Evaluate expression.
        Stack<String> numberStack = new Stack<>();
        for(String token: tokens) {
            boolean isOperation = token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("%");
            if(isOperation) {
                double secondTerm = Double.parseDouble(numberStack.pop());
                double firstTerm = Double.parseDouble(numberStack.pop());
                switch(token) {
                    case "+":
                        numberStack.push(String.valueOf(firstTerm + secondTerm));
                        break;
                    case "-":
                        numberStack.push(String.valueOf(firstTerm - secondTerm));
                        break;
                    case "*":
                        numberStack.push(String.valueOf(firstTerm * secondTerm));
                        break;
                    case "/":
                        numberStack.push(String.valueOf(firstTerm / secondTerm));
                        break;
                    case "%":
                        numberStack.push(String.valueOf(firstTerm % secondTerm));
                        break;
                }
            }
            else {
                numberStack.push(token);
            }
        }
        System.out.println(Double.parseDouble(numberStack.peek()));
        return Double.parseDouble(numberStack.peek());
    }

    public String getDisplay() {
        return expressionBuilder.toString();
    }
}
