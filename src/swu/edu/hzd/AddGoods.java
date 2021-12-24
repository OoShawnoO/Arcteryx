package swu.edu.hzd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet
public class AddGoods extends HttpServlet {



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if(request.getAttribute("commited")!=null){
            if(request.getAttribute("commited").equals("No")){
                if(request.getAttribute("FileType").equals(".xls")){
                    System.out.println("Right!");
                    String path = String.valueOf(request.getAttribute("filepath"));
                    try {
                        ReadExcel.Read(path);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                else{
                    System.out.println("error");
                    request.setAttribute("warp-url","Add.html?success=-1");
                }
            }
        }

        else{
            String name = request.getParameter("input1");
            Float price = Float.valueOf(request.getParameter("input2"));
            Float cost = Float.valueOf(request.getParameter("input3"));
            String uploader = String.valueOf(request.getSession().getAttribute("username"));
            String intro = String.valueOf(request.getParameter("comment"));
            System.out.println(name+"  "+price+"  "+cost+"  "+uploader);
            SQLtool sqLtool = new SQLtool();
            try {
                sqLtool.Insert("record",name,uploader,price,cost,intro,"");
                response.sendRedirect("Add.html");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
