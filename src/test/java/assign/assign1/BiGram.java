package assign.assign1;

import java.util.ArrayList;
import java.util.HashMap;

public class BiGram {
	// creates martix with BiGram vectors
	public static ArrayList<ArrayList<String>> getBigram(ArrayList<ArrayList<String>> token_matrix){
		ArrayList<ArrayList<String>> bigram_matrix = new ArrayList<ArrayList<String>>();
		ArrayList<String> bigram_vector = new ArrayList<String>();
		for(ArrayList<String> vector:token_matrix){
			for(int i = 0;i<vector.size()-1;i++){
				bigram_vector.add(vector.get(i)+" "+vector.get(i+1));
			}
			bigram_matrix.add(bigram_vector);
			//System.out.println(bigram_vector);
			bigram_vector=new ArrayList<String>();
		}
		return(bigram_matrix);
	}
	//count similarities between vectors
	public static HashMap<Integer,Integer> getSimScores(ArrayList<ArrayList<String>> bigram_matrix){
		HashMap<Integer,Integer> sim = new HashMap<Integer,Integer>();
		int score=0;	
			for(int j = 0;j<bigram_matrix.size();j++){
				for(int k = 0;k<bigram_matrix.size();k++){
					if(j==k)continue;
					for(int l=0;l<bigram_matrix.get(j).size();l++){
						for(int m=0;m<bigram_matrix.get(k).size();m++){
							if(bigram_matrix.get(j).get(l).equals(bigram_matrix.get(k).get(m))){
								score++;
							}
						}
					}
				}
				sim.put(j, score);
				score=0;
			}
		return(sim);			
	}		
}
