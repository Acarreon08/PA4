// Name: Anthony Carreon
// USC NetID: Carreona
// CS 455 PA4
// Fall 2022

import java.util.*;

public class ScoreTable {
   private ArrayList<String> words;
   private String inputWord;
   private final Integer ONE = 1;
   private final Integer TWO = 2;
   private final Integer THREE = 3;
   private final Integer FOUR = 4;
   private final Integer FIVE = 5;
   private final Integer EIGHT = 8;
   private final Integer TEN = 10;
   private Map<Character, Integer> charScores;
   private Map<String, Integer> setOfWords;
   public ScoreTable(ArrayList<String> words, String word){
     setOfWords = new TreeMap<String, Integer>();
     charScores = new HashMap<Character, Integer>();
     setCharValues();
     inputWord = word;
     for (int i = 0; i < words.size(); i++) {
        setOfWords.put(words.get(i),getScore(words.get(i)));
     }
   }

   private void setCharValues() {
      for (char singleChar = 'a' ; singleChar <= 'z'; singleChar++) {
         if (singleChar == 'a' ||singleChar == 'e' ||singleChar == 'i' ||singleChar == 'o' || singleChar == 'u' ||singleChar == 'l' ||
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

   public int getScore(String word){
      int score = 0;
      for (int i = 0; i < word.length(); i++) {
         score+= charScores.get(word.charAt(i));
      }
      return score;
   }

   public void printScores(){
      System.out.println("We can make " + setOfWords.size() + " words from \"" + inputWord + "\"" );
      System.out.println("All of the words with their scores (sorted by score):");

      List<Map.Entry<String, Integer>> list = new ArrayList<>(setOfWords.entrySet());
      Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
         @Override
         public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            if (o1.getValue() > o2.getValue()) {
               return -1;
            } else if (o1.getValue() == o2.getValue()){
               return 0;
            } else {
               return 1;
            }
         }
      });
      for (Map.Entry<String, Integer> curr : list) {
         System.out.println(curr.getValue() + ": " + curr.getKey() );

      }

   }
}

