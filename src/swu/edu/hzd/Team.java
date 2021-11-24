package swu.edu.hzd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="Team")
public class Team extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected  void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1 style='text-align:center;'>当你看到这个网站的时候可能我就要提醒您</h1>");
        out.println("<h1 style='text-align:center'>您没有本站的管理员权限哦，没有办法对数据进行修改。</h1>");
        out.println("<center><button style='width:100px;height:80px;' onclick='window.location.href=\"/RecordSystem/index.jsp\"'>主页</a></center>");
        out.println("<marquee style='margin-top:15%;' direction=\"right\"><img src=\"/image/2.gif\" style='width=100px;height:100px;'><p style=\"font-family: 'Microsoft JhengHei Light ';\">没错就是本人写的</p></marquee>");
    }

}
