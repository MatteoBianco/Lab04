package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO cDAO;
	private StudenteDAO sDAO;
	
	public Model() {
		
		cDAO = new CorsoDAO();
		sDAO = new StudenteDAO();
	}
	
	public List<Corso> elencoCorsi() {
		
		return cDAO.getTuttiICorsi();
	}
	
	public Studente datiStudente(int matricola) {
		
		return sDAO.getStudente(new Studente(matricola, "", "", ""));
	}
	
	public List<Studente> elencoStudentiPerCorso(Corso corso) {
		
		if(corso.getCodins() == "") {				
			return this.elencoStudentiIscritti();
		}
		return cDAO.getStudentiIscrittiAlCorso(corso);
	}

	public List<Studente> elencoStudentiIscritti() {
		
		return sDAO.getStudentiIscritti();
	}
	
	public List<Corso> elencoCorsiPerStudente(Studente studente) {
		
		return sDAO.getCorsiPerStudente(studente);
	}
}
