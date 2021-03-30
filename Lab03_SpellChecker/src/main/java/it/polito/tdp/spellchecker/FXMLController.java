package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	private Dictionary model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextArea txtTesto;

    @FXML
    private Button btnSpell;

    @FXML
    private Label wrongWords;

    @FXML
    private TextArea txtResult;

    @FXML
    private Label nErrors;

    @FXML
    private Button btnClear;

    @FXML
    private Label time;

    @FXML
    void doClear(ActionEvent event) {
    	
    	this.model.resetDictionary();
    	txtResult.clear();
    	txtTesto.clear();
    }
    
    @FXML
    void handleLingua(ActionEvent event) {
    	this.model.loadDictionary(comboBox.getValue());
    }

    @FXML
    void doSpell(ActionEvent event) {
    	
    	long inizio = System.currentTimeMillis();
    	
    	List<String> lista = new LinkedList<String>();
    	String language = txtTesto.getText();
    	language.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_()\\[\\]\"]", "");
    	
    	String[] array = language.split(" ");
    	for(int i=0; i<array.length;i++) {
    		lista.add(array[i].toLowerCase());
    	}

    	this.model.spellCheckTest(lista);
    	txtResult.setText(this.model.elencoParoleErrate());
    	
    	nErrors.setText("Sono presenti " + this.model.numeroParoleErrate() + " parole errate");
    	long fine = System.currentTimeMillis();
    	time.setText("Il tempo in millisecondi è: "+(fine-inizio));
    }

    @FXML
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'Scene.fxml'.";
        assert wrongWords != null : "fx:id=\"wrongWords\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert nErrors != null : "fx:id=\"nErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Dictionary model) {
    	this.model = model;
    	comboBox.getItems().addAll("English","Italian");
    }
}



