package DictionaryApplication.DictionaryCommandLine;

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
    public boolean equals( Object obj ) {
        boolean result = false;
        if (obj instanceof Word) {
            Word temp = (Word) obj;
            result = this.getWordTarget().equals(temp.getWordTarget())
                    && this.getWordExplain().equals(temp.getWordExplain());
        }
        return result;
    }
}
