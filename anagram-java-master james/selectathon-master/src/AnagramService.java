
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnagramService {

    public List<AnagramCounter> compute(String dictionaryLocation) throws IOException {
        List<String> words = FileUtils.readLines(new File(dictionaryLocation));
        int[] count = new int[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//16 indexs so that i dont have do a -1 at each update.

        //make graph.
        LetterNode masterNode = new LetterNode("+", false, null, 0);
        LetterNode currentNode;

        //builds graph 
        for (String word : words) {

////////            if(has lower case letters){
////////                word=word in all caps.
////////            }
            masterNode.addchildren(word);

        }
        //  System.out.println(masterNode);

        for (String word : words) {

            //  System.out.println(word);
            int update = searchGraph(word, masterNode);
//                if(update!=0){
//                    update=update-1;
//                }
            if (update > 0) {//added due to -100000 effeciency 
                count[word.length()] = count[word.length()] + update;
            }

            //delet anagrams of this word
        }

        return new ArrayList<>(Arrays.asList(
                new AnagramCounter(1, count[1]),
                new AnagramCounter(2, count[2]),
                new AnagramCounter(3, count[3]),
                new AnagramCounter(4, count[4]),
                new AnagramCounter(5, count[5]),
                new AnagramCounter(6, count[6]),
                new AnagramCounter(7, count[7]),
                new AnagramCounter(8, count[8]),
                new AnagramCounter(9, count[9]),
                new AnagramCounter(10, count[10]),
                new AnagramCounter(11, count[11]),
                new AnagramCounter(12, count[12]),
                new AnagramCounter(13, count[13]),
                new AnagramCounter(14, count[14]),
                new AnagramCounter(15, count[15])
        ));
    }

    //searches graph for anagrams
    private int searchGraph(String word, LetterNode masterNode) {
        int ans = 0;
        int childSubAns = 0;
        if (masterNode != null) {

            LetterNode currentNode = masterNode;

            if (word.length() == 1) {
                if (currentNode.children[(int) word.charAt(0) - 65] != null) {//there is a node for that letter
                    currentNode = currentNode.children[(int) word.charAt(0) - 65];
                    if (currentNode.isfinal) {

                        ans = ans + 1;
                        currentNode.isfinal = false;
                        currentNode.UsedTofinal = true;
                    } else if (currentNode.UsedTofinal) {//currentNode.UsedTofinal==true
                        //no need to keep searching word. all anagrams already found

                        ans = -100000;

                    }
                }

            } else {//word is longer than 1 so spawn new search on each letter

                for (int i = 0; i < word.length(); i++) {
                    currentNode = masterNode;
                    if (currentNode.children[(int) word.charAt(i) - 65] != null) {//there is a node for that letter

                        currentNode = masterNode.children[(int) word.charAt(i) - 65];
                        //  System.out.println("send serch from word  " + word + "  letter   " + word.charAt(i) + "  substign  " + (word.substring(0, i) + word.substring(i + 1)) + "   in " + currentNode);
                        childSubAns = searchGraph((word.substring(0, i) + word.substring(i + 1)), currentNode);

                        if (childSubAns < 0) {

                            return -10000;
                        } else {
                            ans = ans + childSubAns;
                        }
                    }
                }
            }
        }

        return ans;
    }
/*
    //A permutations methord to return all permutations of a given String. Not ever used in final
    public String[] getPermutations(String word) {

        ArrayList<String> allperms = new ArrayList<String>();
        int wl = word.length();

        if (wl <= 1) {
            return new String[]{word};
        } else if (wl == 2) {
            return new String[]{word, swap(word, 0, 1)};
        } else {
            for (int i = 0; i < wl; i++) {

                String[] subPerm = getPermutations(swap(word, 0, i).substring(1));

                for (int j = 0; j < subPerm.length; j++) {
                    allperms.add(word.charAt(i) + subPerm[j]);
                }

            }

        }

        int size = allperms.size();
        String[] ans = new String[size];

        //put all perms in
        for (int i = 0; i < size; i++) {
            ans[i] = allperms.get(i);
            //   System.out.println(ans[i]);//test to see if all perms 
        }

        return ans;
    }

    //not used in final
    // swap the characters at indices i and j
    public String swap(String word, int i, int j) {
        String[] splitword = word.split("");
        String temp = splitword[i];
        splitword[i] = splitword[j];
        splitword[j] = temp;
        int n = word.length();
        word = "";
        for (int k = 0; k < n; k++) {
            word = word + splitword[k];

        }

        return word;
    }
*/
}
