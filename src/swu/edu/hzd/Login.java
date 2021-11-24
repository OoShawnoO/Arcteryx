package swu.edu.hzd;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;



@WebServlet(name="Login",value = "/Login")
public class Login extends HttpServlet {
    public static ArrayList<HttpSession> sessions = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("i am doing things");
    }

    protected  void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        SQLtool sqLtool = new SQLtool();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        System.out.println(username+" "+password);

        try {
            if(sqLtool.Select_Users(username,password)){
                session.setAttribute("username",username);
                sessions.add(session);
                if(!response.isCommitted()) {
                    response.sendRedirect("index.jsp");
                }

            }
            else{
                int ok = -1;
                response.sendRedirect(String.format("login.html?true=%d",ok));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
