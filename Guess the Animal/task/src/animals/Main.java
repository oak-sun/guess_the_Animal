package animals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final List<String> yes = Arrays.asList(
            "y", "yes", "yeah", "yep", "sure",
            "right", "affirmative", "correct", "indeed",
            "you bet", "exactly", "you said it");
    private static final List<String> no = Arrays.asList(
            "n", "no", "no way", "nah", "nope",
            "negative", "i don't think so", "yeah no");
    private static final List<String> yesOrNo =
            Arrays.asList("Come on, yes or no!",
                    "Please confirm, yes or no!",
                    "Please simplify, yes or no!",
                    "Sorry, i don't understand, yes or no");
    private static final List<String> adios = Arrays.asList(
            "See you soon!",
            "Have a nice day!",
            "Goodbye!");
    private static final Random rndm = new Random();
    private static final List<String> animals = new ArrayList<>();
    private static final Map<String, String> animalFacts =
            new LinkedHashMap<>();
    private static final Scanner sc = new Scanner(System.in);
    private static final List<String> can = Arrays.asList(
            "it can",
            "it has",
            "it is");
    private static final List<String> cannot = Arrays.asList(
            "it can't",
            "it doesn't have",
            "it isn't");
    private static final List<String> question = Arrays.asList(
            "Can it",
            "Does it have",
            "Is it");
    private static final List<String> answer = Arrays.asList(
            "It can fly",
            "It has horn",
            "It is a mammal");
    private static final List<String> checkList =
            new ArrayList<>();

    public static void main(String... args) {
        for (int i = 0; i < 2; i++) {
            animal();
        }
        checkAnswer();
        adios();
    }

    public static void animal() {
        System.out.println(
                animals.isEmpty() ?
                "Enter the first animal:" :
                "Enter the second animal:");
        var input = sc.nextLine();
        var arr = input.split(" ");
        var animal = "";
        if (arr.length > 1 && Arrays
                        .asList(new String[]{"a", "an", "the"})
                        .contains(arr[0].toLowerCase())) {
            animal = input
                    .toLowerCase()
                    .substring(arr[0].length() + 1);
        } else {
            animal = input.toLowerCase();
        }
        if (Arrays.asList(
                new Character[]
                {'a', 'e', 'i', 'o', 'u'})
                .contains(animal.toCharArray()[0]) ||
                animal.contains("xe")) {
            animal = "an " + animal;
        } else {
            animal = "a " + animal;
        }
        animals.add(animal);
    }
    public static void checkAnswer() {
        System.out.println(
                "Specify a fact that distinguishes " +
                animals.get(0) +
                " from " +
                animals.get(1) +
                ".");
        System.out.println(
                "The sentence should be of the format:" +
                " 'It can/has/is ...'");
        System.out.println();
        var input = sc.nextLine();
        while (!input
                .toLowerCase()
                .matches("(it has [\\w\\s]+[.]*" +
                        "[?]*!*)|(it can [\\w\\s]+[.]" +
                        "*[?]*!*)|(it is [\\w\\s]+" +
                        "[.]*[?]*!*)")) {
            System.out.println("The examples of a statement:");
            answer.forEach(System.out::println);
            System.out.println(
                    "Specify a fact that distinguishes " +
                    animals.get(0) +
                    " from " +
                    animals.get(1) +
                    ".");
            System.out.println(
                    "The sentence should be of the" +
                    " format: 'It can/has/is ...'");
            input = sc.nextLine();
        }
        System.out.println(
                "correct for " +
                        animals.get(1) +
                        "?");
        var negate = userAnswer();
        input.replaceAll("\\.", "");
        animalFacts.put(animals.get(0),
                        output(input, negate));
        animalFacts.put(animals.get(1),
                        output(input, !negate));
        question(input);
        System.out.println(
                "I learned the following facts about animals:");
        animalFacts.forEach((k, v) -> System.out.println(
                "The " +
                k.split(" ")[1] +
                " " +
                v +
                "."));
        System.out.println("I can distinguish these animals" +
                " by asking the question:");
        checkList.forEach(System.out::println);

    }

    public static boolean userAnswer() {
        var input = sc.nextLine().trim();
        if (input.lastIndexOf(".") == input.length() - 1
                || input.lastIndexOf("!") == input.length() - 1) {
            input = input.substring(0, input.length() - 1);
        }
        boolean answer;
        while (true) {
            if (yes.contains(input.toLowerCase())) {
                answer = true;
                break;
            } else if (no.contains(input.toLowerCase())) {
                answer = false;
                break;
            } else {
                System.out.println("Please answer yes or no");
                System.out.println(yesOrNo.get(
                                rndm.nextInt(4)));
                input = sc.nextLine().trim();
                if (input.lastIndexOf(".") ==
                        input.length() - 1
                        || input.lastIndexOf("!") ==
                        input.length() - 1) {
                    input = input
                            .substring(0, input.length() - 1);
                }
            }
        }
        return answer;
    }

    public static String output(String input,
                                     boolean negate) {
        for (int i=0; i< can.size(); i++) {
            if (input.toLowerCase().contains(can.get(i))) {
                if (negate) {
                    input = input
                            .toLowerCase()
                            .replaceAll(can.get(i),
                                       cannot.get(i));
                }
                break;
            }
        }
        return input.substring(3);
    }

    public static void question(String input) {
        for (int i=0; i< can.size(); i++) {
            if (input
                    .toLowerCase()
                    .contains(can.get(i))) {
                input = input
                        .toLowerCase()
                        .replaceAll(can.get(i),
                                    question.get(i));
                break;
            }
        }
        checkList.add(input + "?");
    }

    public static void adios() {
        System.out.println();
        System.out.println(adios.get(rndm.nextInt(3)));
    }
}