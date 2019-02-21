package edu.eci.arsw.cinema.filters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
///////////////@Service
@Service
public class ByAvailability implements Filtro {

	@Override
	public List<Movie> filter(Cinema cinema, String date, String filtro) {
		List<CinemaFunction> cfs = cinema.getFunctions();
		
		List<Movie> validas = new ArrayList<>();
	
		
		int minimo =Integer.valueOf(filtro);
		for (CinemaFunction cf : cfs) {
			
			int vacios = 0;
			if (cf.getDate().equals(date)) {
				List<List<Boolean>> seats = cf.getSeats();
				
				for (int i = 0; i < seats.size(); i++) {
					for (int j = 0; j < seats.get(0).size(); j++) {
						if (seats.get(i).get(j).equals(true)) {
							vacios++;
						}
					}
				}
				if (vacios > minimo) {
					validas.add(cf.getMovie());
				}
				
			}
		}
		return validas;
	}

}
