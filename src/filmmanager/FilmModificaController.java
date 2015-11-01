/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmmanager;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import support.AttoreOscar;
import support.Cinema;
import support.Cinema.State;
import support.Film;
import support.Genere;
import support.Produttore;
import support.Regista;
import support.RegistaOscar;

/**
 * FXML Controller class
 *
 * @author Mark
 */
public class FilmModificaController implements Initializable {

    private Film film;
    boolean modifica;
    FilmManagerController main;
    int minOscar;

    public ObservableList<AttoreOscar> attoriPres, attoriNonPres;
    public ArrayList<AttoreOscar> attoriMod;

    public ObservableList<RegistaOscar> regiaPres, regiaNonPres;
    public RegistaOscar regiaMod;

    public ObservableList<Genere> generiPres, generiNonPres;
    public ArrayList<Genere> generiMod;

    public ObservableList<Produttore> prodPres, prodNonPres;
    public ArrayList<Produttore> prodMod;

    @FXML
    private TextField tfIdFilm, tfNome, tfDurata, tfNazione, tfVoto, tfBudget, tfNumOscar;
    @FXML
    private DatePicker dpDataUscita;
    @FXML
    private TextArea taDescrizione;
    @FXML
    private Button btConferma, btAnnulla;
    @FXML
    private ListView<AttoreOscar> listAttori;
    @FXML
    private CheckBox cbOscarAttore, cbOscarRegia;
    @FXML
    private ListView<Produttore> listProduttori;
    @FXML
    private ListView<Genere> listGeneri;
    @FXML
    private ListView<RegistaOscar> listRegia;

    public void initData(Film film, boolean modifica, FilmManagerController manager) {

        this.modifica = modifica;
        this.film = film;
        this.main = manager;

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

            attoriPres = Cinema.getInfo(AttoreOscar.class, film, true);
            attoriNonPres = Cinema.getInfo(AttoreOscar.class, film, false);

            generiPres = Cinema.getInfo(Genere.class, film, true);
            generiNonPres = Cinema.getInfo(Genere.class, film, false);

            prodPres = Cinema.getInfo(Produttore.class, film, true);
            prodNonPres = Cinema.getInfo(Produttore.class, film, false);

            regiaPres = Cinema.getInfo(RegistaOscar.class, film, true);
            regiaNonPres = Cinema.getInfo(RegistaOscar.class, null, false);

            for (AttoreOscar att : attoriPres) {
                if (att.isOscar()) {
                    minOscar++;
                }
            }

            if (!regiaPres.isEmpty()) {

                regiaMod = regiaPres.get(0);
                if (regiaMod.isOscar()) {
                    minOscar++;
                    cbOscarRegia.setSelected(true);
                }
            }

            /*
             Regista r = (Regista) Cinema.getInfo(Regista.class, film, true).get(0);
             boolean find = false;
             for (int i = 0; i < choiceRegista.getItems().size() && !find; i++) {
             if(choiceRegista.getItems().get(i).getId() == r.getId())
             choiceRegista.getSelectionModel().select(i);
             }
             */
        } else {
            minOscar = 0;
            tfNumOscar.setText(minOscar + "");

            attoriPres = FXCollections.observableArrayList();
            attoriNonPres = Cinema.getInfo(AttoreOscar.class, null, false);

            generiPres = FXCollections.observableArrayList();
            generiNonPres = Cinema.getInfo(Genere.class, null, false);

            prodPres = FXCollections.observableArrayList();
            prodNonPres = Cinema.getInfo(Produttore.class, null, false);

            regiaPres = FXCollections.observableArrayList();
            regiaNonPres = Cinema.getInfo(RegistaOscar.class, null, false);
        }

        listAttori.setItems(attoriPres);
        listGeneri.setItems(generiPres);
        listProduttori.setItems(prodPres);
        listRegia.setItems(regiaPres);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        attoriMod = new ArrayList<>();
        regiaMod = null;
        generiMod = new ArrayList<>();
        prodMod = new ArrayList<>();

        /// gestione selezione attore
        listAttori.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AttoreOscar>() {

            @Override
            public void changed(ObservableValue<? extends AttoreOscar> observable, AttoreOscar oldValue, AttoreOscar newValue) {

                if (newValue != null) {
                    cbOscarAttore.setSelected(newValue.isOscar());
                } else {
                    cbOscarAttore.setSelected(false);
                }
            }
        });

        cbOscarAttore.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if (!listAttori.getSelectionModel().isEmpty()) {

                    int i = listAttori.getSelectionModel().getSelectedIndex();
                    AttoreOscar a = attoriPres.get(i);
                    if (a.isOscar() != newValue) {
                        a.setOscar(newValue);
                        if (a.getState() == State.NONE) {
                            a.setState(State.MODIFIED);
                            attoriMod.add(a);
                        }
                        if (newValue) {
                            addOscar();
                        } else {
                            removeOscar();
                        }
                    }
                } else {
                    cbOscarAttore.setSelected(false);
                }
            }
        });

        cbOscarRegia.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if (!regiaPres.isEmpty()) {
                    RegistaOscar r = regiaPres.get(0);
                    if (r.isOscar() != newValue) {
                        regiaMod.setOscar(newValue);
                        if (regiaMod.getState() != State.INSERTED) {
                            regiaMod.setState(State.MODIFIED);
                        }
                        if (newValue) {
                            addOscar();
                        } else {
                            removeOscar();
                        }
                    }
                } else {
                    cbOscarRegia.setSelected(false);
                }
            }
        });
    }

    @FXML
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

            if (!tfNumOscar.getText().trim().equals("") && minOscar < Integer.parseInt(tfNumOscar.getText())) {
                film.setNum_oscar(Integer.parseInt(tfNumOscar.getText()));
            } else {
                film.setNum_oscar(minOscar);
            }

            if (modifica) {

                Cinema.updateInfo(film);
            } // altrimenti inserimento
            else {

                Cinema.insertInfo(film);
                main.insertInfo(film);
            }

            if (!attoriMod.isEmpty()) {
                Cinema.updateFilmAttori(attoriMod, film);
            }
            if (regiaMod != null) {
                Cinema.updateFilmRegista(regiaMod, film);
            }
            if (!generiMod.isEmpty()) {
                Cinema.updateFilmGeneri(generiMod, film);
            }
            if(!prodMod.isEmpty())
            {
                Cinema.updateFilmProd(prodMod, film);
            }

            Stage stage = (Stage) btConferma.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void onClickAnnulla(ActionEvent event) {
        Stage stage = (Stage) btAnnulla.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onChangeRegista(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListaAggiungi.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.NONE);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Cambia Regista");
        stage.setScene(new Scene(root));

        ListaAggiungiController scene = fxmlLoader.<ListaAggiungiController>getController();
        scene.initData(this, RegistaOscar.class);
        stage.show();

    }

    @FXML
    public void onAddAttore(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListaAggiungi.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.NONE);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Aggiungi Attore");
        stage.setScene(new Scene(root));

        ListaAggiungiController scene = fxmlLoader.<ListaAggiungiController>getController();
        scene.initData(this, AttoreOscar.class);
        stage.show();
    }

    @FXML
    public void onAddProduttore(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListaAggiungi.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.NONE);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Aggiungi Casa Produttrice");
        stage.setScene(new Scene(root));

        ListaAggiungiController scene = fxmlLoader.<ListaAggiungiController>getController();
        scene.initData(this, Produttore.class);
        stage.show();
    }

    @FXML
    public void onAddGenere(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListaAggiungi.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.NONE);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Aggiungi Genere");
        stage.setScene(new Scene(root));

        ListaAggiungiController scene = fxmlLoader.<ListaAggiungiController>getController();
        scene.initData(this, Genere.class);
        stage.show();
    }

    @FXML
    public void onRemoveRegista(ActionEvent event) {

        if (regiaMod != null) {
            RegistaOscar r = regiaPres.get(0);
            cbOscarRegia.setSelected(false);
            regiaPres.remove(r);
            regiaNonPres.add(r);

            switch (regiaMod.getState()) {

                case MODIFIED:
                case NONE:
                    regiaMod.setState(State.DELETED);
                    break;
                case INSERTED:
                    regiaMod = null;
                    break;
            }

            System.out.println("remove regia: " + regiaMod);
        }
    }

    @FXML
    public void onRemoveAttore(ActionEvent event) {

        if (!listAttori.getSelectionModel().isEmpty()) {
            AttoreOscar a = listAttori.getSelectionModel().getSelectedItem();
            if (a.isOscar()) {
                a.setOscar(false);
                removeOscar();
            }
            attoriPres.remove(a);
            attoriNonPres.add(a);
            switch (a.getState()) {
                case INSERTED:
                    attoriMod.remove(a);
                    a.setState(State.NONE);
                    break;

                case MODIFIED:
                    a.setState(State.MOD_DELETED);
                    break;

                case NONE:
                    attoriMod.add(a);
                    a.setState(State.DELETED);
                    break;
            }
            if (a.isOscar()) {
                removeOscar();
            }
            System.out.println("removed actor: " + attoriMod);
        }
    }

    @FXML
    public void onRemoveProduttore(ActionEvent event) {
        Produttore p = listProduttori.getSelectionModel().getSelectedItem();
        prodPres.remove(p);
        prodNonPres.add(p);

        switch (p.getState()) {
            case INSERTED:
                prodMod.remove(p);
                p.setState(State.NONE);
                break;

            case NONE:
                prodMod.add(p);
                p.setState(State.DELETED);
                break;
        }
        System.out.println("removed prod: " + prodMod);
    }

    @FXML
    public void onRemoveGenere(ActionEvent event) {
        Genere g = listGeneri.getSelectionModel().getSelectedItem();
        generiPres.remove(g);
        generiNonPres.add(g);

        switch (g.getState()) {
            case INSERTED:
                generiMod.remove(g);
                g.setState(State.NONE);
                break;

            case NONE:
                generiMod.add(g);
                g.setState(State.DELETED);
                break;
        }
        System.out.println("removed generi: " + generiMod);
    }

    public void addOscar() {
        minOscar++;
        if (Integer.parseInt(tfNumOscar.getText()) < minOscar) {
            tfNumOscar.setText(minOscar + "");
        } else {
            int num = Integer.parseInt(tfNumOscar.getText());
            num++;
            tfNumOscar.setText(num + "");
        }
    }

    public void removeOscar() {
        minOscar--;
        if (Integer.parseInt(tfNumOscar.getText()) < minOscar) {
            tfNumOscar.setText(minOscar + "");
        } else {
            int num = Integer.parseInt(tfNumOscar.getText());
            num--;
            tfNumOscar.setText(num + "");
        }
    }

    public void resetBoxRegia() {
        cbOscarRegia.setSelected(false);
    }

}
