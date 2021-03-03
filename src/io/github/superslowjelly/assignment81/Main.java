package io.github.superslowjelly.assignment81;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    // Assign a letter grade based on a provided score.
    public static String getLetterGrade(double score) {
        if (score > 98) return "A+";
        else if (score > 93) return "A";
        else if (score > 90) return "A-";
        else if (score > 87) return "B+";
        else if (score > 83) return "B";
        else if (score > 80) return "B-";
        else if (score > 77) return "C+";
        else if (score > 73) return "C";
        else if (score > 70) return "C-";
        else if (score > 67) return "D+";
        else if (score > 63) return "D";
        else if (score > 60) return "D-";
        else return "F";
    }

    // Returns length of longest name in students hashmap, used in formatting.
    private static int longestName(HashMap<String, Double> students) {
        int longestName = 4;
        for (Map.Entry<String, Double> entry : students.entrySet()) {
            int length;
            if (longestName < (length = entry.getKey().length()))
                longestName = length;
        }
        return longestName;
    }

    // Error printing method, since the same error is printed more than once.
    private static void error() {
        System.out.print("\u001B[31mInvalid input!\u001B[0m ");
    }

    // Prompt the user for a student's name.
    private static String getName() {
        System.out.print("Please enter the student's name: ");
        String input = SCANNER.nextLine();
        if (!input.equals("-1")) {
            if (!input.equals(""))
                return input;
            else {
                error();
                return getName();
            }
        } else return null;
    }

    // Prompt the user for a student's grade.
    private static Double getGrade() {
        System.out.print("Please enter the student's grade: ");
        String input = SCANNER.nextLine();
        if (input.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) // Source: http://www.regular-expressions.info/floatingpoint.html
            return Double.parseDouble(input);
        else {
            error();
            return getGrade();
        }
    }

    public static void main(String[] args) {
        // Only allow exits during student name entry to prevent incomplete data.
        System.out.println("Please enter \"-1\" as a student's name to exit!");

        // Populate the hashmap, and handle exit key "-1".
        HashMap<String, Double> studentGrades = new HashMap<>();
        boolean flag = false;
        while (!flag) {
            String name = getName();
            if (name != null)
                studentGrades.put(name, getGrade());
            else flag = true;
        }

        // Unreadable spaghetti code for pretty box output go brrr.
        int nameLength = longestName(studentGrades);
        StringBuilder paddingBuilder = new StringBuilder();
        for (int i = 0; i < nameLength + 3; i++) paddingBuilder.append("─");
        String namePadding = paddingBuilder.toString(), scorePadding = "────────", gradePadding = "───────";
        StringBuilder output = new StringBuilder("┌").append(namePadding).append("┬").append(scorePadding).append("┬").append(gradePadding).append("┐\n")
                .append("│ ").append(String.format("%-" + (nameLength + 1) + "s", "Name")).append(" │ Score  │ Grade │\n")
                .append("├").append(namePadding).append("┼").append(scorePadding).append("┼").append(gradePadding).append("┤\n");
        for (Map.Entry<String, Double> entry : studentGrades.entrySet())
            output.append("│ ").append(String.format("%-" + (nameLength + 1) + "s", entry.getKey())).append(" │ ").append(String.format("%-6s", entry.getValue())).append(" │ ").append(String.format("%-5s", getLetterGrade(entry.getValue()))).append(" │\n");
        output.append("└").append(namePadding).append("┴").append(scorePadding).append("┴").append(gradePadding).append("┘");

        System.out.print(output);
    }

}
