// Name: Anthony Carreon
// USC NetID: Carreona
// CS 455 PA4
// Fall 2022

import java.util.*;

/**
 A Rack of Scrabble tiles
 */

public class Rack {

   /**
    Finds all subsets of the multiset starting at position k in unique and mult.
    unique and mult describe a multiset such that mult[i] is the multiplicity of the char
    unique.charAt(i).
    PRE: mult.length must be at least as big as unique.length()
    0 <= k <= unique.length()
    @param unique a string of unique letters
    @param mult the multiplicity of each letter from unique.
    @param k the smallest index of unique and mult to consider.
    @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
    each subset is represented as a String that can have repeated characters in it.
    @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();

      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }

      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);

      // prepend all possible numbers of the first char (i.e., the one at position k)
      // to the front of each string in restCombos.  Suppose that char is 'a'...

      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets
            // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }

      return allCombos;
   }


   /**
    * countLetters method is used with the rack object from the main class that makes a Map <Character, Integer>
    * to store the amount of times a character appears inside the word that is sent within the parameters.
    * @param  word String is split up to count character frequency for Map.
    * @return an ArrayList that is created from the allSubsets method to store sets of different substring(s).
    */
   public static ArrayList<String> countLetters(String word){
      Map<Character, Integer> letters = new TreeMap<Character,Integer>();
      for (int i = 0; i < word.length(); i++) {
         Integer count = letters.get(word.charAt(i));
         if (count == null){
            count = 1;
         } else {
            count++;
         }
         letters.put(word.charAt(i), count);
      }

      String unique = "";
      int[] numArray = new int[letters.size()];
      int i = 0;
      for (Character c: letters.keySet()) {
         unique+= c;
         numArray[i] = letters.get(c);
         i++;
      }

      return allSubsets(unique, numArray, 0);
   }

}

