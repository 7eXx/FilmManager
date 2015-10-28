/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmmanager;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import support.Attore;
import support.Cinema;
import support.Film;
import support.Genere;
import support.Produttore;
import support.Regista;

/**
 * FXML Controller class
 *
 * @author Mark
 */
public class FilmModificaController implements Initializable {
    
    private Film film;
    boolean modifica;
    FilmManagerController main;
    
    ObservableList<Attore> attoriPart, attoriNonPart;
    
    Regista rlast;
    
    @FXML
    private TextField tfIdFilm, tfNome, tfDurata, tfNazione, tfVoto, tfBudget, tfNumOscar;
    @FXML
    private DatePicker dpDataUscita;
    @FXML
    private TextArea taDescrizione;
    @FXML
    private Button btConferma, btAnnulla;
    @FXML
    private ListView<Attore> listAttori;
    @FXML
    private ListView<Produttore> listProduttori;
    @FXML
    private ListView<Genere> listGeneri;
    @FXML
    private ChoiceBox<Regista> choiceRegista;
    
    
    public void initData(Film film, boolean modifica, FilmManagerController manager) {
        
        this.modifica = modifica;
        this.film = film;
        this.main = manager;
        
        choiceRegista.setItems(Cinema.getInfo(Regista.class, null, false));
        
        if (modifica) {
            
            tfIdFilm.setText(film.getId() + "");
            tfNome.setText(film.getNome());
            
            tfDurata.setText(film.getDurata() + "");
            tfNazione.setText(film.getNazione());
            
            DecimalFormat df = new DecimalFormat("#.##");
            tfBudget.setText(df.format(film.getBudget()));
            
            if (film.getData_uscita() != null) {
                dpDataUscita.setValue(LocalDate.parse(film.getData_uscita()));
            }
            
            tfVoto.setText(film.getVoto() + "");
            taDescrizione.setText(film.getDescrizione());
            tfNumOscar.setText(film.getNum_oscar() + "");
            
            attoriPart = Cinema.getInfo(Attore.class, film, true);
            attoriNonPart = Cinema.getInfo(Attore.class, film, false);
            listAttori.setItems(attoriPart);
            
            listGeneri.setItems(Cinema.getInfo(Genere.class, film, true));
            listProduttori.setItems(Cinema.getInfo(Produttore.class, film, true));
            
            Regista r = (Regista) Cinema.getInfo(Regista.class, film, true).get(0);
            boolean find = false;
            for (int i = 0; i < choiceRegista.getItems().size() && !find; i++) {
                if(choiceRegista.getItems().get(i).getId() == r.getId())
                    choiceRegista.getSelectionModel().select(i);
            }
        }
        else
        {
            attoriPart = FXCollections.observableArrayList();
            attoriNonPart = Cinema.getInfo(Class.class, null, false);
        }
        /*
        for (Attore next : attoriPart) {
            System.out.println("presente: " + next);
        }
        
        for (Attore next : attoriNonPart) {
            System.out.println("non presente " + next);
        }
        */
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // prima viene invocato initialize e poi il metodo per mandare i dati
    }
    
    public void onClickConferma(ActionEvent event) {
        if (!tfNome.getText().trim().equals("")) {
            
            if (!modifica) {
                film = new Film();
            }
            
            film.setNome(tfNome.getText());
            
            if (!tfDurata.getText().trim().equals("")) {
                film.setDurata(Integer.parseInt(tfDurata.getText()));
            } else {
                film.setDurata(0);
            }
            
            if (tfNazione.getText() != null && !tfNazione.getText().trim().equals("")) {
                film.setNazione(tfNazione.getText());
            } else {
                film.setNazione(null);
            }
            
            if (!tfBudget.getText().trim().equals("")) {
                film.setBudget(Double.parseDouble(tfBudget.getText()));
            } else {
                film.setBudget(0);
            }
            
            if (dpDataUscita.getValue() != null) {
                film.setData_uscita(dpDataUscita.getValue().toString());
            } else {
                film.setData_uscita(null);
            }
            
            if (!tfVoto.getText().trim().equals("")) {
                film.setVoto(Integer.parseInt(tfVoto.getText()));
            } else {
                film.setVoto(0);
            }
            
            if (taDescrizione.getText() != null && !taDescrizione.getText().trim().equals("")) {
                film.setDescrizione(taDescrizione.getText());
            } else {
                film.setDescrizione(null);
            }
            
            if (!tfNumOscar.getText().trim().equals("")) {
                film.setNum_oscar(Integer.parseInt(tfNumOscar.getText()));
            } else {
                film.setNum_oscar(0);
            }
            
            if (modifica) {
                
                Cinema.updateInfo(film);
            } // altrimenti inserimento
            else {
                
                Cinema.insertInfo(film);
                main.insertInfo(film);
            }  
            
            
            Stage stage = (Stage) btConferma.getScene().getWindow();
            stage.close();
        }
    }
    
    public void onClickAnnulla(ActionEvent event) {
        Stage stage = (Stage) btAnnulla.getScene().getWindow();
        stage.close();
    }
    
}
