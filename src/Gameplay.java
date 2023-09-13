import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Gameplay {

    private String hiddenWord;

    public String getHiddenWord() {
        return hiddenWord;
    }

    public char[] currentProgress;

    public boolean initialization() throws IOException {
        boolean startGame;
        BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));
        System.out.println("Start new game? y/n");
        while (true) {
            String string = reader.readLine();
            if (string.equalsIgnoreCase("Y")) {
                startGame = true;
                System.out.println("Starting new game");
                break;
            } else if (string.equalsIgnoreCase("N")) {
                startGame = false;
                System.out.println("Exiting program");
                break;
            } else {
                System.out.println("Unsupported symbol, please enter y/n");
            }
        }


        return startGame;
    }

    public String randomizationWord(GameDictionary gameDictionary) {

        Random rand = new Random();
        int n = rand.nextInt(gameDictionary.dictionary.size());
        hiddenWord = gameDictionary.dictionary.get(n);
        return gameDictionary.dictionary.get(n);

    }

    public String displayHiddenWord(String word) {
        System.out.println("Hidden word:");
        StringBuilder builtWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            builtWord.append("_");
        }
        currentProgress = builtWord.toString().toCharArray();
        return builtWord.toString();
    }

    public boolean isAlpha(String string) {
        char[] chars = string.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean isContains(String word, char letter) {
        char[] chars = word.toCharArray();

        for (char c : chars) {
            if (c == letter) {
                return true;
            }
        }
        return false;
    }

    public void checkLetter() {
        String imputString;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            //Get string from console and check is it
            // 1)one symbol 2)actual letter 3) wasn't checked already 4) is the letter included in the word?
            //Mistake count equal 6 because we have 6 gallows position
            System.out.println("Enter next letter");

            int mistakeCount = 0;
            HashSet<Character> usedChars = new HashSet<>();
            while (mistakeCount < 6) {
                imputString = reader.readLine();
                if (imputString.length() != 1) {
                    System.out.println("Enter 1 letter");
                } else if (isAlpha(imputString)) {
                    char currentChar = imputString.toCharArray()[0];
                    if (usedChars.contains(currentChar)) {
                        System.out.println("Symbol was checked already. Enter another letter");
                        continue;
                    } else {
                        usedChars.add(currentChar);
                    }
                    if (isContains(hiddenWord, currentChar)) {
                        for (int i = 0; i < hiddenWord.length(); i++) {
                            if (hiddenWord.toCharArray()[i] == currentChar) {
                                currentProgress[i] = currentChar;
                            }
                        }
                        System.out.println("You are right! " + Arrays.toString(currentProgress));
                    } else {
                        System.out.println("No letter " + currentChar + " in secret word" + Arrays.toString(currentProgress));
                        System.out.println(Status.getByVal(mistakeCount).getStatus());
                        mistakeCount++;
                    }
                } else {
                    System.out.println("Enter Latin letter");
                }
                if (!isContains(Arrays.toString(currentProgress), '_')) {
                    System.out.println("You won!");
                    break;
                }
                if (mistakeCount == 6) {
                    System.out.println("You lost");
                    System.out.println("Hidden word was "+hiddenWord);
                }
            }


        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            throw new RuntimeException(e);
        }


    }
}
