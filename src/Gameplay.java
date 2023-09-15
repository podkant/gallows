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
    private final HashSet<Character> usedChars = new HashSet<>();


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
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                return true;
            }
        }
        return false;
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

    public boolean isInputValid(String inputString) {
        //Check: is current symbol latin letter?
        if (inputString.length() != 1) {
            System.out.println("Enter 1 letter");
            return false;
        } else if (!isAlpha(inputString)) {
            System.out.println("Enter Latin letter");
            return false;
        } else
            return true;
    }

    public boolean isNotDuplicated(char currentChar) {
        //Check: is current letter was used early?
        if (usedChars.contains(currentChar)) {
            System.out.println("Symbol was checked already. Enter another letter");
            return false;
        } else {
            usedChars.add(currentChar);
            return true;
        }
    }

    public void checkLetter() {
        String inputString;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            //Get string from console and check is the letter included in the word?
            //Mistake count equal 6 because we have 6 gallows position
            System.out.println("Enter next letter");
            int mistakeCount = 0;
            while (mistakeCount < 6) {
                inputString = reader.readLine();
                char currentChar = inputString.toLowerCase().toCharArray()[0];
                if (isInputValid(inputString) && isNotDuplicated(currentChar)) {
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
                }
                if (!isContains(Arrays.toString(currentProgress), '_')) {
                    System.out.println("You won!");
                    break;
                }
                if (mistakeCount == 6) {
                    System.out.println("You lost");
                    System.out.println("Hidden word was " + getHiddenWord());
                }
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            throw new RuntimeException(e);
        }


    }
}
