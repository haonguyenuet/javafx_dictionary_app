package DictionaryApplication.Trie;
import java.util.*;

public class Trie {

	protected final Map<Character, Trie> children;
	protected String content;
	protected boolean terminal = false;

	public Trie() {
		this(null);
	}

	private Trie(String content) {
		this.content = content;
		children = new HashMap<Character, Trie>();
	}

	//method to append character
	protected void add(char character) {
		String s;
		if (this.content == null) {
			s = Character.toString(character);
		} else {
			s = this.content + character;
		}
		children.put(character, new Trie(s));
	}


	//method for inserting a new diagnosis
	public void insert(String diagnosis) {
		if (diagnosis == null) {
			throw new IllegalArgumentException("Null diagnoses entries are not valid.");
		}
		Trie node = this;
		for (char c : diagnosis.toCharArray()) {
			if (!node.children.containsKey(c)) {
				node.add(c);
			}
			node = node.children.get(c);
		}
		node.terminal = true;
	}

	public List<String> autoComplete(String prefix) {
		Trie Trienode = this;
		for (char c : prefix.toCharArray()) {
			if (!Trienode.children.containsKey(c)) {
				return null;
			}
			Trienode = Trienode.children.get(c);
		}
		return Trienode.allPrefixes();
	}

	protected List<String> allPrefixes() {
		List<String> diagnosisresults = new ArrayList<String>();
		if (this.terminal) {
			diagnosisresults.add(this.content);
		}
		for (Map.Entry<Character, Trie> entry : children.entrySet()) {
			Trie child = entry.getValue();
			Collection<String> childPrefixes = child.allPrefixes();
			diagnosisresults.addAll(childPrefixes);
		}
		return diagnosisresults;
	}

}