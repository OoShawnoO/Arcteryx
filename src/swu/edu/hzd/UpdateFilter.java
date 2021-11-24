package swu.edu.hzd;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateFilter implements Filter {

    private final static String ADMIN_USERNAME = "huzhida";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(!(request.getSession().getAttribute("username")).equals(ADMIN_USERNAME)){
            if(!response.isCommitted()){
                response.sendRedirect("/RecordSystem/team");
            }
        }
        else{
            filterChain.doFilter(request,response);
        }


    }
}
