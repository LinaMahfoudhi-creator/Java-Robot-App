import java.util.Random;
public abstract class RandomGenerator {
    public static String idGenerator(){
        Random rand = new Random();

        // Random uppercase letter + digit
        char firstLetter = (char) ('A' + rand.nextInt(26));
        int firstNumber = rand.nextInt(10);

        // Random uppercase letter + digit
        char secondLetter = (char) ('A' + rand.nextInt(26));
        int secondNumber = rand.nextInt(10);

        return "" + firstLetter + firstNumber + "-" + secondLetter + secondNumber;
    }
}
