/*
    Part 4 - Hacker Problem - FractionCalculatorAdvanced
 */

import java.util.Scanner;

public class FractionCalculatorAdvanced {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println(" \t NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
        System.out.println(" \t NN                                                   NN");
        System.out.println(" \t NN         FRACTION CALCULATOR ADVANCED              NN");
        System.out.println(" \t NN                                                   NN");
        System.out.println(" \t NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
        System.out.println();
        System.out.println(" \t   This program allows the user to perform standard");
        System.out.println(" \t   mathematical functions on regular fractions. It");
        System.out.println(" \t   add, subtract, multiply and divide fractions until");
        System.out.println(" \t   you type Q to quit.");
        System.out.println();
        System.out.println("Valid operations are of the form \"[FRAC] [OPERATION] [FRAC]\".");
        System.out.println("[FRAC] can be either a single integer or two integers separated by \"/\".");
        System.out.println("[OPERATION] can be +, -, *, / or =.");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println();

        int quit = 0;

        while (quit == 0) {

            System.out.print("Please enter your fractional computation (Q to quit): ");

            String convolute = input.nextLine();
            if (convolute.equalsIgnoreCase("q")) { quit = 1; continue; }

            int exit = 0;
            while (exit == 0) {

                convolute = verifyInput(convolute);

                // Parse String for further testing...

                String[] choppy = convolute.split(" ");
                String elem1 = choppy[0];
                String elem2 = choppy[1];
                String elem3 = choppy[2];

                if (!(validFraction(elem1))) {
                    System.out.print("Your first element is not a typical fraction. Please use integer form a/b or a. Re-type full operation: ");
                    convolute = input.nextLine();
                    continue;
                }

                if (!(validOperator(elem2))) {
                    System.out.print("Your operator must be: +, -, /, *, =. Please re-enter your whole equation: ");
                    convolute = input.nextLine();
                    continue;
                }

                if (!(validFraction(elem3))) {
                    System.out.print("Your third element is not a typical fraction. Please use integer form a/b or a. Re-type full operation: ");
                    convolute = input.nextLine();
                    continue;
                }

                exit = 1;
            }

            // Parse and cast.
            String[] choppy = convolute.split(" ");

            if (!(choppy[0].contains("/"))) { choppy[0] = choppy[0] + "/1"; }
            if (!(choppy[2].contains("/"))) { choppy[2] = choppy[2] + "/1"; }

            // Cast fraction1
            String[] finechop = choppy[0].split("/");
            int[] frac1 = new int[2];
            frac1[0] = Integer.parseInt(finechop[0]);
            frac1[1] = Integer.parseInt(finechop[1]);
            Fraction fraction1 = new Fraction(frac1[0], frac1[1]);

            // Cast fraction2
            finechop = choppy[2].split("/");
            int[] frac2 = new int[2];
            frac2[0] = Integer.parseInt(finechop[0]);
            frac2[1] = Integer.parseInt(finechop[1]);
            Fraction fraction2 = new Fraction(frac2[0], frac2[1]);

            // Equation is: fraction1 choppy[1] fraction2, process normally.

            if (choppy[1].equals("+")) {
                Fraction sum = fraction1.add(fraction2);
                sum.toLowestTerms();
                System.out.println(fraction1.toString() + " + " + fraction2.toString() + " = " + sum.toString());
            } else if (choppy[1].equals("-")) {
                Fraction rem = fraction1.subtract(fraction2);
                rem.toLowestTerms();
                System.out.println(fraction1.toString() + " - " + fraction2.toString() + " = " + rem.toString());
            } else if (choppy[1].equals("/")) {
                Fraction quotient = fraction1.divide(fraction2);
                quotient.toLowestTerms();
                System.out.println(fraction1.toString() + " / " + fraction2.toString() + " = " + quotient.toString());
            } else if (choppy[1].equals("*")) {
                Fraction product = fraction1.multiply(fraction2);
                product.toLowestTerms();
                System.out.println(fraction1.toString() + " * " + fraction2.toString() + " = " + product.toString());
            } else {
                boolean answer = fraction1.equals(fraction2);
                System.out.println(fraction1.toString() + " = " + fraction2.toString() + " is " + answer);
            }
            System.out.println("-------------------------------------------------------------------------");
        }
    }

    public static String verifyInput(String in) {
        int exit = 0;

        while (exit == 0) {
            // Must include spaces.
            if (isSpaced(in)) {
                // Parse
                String[] choppy = in.split(" ");
                if (choppy.length == 3) {
                    exit = 1;
                } else {
                    System.out.print("Your entry must contain exactly three (3) items [FRAC] [OPERATION] [FRAC], try again: ");
                    in = input.nextLine();
                }
            } else {
                System.out.print("Your computation must include spaces to separate the fractions and operation, try again: ");
                in = input.nextLine();
            }
        }
        return in;
    }

    public static boolean isSpaced(String in) {
        if (in.contains(" ")) {
            return true;
        } else {
            return false;
        }
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

    public static boolean validOperator(String in) {
        if ((in.equals("+")) || (in.equals("-")) || (in.equals("/")) || (in.equals("*")) || (in.equals("="))) {
            return true;
        } else {
            return false;
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
