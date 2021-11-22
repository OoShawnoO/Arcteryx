package swu.edu.hzd;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name="Delete")
public class Delete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        SQLtool sqLtool = new SQLtool();
        int id = Integer.valueOf(request.getParameter("delete"));
        try {
            sqLtool.Delete(id);
            response.sendRedirect("recordlist.jsp");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        this.doPost(request,response);
    }
}
