package assign.assign1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class Parser {
	//get stop list
	public static ArrayList<String> getStopList(String name) throws IOException {
        File stop_en = new File(name);
        Scanner stop_list = new Scanner(new FileReader(stop_en));
        ArrayList<String> stop_words = new ArrayList<String>();
        while (stop_list.hasNext()) {
        	stop_words.add(stop_list.nextLine());
        }
        stop_list.close();
        return(stop_words);
        }
	//To get Model
		public static TokenizerModel getModel(String source) throws IOException{
			InputStream modelIn = new FileInputStream(source);		
				TokenizerModel model = new TokenizerModel(modelIn);			
				  if (modelIn != null) {
				    try {
				      modelIn.close();
				    }
				    catch (IOException e) {
				    }
				  }		 
			return(model);
		}
		
		//Tokenizer
		public String[] getTokens(TokenizerModel model,String review) throws IOException{
			Tokenizer tokenizer = new TokenizerME(model);
			String tokens[] = tokenizer.tokenize(review);
			return(tokens);
		}
		
		//Parser
		public ArrayList<String> getParsed(String review,TokenizerModel model,ArrayList<String> stop_list)throws IOException{
			ArrayList<String> tokenList = new ArrayList<String>(Arrays.asList(this.getTokens(model, review)));
			ArrayList<String> parsedList = new ArrayList<String>();
			tokenList.removeAll(stop_list);
			for(String t: tokenList){
				if(t.matches("[a-z]+")){
					parsedList.add(t);
				}
			}
			//System.out.println(parsedList);
			return(parsedList);
		}
}
