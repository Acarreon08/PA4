// Name: Anthony Carreon
// USC NetID: Carreona
// CS 455 PA4
// Fall 2022

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * WordFinder class finds word(s) that belong to a set of characters that a user input from the command line. The set of words that are matched
 * are from a file that is read in from main method's parameter. AnagramsDictionary class and anagrams object is used to read in a file
 * and print any exceptions, such as duplicate words in the dictionary file or if the file doesn't exist. The Rack class and
 * rack object is used to make substrings of the input in different orders to see if they match with any of the words from the dictionary
 * file.
 */
public class WordFinder {
   /**
    * main method makes a scanner object to read in command input(s) from user to find words that match the input from a file that
    * is provided while running program.
    * @throws FileNotFoundException  if the file is not found
    * @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public static void main(String[] args) throws IllegalDictionaryException, FileNotFoundException {
//      System.out.println("Rack ?");
      Scanner in = new Scanner(System.in);
//      int i = 0;
//      while (args.length > 0) {
//         String fileName = args[i];
         AnagramDictionary anagrams = new AnagramDictionary("/Users/anthony/IdeaProjects/PA4/src/sowpods.txt");
         Rack rack = new Rack();
         ArrayList<String> listOfInput = new ArrayList<String>();
         boolean done = false;
//         System.out.println("Rack ?");
         while (!done) {
            String word = in.next();
            done = checkInput(listOfInput, done, word);
         }
         getResults(anagrams, rack, listOfInput);
//         i++;
//      }
   }


   /**
    * checkInput method returns a boolean to check if the user inputs a "." to end the user input of the set of characters (string[s]).
    * I trimmed all white spaces and all non-alphabetical characters.
    * @param listOfInput ArrayList<String> is used to store the set of character(s) or strings from user.
    * @param done boolean used when user inputs "." to end while loop in main method.
    * @param word String is each set of characters or string one at a time.
    * @return a boolean to end while loop in main method.
    */
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


   /**
    * getResults method uses the anagrams object to find the matches from the dictionary and print the results.
    * E.g. '8: calm'
    * @param  anagrams AnagramDictionary is used to store the words that are matched from the dictionary.
    * @param  rack Rack is the set of substring in different order from a set of character(s) or string(s) from user.
    * @param listOfInput ArrayList<String> is used to store the set of character(s) or string(s) from user.
    */
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

////HOW would you map canonical form to the words to the list of anagrams that share the same form