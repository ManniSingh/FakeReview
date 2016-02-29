package assign.assign1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CsvWriter {
	public static void getOutput(HashMap<Integer,Integer> output,String filename) throws IOException{
		File out = new File("/Users/manni/"+filename+".csv");
		out.createNewFile();
		try(PrintWriter writer = new PrintWriter(out)){
			for(Map.Entry<Integer,Integer> entry:output.entrySet()){
				writer.append(entry.getKey().toString())
					  .append(',')
					  .append(entry.getValue().toString())
					  .append("\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void getOutput(String[] positive,String[] negative,String filename) throws IOException{
		File out = new File("/Users/manni/"+filename+".csv");
		out.createNewFile();
		try(PrintWriter writer = new PrintWriter(out)){
			for(int i=0;i<positive.length;i++){
				writer.append(positive[i])
					  .append(',')
					  .append(negative[i])
					  .append("\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
