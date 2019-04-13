package normalClass;

public class AddCalculator implements CalculatorInterface {

    @Override
    public int calc(int a, int b) {
        return a + b;
    }
}
