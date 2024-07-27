package baseball;

import java.util.Scanner;

public class Calculator {

    public int add(int i1, int i2) {
        return i1 + i2;
    }

    public int subtract(int i1, int i2) {
        return i1 - i2;
    }

    public int multiple(int i1, int i2) {
        return i1 * i2;
    }

    public int divide(int i1, int i2) {
        return i1 / i2;
    }

    public String[] getSplitValues() {
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        return value.split(" ");
    }

    public int doCalculate(int i1, int i2, String symbol){
        if(symbol.equals("+")){
            return add(i1, i2);
        }
        if(symbol.equals("-")){
            return subtract(i1, i2);
        }
        if(symbol.equals("*")){
            return multiple(i1, i2);
        }
        if(symbol.equals("/")){
            return divide(i1, i2);
        }
        return 0;
    }

    public int doCalculator() {
        String values[] = getSplitValues();
        int answer = Integer.parseInt(values[0]);
        int number = 0;
        String symbol = "";
        for (int i = 1; i < values.length; i++) {
            if (i % 2 == 0) {
                number = Integer.parseInt(values[i]);
                answer = doCalculate(answer, number, symbol);
                continue;
            }
            symbol = values[i];
        }
        return answer;
    }
}
