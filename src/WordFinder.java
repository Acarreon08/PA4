// Name: Anthony Carreon
// USC NetID: Carreona
// CS 455 PA4
// Fall 2022

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordFinder {
   public WordFinder() {
   }

   public static void main(String[] args) throws IllegalDictionaryException, FileNotFoundException {
      Scanner in = new Scanner(System.in);
      AnagramDictionary anagrams = new AnagramDictionary("/Users/anthony/IdeaProjects/PA4/src/shortDictionary.txt");
      Rack rack = new Rack();
      System.out.println("Type . to quit." + "\n");
      System.out.print("Rack? ");
      String word = in.useDelimiter("[^a-zA-Z]+").nextLine().trim().replace(" ","");
      anagrams.setRack(rack.countLetters(word));
      System.out.println();
      //System.out.println(subsets);
      ScoreTable scores = new ScoreTable(anagrams.getAnagramsOf(word), word);
      scores.printScores();
   }
}


/*
* eat
* eta
* aet
* ate
* tea
* tae
* */