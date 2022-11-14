// Name: Anthony Carreon
// USC NetID: Carreona
// CS 455 PA4
// Fall 2022

import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordFinder {
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      //AnagramDictionary anagrams = new AnagramDictionary("sowpods.txt");
      Rack rack = new Rack();
      ScoreTable scores = new ScoreTable();
      System.out.println("Type . to quit." + "\n");

      boolean done = false;
      while (!done){
         try {
            System.out.print("Rack? ");
            String word = in.useDelimiter("[^a-zA-Z]+").nextLine().trim().replace(" ","");
            rack.countLetters(word);
            done = true;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
//         catch (FileNotFoundException e){
//            System.out.println("ERROR: Dictionary file \"testFiles/foobar.txt\" does not exist.");
//         }
      }
   }
}