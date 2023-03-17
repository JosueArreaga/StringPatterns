package util;

public class ArithmeticExeption extends Exception{

    private String ArithmeticString;
    private int occurrenceIndex;

    @Override
    public String getMessage() {
        return ArithmeticString + " is a positive Arithmetic string that is found at index " + occurrenceIndex + "!";
    }
    public ArithmeticExeption(String singletonString, int index) {
        this.ArithmeticString = singletonString;
        occurrenceIndex = index;
    }

    public String getArithmeticString;

}
