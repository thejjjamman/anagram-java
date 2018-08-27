
import java.util.ArrayList;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author James
 */
public class LetterNode {

    String letter;
    boolean isfinal;
        boolean UsedTofinal=false;//so once you find something at end of path that used to be. You can just stop looking
    LetterNode perent;
    int depth;
    //ArrayList<LetterNode> letters = new ArrayList<LetterNode>();
    LetterNode[] children;

    public LetterNode(String letter, boolean isfinal, LetterNode perent, int depth) {
        this.letter = letter;
        this.isfinal = isfinal;
        this.perent = perent;
        this.depth = depth;//or perent.depth+1
        children = new LetterNode[27];

    }

   
    public  void addchildren(String word) {
        //will assume dictionary is in alphaetical order. and all caps

        if (word.length() == 1) {
            //check if node is there
            //if not add final node
            // if is there make final

            if (this.children[(int) word.charAt(0) - 65] == null) {//if null put something there
                this.children[(int) word.charAt(0) - 65] = new LetterNode(word, true, this, this.depth + 1);

            } else {//if not null update
                this.children[(int) word.charAt(0) - 65].isfinal = true;
            }

        } else {//word is longer than 1

                if (this.children[(int) word.charAt(0) - 65] == null) {//if null put something there
                    
                    this.children[(int) word.charAt(0) - 65] = new LetterNode(word.charAt(0)+"", false, this, this.depth + 1);
                    //then make sure new node gets rest of word
                    this.children[(int) word.charAt(0) - 65].addchildren((word.substring(1)));
                       

                } else {//if not null update
                     this.children[(int) word.charAt(0) - 65].addchildren((word.substring(1)));
                }
        }
}

@Override
        public String toString() {
        String out =letter+"(";
        for (int i = 0; i < children.length; i++) {
            if(children[i]!=null){
               
                     out=out+children[i].depth+children[i].isfinal+children[i].toString()+"";
                
            }
           
        }
        out=out+")";
        return out;
    }

}
