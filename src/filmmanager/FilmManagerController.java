/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmmanager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import support.Attore;
import support.Cinema;
import support.Film;
import support.Regista;

/**
 *
 * @author marco
 */
public class FilmManagerController implements Initializable {

    private static final Logger LOG = Logger.getLogger(FilmManagerController.class.getName());

    ObservableList<Film> films;
    ObservableList<Attore> attori;
    ObservableList<Regista> registi;

    @FXML
    private TabPane tabPaneInfo;

    @FXML
    private Tab tabFilms, tabAttori, tabDirettori, tabProduttori, tabGeneri;

    @FXML
    private TableView<Film> tableFilms;
    @FXML
    private TableColumn filmNome, filmDurata, filmNazione, filmBudget, filmDataUscita, filmVoto, filmNumOscar;

    @FXML
    private TableView<Attore> tableAttori;
    @FXML
    private TableColumn attoreNome, attoreCognome, attoreNazione, attoreCitta, attoreDataNascita;

    @FXML
    private TableView<Regista> tableRegisti;
    @FXML
    private TableColumn registaNome, registaCognome, registaNazione, registaDataNascita;

    @FXML
    private TableView tableGeneri;
    @FXML
    private TableView tableProduttori;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        assert tableFilms != null : "fx:id=\"tableFilms\" was not injected: check your FXML file 'FilmManager.fxml'.";

        // caricamento colonne
        filmNome.setCellValueFactory(new PropertyValueFactory("nome"));
        filmDurata.setCellValueFactory(new PropertyValueFactory("durata"));
        filmNazione.setCellValueFactory(new PropertyValueFactory("nazione"));
        filmBudget.setCellValueFactory(new PropertyValueFactory("budget"));
        filmDataUscita.setCellValueFactory(new PropertyValueFactory("data_uscita"));
        filmVoto.setCellValueFactory(new PropertyValueFactory("voto"));
        filmNumOscar.setCellValueFactory(new PropertyValueFactory("num_oscar"));

        attoreNome.setCellValueFactory(new PropertyValueFactory("nome"));
        attoreCognome.setCellValueFactory(new PropertyValueFactory("cognome"));
        attoreNazione.setCellValueFactory(new PropertyValueFactory("nazione"));
        attoreCitta.setCellValueFactory(new PropertyValueFactory("citta"));
        attoreDataNascita.setCellValueFactory(new PropertyValueFactory("data_nascita"));

        registaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        registaCognome.setCellValueFactory(new PropertyValueFactory("cognome"));
        registaNazione.setCellValueFactory(new PropertyValueFactory("nazione"));
        registaDataNascita.setCellValueFactory(new PropertyValueFactory("data_nascita"));

        // Carica i Film
        films = Cinema.getInfo(Film.class);
        tableFilms.setItems(films);

        // carica gli attori
        attori = Cinema.getInfo(Attore.class);
        tableAttori.setItems(attori);

        // carica i registi
        registi = Cinema.getInfo(Regista.class);
        tableRegisti.setItems(registi);

        // seleziona una riga
        //tableFilms.getSelectionModel().clearSelection();
    }

    public void onClickModifica(ActionEvent event) throws IOException {

        LOG.log(Level.INFO, "valore di tabFilms selected: {0}", tabFilms.isSelected());

        if (tabFilms.isSelected()) {

            if (!tableFilms.getSelectionModel().isEmpty()) {

                int i = tableFilms.getSelectionModel().getFocusedIndex();
                Film film = films.get(i);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FilmModifica.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.DECORATED);
                stage.setTitle("Modifica Film");
                stage.setScene(new Scene(root));

                FilmModificaController manager = fxmlLoader.<FilmModificaController>getController();
                manager.initData(film, true, this);

                stage.show();

            } else {
                LOG.info("non è stato selezionato nulla");
            }
        } else if (tabAttori.isSelected()) {

            if (!tableAttori.getSelectionModel().isEmpty()) {

                int i = tableAttori.getSelectionModel().getFocusedIndex();
                Attore attore = attori.get(i);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AttoreModifica.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.DECORATED);
                stage.setTitle("Modifica Attore");
                stage.setScene(new Scene(root));

                AttoreModificaController manager = fxmlLoader.<AttoreModificaController>getController();
                manager.initData(attore, true, this);

                stage.show();
            }
        }
    }

    public void onClickNuovoFilm(ActionEvent event) throws IOException {

        if (tabPaneInfo.getSelectionModel().getSelectedItem() != tabFilms) {
            tabPaneInfo.getSelectionModel().select(tabFilms);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FilmModifica.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Inserisci Film");
        stage.setScene(new Scene(root));

        FilmModificaController manager = fxmlLoader.<FilmModificaController>getController();
        manager.initData(null, false, this);
        stage.show();
    }

    public void onClickNuovoAttore(ActionEvent event) throws IOException {

        if (tabPaneInfo.getSelectionModel().getSelectedItem() != tabAttori) {
            tabPaneInfo.getSelectionModel().select(tabAttori);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AttoreModifica.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Inserisci Attore");
        stage.setScene(new Scene(root));

        AttoreModificaController manager = fxmlLoader.<AttoreModificaController>getController();
        manager.initData(null, false, this);
        stage.show();
    }

    public void onClickNuovoProduttore(ActionEvent event) {
        tabPaneInfo.getSelectionModel().select(tabProduttori);
    }

    public void onClickElimina(ActionEvent event) {

        if (tabFilms.isSelected()) {

            if (!tableFilms.getSelectionModel().isEmpty()) {

                int i = tableFilms.getSelectionModel().getFocusedIndex();
                Film f = films.get(i);

                Cinema.deleteInfo(f);
                films.remove(f);
            }
        } else if (tabAttori.isSelected()) {

            if (!tableAttori.getSelectionModel().isEmpty()) {

                int i = tableAttori.getSelectionModel().getFocusedIndex();
                Attore a = attori.get(i);

                Cinema.deleteInfo(a);
                attori.remove(a);
            }
        }
    }

    /**
     * aggiunge un oggetto alla relativa lista
     *
     * @param o oggetto da aggiungere
     */
    public void insertInfo(Object o) {

        if (o instanceof Film) {
            Film f = (Film) o;
            films.add(f);
        } else if (o instanceof Attore) {
            Attore a = (Attore) o;
            attori.add(a);
        }
    }

    /**
     * aggiorna la lista recuperando dal datavbase
     */
    /*public void updateFilms() {
        
     films = Cinema.getFilms();
        
     tableFilms.setItems(null);
     tableFilms.setItems(films);
     tableFilms.getSelectionModel().clearSelection();
     }*/
}
