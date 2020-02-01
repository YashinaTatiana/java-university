package part1;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;
import static java.lang.String.format;

public class ConditionalOp extends Base {

    public static String compareSumOfSquaresAndSquareOfSum(double a, double b) {
        double sumOfSquares = a * a + b * b;
        double squareOfSum = pow((a + b), 2);
        if (sumOfSquares > squareOfSum) {
            return "The sum of the squares is greater.";
        } else if (sumOfSquares < squareOfSum) {
            return "The square of sum is greater.";
        } else {
            return "The sum of squares equals the square of sum.";
        }
    }

    public static double getPremium(int experience, double salary) {
        if (experience < 0 || salary < 0) {
            throw new IllegalArgumentException("Arguments can not have negative values!");
        }
        double premium = 0;
        if (experience >= 2 && experience < 5) {
            premium = 0.02 * salary;
        } else if (experience >= 5 && experience < 10) {
            premium = 0.05 * salary;
        }
        return premium;
    }

    public static String isFartherPoint(int x0, int y0, int x1, int y1) {
        double firstLen = pow(x0, 2) + pow(y0, 2);
        double secLen = pow(x1, 2) + pow(y1, 2);
        if (firstLen > secLen) {
            return "The first point is located farther.";
        } else if (firstLen < secLen) {
            return "The second point is located farther.";
        } else {
            return "Points have equal distances.";
        }
    }

    public static boolean triangleExists(double a, double b, double c) {
        return (a > 0 && b > 0 && c > 0
                && (a + b) > c && (a + c) > b && (b + c) > a);
    }

    public static boolean isRectangularTriangle(double a, double b, double c) {
        if (!triangleExists(a, b, c)) {
            throw new IllegalArgumentException("The triangle with such sides not exists!");
        }
        double maxAB = max(a, b);
        double maxSide = max(maxAB, c);
        if (maxSide == c) {
            return pow(maxSide, 2) == (pow(a, 2) + pow(b, 2));
        } else {
            return pow(maxSide, 2) == pow(c, 2) + pow(min(a, b), 2);
        }
    }

    public static String getSeasonByMonth(int month) {
        if (month > 12 || month < 1) {
            throw new IllegalArgumentException("Month value is invalid");
        }
        if (month < 2 || month == 12) {
            return "Winter";
        } else if (month > 2 && month < 6) {
            return "Spring";
        } else if (month > 5 && month < 9) {
            return "Summer";
        } else {
            return "Autumn";
        }
    }

    public static List squaresOfPositive(List<Integer> mas) {
        if (mas == null) {
            throw new IllegalArgumentException();
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < mas.size(); i++) {
            int a = mas.get(i);
            if (a > 0) {
                res.add(a * a);
            } else {
                res.add(a);
            }
        }
       return res;
    }

    public static void run() {
        while (true) {
            try {
                System.out.print("\n\n---------------------Choose action:-------------------- " +
                        "\n-------------------------------------------------------" +
                        "\n1 - Compare the sum of squares and the square of sum for two numbers" +
                        "\n2 - Get premium" +
                        "\n3 - Find the farther point" +
                        "\n4 - Determine if triangle is rectangular" +
                        "\n5 - Squares of positive numbers" +
                        "\n6 - Get the season name by month\n>>");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: {
                        System.out.print("Input first number:");
                        double a = sc.nextDouble();
                        System.out.print("Input second number:");
                        double b = sc.nextDouble();
                        System.out.println(compareSumOfSquaresAndSquareOfSum(a, b));
                    }
                    break;
                    case 2: {
                        System.out.print("Input the experience: ");
                        int experience = sc.nextInt();
                        System.out.print("Input the salary: ");
                        double salary = sc.nextDouble();
                        double res = getPremium(experience, salary);
                        System.out.println(format("Premium: %s.\nThe amount payable: %s.", res, res + salary));
                    } break;
                    case 3: {
                        System.out.print("Input coordinates of the first point A(x0, y0).\nx0=");
                        int x0 = sc.nextInt();
                        System.out.print("y0=");
                        int y0 = sc.nextInt();
                        System.out.print("Input coordinates of the second point B(x1, y1).\nx1=");
                        int x1 = sc.nextInt();
                        System.out.print("y1=");
                        int y1 = sc.nextInt();
                        System.out.println(isFartherPoint(x0, y0, x1, y1));
                    } break;
                    case 4: {
                        System.out.print("a = ");
                        double a = sc.nextDouble();
                        System.out.print("b = ");
                        double b = sc.nextDouble();
                        System.out.print("c = ");
                        double c = sc.nextDouble();
                        System.out.println(isRectangularTriangle(a, b, c));
                    } break;
                    case 5: {
                        List<Integer> mas = inputArray(3);
                        printArray(squaresOfPositive(mas));
                    } break;
                    case 6: {
                        System.out.print("Input month number: ");
                        int month = sc.nextInt();
                        System.out.println(getSeasonByMonth(month));
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
