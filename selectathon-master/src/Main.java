import java.util.List;

/// <summary>
/// INSTRUCTIONS:
/// Write the logic within the Anagram Service class to determine how many anagrams exist in a given list of words.
/// The definition of an anagram can be found here: https://www.google.co.za/?#q=definition+of+anagram
///
/// The output should list how many anagrams exist for a given character count.
/// E.g. Words with the character length of 4 had 5000 anagrams
///
/// Feel free to change the structure of the solution if you feel it helps optimise execution speed, memory usage etc.
/// Feel free to ask other colleagues and / or use google when crafting your solution.
/// Document assumptions (if any) as comments in code.
///
/// Your code should:
///     a) make use of the included file Dictionary.txt,
///     b) write your results to the console in the form "Words with the character length of x had y anagrams"
///     c) include the total time in milliseconds somewhere in your console output
///     d) not write to the console on processing each word
///
/// Should BSG proceed with a follow-up interview, you will be required to walk through your code as part of the face to face interview process.
///
/// Press return the completed solution with 48 hours to BSG, using the same email address that you received the zipped package.
/// Please re-zip your solution excluding all unnecessary files (e.g. *.user, *.suo, bin folder, obj folder)
/// </summary>
public class Main {

    //James Murray
     //Overview of code
            /*
            take in dictionary
            make graph of all words
            nodes = letters
            Final nodes= nodes that a word ends on
            Edges = adjacent letters in a word. 
            (eg. The word "ABC" would have Node A connected to B and B connected to C
            Then I search graph for every word. Unless that word has already been found to be part of another's anagram list.
            This optimisation is done by searching for each word in dictionary but if a word finds an anagram of itself that has already been found by another word it quits.

            Also the way I index nodes relies on the letters all being capitals
            */
    
    
    public static void main(String[] args) {
        try {
            String dictionaryLocation = "./resources/Dictionary.txt";

            long startTime = System.currentTimeMillis();
           List<AnagramCounter> anagramResults = new AnagramService().compute(dictionaryLocation);
            long endTime = System.currentTimeMillis();

            System.out.println("I decided  that the number of anagrams of a selected length was:");
            System.out.println("\t The sum of the number of times a given letter combination is found ");
            System.out.println("\t For example: In the list :ABC, CBA, BCA, XYZ,ZYX:\n"
                    + "\t I would say there are 5 anagrams of 3 letter words because\n"
                    + "\t The letters  A,B and C are found in 3 combinations \n"
                    + "\t And the letters X,Y and Z are found  in 2");
            
           
            
            
            System.out.println("");
            System.out.println();
            System.out.println("Anagram Results (completed in " + (endTime - startTime) + " ms):");
            System.out.println();

            for (AnagramCounter anagramCounter : anagramResults) {
                System.out.println("Words with the character length of " +  anagramCounter.getWordLength() +
                        " had " + anagramCounter.getCount() + " anagrams");
            }
        } catch (Exception e) {
            System.out.println("The following exception occurred:");
            System.out.println( e.toString());
        
        }
    }
}
