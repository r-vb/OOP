import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class InvalidCharacterException extends Exception {
    public InvalidCharacterException(String message) {
        super(message);
    }
}

public class FileParsingExample {
    public static void main(String[] args) {
        String sourceFileName = "alphabets.txt";
        String consonantsFileName = "consonants.txt";
        String vowelsFileName = "vowels.txt";

        try {
            File sourceFile = new File(sourceFileName);
            FileReader fileReader = new FileReader(sourceFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            File consonantsFile = new File(consonantsFileName);
            FileWriter consonantsFileWriter = new FileWriter(consonantsFile);

            File vowelsFile = new File(vowelsFileName);
            FileWriter vowelsFileWriter = new FileWriter(vowelsFile);

            int charInt;
            while ((charInt = bufferedReader.read()) != -1) {
                char character = (char) charInt;
                if (Character.isLetter(character)) {
                    if (isVowel(character)) {
                        vowelsFileWriter.write(character);
                    } else {
                        consonantsFileWriter.write(character);
                    }
                } else if (Character.isDigit(character)) {
                    throw new InvalidCharacterException("Invalid character (digit) found in the file: " + character);
                }
            }

            bufferedReader.close();
            consonantsFileWriter.close();
            vowelsFileWriter.close();

            System.out.println("Files created successfully.");
        } catch (IOException e) {
            System.err.println("Error occurred while reading/writing files: " + e.getMessage());
        } catch (InvalidCharacterException e) {
            System.err.println(e.getMessage());
        }
    }

    private static boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
