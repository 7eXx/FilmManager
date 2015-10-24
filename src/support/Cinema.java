/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * classe di supporto per le operazioni sul database
 *
 * @author marco
 */
public class Cinema {

    static final String DB = "CINEMA";
    static final String USER = "utente";
    static final String PASS = "password";
    static final String DBURL = "jdbc:sqlserver://localhost:1433;databaseName=" + DB;

    private static ObservableList getFilms(Object o, Statement stmt, ResultSet rs) throws SQLException {

        String query = "SELECT * FROM FILM";

        if (o != null) {
            if (o instanceof Genere) {
                Genere g = (Genere) o;
                query = "SELECT Film." + Film.ID + "," + Film.NOME + "," + Film.DURATA + "," + Film.NAZIONE + ","
                        + Film.BUDGET + "," + Film.DATA_USCITA + "," + Film.VOTO + "," + Film.DESCRIZIONE + "," + Film.NUM_OSCAR
                        + " FROM Film INNER JOIN Classificazione ON Film.ID_film = Classificazione.ID_film"
                        + " WHERE ID_genere = " + g.getId();
            }
        }

        rs = stmt.executeQuery(query);

        ObservableList<Film> data = FXCollections.observableArrayList();
        while (rs.next()) {
            Film f = new Film();

            f.setId(rs.getInt(Film.ID));
            f.setNome(rs.getString(Film.NOME));
            f.setDurata(rs.getInt(Film.DURATA));
            f.setNazione(rs.getString(Film.NAZIONE));
            f.setBudget(rs.getDouble(Film.BUDGET));
            f.setData_uscita(rs.getString(Film.DATA_USCITA));
            f.setVoto(rs.getInt(Film.VOTO));
            f.setDescrizione(rs.getString(Film.DESCRIZIONE));
            f.setNum_oscar(rs.getInt(Film.NUM_OSCAR));

            data.add(f);
        }
        return data;
    }

    private static boolean updateFilm(Film film, Statement stmt, ResultSet rs) throws SQLException {

        String query = "SELECT * FROM FILM WHERE ID_Film = " + film.getId();
        rs = stmt.executeQuery(query);

        rs.first();
        rs.updateString(Film.NOME, film.getNome());
        rs.updateInt(Film.DURATA, film.getDurata());
        if (film.getNazione() != null) {
            rs.updateString(Film.NAZIONE, film.getNazione());
        }
        rs.updateDouble(Film.BUDGET, film.getBudget());
        if (film.getData_uscita() != null) {
            rs.updateString(Film.DATA_USCITA, film.getData_uscita());
        }
        rs.updateInt(Film.VOTO, film.getVoto());
        if (film.getDescrizione() != null) {
            rs.updateString(Film.DESCRIZIONE, film.getDescrizione());
        }
        rs.updateInt(Film.NUM_OSCAR, film.getNum_oscar());
        rs.updateRow();
        return true;
    }

    private static boolean insertFilm(Film film, Connection conn, PreparedStatement stmt, ResultSet rs) throws SQLException {

        String query = "Insert Into FILM (nome, durata, nazione, budget, data_uscita, voto, descrizione, num_oscar) values(?,?,?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1, film.getNome());
        stmt.setInt(2, film.getDurata());
        stmt.setString(3, film.getNazione());
        stmt.setDouble(4, film.getBudget());
        stmt.setString(5, film.getData_uscita());
        stmt.setInt(6, film.getVoto());
        stmt.setString(7, film.getDescrizione());
        stmt.setInt(8, film.getNum_oscar());

        stmt.executeUpdate();
        rs = stmt.getGeneratedKeys();

        if (rs != null && rs.next()) {
            film.setId(rs.getInt(1));
        }
        return true;

    }

    private static ObservableList getAttori(Statement stmt, ResultSet rs) throws SQLException {

        String query = "SELECT * FROM ATTORE";
        rs = stmt.executeQuery(query);
        ObservableList<Attore> data = FXCollections.observableArrayList();

        while (rs.next()) {
            Attore a = new Attore();

            a.setId(rs.getInt(Attore.ID));
            a.setNome(rs.getString(Attore.NOME));
            a.setCognome(rs.getString(Attore.COGNOME));
            a.setNazione(rs.getString(Attore.NAZIONE));
            a.setCitta(rs.getString(Attore.CITTA));
            a.setData_nascita(rs.getString(Attore.DATA_NASCITA));
            a.setBiografia(rs.getString(Attore.BIOGRAFIA));

            data.add(a);
        }
        return data;
    }

    private static boolean updateAttore(Attore attore, Statement stmt, ResultSet rs) throws SQLException {
        String query = "SELECT * FROM ATTORE WHERE ID_attore = " + attore.getId();
        rs = stmt.executeQuery(query);

        rs.first();
        // nome e cognome
        rs.updateString(Attore.NOME, attore.getNome());
        rs.updateString(Attore.COGNOME, attore.getCognome());
        // nazione e citta
        rs.updateString(Attore.NAZIONE, attore.getNazione());
        rs.updateString(Attore.CITTA, attore.getCitta());
        // data nascita
        rs.updateString(Attore.DATA_NASCITA, attore.getData_nascita());
        // biografia
        rs.updateString(Attore.BIOGRAFIA, attore.getBiografia());
        // aggiornamento riga
        rs.updateRow();
        return true;
    }

    private static boolean insertAttore(Attore attore, Connection conn, PreparedStatement stmt, ResultSet rs) throws SQLException {

        String query = "Insert Into ATTORE (nome, cognome, nazione, citta, data_nascita, biografia) values(?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1, attore.getNome());
        stmt.setString(2, attore.getCognome());
        stmt.setString(3, attore.getNazione());
        stmt.setString(4, attore.getCitta());
        stmt.setString(5, attore.getData_nascita());
        stmt.setString(6, attore.getBiografia());

        stmt.executeUpdate();
        rs = stmt.getGeneratedKeys();

        if (rs != null && rs.next()) {
            attore.setId(rs.getInt(1));
        }
        return true;
    }

    private static ObservableList getRegisti(Statement stmt, ResultSet rs) throws SQLException {

        String query = "SELECT * FROM REGISTA";
        rs = stmt.executeQuery(query);
        ObservableList<Regista> data = FXCollections.observableArrayList();

        while (rs.next()) {

            Regista r = new Regista();

            r.setId(rs.getInt(Regista.ID));
            r.setNome(rs.getString(Regista.NOME));
            r.setCognome(rs.getString(Regista.COGNOME));
            r.setNazione(rs.getString(Regista.NAZIONE));
            r.setData_nascita(rs.getString(Regista.DATA_NASCITA));
            r.setBiografia(rs.getString(Regista.BIOGRAFIA));

            data.add(r);
        }
        return data;
    }

    private static boolean updateRegista(Regista regista, Statement stmt, ResultSet rs) throws SQLException {
        String query = "SELECT * FROM REGISTA WHERE ID_regista = " + regista.getId();
        rs = stmt.executeQuery(query);

        rs.first();
        // nome
        rs.updateString(Regista.NOME, regista.getNome());
        // cognome

        rs.updateString(Regista.COGNOME, regista.getCognome());

        // nazione
        rs.updateString(Regista.NAZIONE, regista.getNazione());
        // data nascita
        rs.updateString(Regista.DATA_NASCITA, regista.getData_nascita());
        // biografia
        rs.updateString(Regista.BIOGRAFIA, regista.getBiografia());
        // aggiorna riga
        rs.updateRow();
        return true;
    }

    private static boolean insertRegista(Regista regista, Connection conn, PreparedStatement stmt, ResultSet rs) throws SQLException {
        String query = "Insert into Regista (nome,cognome,nazione, data_nascita, biografia) values(?,?,?,?,?)";
        stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1, regista.getNome());
        stmt.setString(2, regista.getCognome());
        stmt.setString(3, regista.getNazione());
        stmt.setString(4, regista.getData_nascita());
        stmt.setString(5, regista.getBiografia());

        stmt.executeUpdate();
        rs = stmt.getGeneratedKeys();
        if (rs != null && rs.next()) {
            regista.setId(rs.getInt(1));
        }
        return true;
    }

    private static ObservableList getProduttori(Statement stmt, ResultSet rs) throws SQLException {
        String query = "SELECT * FROM CASA_PRODUTTRICE";
        rs = stmt.executeQuery(query);

        ObservableList<Produttore> data = FXCollections.observableArrayList();
        while (rs.next()) {
            Produttore p = new Produttore();

            p.setId(rs.getInt(Produttore.ID));
            p.setNome(rs.getString(Produttore.NOME));
            p.setNazione(rs.getString(Produttore.NAZIONE));
            p.setDescrizione(rs.getString(Produttore.DESCRIZIONE));

            data.add(p);
        }
        return data;
    }

    private static boolean updateProduttore(Produttore produttore, Statement stmt, ResultSet rs) throws SQLException {
        String query = "SELECT * FROM CASA_PRODUTTRICE WHERE ID_casa = " + produttore.getId();
        rs = stmt.executeQuery(query);

        rs.first();
        // nome
        rs.updateString(Produttore.NOME, produttore.getNome());
        // nazione
        rs.updateString(Produttore.NAZIONE, produttore.getNazione());
        // descrizione
        rs.updateString(Produttore.DESCRIZIONE, produttore.getDescrizione());
        // aggiornamento riga
        rs.updateRow();
        return true;
    }

    private static boolean insertProduttore(Produttore produttore, Connection conn, PreparedStatement stmt, ResultSet rs) throws SQLException {
        String query = "INSERT INTO CASA_PRODUTTRICE (nome, nazione, descrizione) values (?,?,?)";
        stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1, produttore.getNome());
        stmt.setString(2, produttore.getNazione());
        stmt.setString(3, produttore.getDescrizione());

        stmt.executeUpdate();
        rs = stmt.getGeneratedKeys();
        if (rs != null && rs.next()) {
            produttore.setId(rs.getInt(1));
        }
        return true;
    }

    private static ObservableList getGeneri(Statement stmt, ResultSet rs) throws SQLException {
        String query = "SELECT * FROM GENERE";
        rs = stmt.executeQuery(query);

        ObservableList<Genere> data = FXCollections.observableArrayList();
        while (rs.next()) {
            Genere g = new Genere();

            g.setId(rs.getInt(Genere.ID));
            g.setGenere(rs.getString(Genere.GENERE));
            g.setDescrizione(rs.getString(Genere.DESCRIZIONE));

            data.add(g);
        }
        return data;
    }

    private static boolean updateGenere(Genere genere, Statement stmt, ResultSet rs) throws SQLException {
        String query = "SELECT * FROM GENERE WHERE ID_genere = " + genere.getId();
        rs = stmt.executeQuery(query);

        rs.first();
        //nome
        rs.updateString(Genere.GENERE, genere.getGenere());
        // descrizione
        rs.updateString(Genere.DESCRIZIONE, genere.getDescrizione());
        // aggiornamento riga
        rs.updateRow();
        return true;
    }

    private static boolean insertGenere(Genere genere, Connection conn, PreparedStatement stmt, ResultSet rs) throws SQLException {
        String query = "INSERT INTO GENERE (genere, descrizione) values (?,?)";
        stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1, genere.getGenere());
        stmt.setString(2, genere.getDescrizione());

        stmt.executeUpdate();
        rs = stmt.getGeneratedKeys();
        if (rs != null && rs.next()) {
            genere.setId(rs.getInt(1));
        }
        return true;
    }

    public static ObservableList getInfo(Class c, Object o) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        ObservableList data = FXCollections.observableArrayList();

        try {
            conn = DriverManager.getConnection(DBURL, USER, PASS);
            if (conn != null) {

                stmt = conn.createStatement();

                if (c.equals(Film.class)) {
                    if (o != null) {
                        data = getFilms(o, stmt, rs);
                    } else {
                        data = getFilms(null, stmt, rs);
                    }
                } else if (c.equals(Attore.class)) {
                    data = getAttori(stmt, rs);
                } else if (c.equals(Regista.class)) {
                    data = getRegisti(stmt, rs);
                } else if (c.equals(Produttore.class)) {
                    data = getProduttori(stmt, rs);
                } else if (c.equals(Genere.class)) {
                    data = getGeneri(stmt, rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("\n---SQLException caught ---\n");
            while (ex != null) {
                System.out.println("Message: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (stmt != null && !stmt.isClosed()) {
                    stmt.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return data;
    }

    public static boolean updateInfo(Object obj) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean success = false;

        try {
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            if (conn != null) {
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                if (obj instanceof Film) {
                    Film f = (Film) obj;
                    success = updateFilm(f, stmt, rs);
                } else if (obj instanceof Attore) {
                    Attore a = (Attore) obj;
                    success = updateAttore(a, stmt, rs);
                } else if (obj instanceof Regista) {
                    Regista r = (Regista) obj;
                    success = updateRegista(r, stmt, rs);
                } else if (obj instanceof Produttore) {
                    Produttore p = (Produttore) obj;
                    success = updateProduttore(p, stmt, rs);
                } else if (obj instanceof Genere) {
                    Genere g = (Genere) obj;
                    success = updateGenere(g, stmt, rs);
                }
            }

        } catch (SQLException ex) {

            System.out.println("\n---SQLException caught ---\n");
            while (ex != null) {
                System.out.println("Message: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (stmt != null && !stmt.isClosed()) {
                    stmt.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return success;
    }

    public static boolean insertInfo(Object obj) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean success = false;

        try {
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            if (conn != null) {

                if (obj instanceof Film) {
                    Film f = (Film) obj;
                    success = insertFilm(f, conn, stmt, rs);
                } else if (obj instanceof Attore) {
                    Attore a = (Attore) obj;
                    success = insertAttore(a, conn, stmt, rs);

                } else if (obj instanceof Regista) {
                    Regista r = (Regista) obj;
                    success = insertRegista(r, conn, stmt, rs);
                } else if (obj instanceof Produttore) {
                    Produttore p = (Produttore) obj;
                    success = insertProduttore(p, conn, stmt, rs);
                } else if (obj instanceof Genere) {
                    Genere g = (Genere) obj;
                    success = insertGenere(g, conn, stmt, rs);
                }
            }
        } catch (SQLException ex) {

            System.out.println("\n---SQLException caught ---\n");
            while (ex != null) {
                System.out.println("Message: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (stmt != null && !stmt.isClosed()) {
                    stmt.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return success;
    }

    public static boolean deleteInfo(Object obj) {
        Connection conn = null;
        Statement stmt = null;
        int rs = -1;
        boolean success = false;

        try {
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            if (conn != null) {

                if (obj instanceof Film) {
                    Film film = (Film) obj;
                    String query = "DELETE FROM FILM WHERE ID_film = " + film.getId();
                    stmt = conn.createStatement();
                    rs = stmt.executeUpdate(query);
                    success = true;
                } else if (obj instanceof Attore) {
                    Attore attore = (Attore) obj;
                    String query = "DELETE FROM ATTORE WHERE ID_attore = " + attore.getId();
                    stmt = conn.createStatement();
                    rs = stmt.executeUpdate(query);
                    success = true;

                } else if (obj instanceof Regista) {
                    Regista regista = (Regista) obj;
                    String query = "DELETE FROM REGISTA WHERE ID_regista = " + regista.getId();
                    stmt = conn.createStatement();
                    rs = stmt.executeUpdate(query);
                    success = true;
                } else if (obj instanceof Produttore) {
                    Produttore produtttore = (Produttore) obj;
                    String query = "DELETE FROM CASA_PRODUTTRICE WHERE ID_casa = " + produtttore.getId();
                    stmt = conn.createStatement();
                    rs = stmt.executeUpdate(query);
                    success = true;
                } else if (obj instanceof Genere) {
                    Genere genere = (Genere) obj;
                    String query = "DELETE FROM GENERE WHERE ID_genere = " + genere.getId();
                    stmt = conn.createStatement();
                    rs = stmt.executeUpdate(query);
                    success = true;
                }
            }
        } catch (SQLException ex) {

            System.out.println("\n---SQLException caught ---\n");
            while (ex != null) {
                System.out.println("Message: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }
        } finally {
            try {
                if (stmt != null && !stmt.isClosed()) {
                    stmt.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return success;
    }
}
