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



   private ArrayList<String> anagrams;
   private ArrayList<String> rack;
   private String fileName;
   private Set<String> dictionaryWords;
   private String currWord;

   /**
    Create an anagram dictionary from the list of words given in the file
    indicated by fileName.
    @param fileName  the name of the file to read from
    @throws FileNotFoundException  if the file is not found
    @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException, IllegalDictionaryException {
      File file = new File(fileName);
      currWord = "";
      anagrams = new ArrayList<String>();
      dictionaryWords = new TreeSet<String>();
      rack = new ArrayList<String>();

      try(Scanner in = new Scanner(file)){
         readData(in);
      }
      catch (FileNotFoundException exception){
         System.out.println("Exiting Program.");
         throw new FileNotFoundException("ERROR: Dictionary file \"" + fileName + "\" does not exist.");
      }
      catch (IllegalDictionaryException exception){
         System.out.println("Exiting Program.");
         throw new IllegalDictionaryException("ERROR: Illegal dictionary: dictionary file has a duplicate word: " + currWord);
      }
   }

   private void readData(Scanner in) throws IllegalDictionaryException {
      while (in.hasNext()){
         currWord = in.next();
         if (dictionaryWords.contains(currWord)){
            throw new IllegalDictionaryException();
         }
         dictionaryWords.add(currWord);
      }
   }



   /**
    Get all anagrams of the given string. This method is case-sensitive.
    E.g. "CARE" and "race" would not be recognized as anagrams.
   // @param s string to process
    @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String string) {
      String sorted = sortWord(string);
      ArrayList<String> perms =  getPermutations(sorted);
      Iterator<String> iterator = dictionaryWords.iterator();
      while (iterator.hasNext()){
         String dictionaryWord = iterator.next();
         if (perms.contains(dictionaryWord)){
            anagrams.add(dictionaryWord);
//            perms.get()
         }
      }

      return anagrams;
   }

   private  ArrayList<String> getPermutations(String sorted) {
      ArrayList <String> result = new ArrayList<String>();
      if (sorted.length() == 0){
         result.add(sorted);
         return result;
      } else {
         for (int i = 0; i < sorted.length(); i++) {
            String shorter = sorted.substring(0,i) + sorted.substring(i+1);
            ArrayList<String> shorterPermutations = getPermutations(shorter);
            for (String s: shorterPermutations) {
               result.add(sorted.charAt(i) + s);
            }
         }
         return result;
      }
   }

   private String sortWord(String string) {
      String sortedWord = "";
      char[] wordArray = string.toCharArray();
      Arrays.sort(wordArray);
      for (int i = 0; i < wordArray.length; i++) {
         sortedWord+= wordArray[i];
      }
      //System.out.println("Sorted word: " + sortedWord);
      return sortedWord;
   }
   public void setRack(ArrayList<String> rack){
      this.rack = rack;
   }

}



