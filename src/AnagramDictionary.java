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
         System.out.println(file.getName() + " input is:");
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

   public void setRack(ArrayList<String> rack){
      findAnagrams(rack);
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

   public void clearRack() {
      anagrams = new ArrayList<String>();
      rackMap = new HashMap<String, Map<Character,Integer>>();
   }

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

   private void addToMap(Map<Character, Integer> rackWordMap, String word) {
      for (int i = 0; i < word.length(); i++) {
         if (!rackWordMap.containsKey(word.charAt(i))){
            rackWordMap.put(word.charAt(i),1);
         } else{
            rackWordMap.put(word.charAt(i),rackWordMap.get(word.charAt(i)) + 1);
         }
      }
   }


   private void findAnagrams(ArrayList<String> rack) {
      Map<Character, Integer> countLettersInRack = new HashMap<Character,Integer>();
      Iterator iter = rack.iterator();
      while (iter.hasNext()){
         String word = String.valueOf(iter.next());
         addToMap(countLettersInRack, word);
      }
   }

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

