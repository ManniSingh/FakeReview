package assign.assign1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


import opennlp.tools.tokenize.TokenizerModel;

public class COMatrix {
	public static ArrayList<HashMap<String,Integer>> getMatrix(TokenizerModel model,ArrayList<String> stop_list) throws IOException{
		//Getting data from files.
		ArrayList<String> t_users = new ArrayList<String>();
		t_users=new FetchData().getData("src/test/java/truth");
		t_users.addAll(new FetchData().getData("src/test/java/truth2"));
		ArrayList<String> f_users = new ArrayList<String>();
		f_users=new FetchData().getData("src/test/java/false");
		f_users.addAll(new FetchData().getData("src/test/java/false2"));
		ArrayList<String> users = new ArrayList<String>();
		users.addAll(t_users);
		users.addAll(f_users);
		
		// counting the terms and putting in a list.
		HashMap<String,Integer> terms = new HashMap<String,Integer>();
		for(String review:users){
			ArrayList<String> parse_list = new Parser().getParsed(review,model,stop_list);
        	for(String term:parse_list){
        		terms.put(term, 1);
        	}
		}
		return(getCOMatrix(terms,getTermMatrix(users,model,stop_list)));		
	}
	
	//Parsed matrix
	public static ArrayList<ArrayList<String>> getTermMatrix(ArrayList<String> users,TokenizerModel model,ArrayList<String> stop_list) throws IOException{
		ArrayList<ArrayList<String>> term_matrix = new ArrayList<ArrayList<String>>();
		for(String review:users){
			ArrayList<String> parse_list = new Parser().getParsed(review,model,stop_list);
			term_matrix.add(parse_list);
		}
		return(term_matrix);
	}
	
	// You may need to set run settings to increase memory capacity, if out of memory error comes!!
	public static ArrayList<HashMap<String,Integer>> getCOMatrix(HashMap<String,Integer> terms,ArrayList<ArrayList<String>> term_matrix){
		//making Confusion matrix
				ArrayList<HashMap<String,Integer>> c_matrix = new ArrayList<HashMap<String,Integer>>();
				for(int i=0;i<term_matrix.size();i++){
					HashMap<String,Integer> vector = new HashMap<String,Integer>();
					for(String feature:terms.keySet()){
							vector.put(feature,0);
					}	
					for(String term:term_matrix.get(i)){
						for(String feature:terms.keySet()){
							if (term.equals(feature)) 
								vector.put(feature, 1);
						}
					}		
					c_matrix.add(vector);
				}
				return(c_matrix);
	}
		
	
}
