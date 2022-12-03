public enum CalculatorAction {
    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, ZERO, DOT,
    ADD, SUB, MULT, DIV, MOD,
    CLEAR, SIGN, EQ;

    String actionToString() {
        switch(this) {
            case EQ:
                return "=";
            case ADD:
                return "+";
            case SUB:
                return "-";
            case MULT:
                return "*";
            case DIV:
                return "/";
            case MOD:
                return "%";
            case CLEAR:
                return "clear";
            case SIGN:
                return "sign";
            case ONE:
                return "1";
            case TWO:
                return "2";
            case THREE:
                return "3";
            case FOUR:
                return "4";
            case FIVE:
                return "5";
            case SIX:
                return "6";
            case SEVEN:
                return "7";
            case EIGHT:
                return "8";
            case NINE:
                return "9";
            case ZERO:
                return "0";
            case DOT:
                return ".";
        }
        return null;
    }
    boolean isOperation() {
        return (this == ADD) || (this == SUB) || (this == MULT) || (this == DIV) || (this == MOD);
    }
    boolean isNumerical() {
        return !this.isOperation() && (this != EQ) && (this != CLEAR) && (this != SIGN); // TODO: do this
    }
    boolean isDot() {
        return this == DOT;
    }
}
