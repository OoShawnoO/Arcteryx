package swu.edu.hzd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name="Update")
public class Update extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        SQLtool sqLtool = new SQLtool();
        request.setCharacterEncoding("UTF-8");
        ArrayList<Goods> arrayList;
        String new_name = request.getParameter("name");
        float new_price = Float.parseFloat(request.getParameter("price"));
        float new_cost = Float.parseFloat(request.getParameter("cost"));
        int id = Integer.parseInt(request.getParameter("id"));
        String old_name="";
        float old_price=0;
        float old_cost=0;
        try {
            arrayList = sqLtool.Select("");
            int i;
            for(i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getId()==id){
                    old_name=arrayList.get(i).getName();
                    old_price= arrayList.get(i).getPrice();
                    old_cost = arrayList.get(i).getCost();
                }
            }
            sqLtool.Update(id,new_name,new_price,new_cost,old_name,String.valueOf(request.getSession().getAttribute("username")),old_price,old_cost);
            response.sendRedirect("Revise.jsp");

        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
