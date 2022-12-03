import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator("Calculator", 300,400);
        // 5 * (5 + 5) / 5
        // 5 5 5 + * 5 /
        // [50, 35, Operation.ADD, 25, Operation.MULT] (50 35 + 25 *)
    }
}
