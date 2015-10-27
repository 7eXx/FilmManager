/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author marco
 */
public class Produttore {
    
    public static final String ID = "ID_casa";
    public static final String NOME = "nome";
    public static final String NAZIONE = "nazione";
    public static final String DESCRIZIONE = "descrizione";
    
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nome = new SimpleStringProperty();
    private final StringProperty nazione = new SimpleStringProperty();
    private final StringProperty descrizione = new SimpleStringProperty();

    public String getDescrizione() {
        return descrizione.get();
    }

    public void setDescrizione(String value) {
        descrizione.set(value);
    }

    public StringProperty descrizioneProperty() {
        return descrizione;
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
        return nome.get();
    }
    
}
