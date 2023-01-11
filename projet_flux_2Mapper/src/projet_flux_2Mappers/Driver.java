package projet_flux_2Mappers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Driver {
	
	private String path;

	private StringBuilder sb1 = new StringBuilder();
	private StringBuilder sb2 = new StringBuilder();
	
	private String s1;
	private String s2;
	
	public Driver(String path) {
		this.path = path;
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
	    return count;
	}

	
	
	public void separation() throws IOException{
		File file = new File(this.getPath(this.path));
	    BufferedReader reader = new BufferedReader(new FileReader(file));

	    StringBuilder sb1 = new StringBuilder();
	    StringBuilder sb2 = new StringBuilder();
	    int nbMots = this.countWord();
	    int count = 0;
	    String line;
	    while ((line = reader.readLine()) != null) {
	        String[] words = line.split("[, :;-]");
	        for (String word : words) {
	            word = word.trim();
	            if (count < nbMots / 2) {
	                sb1.append(word).append(" ");
	            } else {
	                sb2.append(word).append(" ");
	            }
	            count++;
	        }
	    }
	    s1 = sb1.toString();
	    //System.out.println(s1.length());
	    s2 = sb2.toString();
	    //System.out.println(s2.length());
	}
	
	
	public String gets1() throws IOException {
		return s1;
	}
	
	
	public String gets2() throws IOException {
		return s2;
	}
}
