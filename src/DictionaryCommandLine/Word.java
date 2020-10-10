package DictionaryCommandLine;

public class Word {
    //    props
    private String wordTarget;
    private String wordExplain;

    //    constructor
    public Word( String wordTarget , String wordExplain ) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
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
}
