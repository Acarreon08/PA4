// Name: Anthony Carreon
// USC NetID: Carreona
// CS 455 PA4
// Fall 2022

import java.util.*;

/**
 * ScoreTable class is used to store alphabetical character values. Also, prints the score of the dictionary words that is
 * added up from its characters.
 */

public class ScoreTable {
   /**
    * Representation invariant:
    * (a <= charScores => z)
    * (setOfWords > 0) only if words are matched
    * (inputWord != null) if a word is matched
    */

   private static final Integer ONE = 1;
   private static final Integer TWO = 2;
   private static final Integer THREE = 3;
   private static final Integer FOUR = 4;
   private static final Integer FIVE = 5;
   private static final Integer EIGHT = 8;
   private static final Integer TEN = 10;
   private Map<Character, Integer> charScores;
   private Map<String, Integer> setOfWords;
   private String inputWord;



   /**
    * ScoreTable constructor makes a TreeMap of Strings and Integers to store the value that the string holds. A HashMap of
    * Characters and Integers stores letters from the alphabet with different scores (final values).
    * @param words ArrayList<String> is the set of words that are matched with the dictionary file.
    * @param word String is the original set of characters (or word).
    */
   public ScoreTable(ArrayList<String> words, String word){
     setOfWords = new TreeMap<String, Integer>();
     charScores = new HashMap<Character, Integer>();
     setCharValues();
     inputWord = word;
     for (int i = 0; i < words.size(); i++) {
        setOfWords.put(words.get(i),getScore(words.get(i)));
     }
   }


   /**
    * getScore method is used to get the total score of each word that input from the method's parameters.
    * @param word String used to scan char by char
    * @return score of the word that is scanned char by char
    */
   public int getScore(String word){
      int score = 0;
      String lowerCase = word.toLowerCase();
      for (int i = 0; i < lowerCase.length(); i++) {
         score+= charScores.get(lowerCase.charAt(i));
      }
      return score;
   }


   /**
    * printScores method is called from the main method of the WordFinder class to get all values of string(s) from dictionary file
    * that is matched with the different substring(s) from the user's input.
    */
   public void printScores(){
      System.out.println("Rack? We can make " + setOfWords.size() + " words from \"" + inputWord + "\"" );
      if (setOfWords.size() > 0){
         System.out.println("All of the words with their scores (sorted by score):");

         List<Map.Entry<String, Integer>> list = new ArrayList<>(setOfWords.entrySet());
         Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  // Comparator class is used to compare the scores of words that were matched from dictionary.
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
               if (o1.getValue() > o2.getValue()) {
                  return -1;        // means first value in Map is smaller than the next value in Map
               } else if (o1.getValue() == o2.getValue()){
                  return 0;         // means first value in Map is the same as the next value in Map
               } else {
                  return 1;         // means first value in Map is larger than the next value in Map
               }
            }
         });
         for (Map.Entry<String, Integer> curr : list) {     // prints the order of words accordingly (higher score to lower score)
            System.out.println(curr.getValue() + ": " + curr.getKey() );

         }
      }
      resetMap();
   }


   /**
    * resetMap method is called from printScores method to reset the setOfScores TreeMap for the next word from user.
    * Meaning, to print a new set of values for the next word from user that matches the words from the dictionary file.
    */
   private void resetMap() {
      setOfWords = new TreeMap<String, Integer>(){};
   }


   /**
    * setCharValues method is used in this class to initialize all letters in the alphabet with values to get a score from a complete word (string).
    */
   private void setCharValues() {
      for (char singleChar = 'a' ; singleChar <= 'z'; singleChar++) {
         if (singleChar == 'a' || singleChar == 'e' ||singleChar == 'i' ||singleChar == 'o' || singleChar == 'u' ||singleChar == 'l' ||
               singleChar == 'n' ||singleChar == 's' ||singleChar == 't' ||singleChar == 'r'){
            charScores.put(singleChar,ONE);
         } else if (singleChar == 'd' || singleChar == 'g'){
            charScores.put(singleChar,TWO);
         } else if (singleChar == 'b' ||singleChar == 'c' ||singleChar == 'm' ||singleChar == 'p'){
            charScores.put(singleChar,THREE);
         } else if (singleChar == 'f' ||singleChar == 'h' ||singleChar == 'v' ||singleChar == 'w' ||singleChar == 'y'){
            charScores.put(singleChar,FOUR);
         } else if (singleChar == 'k'){
            charScores.put(singleChar,FIVE);
         } else if (singleChar == 'j' || singleChar == 'x'){
            charScores.put(singleChar,EIGHT);
         } else {
            charScores.put(singleChar,TEN);
         }
      }
   }
}

