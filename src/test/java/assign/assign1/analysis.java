package assign.assign1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import opennlp.tools.tokenize.TokenizerModel;



public class analysis {	
	//Main block
	public static void main(String[] args) throws IOException {   
        //Stop words reading
		ArrayList<String> stop_list = Parser.getStopList("src/test/java/stop-words_english_6_en.txt");
        //Tokenizer model
        TokenizerModel model=Parser.getModel("src/test/java/en-token.bin");
        //User data reading
        ArrayList<String> users = new FetchData().getData("src/test/java/texts");
        //Frequency matrix
        ArrayList<HashMap<String,Integer>> matrix = new ArrayList<HashMap<String,Integer>>();
        ArrayList<ArrayList<String>> token_matrix = new ArrayList<ArrayList<String>>();
        for(String review:users){
        	ArrayList<String> parse_list = new Parser().getParsed(review,model,stop_list);
        	matrix.add(Scoring.getFreqMatrix(parse_list));
        	token_matrix.add(parse_list);
        } 
        //term count vector
        //System.out.println(token_matrix.size());
        
        /*Method 1.
         *getting unique vectors(reviews)	
        */
        //System.out.println(Scoring.getScores(matrix,Scoring.getTermCounts(matrix)));
        //CsvWriter.getOutput(Scoring.getScores(matrix,Scoring.getTermCounts(matrix)), "unique");
        /*Method 2.
         *getting bi-gram similarity between vectors
         */
        //System.out.println(BiGram.getSimScores(BiGram.getBigram(token_matrix)));
        //CsvWriter.getOutput(BiGram.getSimScores(BiGram.getBigram(token_matrix)), "bigram");
        
        /*Method 3.
         * Hype detection
         */
        ArrayList<HashMap<Integer,Integer>> sentiment_matrix = new ArrayList<HashMap<Integer,Integer>>();
        sentiment_matrix=Sentiment.getSentiAnalysis(token_matrix);
        String[] positive = new String[sentiment_matrix.get(0).size()];
        String[] negative = new String[sentiment_matrix.get(1).size()];
        //System.out.println(sentiment_matrix);
        for(int i=0;i<sentiment_matrix.get(1).size();i++){
        	positive[i] = sentiment_matrix.get(0).get(i).toString();
        	negative[i] = sentiment_matrix.get(1).get(i).toString();
        }
        
        //CsvWriter.getOutput(positive, negative, "sentiment"); 
        
        COMatrix.getMatrix(model,stop_list);
        
     }
}

