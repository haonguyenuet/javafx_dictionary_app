package DictionaryApplication.DictionaryCommandLine;

import java.util.Comparator;

public class SortDictionaryByWord implements Comparator<Word> {
	@Override
	public int compare( Word w1 , Word w2 ) {
		return w1.getWordTarget().compareTo(w2.getWordTarget());
	}
}
