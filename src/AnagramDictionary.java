// Name: Anthony Carreon
// USC NetID: Carreona
// CS 455 PA4
// Fall 2022

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 A dictionary of all anagram sets.
 Note: the processing is case-sensitive; so if the dictionary has all lower
 case words, you will likely want any string you test to have all lower case
 letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {

   /**
    * Representation invariant:
    * (currWord != null) if there is a next word in the file to read in
    * (anagrams > 0) when there is matched strings
    * (!dictMap.IsEmpty()) if there is any word to read in from file
    * (!rackMap.IsEmpty()) if user inputs a set of characters that make a string (string.length() > 1)
    */
   private String currWord;
   private ArrayList<String> anagrams;
   private  Map<String, Map<Character,Integer>> dictMap;
   private  Map<String, Map<Character,Integer>> rackMap;

   /**
    Create an anagram dictionary from the list of words given in the file
    indicated by fileName.
    @param fileName  the name of the file to read from
    @throws FileNotFoundException  if the file is not found
    @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException, IllegalDictionaryException {
      currWord = "";
      anagrams = new ArrayList<String>();
      rackMap = new HashMap<String, Map<Character,Integer>>();
      dictMap = new HashMap<String, Map<Character,Integer>>();

      File file = new File(fileName);
      try(Scanner in = new Scanner(file)){
         readData(in);
      }
      catch (FileNotFoundException exception){
         System.out.println("ERROR: Dictionary file \"" + fileName + "\" does not exist.");
         System.out.println("Exiting Program.");
         System.exit(0);
      }
      catch (IllegalDictionaryException exception){
         System.out.println("ERROR: Illegal dictionary: dictionary file has a duplicate word: " + currWord);
         System.out.println("Exiting Program.");
         System.exit(0);

      }
   }



   /**
    * setRack method helps find the anagrams from the dictionary words, matching them with the different substring(s) from
    * users input. A HashMap of Characters and Integers is used to get the character frequencies of each rack string. This is used to
    * add to a HashMap of Strings and a Map of Characters & Integers that is later compared with a HashMap of Strings and a
    * Map of Characters & Integers from the dictionary file.
    * @param  rack ArrayList<String> set of different substrings from user input.
    *
    */
   public void setRack(ArrayList<String> rack){
      Iterator<String> iterator = rack.iterator();
      while (iterator.hasNext()) {
         Map<Character,Integer> rackWordMap = new HashMap<Character,Integer>();
         String rackWord = iterator.next();
         addToMap(rackWordMap, rackWord);
         rackMap.put(rackWord, rackWordMap);
      }
   }



   /**
    Get all anagrams of the given string. This method is case-sensitive.
    E.g. "CARE" and "race" would not be recognized as anagrams.
   // @param s string to process
    @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String string) {
      for (String other: rackMap.keySet()) {
         for(String maybe: dictMap.keySet()) {
            if (dictMap.get(maybe).equals(rackMap.get(other))) {
               anagrams.add(maybe);
            }
         }
      }
      return anagrams;
   }



   /**
    * clearRack method is called by the anagram object in WordFinder class to make new data storage objects within this class.
    * For example, anagrams - set of substring(s) & rackMap - that stores character frequencies from the different substring(s) are set to new objects.
    */
   public void clearRack() {
      anagrams = new ArrayList<String>();
      rackMap = new HashMap<String, Map<Character,Integer>>();
   }


   /**
    * readData method is used to read in each word from the dictionary file and put the value of the character frequency of each word in the
    * dictMap. This method checks for a duplicate word within dictionary file and if there is a duplicate word it will throw an exception.
    * @param in Scanner scans line by line from the dictionary file.
    * @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   private void readData(Scanner in) throws IllegalDictionaryException {
      dictMap = new HashMap<String, Map<Character, Integer>>();
      while (in.hasNext()){
         currWord = in.next();
         if (dictMap.containsKey(currWord)){
            throw new IllegalDictionaryException();
         }
         dictMap.put(currWord,countChar(currWord));
      }
   }



   /**
    * addToMap method is called with the class to store the value of each word that is sent via parameter.
    * @param rackWordMap Map<Character, Integer> stores character frequencies from a string
    * @param word String scanned char by char.
    */
   private void addToMap(Map<Character, Integer> rackWordMap, String word) {
      for (int i = 0; i < word.length(); i++) {
         if (!rackWordMap.containsKey(word.charAt(i))){
            rackWordMap.put(word.charAt(i),1);
         } else{
            rackWordMap.put(word.charAt(i),rackWordMap.get(word.charAt(i)) + 1);
         }
      }
   }


   /**
    * countChar method is called within this class to count the characters frequency from each word that is sent via
    * parameters.
    * @param word String scanned char by char to count character frequencies.
    * @return a Map of characters frequencies.
    */
   private Map<Character, Integer> countChar(String word){
      Map<Character, Integer> countLetters = new HashMap<Character,Integer>();
      for (int i = 0; i < word.length(); i++) {
         char character = word.charAt(i);
         if (!countLetters.containsKey(character)){
            countLetters.put(character,1);
         } else{
            countLetters.put(character,countLetters.get(character) + 1);
         }
      }
      return countLetters;
   }

}

