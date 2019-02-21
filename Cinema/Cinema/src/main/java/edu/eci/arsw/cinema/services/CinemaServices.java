/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.filters.Filtro;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.model.Seat;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */
@Configuration
public class CinemaServices {
	@Autowired
	CinemaPersitence cps;
	
	
	

	public void addNewCinema(Cinema c) throws CinemaPersistenceException {
		//System.out.println(c);
		cps.saveCinema(c);
	}

	public Map<String, Cinema> getAllCinemas() {
		return cps.getCinemas();
	}

	/**
	 * 
	 * @param name
	 *            cinema's name
	 * @return the cinema of the given name created by the given author
	 * @throws CinemaException
	 * @throws CinemaPersistenceException 
	 */
	public Cinema getCinemaByName(String name) throws CinemaException, CinemaPersistenceException {
		return cps.getCinema(name);

	}

	public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
		
			cps.buyTicket(row, col, cinema, date, movieName);
		

	}

	public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
		return cps.getFunctionsbyCinemaAndDate(cinema, date);
	}
}
