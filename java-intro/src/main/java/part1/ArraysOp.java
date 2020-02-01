package part1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class ArraysOp extends Base {

    public static List getEvenNumbers(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("List is null!");
        }
        return numbers.stream().filter(x -> (x % 2 == 0)).collect(toList());
    }

    public static List getOddNumbers(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("List is null!");
        }
        return numbers.stream().filter(x -> (x % 2 != 0)).collect(toList());
    }

    public static List dividedBy5Or10(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("List is null!");
        }
        return numbers.stream().filter(x -> (x % 5 == 0 || x % 10 == 0)).collect(toList());
    }

    public static List dividedBy3Or9(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("List is null!");
        }
        return numbers.stream().filter(x -> (x % 3 == 0 || x % 9 == 0)).collect(toList());
    }

    public static boolean isPrime(int i){
        if (i <= 1) {
            return false;
        } else if (i <=3) {
            return true;
        } else if (i % 2==0 || i % 3 ==0) {
            return false;
        }
        int n = 5;
        while (n * n <=i){
            if (i % n ==0 || i % (n + 2) == 0) {
                return false;
            }
            n += 6;
        }
        return true;
    }

    public static List findPrimeElements(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("List is null!");
        }
        List<Integer> res = new ArrayList();
        numbers.stream().filter(ArraysOp::isPrime)
                .collect(toList())
                .forEach(x -> {
                    res.add(x);
                });
        return res;
    }

    public static int gcd(int a, int b){
        return b == 0 ? a : gcd(b,a % b);
    }

    public static int lcm(int a, int b){
        return a / gcd(a,b) * b;
    }

    public static int findGcdInArray(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("List is null!");
        }
        int nd = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            nd = gcd((nd < numbers.get(i) ? nd : numbers.get(i)),
                    (nd < numbers.get(i) ? numbers.get(i) : nd));
        }
        return nd;
    }

    public static int findLcmInArray(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("List is null!");
        }
        int nk = 1;
        for (int i=0; i < numbers.size(); i++) {
            nk = lcm(nk, numbers.get(i));
        }
        return nk;
    }

    static int numSquareSum(int n) {
        int squareSum = 0;
        while (n != 0) {
            squareSum += (n % 10) * (n % 10);
            n /= 10;
        }
        return squareSum;
    }

    static boolean isHappyNumber(int n) {
        int slow, fast;
        slow = fast = n;
        do {
            slow = numSquareSum(slow);
            fast = numSquareSum(numSquareSum(fast));
        }
        while (slow != fast);
        return (slow == 1);
    }

    public static List happyNumbers(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("List is null!");
        }
        List<Integer> res = new ArrayList();
        numbers.stream().filter(ArraysOp::isHappyNumber)
                .collect(toList())
                .forEach(x -> res.add(x));
        return res;
    }

    public static String printDigit(int i) {
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("Number must have values from 0 to 9!");
        }
        List<String> writtenDigits = new ArrayList();
        writtenDigits.add("Zero");
        writtenDigits.add("One");
        writtenDigits.add("Two");
        writtenDigits.add("Three");
        writtenDigits.add("Four");
        writtenDigits.add("Five");
        writtenDigits.add("Six");
        writtenDigits.add("Seven");
        writtenDigits.add("Eight");
        writtenDigits.add("Nine");
        return writtenDigits.get(i);
    }

    public static void run() {
        while (true) {
            try {
                System.out.print("\n\n---------------------Choose action:-------------------- " +
                        "\n-------------------------------------------------------" +
                        "\n1 - Print the even and odd numbers from the array" +
                        "\n2 - Print numbers divisible by 3 or 9" +
                        "\n3 - Print numbers divisible by 5 or 10" +
                        "\n4 - Print the gcd and the lcm from the array" +
                        "\n5 - Print the 'happy' numbers from the array" +
                        "\n6 - Print the digit from 0 to 9\n>>");
                int choice = Base.sc.nextInt();
                switch (choice) {
                    case 1: {
                        Base.printArray(getEvenNumbers(Base.inputArray()));
                        Base.printArray(getOddNumbers(Base.inputArray()));
                    }
                    break;
                    case 2: {
                        Base.printArray(dividedBy3Or9(Base.inputArray()));
                    }
                    break;
                    case 3: {
                        printArray(dividedBy5Or10(Base.inputArray()));
                    }
                    break;
                    case 4: {
                        List array = inputArray();
                        System.out.println(format("gcd: %s", findGcdInArray(array)));
                        System.out.println(format("lcm: %s", findLcmInArray(array)));
                    }
                    break;
                    case 5: {
                        printArray(findPrimeElements(Base.inputArray()));
                    }
                    break;
                    case 6: {
                        printArray(happyNumbers(Base.inputArray()));
                    }
                    break;
                    case 7: {
                        System.out.println("n = ");
                        System.out.println(printDigit(Base.sc.nextInt()));
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
