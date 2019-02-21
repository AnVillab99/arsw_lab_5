package edu.eci.arsw.cinema.filters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
//@Service
//@Service
public class ByGender implements Filtro {

	@Override
	public List<Movie> filter(Cinema cinema, String date, String filtro) {
		
		List<CinemaFunction> cfs = cinema.getFunctions(); 
		List<Movie> validas = new ArrayList<Movie>() ;
        for (CinemaFunction cf : cfs) {
            Movie m = cf.getMovie();
            if (cf.getDate().equals(date) && m.getGenre().equals(filtro)) {            	
                validas.add(m);
            }
        }
        return validas;
		
	}

}
