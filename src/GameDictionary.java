import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class GameDictionary {
    public GameDictionary() {
    }

    private String inputUrl;
    private Path filePath;
    private String fileName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileName() {
        return fileName;
    }
    public ArrayList<String> dictionary;

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFilePath() {
        return filePath;
    }

    public String getInputUrl() {
        return inputUrl;
    }
    public void setInputUrl(String inputUrl) {
        this.inputUrl = inputUrl;
    }

    public void createFileFromUrl() {

        try (BufferedInputStream input = new BufferedInputStream(new URI(getInputUrl()).toURL().openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(filePath.toString())) {
            int bytesRead;
            byte[] dataBuffer = new byte[1024];
            while ((bytesRead = input.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException | URISyntaxException e) {
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
                normDictionary.add(word.toLowerCase());
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

    public void createDictionaryFromFile(Path filePath, Integer minWordLength) {
        ArrayList<String> dictionary = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(filePath);
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
