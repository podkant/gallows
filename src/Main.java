import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        GameDictionary noun = new GameDictionary();
        noun.setInputUrl("https://www.desiquintans.com/downloads/nounlist/nounlist.txt");
        noun.setFileName("noun_dictionary");
        noun.setFilePath(Files.createTempFile(noun.getFileName(),".txt"));
        noun.createFileFromUrl();
        noun.createDictionaryFromFile(noun.getFilePath(),7);


        Gameplay gameplay = new Gameplay();
        if(gameplay.initialization()){
            String wordForGame= gameplay.randomizationWord(noun);
            System.out.println(gameplay.displayHiddenWord(wordForGame));
            gameplay.checkLetter();
        }

    }
}