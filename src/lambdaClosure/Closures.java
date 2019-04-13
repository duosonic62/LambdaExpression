package lambdaClosure;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Closures {
    public static void main(String[] args) throws InterruptedException {
        Supplier<Supplier> outer = () -> {
            final String outerDateTime = LocalDateTime.now().toString();
            return  () -> {
                final String innerDateTime = LocalDateTime.now().toString();
                return "outer " + outerDateTime + "\ninner " + innerDateTime + "\n";
            };
        };


        Supplier closure = outer.get();

        System.out.println(closure.get());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(closure.get());

    }
}
