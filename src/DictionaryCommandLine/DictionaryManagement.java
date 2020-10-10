package DictionaryCommandLine;

import java.io.*;
import java.util.*;


public class DictionaryManagement {
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Insert from command line
     */
    public void insertFromCommandline(Dictionary dictionary) {
        System.out.print("Nhap so luong tu vung: ");
        int numOfWords = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numOfWords; i++) {
            System.out.print("Nhap tu tieng anh: ");
            String englishWord = scanner.nextLine();
            System.out.print("Giai nghia tieng viet: ");
            String explanation = scanner.nextLine();
            Word newWord = new Word(englishWord, explanation);
            dictionary.add(newWord);
        }
    }

    /**
     * Insert from text file use BufferedReader
     */
    public void insertFromFile(Dictionary dictionary, String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader buf = new BufferedReader(fileReader);

            String line;
            while ((line = buf.readLine()) != null) {
                String[] words = line.split("\t");
                // init new word
                String englishWord = words[0];
                String explanation = words[1];
                Word newWord = new Word(englishWord, explanation);
                dictionary.add(newWord);
            }
            // close file
            buf.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public void exportToFile(Dictionary dictionary, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter buf = new BufferedWriter(fileWriter);
            // write to file from current dictionary
            for (Word word : dictionary) {
                buf.write(word.getWordTarget() + "\t" + word.getWordExplain());
                buf.newLine();
            }
            buf.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }

    }

    public void addNewWord(Dictionary dictionary) {
        try {
            System.out.print("Nhap tu tieng anh: ");
            String englishWord = scanner.nextLine();
            System.out.print("Giai nghia tieng viet: ");
            String explanation = scanner.nextLine();
            dictionary.add(new Word(englishWord, explanation));
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }
    public void updateWord(Dictionary dictionary, String word) {

    }

    public void deleteWord(Dictionary dictionary, String word) {

    }

    /**
     * Search words that contain the searchKey
     */
    public void dictionarySearcher(Dictionary dictionary) {
        System.out.println("Nhap tu can tim kiem: ");
        String searchKey = scanner.nextLine();
        List<String> searchedWord = new ArrayList<>();
        for (Word word : dictionary) {
            String englishWord = word.getWordTarget();
            if (englishWord.toLowerCase().contains(searchKey.toLowerCase())) {
                searchedWord.add(englishWord);
            }
        }
        for (String word : searchedWord) {
            System.out.println(word);
        }
    }
}
