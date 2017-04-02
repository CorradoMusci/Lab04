package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class MainDiProvaDAO {

	public static void main(String[] args) {

		CorsoDAO c = new CorsoDAO();

		System.out.println(c.getTuttiICorsi());

		StudenteDAO sDAO = new StudenteDAO();

		Studente s = sDAO.getStudenteDAO(156622);

		System.out.println(s.getNome());
        
		Corso s1  = null;
		for (Corso f : sDAO.getCorsiDelloStudente(156622)) {
			System.out.println(f.toString() + "\n");
		    s1 = f;
		}
		
		
		c.inscriviStudenteACorso(156622,s1);
		
		for (Corso f : sDAO.getCorsiDelloStudente(156622)) {
			System.out.println(f.toString() + "\n");
		    s1 = f;
		}

	}
}