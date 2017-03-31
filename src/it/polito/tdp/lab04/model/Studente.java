package it.polito.tdp.lab04.model;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Studente {

	private int matricola;
	private String nome;
	private String cognome;
	private String cds;

	public Studente(int matricola, String nome, String cognome, String cds) {
		super();
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.cds = cds;
	}

	public Studente() {
		// TODO Auto-generated constructor stub
	}

	public int getMatricola() {
		return matricola;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getCds() {
		return cds;
	}
	
	

}
