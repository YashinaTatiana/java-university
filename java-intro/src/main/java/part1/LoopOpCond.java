package part1;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LoopOpCond extends Base {

    public static boolean notFiveAndThree(int number) {
        return (number % 5 != 0) && (number % 3 == 0);
    }

    public static int sumOfDigits(int n) {
        int sum = 0;
        while (n > 0) {
            sum = sum + n % 10;
            n /= 10;
        }
        return sum;
    }

    public static List notDividedByFiveAndDividedByThree(List<Integer> numbers) {
        if (null == numbers) {
            throw new IllegalArgumentException("List is null!");
        }
        List res = new ArrayList();
        numbers.stream().filter(x -> notFiveAndThree(sumOfDigits(x)) && notFiveAndThree(x))
                .collect(toList())
                .forEach(x -> res.add(x));
        return res;
    }

    public static List dividedByFive(int n) {
        List res = new ArrayList();
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0) {
                res.add(i);
            }
        }
        return res;
    }

    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        int ost = 0;
        while (ost == 0) {
            ost = n % 2;
            n /= 2;
        }
        return n == 0;
    }

    public static List fib(int n) {
        List res = new ArrayList();
        if (n <= 1) {
            return res;
        }
        int n0 = 1;
        int n1 = 1;
        int n2 = n0 + n1;
        res.add(n0);
        res.add(n1);
        for (int i = 3; i < n && n2 < n; i++) {
            res.add(n2);
            n0 = n1;
            n1 = n2;
            n2 = n0 + n1;
        }
        return res;
    }

    public static void run() {
        while (true) {
            try {
                System.out.print("\n\n---------------------Choose action:-------------------- " +
                        "\n-------------------------------------------------------" +
                        "\n1 - Find all numbers that are non-multiple five and multiples of 3, " +
                        "\nand the sum of the digits of which is also non-multiple five and is a multiple of 3." +
                        "\n2 - Find all numbers that are multiples of five for numbers from 1 to N." +
                        "\n3 - Is power of two" +
                        "\n4 - Find all fibonacci\n>>");
                int choice = Base.sc.nextInt();
                switch (choice) {
                    case 1: {
                        List<Integer> numbers = inputArray();
                        printArray(notDividedByFiveAndDividedByThree(numbers));
                    } break;
                    case 2: {
                        System.out.print("N = ");
                        printArray(dividedByFive(sc.nextInt()));
                    } break;
                    case 3: {
                        System.out.print("N = ");
                        System.out.println("Result: " + isPowerOfTwo(sc.nextInt()));
                    } break;
                    case 4: {
                        System.out.print("n = ");
                        printArray(fib(sc.nextInt()));
                    } break;
                    default: break;
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        run();
    }
}
