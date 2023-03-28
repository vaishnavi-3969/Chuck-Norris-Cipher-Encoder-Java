package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            System.out.println("\nPlease input operation (encode/decode/exit):");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                case "encode":
                    encode();
                    System.out.println();
                    break;
                case "decode":
                    decode();
                    System.out.println();
                    break;
                case "exit":
                    System.out.println("Bye!");
                    flag = false;
                    break;
                default:
                    System.out.printf("There is no '%s' operation \n", input);
                    break;
            }
        }
    }

    public static boolean containsZeroes(String input){
        for (int i = 0; i < input.length(); i++) {
            return input.matches("^[0]+$");
        }
        return false;
    }

    public static void decode() {
        System.out.println("Input encoded string:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] temp = input.split(" ");
        final int newArrLength = temp.length / 2;

        if (temp.length % 2 != 0) {
            System.out.print("Encoded string is not valid.");
            return;
        }
            String[] subStrings = new String[newArrLength];
            boolean isZeros = false;
            boolean isContainingOnlyZeros = false;

            for (int i = 0; i < temp.length; i++) {

                isContainingOnlyZeros = containsZeroes(temp[i]);
                if (!isContainingOnlyZeros) {
                    System.out.print("Encoded string is not valid.");
                    return;
                }
                if (i % 2 == 0) {
                    if (!temp[i].equals("00") && !temp[i].equals("0")) {
                        System.out.print("Encoded string is not valid.");
                        return;
                    }

                    if (temp[i].equals("00")) {
                        isZeros = true;
                    } else {
                        isZeros = false;
                    }
                } else {
                    if (isZeros) {
                        subStrings[i / 2] = temp[i];
                    } else {
                        subStrings[i / 2] = temp[i].replaceAll("0", "1");
                    }
                }
            }

            String binaryString = "";
            for (String substring : subStrings) {
                binaryString += substring;
            }

            if (binaryString.length() % 7 != 0) {
                System.out.print("Encoded string is not valid.");
                return;
            }

            String[] binaryDigits = binaryString.split("(?<=\\G.{" + 7 + "})");

            System.out.println("Decoded string:");
            int tempStore = 0;
            for (String binaryDigit : binaryDigits) {
                tempStore = Integer.parseInt(binaryDigit, 2);
                char character = (char) tempStore;
                System.out.print(character);
            }
        }

    public static void encode(){
        System.out.println("Input string:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String output = "";
        char tmpDigit = ' ';
        String space = "";

        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            int decimal = (int) character;
            String binaryValue = Integer.toBinaryString(decimal);
            String binaryValueFormatted = String.format("%7s", binaryValue).replaceAll(" ", "0");

            for (int j = 0; j < binaryValueFormatted.length(); j++) {
                char binaryDigit = binaryValueFormatted.charAt(j);

                if (tmpDigit != binaryDigit) {
                    space = (j == 0 && i == 0) ? "" : " ";
                    if (binaryDigit == '1'){
                        output = output + space + "0 0";
                        tmpDigit = '1';
                    } else if (binaryDigit == '0') {
                        output = output + space + "00 0";
                        tmpDigit = '0';
                    }
                } else {
                    output = output + "0";
                }
            }
        }
        System.out.println("Encoded string:");
        System.out.println(output);
    }

}