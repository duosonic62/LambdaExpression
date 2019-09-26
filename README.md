# Javaラムダ式

javaラムダ式はメソッドが一つしかないインターフェースを実装してnewします。  
ここではある式を計算した結果を表示するプログラムを例にラムダ式について説明しようかと思います。

## ノーマルなクラスの受け渡し
まずはノーマルなクラスを受け渡すことで、計算した結果を表示するプログラムを実現したいと思います。
ソースは下記の4つです。

```
Main.java                   メインのクラス
Display.java                計算結果を表示するクラス
CalcuratorInterface.java    計算について定義したインターフェース
Display.java                CalcuratorInterface

```

まずDisplayクラスでは、計算を担当するcalculatorと計算する値(a, b)を受け取り、
calculatorで(a, b)の値を計算して表示します。

```java
public class Display {
    public void showCalc(CalculatorInterface calculator, int a, int b) {
        System.out.println(calculator.calc(a, b));
    }
}
```

CalculatorInterfaceでは計算を行うメソッド、calcを定義します。
```java

public interface CalculatorInterface {
    int calc(int a, int b);
}

```

AddCalculatorではCalculatorInterfaceを実装して、引数(a, b)を足した値を返します。
```java
public class AddCalculator implements CalculatorInterface {

    @Override
    public int calc(int a, int b) {
        return a + b;
    }
}

```

MainクラスではDisplayクラスに計算を行う```calculator```と計算する値を渡して計算結果を表示させています。
```java
public class Main {
    public static void main(String[] args) {
        Display display = new Display();
        CalculatorInterface calculator = new AddCalculator();
        display.showCalc(calculator, 3, 5);
    }
}

```

なんてことはないですね。

## インナークラスで受け渡し
次に、インナークラスを使ってみたいと思います。
CalcuratorInterface.javaと、Display.javaは変更ありません。
  
ただMainクラスだけで使う足し算をするだけのクラスのために、わざわざクラスファイルを作成するのは面倒だと思いませんか。
それなら、以下のようにMainの中で定義してしまえば、クラスファイルを作成する手間が無くなります。
```java
public class Main {
    public static void main(String[] args) {
        Display display = new Display();

        // ここでしか使わないし・・・
        class AddCalculator implements CalculatorInterface {

            @Override
            public int calc(int a, int b) {
                return a + b;
            }
        }

        CalculatorInterface calculator = new AddCalculator();
        display.showCalc(calculator, 3, 5);
    }
}
```

これで、AddCalculator.javaを作成せずにすみましたね。

## 無名クラスを関数で受け渡し
インナークラスを使って、クラスファイルを一つ作らずにすみました。
けどもっと、省略できそうですよね。  

一回しか使わないクラスに名前つける必要はありません。
そこでcalculatorに代入するタイミングで、その場でクラスを実装してしまいましょう。
```java
public class Main {
    public static void main(String[] args) {
        Display display = new Display();

        // 一回なら使い捨てで
        CalculatorInterface calculator = new CalculatorInterface() {
            @Override
            public int calc(int a, int b) {
                return a + b;
            }
        };
        display.showCalc(calculator, 3, 5);
    }
}
```

これが無名クラスです。使い回しはできませんが、一回しか使わないならわざわざ名前をつける必要もないし楽ですよね。


## ラムダ式
無名クラスを使って、名前をつけずにクラスを作ることができました。  
しかし、CalculatorInterfaceを実装するなら```new CalculatorInterface()```とかわざわざわかりきったことを書くのは面倒ですよね。  
それに、メソッドが一つしかないなら、どのメソッドを実装するかも一意に決まっています。  
メソッドの引数もインターフェースで型の定義がされているし、宣言する必要があるのでしょうか。  

そこでラムダ式の出番です。
```java
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
```

ラムダ式は以下の部分ですね。この部分で、CalculatorInterfaceのcalcメソッドの実装を表現しています。

```
(a, b) -> {
    return a + b;
};
```
```(a, b)```は引数です。メソッドの引数はインターフェースで型の定義がされているのでわざわざ書きません。
```->```以降がメソッドの実装です。```a+b```をリターンしてますね。

ラムダ式では、メソッドが一つしかないインターフェースを実装したクラスをnewします。