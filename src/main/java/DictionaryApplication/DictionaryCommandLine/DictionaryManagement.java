package DictionaryApplication.DictionaryCommandLine;

import java.io.*;

import java.util.*;

public class DictionaryManagement {
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
				word.setWordExplain(meaning);
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
			Map<String, Word> map = new HashMap<String, Word>();
			for (Word word : dictionary) {
				String key = word.getWordTarget();
				if (!map.containsKey(key)) {
					map.put(key , word);
				}
			}
			Collection<Word> unique = map.values();
			dictionary.clear();
			dictionary.addAll(unique);
			dictionary.sort(new SortDictionaryByWord());

			FileWriter fileWriter = new FileWriter(path);
			BufferedWriter buf = new BufferedWriter(fileWriter);
			// write to file from current dictionary
			for (Word word : dictionary) {
				buf.write("|" + word.getWordTarget() + "\n" + word.getWordExplain());
			}
			buf.close();
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e);
		}
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
//    public int searchWordByTrie(String)

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
			exportToFile(dictionary , path);
		} catch (NullPointerException e) {
			System.out.println("Null Exception.");
		}
	}

	public void addWord( Word word , String path ) {
		try (FileWriter fileWriter = new FileWriter(path , true);
			 BufferedWriter buf = new BufferedWriter(fileWriter)) {
			buf.write("\n" + "|" + word.getWordTarget() + "\n" + word.getWordExplain());
		} catch (IOException e) {
			System.out.println("IOException.");
		} catch (NullPointerException e) {
			System.out.println("Null Exception.");
		}
	}
}
