
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.util.Random;
public class MarkovModel {
    public int N;
    private String myText;
    private Random myRandom;
    public MarkovModel(int n) {
        N = n;
    }
    public MarkovModel() {
        myRandom = new Random();
    }
    
   
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String>follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length()-1) {
            int start = myText.indexOf(key,pos);
            if(start==-1) {
                break;
            }
            if(start+key.length()>=myText.length()) {
                break;
            }
            String next = myText.substring(start+key.length(),start+key.length()+1);
            follows.add(next);
            pos=start+key.length();
        }
        return follows;
    }
    

    
    public String getRandomText(int numChars){
        if (myText == null){
    	    return "";
    	}
    	//only need three changes here if you want to use the N value
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index, index+N);
        sb.append(key);
       
        for(int k=0; k < numChars-N; k++){
            ArrayList<String>follows = getFollows(key);
            if(follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1)+next;
        }
        
        return sb.toString();
    }
}
