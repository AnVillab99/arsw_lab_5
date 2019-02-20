package edu.eci.arsw.cinema.creoUI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.cinema.services.CinemaServices;

public class principal {
	public static void main(String a[]) {

		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		CinemaServices cs = ac.getBean(CinemaServices.class);
	}

}
