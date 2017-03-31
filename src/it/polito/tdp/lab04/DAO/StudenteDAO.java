package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudenteDAO(int matricola) {

		final String sql = "Select matricola,cognome,nome,cds from studente where matricola = ?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"),
						rs.getString("cds"));

				return s;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		return null;
	}
	
	public boolean studenteIscrittoAlCorso(Corso corso,int matricola) {
		// TODO
		

			final String sql = "Select studente.matricola, studente.nome ,studente.cognome,corso.codins FROM iscrizione, studente,corso"+
					"where iscrizione.matricola=studente.matricola and iscrizione.codins = corso.codins"+
					"and corso.codins=? and iscrizione.matricola = ?";


			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setString(1,corso.getCodins());
				st.setInt(2,matricola);

				ResultSet rs = st.executeQuery();
                 
				System.out.println(rs);
				


			} catch (SQLException e) {
				 e.printStackTrace();
				throw new RuntimeException("Errore Db");
			}
			return false;
		
	}

	/*
	 * Data una matricola ed il codice insegnamento,
	 * iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		return false;
	}

}
