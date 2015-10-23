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
public class Genere {
    
    public static final String ID = "ID_genere";
    public static final String GENERE = "genere";
    public static final String DESCRIZIONE = "descrizione";
    
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty genere = new SimpleStringProperty();
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

    public String getGenere() {
        return genere.get();
    }

    public void setGenere(String value) {
        genere.set(value);
    }

    public StringProperty genereProperty() {
        return genere;
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
    public String toString ()
    {
        return genere.get();
    }
}
