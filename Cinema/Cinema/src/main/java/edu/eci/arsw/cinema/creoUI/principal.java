package edu.eci.arsw.cinema.creoUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaServices;

public class principal {
	public static void main(String a[]) {

		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		CinemaServices cs = ac.getBean(CinemaServices.class);
		List<CinemaFunction> funciones = new ArrayList<CinemaFunction>();

		Movie movie1 = new Movie("aprueba", "Drama");
		CinemaFunction function = new CinemaFunction(movie1, "02/02/2019");

		Movie movie2 = new Movie("trampa", "Accion");
		CinemaFunction function1 = new CinemaFunction(movie2, "02/02/2019");

		Movie movie3 = new Movie("examen", "Drama");
		CinemaFunction function2 = new CinemaFunction(movie3, "02/02/2019");

		Movie movie4 = new Movie("graduarse", "scifi");
		CinemaFunction function3 = new CinemaFunction(movie4, "03/02/2019");

		funciones.add(function);
		funciones.add(function1);
		funciones.add(function2);
		funciones.add(function3);

		Cinema c = new Cinema("Cine Colombia", funciones);

		try {
			System.out.println("               Registrar cine " );
			
			cs.addNewCinema(c);
			System.out.println(cs.getCinemaByName("Cine Colombia").getName());
			
			
			System.out.println("               Consultar cine ");
			Map<String, Cinema> cines = cs.getAllCinemas();
			for (String ci : cines.keySet()) {
				System.out.println(cines.get(ci).getName());
			}

			System.out.println("               Obtener funciones por la fecha");
			List<CinemaFunction> cf = cs.getFunctionsbyCinemaAndDate("Cine Colombia", "02/02/2019");
			for (CinemaFunction pelicula : cf) {
				System.out.println("movie " + pelicula.getMovie().getName() + "  fecha " + pelicula.getDate());
			}
			cf = cs.getFunctionsbyCinemaAndDate("Cine Colombia", "03/02/2019");
			for (CinemaFunction pelicula : cf) {
				System.out.println("movie " + pelicula.getMovie().getName() + "  fecha " + pelicula.getDate());
			}

			System.out.println("             Comprar tickets");
			cs.buyTicket(0, 0, "Cine Colombia", "02/02/2019", "examen");
			cs.buyTicket(0, 1, "Cine Colombia", "02/02/2019", "examen");
			cs.buyTicket(0, 2, "Cine Colombia", "02/02/2019", "examen");
			cs.buyTicket(0, 3, "Cine Colombia", "02/02/2019", "examen");
			cs.buyTicket(0, 4, "Cine Colombia", "02/02/2019", "examen");
			cs.buyTicket(0, 5, "Cine Colombia", "02/02/2019", "examen");
			cs.buyTicket(1, 0, "Cine Colombia", "02/02/2019", "examen");
			cs.buyTicket(2, 0, "Cine Colombia", "02/02/2019", "examen");
			cs.buyTicket(3, 0, "Cine Colombia", "02/02/2019", "examen");
			System.out.println("Comprado");
			
			 
            /*
            System.out.println("              Filtro genero");
            List<Movie> filtrado = cs.filter("Cine Colombia", "02/02/2019", "Accion");        
            if(filtrado.size()==1) {
            	System.out.println("Peliculas con genero Accion son: ");
            	for(Movie m:filtrado) {
            		System.out.println(m.getName());
            	}
            }
             else {
            	System.out.println("Error en el filtro generos");
            }
            
            */
           
            System.out.println("                        Filtro por disponibilidad ");                 
                       
            List<Movie> filtrado = cs.filter("Cine Colombia", "02/02/2019", "80");     
           
            if(filtrado.size()==2) {
            	System.out.println("Peliculas con mas de 80 asientos son: ");
            	for(Movie m:filtrado) {
            		System.out.println(m.getName());
            	}
            }
            else {
            	System.out.println("Error en el filtro disponibilidad");
            }
            
            
            
		} catch (CinemaException ex) {
			System.out.println(ex.getMessage());
		} catch (CinemaPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
