import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.util.List;

public class ProcessStudentServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // se citesc parametrii din cererea de tip POST
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        int varsta = Integer.parseInt(request.getParameter("varsta"));
        int anCurent = Year.now().getValue();
        int anNastere = anCurent - varsta;

        SqlWorkClass sqlStudent = new SqlWorkClass();
        sqlStudent.setStudent(nume, prenume, varsta, anNastere); ///Punem studentul in baza de date
        List<StudentBean> listaStudenti = sqlStudent.getStudent();

        request.setAttribute("listaStudenti", listaStudenti);
        request.getRequestDispatcher("./info-student.jsp").forward(request, response);
    }
}