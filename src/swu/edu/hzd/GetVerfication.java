package swu.edu.hzd;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;


@WebServlet(name="GetVerfication")
public class GetVerfication extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request,response);
    }

    protected  void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("image/jpg");
        Verfication verfication = new Verfication();
        BufferedImage image = verfication.getImage();
        request.getSession().setAttribute("verfication",verfication.getText());
        if(!response.isCommitted()) {
            Verfication.output(image, response.getOutputStream());
        }
    }
}
