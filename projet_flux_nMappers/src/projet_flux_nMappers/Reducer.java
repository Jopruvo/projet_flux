package projet_flux_nMappers;

import java.util.ArrayList;
import java.util.HashMap;

public class Reducer extends Thread{
	
	private ArrayList<Mapper>listMapper;
	private HashMap<String, Integer> h;
	private int numReducer;
	
	public Reducer(int numReducer, ArrayList<Mapper> listMapper) {
		this.numReducer = numReducer;
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
		
		int nbMapper = listMapper.size();
		ArrayList<HashMap<String, Integer>> hash = new ArrayList<HashMap<String, Integer>>();
		
		for(int i = 0; i<nbMapper; i++) {
			hash.add(this.listMapper.get(i).gethn(this.numReducer));
		}
		
		
		this.h.putAll(hash.get(0));
		
		for(HashMap<String, Integer> hashmap : hash) {
			for(String key: hashmap.keySet()) {
				if(h.containsKey(key)) {
					this.h.put(key, this.h.get(key) + hashmap.get(key));
				}
				else {
					this.h.put(key, hashmap.get(key));
				}
			}
		}
		

	}
	
	public void run() {
		this.reduce();
	}
}


