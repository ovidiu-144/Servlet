import beans.StudentBean;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.*;
import java.util.*;


public class SqlWorkClass {
    private final Connection conn;

    public SqlWorkClass(){
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "/home/ovidiu/Facultate/SD/labsd01/SD_Laborator_01/students.db";
            String url = "jdbc:sqlite:" + path;
            conn = DriverManager.getConnection(url);
            createTable();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void createTable () throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS student (" +
                "NUME STRING NOT NULL," +
                "PRENUME STRING NOT NULL," +
                "VARSTA INTEGER NOT NULL," +
                "AN_NASTERE INTEGER)";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);

        System.out.println("Table created successfully!\n");
        stmt.close();
    }
    public void setStudent (String nume, String prenume, int varsta, int anNastere){
        String sql = "INSERT INTO student (NUME, PRENUME, VARSTA, AN_NASTERE) " +
                "VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nume);
            stmt.setString(2, prenume);
            stmt.setInt(3, varsta);
            stmt.setInt(4, anNastere);
            stmt.executeUpdate();
            System.out.println("Am adaugat un student");

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<StudentBean> getStudent (){
        String sql = "SELECT * FROM student";
        List<StudentBean> listaStudenti = new ArrayList<>();
        String nume, prenume;
        int varsta, anNastere;
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                nume = rs.getString("NUME");
                prenume = rs.getString("PRENUME");
                varsta = rs.getInt("VARSTA");
                anNastere = rs.getInt("AN_NASTERE");

                StudentBean bean = new StudentBean();
                bean.setNume(nume);
                bean.setPrenume(prenume);
                bean.setVarsta(varsta);
                bean.setAnNastere(anNastere);
                listaStudenti.add(bean);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listaStudenti;
    }
    public List<StudentBean> getStudent (String numeCautat){
        String sql = "SELECT * FROM student";
        List<StudentBean> listaStudenti = new ArrayList<>();

        numeCautat = numeCautat.toLowerCase();  // transformam in litere mici
        String nume, prenume;
        int varsta, anNastere;
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                nume = rs.getString("NUME");
                prenume = rs.getString("PRENUME");
                varsta = rs.getInt("VARSTA");
                anNastere = rs.getInt("AN_NASTERE");

                StudentBean bean = new StudentBean();
                bean.setNume(nume);
                bean.setPrenume(prenume);
                bean.setVarsta(varsta);
                bean.setAnNastere(anNastere);

                // Adaugam studentul doar daca este egal cu numele

                if (numeCautat.equals(nume.toLowerCase()))
                    listaStudenti.add(bean);
            }
            stmt.close();
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listaStudenti;
    }
    public void createJson () throws IOException {
        List<StudentBean> listaStudenti = getStudent();
        JsonArrayBuilder json = Json.createArrayBuilder();

        for (StudentBean student : listaStudenti) {
            // creem fiecare valuare de tip student
            JsonValue value = Json.createObjectBuilder()
                    .add("nume", student.getNume())
                    .add("prenume", student.getPrenume())
                    .add("varsta", student.getVarsta())
                    .add("anNastere", student.getAnNastere())
                    .build();
            json.add(value);

        }
        JsonArray jsonArray = json.build();
        try {
            FileWriter fileWriter = new FileWriter("/home/ovidiu/Facultate/SD/labsd01/SD_Laborator_01/studentiJson.json");
            JsonWriter jsonWriter = Json.createWriter(fileWriter);
            jsonWriter.writeArray(jsonArray);

            fileWriter.close();
            jsonWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

/// Cautare dupa nume / prenume check
/// export baza de date ca JSON check
/// afisata pe o pagina separata JSP
