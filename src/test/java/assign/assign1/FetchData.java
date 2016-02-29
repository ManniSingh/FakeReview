package assign.assign1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FetchData {
	public ArrayList<String> getData(String source) throws IOException{
		ArrayList<String> users = new ArrayList<String>();
        File dir = new File(source);
        File[] files = dir.listFiles();
        String file_text = "";
        for (File f : files) {
            if((f.isFile()) & (!f.isHidden())) {               
                Scanner inputStream = new Scanner(new FileReader(f));
                while (inputStream.hasNext()) {
                	file_text+=inputStream.nextLine().toLowerCase();
                	//System.out.println(file_text);
                }
                inputStream.close();
                users.add(file_text);
                file_text="";
            }
        }
        return(users);
	}
}
