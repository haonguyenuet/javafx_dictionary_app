package DictionaryApplication.DictionaryCommandLine;

import DictionaryApplication.Trie.Trie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

import java.util.*;

public class DictionaryManagement {
	private Trie trie = new Trie();

	/**
	 * Insert from text file use BufferedReader
	 */
	public void insertFromFile( Dictionary dictionary , String path ) {
		try {
			FileReader fileReader = new FileReader(path);
			BufferedReader buf = new BufferedReader(fileReader);
			//store first value of english word
			String englishWord = buf.readLine();
			englishWord = englishWord.replace("|" , "");
			String line;

			while ((line = buf.readLine()) != null) {
				Word word = new Word();
				word.setWordTarget(englishWord);
				// initialize meaning
				String meaning = line + "\n";
				while ((line = buf.readLine()) != null) {
					if (!line.startsWith("|")) meaning += line + "\n";
					else {
						englishWord = line.replace("|" , "");
						break;
					}
				}
				word.setWordExplain(meaning.trim());
				dictionary.add(word);
			}
			// close file
			buf.close();
		} catch (IOException e) {
			System.out.println("An error occur with file: " + e);
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e);
		}
	}

	public void exportToFile( Dictionary dictionary , String path ) {
		try {
			FileWriter fileWriter = new FileWriter(path);
			BufferedWriter buf = new BufferedWriter(fileWriter);
			// write to file from current dictionary
			for (Word word : dictionary) {
				buf.write("|" + word.getWordTarget() + "\n" + word.getWordExplain());
				buf.newLine();
			}
			buf.close();
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e);
		}
	}

	/**
	 * using trie algorithm for search prefix
	 */
	public ObservableList<String> lookupWord( Dictionary dictionary , String key ) {
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			List<String> results = trie.autoComplete(key);
			if (results != null) {
				int length = Math.min(results.size() , 15);
				for (int i = 0; i < length; i++) {
					list.add(results.get(i));
				}
			}
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e);
		}
		return list;
	}

	/**
	 * search for a word by BinarySearch
	 * time complexity: O(logN)
	 */
	public int searchWord( Dictionary dictionary , String keyword ) {
		try {
			dictionary.sort(new SortDictionaryByWord());
			int left = 0;
			int right = dictionary.size() - 1;
			while (left <= right) {
				int mid = left + (right - left) / 2;
				int res = dictionary.get(mid).getWordTarget().compareTo(keyword);
				if (res == 0) {
					return mid;
				}
				if (res <= 0) {
					left = mid + 1;

				} else {
					right = mid - 1;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Null Exception.");
		}
		return -1;
	}

	public void updateWord( Dictionary dictionary , int index , String meaning , String path ) {
		try {
			dictionary.get(index).setWordExplain(meaning);
			exportToFile(dictionary , path);
		} catch (NullPointerException e) {
			System.out.println("Null Exception.");
		}
	}

	public void deleteWord( Dictionary dictionary , int index , String path ) {
		try {
			dictionary.remove(index);
			trie = new Trie();
			setTrie(dictionary);
			exportToFile(dictionary , path);
		} catch (NullPointerException e) {
			System.out.println("Null Exception.");
		}
	}

	public void addWord( Word word , String path ) {
		try (FileWriter fileWriter = new FileWriter(path , true);
			 BufferedWriter buf = new BufferedWriter(fileWriter)) {
			buf.write("|" + word.getWordTarget() + "\n" + word.getWordExplain());
			buf.newLine();
		} catch (IOException e) {
			System.out.println("IOException.");
		} catch (NullPointerException e) {
			System.out.println("Null Exception.");
		}
	}

	// async
	public void setTimeout( Runnable runnable , int delay ) {
		new Thread(() -> {
			try {
				Thread.sleep(delay);
				runnable.run();
			} catch (Exception e) {
				System.err.println(e);
			}
		}).start();
	}

	// insert dictionary to trie
	public void setTrie( Dictionary dictionary ) {
		try {
			for (Word word : dictionary) {
				trie.insert(word.getWordTarget());
			}
		} catch (NullPointerException e) {
			System.out.println("Something went wrong: " + e);
		}
	}
}
