package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO corsoDAO = new CorsoDAO();

	private StudenteDAO studenteDAO = new StudenteDAO();
	
	
	public List<Corso> getCorsi() {
		return corsoDAO.getTuttiICorsi();
	}

	public Studente getStudente(int matricola) {
		return studenteDAO.getStudenteDAO(matricola);
	}

	public List<Studente> getStudentiInscrittiAlCorso(Corso corso) {
		return corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCorsiDelloStudente(int matricola){
		return studenteDAO.getCorsiDelloStudente(matricola);
	}
	
	public boolean studenteIscrittoAlCorso(int matricola, Corso corso) {
		return corsoDAO.studenteIscrittoAlCorso(matricola, corso);
	}
	
	public boolean inscriviStudenteACorso(int matricola, Corso corso) {
	return corsoDAO.inscriviStudenteACorso(matricola, corso);
	}

}
