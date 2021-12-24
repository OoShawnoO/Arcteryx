package swu.edu.hzd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name="AjaxResponse")
public class AjaxResponse extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SQLtool sqLtool = new SQLtool();
        String page = request.getParameter("page");
        int offset = (Integer.valueOf(page) -1)*6;
        String sql = "SELECT * FROM record ORDER BY id asc LIMIT 6 OFFSET " + offset;
        try {
            ArrayList<Goods> arrayList = sqLtool.ExecuteSQL(sql);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try{
                Writer writer = response.getWriter();
                writer.write(this.toJson(arrayList));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String toJson(ArrayList<Goods> arrayList){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"goods\":[");
        for(int i=0;i<arrayList.size();i++){
            Goods good = arrayList.get(i);
            if(i>0) sb.append(",");
            sb.append(String.format(
                    "{\"id\":%s, \"name\": \"%s\",\"intro\": \"%s\",\"imgsrc\":\"%s\",\"price\": \"%s\"}",
                    good.getId(),good.getName(),good.getIntro(),good.getImgsrc(),good.getPrice()
            ));
        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }
}
