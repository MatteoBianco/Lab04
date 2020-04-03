/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;

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

public class FXMLController {
	
	private Model model;

    @FXML
    private ComboBox<Corso> comboBox;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtCognome;

    @FXML
    private CheckBox btnCheck;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private Button btnCercaCorsi;
    
    @FXML
    private Button btnVerificaIscrizione;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtArea;

    @FXML
    private Button btnReset;

    @FXML
    void cercaCorsi(ActionEvent event) {
    	
    	txtArea.clear();
    	if(!this.controlloMatricola(txtMatricola.getText()))
			return;
		int matricola = Integer.parseInt(txtMatricola.getText());
		
		Studente s = model.datiStudente(matricola);
		if(s.getCognome().equals("") && s.getNome().equals("")) {
			txtArea.setText("Nessuno studente trovato con la matricola selezionata\n");
		}
		else {
			if(model.elencoCorsiPerStudente(s).isEmpty()) {
				txtArea.setText("Lo studente selezionato non è iscritto ad alcun corso\n");
			}
			else {
				for(Corso c : model.elencoCorsiPerStudente(s)) {
					txtArea.appendText(c.toString());
				}
			}
		}
		
    	
    }

    @FXML
    void cercaIscritti(ActionEvent event) {
    	
    	txtArea.clear();
    	Corso corso = comboBox.getValue();
    	if(corso == null) {
    		txtArea.setText("Attenzione: selezionare un corso, o lo spazio vuoto per visualizzare "
    				+ "gli studenti iscritti a qualsiasi corso\n");
    		return;
    	}
    	if(model.elencoStudentiPerCorso(corso).isEmpty()) {
    		txtArea.setText("Nessuno studente iscritto al corso selezionato\n");
    	}
    	else {
    		for(Studente s : model.elencoStudentiPerCorso(corso)) {
    		txtArea.appendText(s.toString());
    		}
    	}
    }

    @FXML
    void check(ActionEvent event) {
    	
		txtArea.clear();
    	if(btnCheck.isSelected()){
    		if(!this.controlloMatricola(txtMatricola.getText()))
    			return;
    		int matricola = Integer.parseInt(txtMatricola.getText());
    		Studente s = model.datiStudente(matricola);
    		if(s.getCognome().equals("") && s.getNome().equals("")) {
    			txtArea.setText("Nessuno studente trovato con la matricola selezionata\n");
    		}
    		else {
    			txtCognome.setText(s.getCognome());
    			txtNome.setText(s.getNome());
    		}
    	}
    	else {
    		txtCognome.clear();
    		txtNome.clear();
    	}
    }

    
    @FXML
    void verificaIscrizione(ActionEvent event) {
    	
    	txtArea.clear();
    	if(!this.controlloMatricola(txtMatricola.getText()))
			return;
		int matricola = Integer.parseInt(txtMatricola.getText());
		Studente s = model.datiStudente(matricola);
		if(s.getCognome().equals("") && s.getNome().equals("")) {
			txtArea.setText("Nessuno studente trovato con la matricola selezionata\n");
			return;
		}
		if(comboBox.getValue() == null || comboBox.getValue().getCodins().equals("")) {
			txtArea.setText("Attenzione: selezionare un corso!");
    		return;
		}
		
		if(model.elencoCorsiPerStudente(s).contains(comboBox.getValue())) {
			txtArea.setText("Studente " + s.getMatricola() + " (" + s.getCognome() + " " +
					s.getNome() + ") iscritto regolarmente al corso " + comboBox.getValue().getCodins() + " (" + comboBox.getValue().getNome() + ")");
		}
		else txtArea.setText("Studente " + s.getMatricola() + " (" + s.getCognome() + " " +
					s.getNome() + ") non iscritto al corso " + comboBox.getValue().getCodins() + " (" + comboBox.getValue().getNome() + ")");
    }
    
    @FXML
    void iscrivi(ActionEvent event) {
    
    	txtArea.clear();
    	if(!this.controlloMatricola(txtMatricola.getText()))
			return;
		int matricola = Integer.parseInt(txtMatricola.getText());
		Studente s = model.datiStudente(matricola);
		if(s.getCognome().equals("") && s.getNome().equals("")) {
			txtArea.setText("Nessuno studente trovato con la matricola selezionata\n");
			return;
		}
		if(comboBox.getValue() == null || comboBox.getValue().getCodins().equals("")) {
			txtArea.setText("Attenzione: selezionare un corso!\n");
    		return;
		}
		if(model.elencoCorsiPerStudente(s).contains(comboBox.getValue())) {
			txtArea.setText("Studente già iscritto al corso selezionato\n");
			return;
		}
		boolean iscritto = model.iscrivi(s, comboBox.getValue());
		if(iscritto)
			txtArea.setText("Iscrizione completata con successo\n");
		else txtArea.setText("Problema nella procedura d'iscrizione\n");
    	  
    }

    @FXML
    void reset(ActionEvent event) {
    	comboBox.getSelectionModel().clearSelection();
    	txtMatricola.clear();
    	txtCognome.clear();
    	txtNome.clear();
    	txtArea.clear();
    	btnCheck.setSelected(false);
    }
    
    private boolean controlloMatricola(String matricola) {
    	
    	if(matricola.equals("")) {
    		txtArea.setText("Inserire matricola\n");
    		return false;
    	}
		
		try{
			Integer.parseInt(txtMatricola.getText());
		} catch(NumberFormatException e) {
			txtArea.setText("La matricola deve contenere solo valori numerici \n");
			return false;
		}
    	return true;
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

	public void setModel(Model model) {
		
		this.model = model;
		comboBox.getItems().add(new Corso("", 0, "", 0));
		comboBox.getItems().addAll(model.elencoCorsi());
	}
}