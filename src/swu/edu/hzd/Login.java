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
        String VerficationCode = request.getParameter("VerficationCode");

        HttpSession session = request.getSession();
        if(!VerficationCode.equalsIgnoreCase(String.valueOf(session.getAttribute("verfication")))){
            if(!response.isCommitted()){
                //System.out.println(VerficationCode+"       "+ request.getSession().getAttribute("verfication"));
                response.sendRedirect("login.html?true=-3");
                return;
            }
        }

        try {
            if(sqLtool.Select_Users(username,password)){
                session.setAttribute("username",username);
                sessions.add(session);
                System.out.println("用户："+username+"   Session:"+session.getId()+"正在会话....");
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
