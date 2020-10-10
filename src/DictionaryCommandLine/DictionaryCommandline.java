package DictionaryCommandLine;

public class DictionaryCommandline {
    public void showAllWords(Dictionary dictionary) {
       try{
           System.out.println("No" + "\t |" + "English" + "\t |" + "Vietnamese");
           for (int i = 0; i < dictionary.size(); i++) {
               System.out.println(i + 1 + "\t |" + dictionary.get(i).getWordTarget() + "\t\t |" + dictionary.get(i).getWordExplain());
           }
       }catch (NullPointerException e) {
           System.out.println("Something went wrong: " + e);
       }
    }

    public DictionaryManagement dictionaryBasic(){
        return new DictionaryManagement();
    }
}