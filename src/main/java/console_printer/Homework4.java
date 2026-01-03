package console_printer;

import net.datafaker.Faker;

public class Homework4 {

    private static final Faker faker = new Faker();

    private static final String FIRST_TASK_MESSAGE = "#1. Применение нескольких арифметических операций" +
            " ( + , -, × , ÷) над двумя примитивами типа int.";
    private static final String SECOND_TASK_MESSAGE = "#2. Применение нескольких арифметических операций" +
            " над int и double в одном выражении.";
    private static final String THIRD_TASK_MESSAGE = "#3. Применение нескольких логических операций (< , >, ≥, ≤).";
    private static final String FOURTH_TASK_MESSAGE = "#4. Получение максимальных и минимальных числовых значений.";
    private static final String FIFTH_TASK_MESSAGE = "#5. Получение переполнения при арифметической операции.";
    private static final String ADDITION_TEMPLATE = "%d + %d = %d";
    private static final String SUBTRACTION_TEMPLATE = "%d - %d = %d";
    private static final String MULTIPLICATION_TEMPLATE = "%d × %d = %d";
    private static final String DIVISION_TEMPLATE = "%d ÷ %d = %d";
    private static final String BIGGER_TEMPLATE = "%d > %d = %s";
    private static final String BIGGER_OR_EQUALS_TEMPLATE = "%d ≥ %f = %s";
    private static final String LESS_TEMPLATE = "%d < %d = %s";
    private static final String LESS_OR_EQUALS_TEMPLATE = "%d ≤ %f = %s";
    private static final String MULTIPLICATION_WITH_OVERFLOW_TEMPLATE = "%d × %f = %s";
    private static final String DIVISION_WITH_OVERFLOW_TEMPLATE = "%f ÷ %f = %s";
    private static final String IS_INFINITE_TEMPLATE = "Является ли неопределённостью (бесконечностью): %s";

    private static String message;

    public static void main(String[] args) {
        printFirstTask();
        printSecondTask();
        printThirdTask();
        printFourthTask();
        printFifthTask();
    }

    /**
     * 1. Применение нескольких арифметических операций ( + , -, × , ÷) над двумя примитивами типа int
     */
    private static void printFirstTask() {
        print(FIRST_TASK_MESSAGE);
        int firstNumber = faker.number().numberBetween(1, 10);
        int secondNumber = faker.number().numberBetween(10, 20);
        var intSum = firstNumber + secondNumber;
        message = ADDITION_TEMPLATE.formatted(firstNumber, secondNumber, intSum);
        print(message);
        var intDifference = firstNumber - secondNumber;
        message = SUBTRACTION_TEMPLATE.formatted(firstNumber, secondNumber, intDifference);
        print(message);
        var intProduct = firstNumber * secondNumber;
        message = MULTIPLICATION_TEMPLATE.formatted(firstNumber, secondNumber, intProduct);
        print(message);
        var intQuotient = firstNumber / secondNumber;
        message = DIVISION_TEMPLATE.formatted(firstNumber, secondNumber, intQuotient);
        print(message);
    }

    /**
     * 2. Применение нескольких арифметических операций над int и double в одном выражении
     */
    private static void printSecondTask() {
        print(SECOND_TASK_MESSAGE);
        int firstNumber = faker.number().numberBetween(1, 10);
        double secondNumber = 0.1;
        var result = firstNumber * (secondNumber + secondNumber) / secondNumber;
        message = "%1$d × (%2$f + %2$f) ÷ %2$f = %3$f".formatted(firstNumber, secondNumber, result);
        print(message);
    }

    /**
     * 3. Применение нескольких логических операций ( < , >, >=, <= )
     */
    private static void printThirdTask() {
        print(THIRD_TASK_MESSAGE);
        int firstNumber = faker.number().numberBetween(1, 10);
        int secondNumber = firstNumber - 1;
        double thirdNumber = firstNumber + 0.0;
        var result = firstNumber < secondNumber;
        message = LESS_TEMPLATE.formatted(firstNumber, secondNumber, result);
        print(message);
        result = firstNumber > secondNumber;
        message = BIGGER_TEMPLATE.formatted(firstNumber, secondNumber, result);
        print(message);
        result = secondNumber >= thirdNumber;
        message = BIGGER_OR_EQUALS_TEMPLATE.formatted(secondNumber, thirdNumber, result);
        print(message);
        result = firstNumber <= thirdNumber;
        message = LESS_OR_EQUALS_TEMPLATE.formatted(firstNumber, thirdNumber, result);
        print(message);
    }


    private static void printFourthTask() {
        print(FOURTH_TASK_MESSAGE);
        int maxInt = Integer.MAX_VALUE;
        print("Максимальное значение типа int = %d".formatted(maxInt));
        int minInt = Integer.MIN_VALUE;
        print("Минимальное значение типа int = %d".formatted(minInt));
        long maxLong = Long.MAX_VALUE;
        print("Максимальное значение типа long = %d".formatted(maxLong));
        long minLong = Long.MIN_VALUE;
        print("Минимальное значение типа long = %d".formatted(minLong));
        float maxFloat = Float.MAX_VALUE;
        print("Максимальное значение типа float = %f".formatted(maxFloat));
        float minFloat = Float.MIN_VALUE;
        print("Минимальное значение типа float = %f".formatted(minFloat));
        double maxDouble = Double.MAX_VALUE;
        print("Максимальное значение типа double = %f".formatted(maxDouble));
        double minDouble = Double.MIN_VALUE;
        print("Минимальное значение типа double = %f".formatted(minDouble));
    }

    private static void printFifthTask() {
        print(FIFTH_TASK_MESSAGE);
        var maxDouble = Double.MAX_VALUE;
        int intNumber = faker.number().numberBetween(2, 10);
        var overflow = intNumber * maxDouble;
        message = MULTIPLICATION_WITH_OVERFLOW_TEMPLATE.formatted(intNumber, maxDouble, overflow);
        print(message);
        message = IS_INFINITE_TEMPLATE.formatted(Double.isInfinite(overflow));
        print(message);

        var minDouble = Double.MIN_VALUE;
        overflow = -maxDouble / minDouble;
        message = DIVISION_WITH_OVERFLOW_TEMPLATE.formatted(maxDouble, minDouble, overflow);
        print(message);
        message = IS_INFINITE_TEMPLATE.formatted(Double.isInfinite(overflow));
        print(message);
    }

    /**
     * Вывод сообщения в консоль с новой строки
     *
     * @param text Отображаемое сообщение.
     */
    private static void print(String text) {
        System.out.println(text);
    }
}
