/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.model.Seat;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author cristian
 */
public class CinemaServices {
	@Autowired
	CinemaPersitence cps;
	Set<Cinema> cinemas;

	public void addNewCinema(Cinema c) {
		cinemas.add(c);
	}

	public Set<Cinema> getAllCinemas() {
		return cinemas;
	}

	/**
	 * 
	 * @param name
	 *            cinema's name
	 * @return the cinema of the given name created by the given author
	 * @throws CinemaException
	 */
	public Cinema getCinemaByName(String name) throws CinemaException {
		Cinema c=null;
		try {
			c= cps.getCinema(name);
		} catch (CinemaPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;

	}

	public void buyTicket(int row, int col, String cinema, String date, String movieName) {
		try {
			cps.buyTicket(row, col, cinema, date, movieName);
		} catch (CinemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
		return cps.getFunctionsbyCinemaAndDate(cinema, date);
	}
}
