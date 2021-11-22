package swu.edu.hzd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet(name="Update")
public class Update extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        SQLtool sqLtool = new SQLtool();
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        float price = Float.valueOf(request.getParameter("price"));
        float cost = Float.valueOf(request.getParameter("cost"));
        int id = Integer.valueOf(request.getParameter("id"));
        try {
            sqLtool.Update(id,name,price,cost);
            response.sendRedirect("Revise.jsp");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
