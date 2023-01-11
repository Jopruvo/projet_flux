package projet_flux_nMappers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Mapper extends Thread {
	
	private String s;
	private int numMapper;
	private int nbReducer;
	private ArrayList<HashMap<String, Integer>> h;

	
	
	
	public Mapper(Driver driver, int numMapper) throws IOException {
		this.nbReducer = driver.getNbMapper();
		this.numMapper = numMapper;
		this.s = driver.getsn(this.numMapper).toString();
		this.h = new ArrayList<HashMap<String, Integer>>(nbReducer);
		
		for(int i = 0; i<nbReducer; i++) {
			h.add(new HashMap<String, Integer>());
		}

	}
	
	public void run() {
		this.countWords(s);
	}
	
	public void countWords(String words) {
		
		if(words.length() != 0) {
			String[] res = words.split("\\s+");
			for (String word : res) {
				for(int i = 0; i<this.nbReducer; i++) {
					if(word.getBytes()[0]%this.nbReducer==i) {
						if(this.h.get(i).get(word) == null) {
		           			this.h.get(i).put(word, 1);
		           		}
		           		else {
		           			this.h.get(i).put(word, this.h.get(i).get(word)+1);
		           		}
					}
				}
	           	}
			}
		System.out.println("Mapper " + String.valueOf(numMapper)+ " termin� : " + String.valueOf(h.get(numMapper).size()));
	}
	
	public HashMap<String, Integer> gethn(int n){
		return this.h.get(n);
	}
	


}

