package util;

public class NegativeArithmeticExeption extends Exception {

    private String negativeArithmeticString;
    private int occurrenceIndex;
    @Override
    public String getMessage() {
        return negativeArithmeticString + " is a negative Arithmetic string that is found at index " + occurrenceIndex + "!";
    }
    public NegativeArithmeticExeption(String singletonString, int index) {
        this.negativeArithmeticString = singletonString;
        occurrenceIndex = index;
    }

    public String getNegativeArithmeticString;
}
