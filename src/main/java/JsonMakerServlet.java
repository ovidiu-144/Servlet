import beans.StudentBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class JsonMakerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SqlWorkClass sqlStudent = new SqlWorkClass();
        sqlStudent.createJson();

        //verificam fisierul
        File file = new File("/home/ovidiu/Facultate/SD/labsd01/SD_Laborator_01/studentiJson.json");
        if (!file.exists()) {
            response.sendError(404, "Nu a fost gasit niciun Json serializat pe disc!");
            return;
        }
        //citim sub forma de string
        String jsonString = new String(Files.readAllBytes(file.toPath()));
        System.out.println(jsonString);

        request.setAttribute("studentiJson", jsonString);
        // redirectionare date catre pagina de afisare a informatiilor studentului
        request.getRequestDispatcher("./json-students.jsp").forward(request, response);
    }
}
