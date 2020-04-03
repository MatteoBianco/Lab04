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
    private Button btnIscrivi;

    @FXML
    private TextArea txtArea;

    @FXML
    private Button btnReset;

    @FXML
    void cercaCorsi(ActionEvent event) {
    	
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
    		int matricola;
    		try{
    			matricola = Integer.parseInt(txtMatricola.getText());
    		} catch(NumberFormatException e) {
    			txtArea.setText("La matricola deve contenere solo valori numerici \n");
    			return;
    		}
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
    void iscrivi(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {

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