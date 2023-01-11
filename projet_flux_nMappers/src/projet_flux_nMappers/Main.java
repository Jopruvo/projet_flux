package projet_flux_nMappers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
	public static void main(String[] args) throws InterruptedException, IOException{
		
		int NombreMapperReducer = 10;
		
		long startTime = System.currentTimeMillis();
		Driver driver = new Driver("projet_flux_nMappers/big_bible.txt", NombreMapperReducer);
		driver.separation();
		System.out.println("separation ok");
		
		long endTime1 = System.currentTimeMillis();
		long totalTime1 = endTime1 - startTime;
		System.out.println("Temps d'ex�cution s�parateur : " + totalTime1 + " ms");
		
		int nbMapper = driver.getNbMapper();
		ArrayList<Mapper> listMapper = new ArrayList<Mapper>(nbMapper);
		ArrayList<Reducer> listReducer = new ArrayList<Reducer>(nbMapper);
		
		for(int i = 0; i<nbMapper; i++) {
			listMapper.add(new Mapper(driver, i));
			listReducer.add(new Reducer(i, listMapper));
		}


		
		for(Mapper mapper : listMapper) {

			mapper.start();
		}
		for(Mapper mapper : listMapper) {
			mapper.join();
		}

		System.out.println("mapper ok");
		long endTime2 = System.currentTimeMillis();
		long totalTime2 = endTime2 - endTime1;
		System.out.println("Temps d'ex�cution mapper : " + totalTime2 + " ms");
		
		
		for(Reducer reducer: listReducer) {
			reducer.start();
		}
		for(Reducer reducer: listReducer) {
			reducer.join();
		}
		

		System.out.println("reducer ok");
		long endTime3 = System.currentTimeMillis();
		long totalTime3 = endTime3 - endTime2;
		System.out.println("Temps d'ex�cution reducer : " + totalTime3 + " ms");
		
		HashMap<String, Integer> resultat = new HashMap<String, Integer>();
		for(Reducer reducer: listReducer) {
			resultat.putAll(reducer.getH());
		}

		
		
		long totalTime4 = endTime3 - startTime;
		System.out.println("Temps d'ex�cution total : " + totalTime4 + " ms");
		
		
		//System.out.println(resultat);
		//System.out.println(resultat.size());
	}
}

