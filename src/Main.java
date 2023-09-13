import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GameDictionary noun = new GameDictionary();
        noun.setInputUrl("https://www.desiquintans.com/downloads/nounlist/nounlist.txt");
        noun.setFilePath("src\\main\\resources\\noun_dictionary.txt");
        noun.createFileFromUrl(noun.getInputUrl(),noun.getFilePath());
        noun.createDictionaryFromFile(noun.getFilePath(),7);
//        noun.dictionary.forEach(System.out::println);



        Gameplay gameplay = new Gameplay();
        if(gameplay.initialization()){
            String wordForGame= gameplay.randomizationWord(noun);
            System.out.println(gameplay.displayHiddenWord(wordForGame));
//            System.out.println(gameplay.getHiddenWord());
            gameplay.checkLetter();
        }

    }
}