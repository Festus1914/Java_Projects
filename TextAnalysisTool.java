import java.util.*;

public class TextAnalysisTool {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User Input
        System.out.println("Enter a paragraph or a lengthy text:");
        String inputText = scanner.nextLine();

        // Character Count
        int charCount = inputText.length();
        System.out.println("Total number of characters: " + charCount);

        // Word Count
        String[] words = inputText.trim().split("\\s+");
        int wordCount = words.length;
        System.out.println("Total number of words: " + wordCount);

        // Most Common Character
        char mostCommonChar = findMostCommonCharacter(inputText);
        System.out.println("Most common character: " + mostCommonChar);

        // Character Frequency
        System.out.println("Enter a character to find its frequency:");
        char charToFind = scanner.next().charAt(0);
        int charFrequency = findCharacterFrequency(inputText, charToFind);
        System.out.println("Frequency of '" + charToFind + "': " + charFrequency);

        scanner.nextLine(); // Clear buffer

        // Word Frequency
        System.out.println("Enter a word to find its frequency:");
        String wordToFind = scanner.nextLine();
        int wordFrequency = findWordFrequency(words, wordToFind);
        System.out.println("Frequency of '" + wordToFind + "': " + wordFrequency);

        // Unique Words Count
        int uniqueWordsCount = countUniqueWords(words);
        System.out.println("Number of unique words: " + uniqueWordsCount);

        scanner.close();
    }

    private static char findMostCommonCharacter(String text) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : text.toLowerCase().toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                charMap.put(c, charMap.getOrDefault(c, 0) + 1);
            }
        }
        return Collections.max(charMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private static int findCharacterFrequency(String text, char character) {
        int count = 0;
        char targetChar = Character.toLowerCase(character);
        for (char c : text.toLowerCase().toCharArray()) {
            if (c == targetChar) {
                count++;
            }
        }
        return count;
    }

    private static int findWordFrequency(String[] words, String word) {
        int count = 0;
        String targetWord = word.toLowerCase();
        for (String w : words) {
            if (w.equalsIgnoreCase(targetWord)) {
                count++;
            }
        }
        return count;
    }

    private static int countUniqueWords(String[] words) {
        Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            uniqueWords.add(word.toLowerCase());
        }
        return uniqueWords.size();
    }
}
