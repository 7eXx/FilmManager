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
 *  questa classe rappresenta l'entita' attore
 * @author Mark
 */
public class Attore {
    
    public static final String ID           = "ID_attore";
    public static final String NOME         = "nome";
    public static final String COGNOME      = "cognome";
    public static final String NAZIONE      = "nazione";
    public static final String CITTA        = "citta";
    public static final String DATA_NASCITA = "data_nascita";
    public static final String BIOGRAFIA    = "biografia";
    
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nome = new SimpleStringProperty();
    private final StringProperty cognome = new SimpleStringProperty();
    private final StringProperty nazione = new SimpleStringProperty();
    private final StringProperty citta = new SimpleStringProperty();
    private final StringProperty data_nascita = new SimpleStringProperty();
    private final StringProperty biografia = new SimpleStringProperty();

    public String getBiografia() {
        return biografia.get();
    }

    public void setBiografia(String value) {
        biografia.set(value);
    }

    public StringProperty biografiaProperty() {
        return biografia;
    }

    public String getData_nascita() {
        return data_nascita.get();
    }

    public void setData_nascita(String value) {
        data_nascita.set(value);
    }

    public StringProperty data_nascitaProperty() {
        return data_nascita;
    }
    
    public String getCitta() {
        return citta.get();
    }

    public void setCitta(String value) {
        citta.set(value);
    }

    public StringProperty cittaProperty() {
        return citta;
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

    public String getCognome() {
        return cognome.get();
    }

    public void setCognome(String value) {
        cognome.set(value);
    }

    public StringProperty cognomeProperty() {
        return cognome;
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
        return nome + " " + cognome + " - " + nazione + " - " + citta;
    }
    
}
