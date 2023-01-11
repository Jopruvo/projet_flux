package projet_flux_2Mappers;

import java.util.ArrayList;
import java.util.HashMap;

public class Reducer extends Thread{
	
	private ArrayList<Mapper>listMapper;
	private HashMap<String, Integer> h;
	private int n;
	
	public Reducer(int n, ArrayList<Mapper>listMapper) {
		this.n = n;
		this.listMapper = listMapper;
		this.h = new HashMap<String, Integer>();
	}
	

	public HashMap<String, Integer> getH() {
		return h;
	}


	public void setH(HashMap<String, Integer> h) {
		this.h = h;
	}


	public void reduce() {
		
		HashMap<String, Integer> hash1 = new HashMap<String, Integer>();
		HashMap<String, Integer> hash2 = new HashMap<String, Integer>();
		
		if(this.n == 1) {
			hash1 = this.listMapper.get(0).geth1();
			hash2 = this.listMapper.get(1).geth1();			
		}
		else {
			hash1 = this.listMapper.get(0).geth2();
			hash2 = this.listMapper.get(1).geth2();		
		}
		
		this.h.putAll(hash1);
		
		for(String key: hash2.keySet()) {
			if(h.containsKey(key)) {
				this.h.put(key, this.h.get(key) + hash2.get(key));
			}
			else {
				this.h.put(key, hash2.get(key));
			}
		}
		
		//System.out.println("Reducer " + String.valueOf(n)+ " : " + h);
		//System.out.println("Reducer " + String.valueOf(n)+ " taille hashmap : " + h.size());
	}
	
	public void run() {
		this.reduce();
	}
}


