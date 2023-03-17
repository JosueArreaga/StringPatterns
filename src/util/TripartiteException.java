package util;

public class TripartiteException extends Exception{
    private String tripartiteString;
    private int occurrenceIndex;
    @Override
    public String getMessage() {
        return  tripartiteString + " is a tripartite string that is found at index " + occurrenceIndex + "!";
    }
    public TripartiteException(String singletonString, int index) {
        this.tripartiteString = singletonString;
        occurrenceIndex = index;
    }

    public String getTripartiteString;
}
