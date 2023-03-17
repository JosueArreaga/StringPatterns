package util;

public class PalindromeException extends Exception{
    private String palindromeString;
    private int occurrenceIndex;
    @Override
    public String getMessage() {
        return palindromeString + " is a palindrome string that is found at index " + occurrenceIndex + "!";
    }
    public PalindromeException(String singletonString, int index) {
        this.palindromeString = singletonString;
        occurrenceIndex = index;
    }

    public String getPalindromeString;
}
