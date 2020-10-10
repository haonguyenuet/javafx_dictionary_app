package DictionaryCommandLine;

public class Test {

	public static void main( String[] args ) {
		// declare dictionary
		Dictionary dictionary = new Dictionary();

		DictionaryCommandline dicCML = new DictionaryCommandline();
		dicCML.dictionaryBasic().insertFromFile(dictionary , "src\\Dictionary\\myFile.txt");
		dicCML.dictionaryBasic().insertFromCommandline(dictionary);
		dicCML.dictionaryBasic().exportToFile(dictionary , "src\\Dictionary\\myFile.txt");
	}

}
