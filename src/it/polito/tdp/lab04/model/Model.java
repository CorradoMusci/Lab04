package it.polito.tdp.lab04.model;


import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	
	public List<Corso> getCorsi(){
		
		CorsoDAO corsoDAO = new CorsoDAO();
		
		return corsoDAO.getTuttiICorsi();
	}
	
	public Studente getStudente(int matricola){
		
		StudenteDAO studenteDAO = new StudenteDAO();
		
		return studenteDAO.getStudenteDAO(matricola);
	}
	
	public List<Studente> getStudentiInscrittiAlCorso(Corso corso){
		
		CorsoDAO corsoDAO = new CorsoDAO();
		
		return corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	

}
