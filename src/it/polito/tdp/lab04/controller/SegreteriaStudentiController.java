package it.polito.tdp.lab04.controller;


import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.StudenteDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	private Model model;
	List<Corso> corsi = new LinkedList<Corso>();
	
	 @FXML
     private CheckBox checkCercaNome;

	@FXML
	private ComboBox<Corso> comboCorso;

	@FXML
	private Button btnCercaIscrittiCorso;

	@FXML
	private Button btnCercaCorsi;

	@FXML
	private Button btnCercaNome;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnIscrivi;

	@FXML
	private TextField txtMatricola;

	@FXML
	private Button btnReset;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	public void setModel(Model model) {
		
		this.model = model;
		
		
		
		comboCorso.getItems().add(new Corso("", 0, "", 0));
		
		comboCorso.getItems().addAll(model.getCorsi());
	}

	@FXML
	void doReset(ActionEvent event) {
		
		txtMatricola.clear();
		
		txtNome.clear();
		
		txtCognome.clear();
		
		txtResult.clear();
		

	}

	@FXML
	void doCercaNome(ActionEvent event) {
		
		if(checkCercaNome.isSelected()){
			
			int matricola =  Integer.parseInt(txtMatricola.getText());
			
			Studente s  = model.getStudente(matricola);
			
			txtNome.setText(s.getNome());
			
			txtCognome.setText(s.getCognome());
		}

	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {
		
		Corso corso = comboCorso.getValue();
		
		if(corso == null){
			txtResult.setText("corso non selezionato");
		}else{
			for(Studente s : model.getStudentiInscrittiAlCorso(corso)){
				System.out.println(model);
				txtResult.appendText(s.getMatricola()+" "+s.getCognome()+" "+s.getNome()+"\n");
			}
		}
		
	

	}

	@FXML
	void doCercaCorsi(ActionEvent event) {
		
		StudenteDAO s = new StudenteDAO();
		
		Corso corso = comboCorso.getValue();
		int matricola  = Integer.parseInt(txtMatricola.getText());
	    
		
		
		boolean trovato = s.studenteIscrittoAlCorso(corso, matricola);
		
		if(trovato == true)
			txtResult.setText("Studente trovato");
		else
			txtResult.setText("Studente non trovato");
		

	}

	@FXML
	void doIscrivi(ActionEvent event) {

	}

	@FXML
	void initialize() {
		
		
		assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	}

}
