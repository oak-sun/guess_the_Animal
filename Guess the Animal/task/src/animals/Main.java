package animals;


import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> yes = List.of(
                "y", "yes", "yeah", "yep",
                        "sure", "right", "affirmative", "correct",
                        "indeed", "youbet", "exactly", "yousaidit");
        List<String> no = List.of(
                "n", "no", "noway", "nah", "nope",
                "negative", "Idontthinkso", "yeahno");
        List<String> adios = List.of(
                "Have a great day!", "Bye now!", "See you next time!",
                "Love ya, bye!", "Bye..", "Goodbye", "Sayonara!",
                "Adios!");
        List<String> yesOrNo = List.of(
                "I'm not sure I caught you: was it yes or no?",
                "Funny, I still don't understand, is it yes or no?",
                "Oh, it's too complicated for me: just tell me yes or no.",
                "Could you please simply say yes or no?",
                "Oh, no, don't try to confuse me: say yes or no.");
        var time = LocalTime.now();
        System.out.println(
                time.isBefore(LocalTime.NOON) ?
                "Good morning!" :
                time.isAfter(LocalTime.of(18, 0)) ?
                        "Good evening!" : "Good Afternoon!");
        System.out.println("\nEnter an animal:");
        var sc = new Scanner(System.in);
        var animal = sc
                            .nextLine()
                            .toLowerCase();
        if (animal.startsWith("a")
                || animal.startsWith("an")
                || animal.startsWith("the")) {
            animal = animal
                    .replaceFirst("an? |the ", "");
        }
        System.out.println(
                "Is it " +
                (animal.matches("[aeio]\\w*|xe\\w*") ?
                        "an " :
                        "a ") + animal + "?");
        var obj = new Object() {
            String answer = sc
                    .nextLine()
                    .replaceAll("\\s", "")
                    .replaceFirst("[!.?]", "");
        };
        while (yes.stream().noneMatch(o ->
                        o.equalsIgnoreCase(obj.answer))
                &&
                no.stream().noneMatch(o ->
                        o.equalsIgnoreCase(obj.answer))) {
            System.out.println(
                    yesOrNo.get(
                            new Random().nextInt(yesOrNo.size())));
            obj.answer = sc
                    .nextLine()
                    .replaceAll("\\s", "")
                    .replaceFirst("[!.?]", "");
        }
        System.out.print("You answered: ");
        if (yes.stream().anyMatch(o ->
                o.equalsIgnoreCase(obj.answer))) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        System.out.println("\n" + adios.get(
                        new Random().nextInt(adios.size())));

    }
}