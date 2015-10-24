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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import support.Attore;
import support.Cinema;
import support.Film;
import support.Genere;
import support.Produttore;
import support.Regista;

/**
 * scena principale di tutto il programma
 *
 * @author marco
 */
public class FilmManagerController implements Initializable {

    private static final Logger LOG = Logger.getLogger(FilmManagerController.class.getName());

    ObservableList<Film> films;
    ObservableList<Attore> attori;
    ObservableList<Regista> registi;
    ObservableList<Produttore> produttori;
    ObservableList<Genere> generi;

    @FXML
    private TabPane tabPaneInfo;

    @FXML
    private Tab tabFilms, tabAttori, tabRegisti, tabProduttori, tabGeneri;

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
    private ListView<Produttore> listProduttori;
    @FXML
    private TextField tfIdProduttore, tfNomeProduttore, tfNazioneProduttore;
    @FXML
    private TextArea taDescrizioneProduttore;

    @FXML
    private ListView<Genere> listGeneri;
    @FXML
    private TextField tfIdGenere, tfNomeGenere;
    @FXML
    private TextArea taDescrizioneGenere;

    @FXML
    private ListView<Film> listGeneriFilm;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // caricamento colonne film
        filmNome.setCellValueFactory(new PropertyValueFactory("nome"));
        filmDurata.setCellValueFactory(new PropertyValueFactory("durata"));
        filmNazione.setCellValueFactory(new PropertyValueFactory("nazione"));
        filmBudget.setCellValueFactory(new PropertyValueFactory("budget"));
        filmDataUscita.setCellValueFactory(new PropertyValueFactory("data_uscita"));
        filmVoto.setCellValueFactory(new PropertyValueFactory("voto"));
        filmNumOscar.setCellValueFactory(new PropertyValueFactory("num_oscar"));
        // caricamento colonne attore
        attoreNome.setCellValueFactory(new PropertyValueFactory("nome"));
        attoreCognome.setCellValueFactory(new PropertyValueFactory("cognome"));
        attoreNazione.setCellValueFactory(new PropertyValueFactory("nazione"));
        attoreCitta.setCellValueFactory(new PropertyValueFactory("citta"));
        attoreDataNascita.setCellValueFactory(new PropertyValueFactory("data_nascita"));
        // caricamento colonne regista
        registaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        registaCognome.setCellValueFactory(new PropertyValueFactory("cognome"));
        registaNazione.setCellValueFactory(new PropertyValueFactory("nazione"));
        registaDataNascita.setCellValueFactory(new PropertyValueFactory("data_nascita"));
        // Carica i Film
        films = Cinema.getInfo(Film.class, null);
        tableFilms.setItems(films);
        // carica gli attori
        attori = Cinema.getInfo(Attore.class, null);
        tableAttori.setItems(attori);
        // carica i registi
        registi = Cinema.getInfo(Regista.class, null);
        tableRegisti.setItems(registi);
        // carico i produttori
        produttori = Cinema.getInfo(Produttore.class, null);
        listProduttori.setItems(produttori);
        // carico i generi
        generi = Cinema.getInfo(Genere.class, null);
        listGeneri.setItems(generi);
        // seleziona una riga
        //tableFilms.getSelectionModel().clearSelection();
        // produttori evento cambia indice
        listProduttori.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produttore>() {
            @Override
            public void changed(ObservableValue<? extends Produttore> observable, Produttore oldValue, Produttore newValue) {

                if (newValue != null) {
                    tfIdProduttore.setText(newValue.getId() + "");
                    tfNomeProduttore.setText(newValue.getNome());
                    tfNazioneProduttore.setText(newValue.getNazione());
                    taDescrizioneProduttore.setText(newValue.getDescrizione());
                }
            }
        });
        // generi evento cambia indice
        listGeneri.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Genere>() {
            @Override
            public void changed(ObservableValue<? extends Genere> observable, Genere oldValue, Genere newValue) {

                if (newValue != null) {
                    tfIdGenere.setText(newValue.getId() + "");
                    tfNomeGenere.setText(newValue.getGenere());
                    taDescrizioneGenere.setText(newValue.getDescrizione());

                    ObservableList<Film> f = Cinema.getInfo(Film.class, newValue);
                    listGeneriFilm.setItems(f);
                } else {
                    tfIdGenere.clear();
                    tfNomeGenere.clear();
                    taDescrizioneGenere.clear();
                    listGeneriFilm.setItems(null);
                }
            }
        });
    }

    @FXML
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
        } else if (tabRegisti.isSelected()) {

            if (!tableRegisti.getSelectionModel().isEmpty()) {

                int i = tableRegisti.getSelectionModel().getFocusedIndex();
                Regista regista = registi.get(i);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistaModifica.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.DECORATED);
                stage.setTitle("Modifica Regista");
                stage.setScene(new Scene(root));

                RegistaModificaController manager = fxmlLoader.<RegistaModificaController>getController();
                manager.initData(regista, true, this);

                stage.show();
            }
        }
    }

    @FXML
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

    @FXML
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

    @FXML
    public void onClickNuovoRegista(ActionEvent event) throws IOException {
        if (tabPaneInfo.getSelectionModel().getSelectedItem() != tabRegisti) {
            tabPaneInfo.getSelectionModel().select(tabRegisti);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistaModifica.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Inserisci Regista");
        stage.setScene(new Scene(root));

        RegistaModificaController manager = fxmlLoader.<RegistaModificaController>getController();
        manager.initData(null, false, this);
        stage.show();
    }

    @FXML
    public void onClickNuovoProduttore(ActionEvent event) {
        // seleziona tab produttore
        tabPaneInfo.getSelectionModel().select(tabProduttori);
        // pulisce tutti i campi
        listProduttori.getSelectionModel().clearSelection();
        tfIdProduttore.clear();
        tfNomeProduttore.clear();
        tfNazioneProduttore.clear();
        taDescrizioneProduttore.clear();
    }

    @FXML
    public void onClickNuovoGenere(ActionEvent event) {
        // selezione tab genere
        tabPaneInfo.getSelectionModel().select(tabGeneri);
        // pulisce tutti i campi
        listGeneri.getSelectionModel().clearSelection();

    }

    @FXML
    public void onClickConfermaProduttore(ActionEvent event) {

        // se diverso da vuoto procede
        if (!tfNomeProduttore.getText().trim().equals("")) {

            boolean modifica = !listProduttori.getSelectionModel().isEmpty();
            Produttore p;
            // se indice selezionato -> modifica dalla lista
            if (modifica) {
                p = listProduttori.getSelectionModel().getSelectedItem();
            } // altrimenti inserimento nuovo
            else {
                p = new Produttore();
            }

            // imposta il nome
            p.setNome(tfNomeProduttore.getText());
            // imposta la nazione
            if (tfNazioneProduttore.getText() != null && !tfNazioneProduttore.getText().trim().equals("")) {
                p.setNazione(tfNazioneProduttore.getText());
            } else {
                p.setNazione(null);
            }
            // imposta la descrizione
            if (taDescrizioneProduttore.getText() != null && !taDescrizioneProduttore.getText().trim().equals("")) {
                p.setDescrizione(taDescrizioneProduttore.getText());
            } else {
                p.setDescrizione(null);
            }

            // indice selezionato aggiorna altrimenti inserisce
            if (modifica) {
                Cinema.updateInfo(p);
                listProduttori.refresh();
            } else {
                Cinema.insertInfo(p);
                produttori.add(p);
            }
        }
    }

    @FXML
    public void onClickConfermaGenere(ActionEvent event) {

        // se diverso da vuoto procede
        if (!tfNomeGenere.getText().trim().equals("")) {

            boolean modifica = !listGeneri.getSelectionModel().isEmpty();
            Genere g;
            // se indice selezionato -> modifica dalla lista
            if (modifica) {
                g = listGeneri.getSelectionModel().getSelectedItem();
            } // altrimenti inserimento nuovo
            else {
                g = new Genere();
            }

            // imposta il nome
            g.setGenere(tfNomeGenere.getText());
            // imposta la descrizione
            if (taDescrizioneGenere.getText() != null && !taDescrizioneGenere.getText().trim().equals("")) {
                g.setDescrizione(taDescrizioneGenere.getText());
            } else {
                g.setDescrizione(null);
            }

            // indice selezionato aggiorna altrimenti inserisce
            if (modifica) {
                Cinema.updateInfo(g);
                listGeneri.refresh();
            } else {
                Cinema.insertInfo(g);
                generi.add(g);
            }
        }
    }

    @FXML
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
        } else if (tabRegisti.isSelected()) {

            if (!tableRegisti.getSelectionModel().isEmpty()) {
                int i = tableRegisti.getSelectionModel().getFocusedIndex();
                Regista r = registi.get(i);

                Cinema.deleteInfo(r);
                registi.remove(r);
            }
        } else if (tabProduttori.isSelected()) {
            if (!listProduttori.getSelectionModel().isEmpty()) {
                int i = listProduttori.getSelectionModel().getSelectedIndex();
                Produttore p = produttori.get(i);

                Cinema.deleteInfo(p);
                produttori.remove(p);
            }
        } else if (tabGeneri.isSelected()) {
            if (!listGeneri.getSelectionModel().isEmpty()) {
                int i = listGeneri.getSelectionModel().getSelectedIndex();
                Genere g = generi.get(i);

                Cinema.deleteInfo(g);
                generi.remove(g);
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
        } else if (o instanceof Regista) {
            Regista r = (Regista) o;
            registi.add(r);
        } else if (o instanceof Produttore) {
            Produttore p = (Produttore) o;
            produttori.add(p);
        } else if (o instanceof Genere) {
            Genere g = (Genere) o;
            generi.add(g);
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
