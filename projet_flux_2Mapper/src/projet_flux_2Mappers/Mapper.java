package projet_flux_2Mappers;

import java.io.IOException;
import java.util.HashMap;

public class Mapper extends Thread {
	
	private String s;
	private int n;
	private HashMap<String, Integer> h1;
	private HashMap<String, Integer> h2;
	
	
	
	public Mapper(Driver driver, int n) throws IOException {
		this.n = n;
		if(n == 1) {
			this.s = driver.gets1();
		}
		else {
			this.s = driver.gets2();
		}
		this.h1 = new HashMap<String, Integer>();
		this.h2 = new HashMap<String, Integer>();
		
	}
	
	public void run() {
		this.countWords(s);
	}
	
	public void countWords(String words) {
		
		if(words.length() != 0) {
			String[] res = words.split("\\s+");
//			if(n==1) {
//				System.out.println("taille :" + res.length);
//				System.out.println("mot 2:"  +res[1]);
//			}
			
		
			for (String word : res) {
//				if(n==1) System.out.println(word);
	           	if(word.getBytes()[0]%2==0) {
	           		if(this.h1.get(word) == null) {
	           			this.h1.put(word, 1);
	           		}
	           		else {
	           			this.h1.put(word, this.h1.get(word)+1);
	           		}
	           			
	        	}
	           	else {
	           		if(this.h2.get(word) == null) {
	           			this.h2.put(word, 1);
	           		}
	           		else {
	           			this.h2.put(word, this.h2.get(word)+1);
	           		}
	           		
	           	}
			}
		}
		
		//System.out.println("Mapper " + String.valueOf(n)+ " : ");
		//System.out.println("Mapper " + String.valueOf(n)+ " : " + h2);
	}
	
	public HashMap<String, Integer> geth1(){
		return this.h1;
	}
	
	public HashMap<String, Integer> geth2(){
		return this.h2;
	}


}

