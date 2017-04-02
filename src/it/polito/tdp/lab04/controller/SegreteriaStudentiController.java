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
		txtResult.clear();

		if (checkCercaNome.isSelected()) {
			int matricola = 0;

			try {
				matricola = Integer.parseInt(txtMatricola.getText());
			} catch (Exception e) {
				txtResult.setText("Formato matricola non corretto\n");
			}

			Studente s = model.getStudente(matricola);
			try {
				txtNome.setText(s.getNome());

				txtCognome.setText(s.getCognome());
			} catch (Exception e) {
				txtResult.setText("Formato matricola non corretto\n");
			}
		}

	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {
		txtResult.clear();

		Corso corso = comboCorso.getValue();

		if (corso == null) {
			txtResult.setText("corso non selezionato");
		} else {
			for (Studente s : model.getStudentiInscrittiAlCorso(corso)) {
				txtResult.appendText(s.getMatricola() + " " + s.getCognome() + " " + s.getNome() + "\n");
			}
		}

	}

	@FXML
	void doCercaCorsi(ActionEvent event) {
		txtResult.clear();

		Corso corso = comboCorso.getValue();
		int matricola = 0;

		try {
			matricola = Integer.parseInt(txtMatricola.getText());
		} catch (NumberFormatException e) {
			txtResult.setText("Formato matricola non corretto");
			return;
		}

		matricola = Integer.parseInt(txtMatricola.getText());

		if (corso.toString() == "") {
			for (Corso c : model.getCorsiDelloStudente(matricola)) {
				txtResult.appendText(c.toString() + "\n");
			}
		} else {
			if (model.studenteIscrittoAlCorso(matricola, corso) == true)
				txtResult.appendText("Lo studente " + matricola + " segue il corso " + corso.toString());
			else
				txtResult.appendText("Lo studente " + matricola + " non segue il corso " + corso.toString());
		}
	}

	@FXML
	void doIscrivi(ActionEvent event) {
		txtResult.clear();

		Corso corso = comboCorso.getValue();
		Integer matricola = null;
		try {
			matricola = Integer.parseInt(txtMatricola.getText());
		} catch (NumberFormatException e) {
			txtResult.setText("Formato matricola non corretto");
			return;
		}

		if (corso != null && corso.getNome() != "") {
			boolean trovato = model.inscriviStudenteACorso(matricola, corso);
			if (trovato)
				txtResult.appendText("Studente già inscritto");
			else
				txtResult.appendText("Studente inscritto con successo");
		} else {
			txtResult.setText("Formato non corretto");
			return;
		}
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
