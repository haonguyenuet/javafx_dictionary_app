package DictionaryApplication.DictionaryCommandLine;

import java.util.Objects;

public class Word {
    //    props
    private String wordTarget;
    private String wordExplain;

    //    constructor
    public Word( String english , String vietnamese ) {
        this.wordExplain = vietnamese;
        this.wordTarget = english;
    }
    public Word() {
        this.wordExplain = "";
        this.wordTarget = "";
    }

    //    Setter and Getter
    public void setWordExplain( String wordExplain ) {
        this.wordExplain = wordExplain;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public void setWordTarget( String wordTarget ) {
        this.wordTarget = wordTarget;
    }

    public String getWordTarget() {
        return wordTarget;
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word = (Word) o;
        return Objects.equals(wordTarget , word.wordTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordTarget , wordExplain);
    }

    @Override
    public String toString() {
        return "Word{" +
                "wordTarget='" + wordTarget + '\'' +
                ", wordExplain='" + wordExplain + '\'' +
                '}';
    }
}
