package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		cdao.getTuttiICorsi();
		
		StudenteDAO sdao = new StudenteDAO();
		System.out.println(sdao.getStudente(new Studente(146101, "", "", "")));
		System.out.println(cdao.getStudentiIscrittiAlCorso(new Corso ("01NATPG", 0, "", 0)));
		
		
	}

}
