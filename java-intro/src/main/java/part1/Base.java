package part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;

public class Base {

    public static Scanner sc = new Scanner(System.in);

    public static void printArray(List a) {
        if (a == null) {
            throw new IllegalArgumentException("List is null!");
        }
        a.forEach(x -> System.out.print(x + "; "));
    }

    public static List<Integer> inputArray() {
        System.out.print("N = ");
        int n = sc.nextInt();
        return inputArray(n);
    }

    public static List<Integer> inputArray(int n) {
        ArrayList<Integer> numbers = new ArrayList();
        for (int i = 0 ; i < n; i++) {
            System.out.print(format("x[%s] = ", i));
            numbers.add(sc.nextInt());
        }
        return numbers;
    }

}
