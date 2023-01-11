package projet_flux_nMappers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Driver {
	
	private String path;
	private ArrayList<StringBuilder> sb;
	private int nbMapper;
	
	public Driver(String path, int nbMapper) {
		this.sb = new ArrayList<StringBuilder>(nbMapper);
		
		for(int i = 0; i<nbMapper; i++) {
			sb.add(new StringBuilder());
		}
		
		
		this.path = path;
		this.nbMapper = nbMapper;
	}
	
	public String getPath(String path) {
		return this.path;
	}

	
	
	public int countWord() throws IOException{
		File file = new File(this.getPath(this.path));
		BufferedReader reader = new BufferedReader(new FileReader(file));

	    int count = 0;
	    String line;
	    while ((line = reader.readLine()) != null) {
	        StringTokenizer tokenizer = new StringTokenizer(line, ", :;-");
	        count += tokenizer.countTokens();
	    }
	    reader.close();
	    return count;
	}

	
	
	public void separation() throws IOException{
		File file = new File(this.getPath(this.path));
	    BufferedReader reader = new BufferedReader(new FileReader(file));
	    int nbMots = this.countWord();
	    int mapper = 0;
	    int count = 0;
	    int resetCount =  nbMots / this.nbMapper;
	    String line;
	    
	    while ((line = reader.readLine()) != null) {
	        String[] words = line.split("[, :;-]");
	        for (String word : words) {
	            word = word.trim();
	            if (count > resetCount && mapper<this.nbMapper-1) {
	            	count = 0;
	            	mapper += 1;
	            }
	            this.sb.get(mapper).append(word + " ");
	            count++;
	        }
	    }
	    reader.close();
	}
	
	
	public StringBuilder getsn(int n) throws IOException {
		return sb.get(n);
	}
	
	public int getNbMapper() {
		return this.nbMapper;
	}
	
	
}
