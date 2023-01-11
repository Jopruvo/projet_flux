package projet_flux_2Mappers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException{
		long startTime = System.currentTimeMillis();
		Driver driver = new Driver("projet_flux_2Mapper/big_bible.txt");
		driver.separation();
		System.out.println("separation ok");
		long endTime1 = System.currentTimeMillis();
		long totalTime1 = endTime1 - startTime;
		System.out.println("Temps d'ex�cution total : " + totalTime1 + " ms");
		
		Mapper mapper1 = new Mapper(driver, 1);
		Mapper mapper2 = new Mapper(driver, 2);
			  
		ArrayList<Mapper> listMapper = new ArrayList<Mapper>();
		listMapper.add(mapper1);
		listMapper.add(mapper2);
		
		Reducer reducer1 = new Reducer(1, listMapper);
		Reducer reducer2 = new Reducer(2, listMapper);
		
		mapper1.start();
		mapper2.start();
		mapper1.join();
		mapper2.join();
		System.out.println("mapper ok");
		long endTime2 = System.currentTimeMillis();
		long totalTime2 = endTime2 - startTime;
		System.out.println("Temps d'ex�cution total : " + totalTime2 + " ms");
		reducer1.start();
		reducer2.start();
		reducer1.join();
		reducer2.join();
		System.out.println("reducer ok");
		long endTime3 = System.currentTimeMillis();
		long totalTime3 = endTime3 - startTime;
		System.out.println("Temps d'ex�cution total : " + totalTime3 + " ms");
		
		HashMap<String, Integer> resultat = new HashMap<String, Integer>();
		resultat.putAll(reducer1.getH());
		resultat.putAll(reducer2.getH());
		
		//System.out.println(resultat);
		//System.out.println(resultat.size());
	}
}

