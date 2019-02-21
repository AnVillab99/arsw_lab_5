/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.filters.Filtro;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */

@Service
public class InMemoryCinemaPersistence implements CinemaPersitence {

	private final Map<String, Cinema> cinemas = new HashMap<>();
	
	
	@Autowired
	public Filtro filtro;
	
	public void setFiltro(@Qualifier("filtroG") Filtro fil) {
		System.out.println("poniendo filtro");
		System.out.println(fil.getClass());
		filtro=fil;
	}
	
	public Filtro fil() {
		return filtro;
	}
	
	public InMemoryCinemaPersistence() {
		// load stub data
		String functionDate = "2018-12-18 15:30";
		List<CinemaFunction> functions = new ArrayList<>();
		CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"), functionDate);
		CinemaFunction funct2 = new CinemaFunction(new Movie("The Night", "Horror"), functionDate);
		functions.add(funct1);
		functions.add(funct2);
		Cinema c = new Cinema("cinemaX", functions);
		cinemas.put("cinemaX", c);
	}

	@Override
	public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
		Cinema c = null;

		try {
			c = getCinema(cinema);
		} catch (CinemaPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (CinemaFunction f : c.getFunctions()) {
			if (f.getMovie().getName().equals(movieName) && f.getDate().equals(date)) {
				f.buyTicket(row, col);
			}
		}

	}

	@Override
	public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
		Cinema c = null;
		List<CinemaFunction> cf = new ArrayList<CinemaFunction>();
		
			try {
				c = getCinema(cinema);
			} catch (CinemaPersistenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (CinemaFunction f : c.getFunctions()) {
				if (f.getDate().equals(date)) {
					cf.add(f);
				}
			}

			

		
		return cf;
	}

	@Override
	public void saveCinema(Cinema c) throws CinemaPersistenceException {
		if (cinemas.containsKey(c.getName())) {
			throw new CinemaPersistenceException("The given cinema already exists: " + c.getName());
		} else {
			cinemas.put(c.getName(), c);
		}
	}

	@Override
	public Cinema getCinema(String name) throws CinemaPersistenceException {
		return cinemas.get(name);
	}
	
	public Map<String, Cinema> getCinemas() {
		return cinemas;
	}
	
	
    @Override
    public List<Movie> filter(String cinema, String date, String filter) throws CinemaPersistenceException {
    	System.out.println(filtro.getClass());
        return filtro.filter(getCinema(cinema), date, filter);
    }
}
