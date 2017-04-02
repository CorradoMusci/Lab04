package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	private LinkedList<Corso> corsi = new LinkedList<Corso>();
	private CorsoDAO corsoDAO = new CorsoDAO();

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

	public LinkedList<Corso> getCorsiDelloStudente(int matricola) {
		// TODO

		final String sql = "Select corso.codins,corso.crediti,corso.nome,corso.pd FROM iscrizione,corso"
				+ " WHERE iscrizione.codins=corso.codins AND iscrizione.matricola=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"),
						rs.getInt("pd"));
				corsi.add(c);
			}

			return corsi;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	

}
