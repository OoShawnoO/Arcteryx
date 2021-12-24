package swu.edu.hzd;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name="Delete")
public class Delete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        SQLtool sqLtool = new SQLtool();
        ArrayList<Goods> arrayList = new ArrayList<>();
        int id = Integer.valueOf(request.getParameter("delete"));
        try {
            arrayList = sqLtool.Select("");
            int i=0;
            for(i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getId()==id){
                    sqLtool.Insert("delete_history",arrayList.get(i).getName(), String.valueOf(request.getSession().getAttribute("username")),arrayList.get(i).getPrice(),arrayList.get(i).getCost(),"","");
                }
            }
            sqLtool.Delete(id);
            response.sendRedirect("recordlist.jsp");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        this.doPost(request,response);
    }
}
