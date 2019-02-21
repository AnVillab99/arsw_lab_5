
import edu.eci.arsw.cinema.filters.ByAvailability;
import edu.eci.arsw.cinema.filters.ByGender;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.impl.InMemoryCinemaPersistence;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cristian
 */
public class InMemoryPersistenceTest {

	@Test
	public void consultFuncitonsCinemaAndDate() {
		List<CinemaFunction> funciones = new ArrayList<CinemaFunction>();

		Movie movie1 = new Movie("aprueba", "Drama");
		CinemaFunction function = new CinemaFunction(movie1, "02/02/2019");

		Movie movie2 = new Movie("trampa", "Accion");
		CinemaFunction function1 = new CinemaFunction(movie2, "02/02/2019");

		Movie movie3 = new Movie("examen", "Drama");
		CinemaFunction function2 = new CinemaFunction(movie3, "02/02/2019");

		Movie movie4 = new Movie("graduarse", "scifi");
		CinemaFunction function3 = new CinemaFunction(movie4, "02/02/2019");

		funciones.add(function);
		funciones.add(function1);
		funciones.add(function2);
		funciones.add(function3);

		Cinema c = new Cinema("Cine Colombia", funciones);

		assertEquals(funciones, c.getFunctions());

		InMemoryCinemaPersistence ipct = new InMemoryCinemaPersistence();
		try {
			ipct.saveCinema(c);
		} catch (CinemaPersistenceException e) {
			assertFalse(true);
			e.printStackTrace();
		}

		boolean igual = true;
		List<CinemaFunction> funcionesProbadas = ipct.getFunctionsbyCinemaAndDate("Cine Colombia", "07/05/2019");
		for (int i = 0; i < funcionesProbadas.size() && igual; i++) {
			String cine1 = funcionesProbadas.get(i).getMovie().getName();
			String cine2 = funciones.get(i).getMovie().getName();
			if (!cine1.equals(cine2)) {
				igual = false;
			}
		}
		assertTrue(igual);

	}

	@Test
	public void saveExistingCinemaTest() {
		InMemoryCinemaPersistence ipct = new InMemoryCinemaPersistence();

		String functionDate = "2018-12-18 15:30";
		List<CinemaFunction> functions = new ArrayList<>();
		CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
		CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
		functions.add(funct1);
		functions.add(funct2);
		Cinema c = new Cinema("Movies Bogotá", functions);

		try {
			ipct.saveCinema(c);
		} catch (CinemaPersistenceException ex) {
			fail("Cinema persistence failed inserting the first cinema.");
		}

		List<CinemaFunction> functions2 = new ArrayList<>();
		CinemaFunction funct12 = new CinemaFunction(new Movie("SuperHeroes Movie 3", "Action"), functionDate);
		CinemaFunction funct22 = new CinemaFunction(new Movie("The Night 3", "Horror"), functionDate);
		functions.add(funct12);
		functions.add(funct22);
		Cinema c2 = new Cinema("Movies Bogotá", functions2);
		try {
			ipct.saveCinema(c2);
			fail("An exception was expected after saving a second cinema with the same name");
		} catch (CinemaPersistenceException ex) {

		}

	}

	@Test
	public void getCinemaByName() throws CinemaPersistenceException {
		InMemoryCinemaPersistence ipct = new InMemoryCinemaPersistence();

		String functionDate = "2018-12-18 15:30";
		List<CinemaFunction> functions = new ArrayList<>();
		CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
		CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
		functions.add(funct1);
		functions.add(funct2);
		Cinema c = new Cinema("Movies Bogotá", functions);
		ipct.saveCinema(c);

		assertNotNull("Loading a previously stored cinema returned null.", ipct.getCinema(c.getName()));
		assertEquals("Loading a previously stored cinema returned a different cinema.", ipct.getCinema(c.getName()), c);
	}

	@Test
	public void consultCinemaByName() {
		List<CinemaFunction> funciones = new ArrayList<CinemaFunction>();
		Movie movie1 = new Movie("aprueba", "Drama");
		CinemaFunction function = new CinemaFunction(movie1, "02/02/2019");

		funciones.add(function);

		Cinema c = new Cinema("Cine Colombia", funciones);
		InMemoryCinemaPersistence cine = new InMemoryCinemaPersistence();
		try {
			cine.saveCinema(c);
		} catch (CinemaPersistenceException e) {
			assertFalse(true);
			e.printStackTrace();
		}
		try {
			Cinema cinemaPrueba = cine.getCinema("Cine Colombia");
			assertEquals(cinemaPrueba.getName(), "Cine Colombia");
		} catch (CinemaPersistenceException e) {
			assertFalse(true);
			e.printStackTrace();
		}
	}

	@Test
	public void buyTicket() {
		List<CinemaFunction> funciones = new ArrayList<CinemaFunction>();

		Movie movie1 = new Movie("aprueba", "Drama");
		CinemaFunction function = new CinemaFunction(movie1, "02/02/2019");
		Movie movie2 = new Movie("trampa", "Accion");
		CinemaFunction function1 = new CinemaFunction(movie2, "02/02/2019");
		Movie movie3 = new Movie("examen", "Drama");
		CinemaFunction function2 = new CinemaFunction(movie3, "02/02/2019");
		Movie movie4 = new Movie("graduarse", "scifi");
		CinemaFunction function3 = new CinemaFunction(movie4, "02/02/2019");
		funciones.add(function);
		funciones.add(function1);
		funciones.add(function2);
		funciones.add(function3);
		Cinema c = new Cinema("Cine Colombia", funciones);
		InMemoryCinemaPersistence cine = new InMemoryCinemaPersistence();
		try {
			cine.saveCinema(c);
		} catch (CinemaPersistenceException e) {
			assertFalse(true);
			e.printStackTrace();
		}

		try {
			cine.buyTicket(1, 1, "Cine Colombia", "02/02/2019", "graduarse");
		} catch (CinemaException e) {
			assertFalse(true);
			e.printStackTrace();
		}

		try {
			cine.buyTicket(1, 1, "Cine Colombia", "02/02/2019", "graduarse");
		} catch (CinemaException e) {
			assertTrue(true);

		}
		
		

	}
	
	
	
	@Test
	public void filterGender() {
		List<CinemaFunction> funciones = new ArrayList<CinemaFunction>();
		Movie movie1 = new Movie("aprueba", "Drama");
		CinemaFunction function = new CinemaFunction(movie1, "02/02/2019");
		Movie movie2 = new Movie("trampa", "Accion");
		CinemaFunction function1 = new CinemaFunction(movie2, "02/02/2019");
		Movie movie3 = new Movie("examen", "Drama");
		CinemaFunction function2 = new CinemaFunction(movie3, "02/02/2019");
		Movie movie4 = new Movie("graduarse", "scifi");
		CinemaFunction function3 = new CinemaFunction(movie4, "02/02/2019");
		funciones.add(function);
		funciones.add(function1);
		funciones.add(function2);
		funciones.add(function3);
		Cinema c = new Cinema("Cine Colombia", funciones);
		InMemoryCinemaPersistence cine = new InMemoryCinemaPersistence();
		cine.setFiltro(new ByGender());
		try {
			cine.saveCinema(c);
		} catch (CinemaPersistenceException e) {
			assertFalse(true);
			e.printStackTrace();
		}
		
		try {
			
			List<Movie> filtrado = cine.filter("Cine Colombia", "02/02/2019", "Accion");
			assertTrue(filtrado.size()==1);
			if(!(filtrado.get(0).getName().equals("trampa"))) {
				assertFalse(true);
			}
			else {
				assertFalse(false);
			}
		}catch (CinemaPersistenceException e) {
			assertFalse(true);
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void filterAvailability() {
		List<CinemaFunction> funciones = new ArrayList<CinemaFunction>();
		Movie movie1 = new Movie("aprueba", "Drama");
		CinemaFunction function = new CinemaFunction(movie1, "02/02/2019");
		Movie movie2 = new Movie("trampa", "Accion");
		CinemaFunction function1 = new CinemaFunction(movie2, "02/02/2019");
		Movie movie3 = new Movie("examen", "Drama");
		CinemaFunction function2 = new CinemaFunction(movie3, "02/02/2019");
		Movie movie4 = new Movie("graduarse", "scifi");
		CinemaFunction function3 = new CinemaFunction(movie4, "02/02/2019");
		funciones.add(function);
		funciones.add(function1);
		funciones.add(function2);
		funciones.add(function3);
		Cinema c = new Cinema("Cine Colombia", funciones);
		InMemoryCinemaPersistence cine = new InMemoryCinemaPersistence();
		cine.setFiltro(new ByAvailability());
		try {
			cine.saveCinema(c);
		} catch (CinemaPersistenceException e) {
			assertFalse(true);
			e.printStackTrace();
		}
		
		try {
			cine.buyTicket(1, 1, "Cine Colombia", "02/02/2019", "graduarse");
			cine.buyTicket(1, 2, "Cine Colombia", "02/02/2019", "graduarse");
			cine.buyTicket(1, 3, "Cine Colombia", "02/02/2019", "graduarse");
			cine.buyTicket(1, 5, "Cine Colombia", "02/02/2019", "graduarse");
			cine.buyTicket(2, 1, "Cine Colombia", "02/02/2019", "graduarse");
			cine.buyTicket(2, 2, "Cine Colombia", "02/02/2019", "graduarse");
			cine.buyTicket(2, 3, "Cine Colombia", "02/02/2019", "graduarse");
			cine.buyTicket(2, 4, "Cine Colombia", "02/02/2019", "graduarse");
			cine.buyTicket(2, 6, "Cine Colombia", "02/02/2019", "graduarse");
			cine.buyTicket(3, 1, "Cine Colombia", "02/02/2019", "graduarse");
			cine.buyTicket(3, 3, "Cine Colombia", "02/02/2019", "graduarse");
			cine.buyTicket(3, 2, "Cine Colombia", "02/02/2019", "graduarse");
			
			
			List<Movie> filtrado = cine.filter("Cine Colombia", "02/02/2019", "80");
			
			assertTrue(filtrado.size()==3);
			
		}catch (CinemaPersistenceException e) {
			assertFalse(true);
			e.printStackTrace();
		} catch (CinemaException e) {
			assertFalse(true);
			e.printStackTrace();
		}
	}

}
