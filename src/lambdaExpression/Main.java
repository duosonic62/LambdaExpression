package lambdaExpression;

public class Main {
    public static void main(String[] args) {
        Display display = new Display();

        //　実装するのがメソッド一つなら、それだけ書けばいいじゃん
        CalculatorInterface calculator = (a, b) -> {
            return a + b;
        };

        display.showCalc(calculator, 3, 5);
    }
}
