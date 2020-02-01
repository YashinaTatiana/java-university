package part1;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public class LoopOpParam extends Base {

    public static List part3_1(int a, int b) {
        if (a >= b) {
            throw new IllegalArgumentException("a must be less than b!");
        }
        List<Integer> res = new ArrayList<>();
        for (int i = a; i <= b; i++) {
            res.add(i);
        }
        return res;
    }

    public static List part3_2(int a, int b) {
        if (a >= b) {
            throw new IllegalArgumentException("a must be less than b!");
        }
        List<Integer> res = new ArrayList<>();
        for (int i = b - 1; i > a; i--) {
            res.add(i);
        }
        return res;
    }

    public static int part3_3(int a, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The value of power must be > 0!");
        }
        int res = a;
        for (int i = 1; i < n; i++) {
            res *= a;
        }
        return res;
    }

    public static List part3_4(int a, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The value of power must be > 0!");
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            res.add((int) pow(a, i));
        }
        return res;
    }

    public static void run() {
        while (true) {
            try {
                System.out.print("\n\n---------------------Choose action:-------------------- " +
                        "\n-------------------------------------------------------" +
                        "\n1 - Print numbers from [a, b] in the ascending order" +
                        "\n2 - Print numbers from (a, b) in the descending order" +
                        "\n3 - Find number A in the power of N" +
                        "\n4 - Find all powers of the number A from 1 to N\n>>");
                int choice = Base.sc.nextInt();
                switch (choice) {
                    case 1: {
                        System.out.print("a=");
                        int a = sc.nextInt();
                        System.out.print("b=");
                        int b = sc.nextInt();
                        List res = part3_1(a, b);
                        System.out.println("Count: " + res.size());
                        printArray(res);
                    }
                    break;
                    case 2: {
                        System.out.print("a=");
                        int a = sc.nextInt();
                        System.out.print("b=");
                        int b = sc.nextInt();
                        List res = part3_2(a, b);
                        System.out.println("Count: " + res.size());
                        printArray(res);
                    }
                    break;
                    case 3: {
                        System.out.print("a=");
                        int a = sc.nextInt();
                        System.out.print("n=");
                        int n = sc.nextInt();
                        System.out.println(part3_3(a, n));
                    }
                    break;
                    case 4: {
                        System.out.print("a=");
                        int a = sc.nextInt();
                        System.out.print("n=");
                        int n = sc.nextInt();
                        Base.printArray(part3_4(a, n));
                    }
                    break;
                    default:
                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                break;
            }
        }
    }

    public static void main(String[] args) {
        run();
    }

}
