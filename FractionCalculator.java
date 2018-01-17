/*
    Part 2 - FractionCalculator Class
 */

import java.util.Scanner;

public class FractionCalculator {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // Part 3 - Putting it all together!

        // 1. Write a short introduction method that describes the calculator program and welcomes your user
        System.out.println(" \t NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
        System.out.println(" \t NN                                                   NN");
        System.out.println(" \t NN                FRACTION CALCULATOR                NN");
        System.out.println(" \t NN                                                   NN");
        System.out.println(" \t NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
        System.out.println();
        System.out.println(" \t   This program allows the user to perform standard");
        System.out.println(" \t   mathematical functions on regular fractions. It");
        System.out.println(" \t   add, subtract, multiply and divide fractions until");
        System.out.println(" \t   you type Q to quit.");
        System.out.println();
        System.out.println("Please enter your fractions in the form a/b, where a and b are integers.");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println();

        int i = 0;

        do {
            String operation = getOperation();
            Fraction frac1 = getFraction();
            Fraction frac2 = getFraction();

            if (operation.equals("+")) {
                Fraction sum = frac1.add(frac2);
                sum.toLowestTerms();
                System.out.println(frac1.toString() + " + " + frac2.toString() + " = " + sum.toString());
            } else if (operation.equals("-")) {
                Fraction rem = frac1.subtract(frac2);
                rem.toLowestTerms();
                System.out.println(frac1.toString() + " - " + frac2.toString() + " = " + rem.toString());
            } else if (operation.equals("/")) {
                Fraction quotient = frac1.divide(frac2);
                quotient.toLowestTerms();
                System.out.println(frac1.toString() + " / " + frac2.toString() + " = " + quotient.toString());
            } else if (operation.equals("*")) {
                Fraction product = frac1.multiply(frac2);
                product.toLowestTerms();
                System.out.println(frac1.toString() + " * " + frac2.toString() + " = " + product.toString());
            } else {
                boolean answer = frac1.equals(frac2);
                System.out.println(frac1.toString() + " = " + frac2.toString() + " is " + answer);
            }
            System.out.println("-------------------------------------------------------------------------");
        } while (i == 0);

    }

    public static String getOperation() {

        System.out.print("Please enter an operation (+, -, /, *, = or Q to quit): ");
        String perform = input.nextLine();

        if (!(perform.equals("+")) && !(perform.equals("-")) && !(perform.equals("/")) && !(perform.equals("*")) && !(perform.equals("=")) && !(perform.equalsIgnoreCase("Q"))) {
            while (!(perform.equals("+")) && !(perform.equals("-")) && !(perform.equals("/")) && !(perform.equals("*")) && !(perform.equals("=")) && !(perform.equalsIgnoreCase("Q"))) {
                System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");
                perform = input.nextLine();
            }
        }

        if (perform.equalsIgnoreCase("q")) {
            System.exit(0);
        }

        return perform;

    }

    public static boolean validFraction(String check) {
        if (check.contains("/")) {
            String[] data = check.split("/");
            if (data[0].matches("-?\\d+")) {
                if (data[1].matches("-?\\d+")) {
                    int data1 = Integer.parseInt(data[1]);
                    if (data1 > 0) {
                        return true;
                    } else { // Negative denom. passed!
                        return false;
                    }
                } else { // Non-integer denom. passed!
                    return false;
                }
            } else { // Non-integer numerator passed!
                return false;
            }
        } else {
            if (check.matches("-?\\d+")) {
                return true;
            } else { // Non-integer data passed!
                return false;
            }
        }
    }

    public static Fraction getFraction() {
        System.out.print("Please enter a fraction (a/b) or integer (a): ");
        String usrIn = input.nextLine();

        if (!validFraction(usrIn)) {
          while (!validFraction(usrIn)) {
              System.out.print("Invalid Fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
              usrIn = input.nextLine();
          }
        }

        // Convert to integers to pass as Fraction
        int[] toInts = new int[2];

        if (usrIn.contains("/")) {
            String[] split = usrIn.split("/");
            toInts[0] = Integer.parseInt(split[0]);
            toInts[1] = Integer.parseInt(split[1]);
        } else {
            toInts[0] = Integer.parseInt(usrIn);
            toInts[1] = 1;
        }

        return new Fraction(toInts[0], toInts[1]);
    }

}
