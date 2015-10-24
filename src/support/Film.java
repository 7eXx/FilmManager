/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Mark
 */
public class Film {
    
    public static final String ID           = "ID_film";
    public static final String NOME         = "nome";
    public static final String DURATA       = "durata";
    public static final String NAZIONE      = "nazione";
    public static final String BUDGET       = "budget";
    public static final String DATA_USCITA  = "data_uscita";
    public static final String VOTO         = "voto";
    public static final String DESCRIZIONE  = "descrizione";
    public static final String NUM_OSCAR    = "num_oscar";
    
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nome = new SimpleStringProperty();
    private final IntegerProperty durata = new SimpleIntegerProperty();
    private final StringProperty nazione = new SimpleStringProperty();
    private final DoubleProperty budget = new SimpleDoubleProperty();
    private final StringProperty data_uscita = new SimpleStringProperty();
    private final IntegerProperty voto = new SimpleIntegerProperty();
    private final StringProperty descrizione = new SimpleStringProperty();
    private final IntegerProperty num_oscar = new SimpleIntegerProperty();

    public int getNum_oscar() {
        return num_oscar.get();
    }

    public void setNum_oscar(int value) {
        num_oscar.set(value);
    }

    public IntegerProperty num_oscarProperty() {
        return num_oscar;
    }

    public String getDescrizione() {
        return descrizione.get();
    }

    public void setDescrizione(String value) {
        descrizione.set(value);
    }

    public StringProperty descrizioneProperty() {
        return descrizione;
    }

    public int getVoto() {
        return voto.get();
    }

    public void setVoto(int value) {
        voto.set(value);
    }

    public IntegerProperty votoProperty() {
        return voto;
    }

    public String getData_uscita() {
        return data_uscita.get();
    }

    public void setData_uscita(String value) {
        data_uscita.set(value);
    }

    public StringProperty data_uscitaProperty() {
        return data_uscita;
    }

    public double getBudget() {
        return budget.get();
    }

    public void setBudget(double value) {
        budget.set(value);
    }

    public DoubleProperty budgetProperty() {
        return budget;
    }

    public String getNazione() {
        return nazione.get();
    }

    public void setNazione(String value) {
        nazione.set(value);
    }

    public StringProperty nazioneProperty() {
        return nazione;
    }
    
    public int getDurata() {
        return durata.get();
    }

    public void setDurata(int value) {
        durata.set(value);
    }

    public IntegerProperty durataProperty() {
        return durata;
    }
    
    public String getNome() {
        return nome.get();
    }

    public void setNome(String value) {
        nome.set(value);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }
  
    @Override
    public String toString()
    {
        return  nome.get() + " - " + durata.get() + " min";
    }

}
