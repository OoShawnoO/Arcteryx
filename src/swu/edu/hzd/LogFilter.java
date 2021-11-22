package swu.edu.hzd;




import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String site = filterConfig.getInitParameter("Site");
        System.out.println("网站名称:"+site);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(request.getServletPath().equals("/Login") || request.getServletPath().equals("/login.html")){
            filterChain.doFilter(request,response);
        }


        HttpSession session = request.getSession();
        if(Login.sessions.contains(session)){
            filterChain.doFilter(request,response);
        }
        else{
            response.sendRedirect("login.html?true=-2");
        }
    }

}
