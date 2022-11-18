// Name: Anthony Carreon
// USC NetID: Carreona
// CS 455 PA4
// Fall 2022

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordFinder {
   public static void main(String[] args) throws IllegalDictionaryException, FileNotFoundException {
      Scanner in = new Scanner(System.in);
      AnagramDictionary anagrams = new AnagramDictionary("/Users/anthony/IdeaProjects/PA4/src/sowpods.txt");
      Rack rack = new Rack();
      ArrayList<String> listOfInput = new ArrayList<String>();
      boolean done = false;
      while (!done) {
         String word = in.next();
         done = checkInput(listOfInput, done, word);
      }
      getResults(anagrams, rack, listOfInput);
   }

   private static boolean checkInput(ArrayList<String> listOfInput, boolean done, String word) {
      if (word.startsWith(".")){
         done = true;
      }
      else{
         String trimWord = word.trim().replaceAll("[0-9$&+,:;=?@#|'<>.^*()%!-]", "");
         listOfInput.add(trimWord);

      }
      return done;
   }

   private static void getResults(AnagramDictionary anagrams, Rack rack, ArrayList<String> listOfInput) {
      System.out.println("Type . to quit.");
      for (int i = 0; i < listOfInput.size(); i++) {
         anagrams.setRack(rack.countLetters(listOfInput.get(i)));
         ScoreTable scores = new ScoreTable(anagrams.getAnagramsOf(listOfInput.get(i)), listOfInput.get(i));
         scores.printScores();
         anagrams.clearRack();
      }
      System.out.println("Rack? ");
   }

}