package assign.assign1;

import java.util.ArrayList;
import java.util.HashMap;

public class Scoring {
	//Frequency matrix
		public static HashMap<String,Integer> getFreqMatrix(ArrayList<String> vector){
			HashMap<String,Integer> freqVector = new HashMap<String,Integer>();
	    	for(String term : vector){
	    		Integer freq = freqVector.get(term);
	    		freqVector.put(term, (freq == null) ? 1 : freq + 1);
	    	}	
	    	return(freqVector);
		}
		//Term count
		public static HashMap<String,Integer> getTermCounts(ArrayList<HashMap<String,Integer>> matrix){
			HashMap<String,Integer> term_freq=new HashMap<String,Integer>();
			for(HashMap<String,Integer> vector:matrix){
				for(HashMap.Entry<String,Integer> pair:vector.entrySet()){
					Integer freq = term_freq.get(pair.getKey());
					term_freq.put(pair.getKey(), (freq == null) ? pair.getValue() : freq + pair.getValue());
				}
			}
			return(term_freq);
		}
		
		//Method 1 
		public static HashMap<Integer,Integer> getScores(ArrayList<HashMap<String,Integer>> matrix,HashMap<String,Integer> term_counts){
			HashMap<Integer,Integer> scoring=new HashMap<Integer,Integer>();
			HashMap<String,Integer> ones=new HashMap<String,Integer>();
			for(String key:term_counts.keySet()){
				if(term_counts.get(key)==1){
					ones.put(key,term_counts.get(key));
				}
			}
			
			//initializing scoring with 0s
			for(int i=0;i<matrix.size();i++){
				scoring.put(i, 0);
			}
			
			//Filling scoring list
			int index = 0;
			int score=0;
			for(HashMap<String,Integer> vector:matrix){		
				for(String mins_pair:ones.keySet()){
					if(vector.containsKey(mins_pair)){
							score+=vector.get(mins_pair);
					}
				}
			scoring.put(index,score);
			score=0;
			index++;
			}
			return(scoring);
		}
		
}
