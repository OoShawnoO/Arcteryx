package swu.edu.hzd;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class UpdateFilter implements Filter {

    private final static String ADMIN_USERNAME = "huzhida";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(request.getServletPath().equals("/Update")){
            try {
                Float.valueOf(request.getParameter("price"));
                Float.valueOf(request.getParameter("cost"));
            }catch (NumberFormatException e){
                response.sendRedirect("Revise.jsp?wrong=-1");
            }

        }
        else if(request.getServletPath().equals("/AddGoods")){
            try{
                Float.valueOf(request.getParameter("input2"));
                Float.valueOf(request.getParameter("input3"));
            }catch (NumberFormatException e){
                response.sendRedirect("Add.html?wrong=-1");
            }
        }

        else if(!(request.getSession().getAttribute("username")).equals(ADMIN_USERNAME)){
            if(!response.isCommitted()){
                response.sendRedirect("/RecordSystem/team");
            }
        }
        else{
            filterChain.doFilter(request,response);
        }


    }
}
