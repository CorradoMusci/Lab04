package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	private LinkedList<Studente> studenti = new LinkedList<Studente>();
	private List<Corso> corsi = new LinkedList<Corso>();

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo Corso alla lista

				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"),
						rs.getInt("pd"));
				corsi.add(c);
			}

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO

	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public LinkedList<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		// TODO

		final String sql = "Select studente.matricola,studente.nome ,studente.cognome,studente.cds FROM iscrizione, studente"
				+ " WHERE iscrizione.matricola=studente.matricola AND codins=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, corso.getCodins());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Studente s = new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"),
						rs.getString("cds"));
				studenti.add(s);
			}

			return studenti;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

	}

	public boolean studenteIscrittoAlCorso(int matricola, Corso corso) {
		// TODO

		final String sql = "Select studente.matricola,studente.nome ,studente.cognome,studente.cds FROM iscrizione,studente"
				+ " WHERE iscrizione.matricola=studente.matricola AND codins=? And studente.matricola = ?";
		int cont = 0;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, corso.getCodins());
			st.setInt(2, matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				cont++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		if(cont != 0)
			return true;
			else 
				return false;	
	}
	
	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al
	 * corso.
	 */
	public boolean inscriviStudenteACorso(int matricola, Corso corso) {
		// TODO


		 if (!this.studenteIscrittoAlCorso(matricola, corso)) {
				final String sql = "Insert into iscrizione values (?,?)";

				try {
					Connection conn = ConnectDB.getConnection();
					PreparedStatement st = conn.prepareStatement(sql);

					st.setInt(1,matricola);
					st.setString(2,corso.getCodins());

					int rs = st.executeUpdate();

				

				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException("Errore Db");
				}
				return false;
		 }

		return true;
	}
}
