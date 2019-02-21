package edu.eci.arsw.cinema.filters;

import java.util.List;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;

public interface Filtro {
	public List<Movie> filter(Cinema cinema, String date, String filtro);

}
