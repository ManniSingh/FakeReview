package assign.assign1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Sentiment {
	public static ArrayList<String> getSentiData(String file) throws IOException {
		//File negative = new File("src/test/java/negative-words.txt");
		Scanner sents = new Scanner(new FileReader(new File(file)));
        ArrayList<String> sent_list = new ArrayList<String>();
        while (sents.hasNext()) {
        	sent_list.add(sents.nextLine());
        }
        sents.close();
        return(sent_list);
	}
	public static HashMap<Integer,Integer> getScore(ArrayList<ArrayList<String>> token_matrix,ArrayList<String> sentis){
		int score=0;
		HashMap<Integer,Integer> scoring = new HashMap<Integer,Integer>();
		for(int i=0;i<token_matrix.size();i++){
			for(String term:token_matrix.get(i)){
				for(String senti:sentis){
					if(term.equals(senti))score++;
				}
			}
			scoring.put(i,score);
			score=0;
		}
		//System.out.println(scoring);
		return(scoring);
	}
	public static ArrayList<HashMap<Integer,Integer>> getSentiAnalysis(ArrayList<ArrayList<String>> token_matrix) throws IOException{
		ArrayList<String> positive = new ArrayList<String>();
		ArrayList<String> negative = new ArrayList<String>();
		ArrayList<HashMap<Integer,Integer>> pos_neg_matrix = new ArrayList<HashMap<Integer,Integer>>();
		positive = getSentiData("src/test/java/positive-words.txt");
		negative = getSentiData("src/test/java/negative-words.txt");
		pos_neg_matrix.add(getScore(token_matrix,positive));
		pos_neg_matrix.add(getScore(token_matrix,negative));	
		return(pos_neg_matrix);
	}
	
}
