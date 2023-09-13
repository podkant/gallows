import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class GameDictionary {
    public GameDictionary() {
    }

    private String inputUrl;
    private String filePath;
    public ArrayList<String> dictionary;

    public String getInputUrl() {
        return inputUrl;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setInputUrl(String inputUrl) {
        this.inputUrl = inputUrl;
    }

    public void createFileFromUrl(String URL, String filePath) {

        try (BufferedInputStream input = new BufferedInputStream(new URL(inputUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            int bytesRead;
            byte[] dataBuffer = new byte[1024];
            while ((bytesRead = input.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private ArrayList<String> normalizeDictionary(ArrayList<String> dictionary) {
        ArrayList<String> normDictionary = new ArrayList<>();
        for (String word : dictionary
        ) {
            
            char[] chars = word.toCharArray();
            boolean normCheck = true;
            for (char c : chars) {
                if (!Character.isLetter(c)) {
                    normCheck=false;
                }
            }
            if (normCheck){
                normDictionary.add(word);
            }
        }
        return normDictionary;
    }

    public void createDictionaryFromFile(String filePath) {
        ArrayList<String> dictionary = new ArrayList<>();
        Path path = Path.of(filePath);
        try {
            dictionary.add(Files.readString(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.dictionary = normalizeDictionary(dictionary);
    }

    public void createDictionaryFromFile(String filePath, Integer minWordLength) {
        ArrayList<String> dictionary = new ArrayList<>();
        Path path = Path.of(filePath);
        try {
            List<String> allLines = Files.readAllLines(path);
            for (String line : allLines
            ) {
                if (line.length() >= minWordLength) {
                    dictionary.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.dictionary = normalizeDictionary(dictionary);
    }

}
