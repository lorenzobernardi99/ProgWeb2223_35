package web.esame.gruppo35.helperClasses;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(filterName = "Filter1")
public class Filter1 implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String CookieUser="false";
        Cookie [] cookies=request.getCookies();
        HttpSession sessione=request.getSession(false);
        String FormAccept= request.getParameter("FormAccept");
        String URL= request.getRequestURI();

        if(cookies!=null){
            for(Cookie c:cookies){
                String name=c.getName();
                if(name.equals("User")){
                    CookieUser="true";
                }
            }
        }


        if(sessione==null){
            sessione=request.getSession(true);
        }

        if(sessione.isNew()){//Verifica che il browser accetti i cookies
            Cookie test=new Cookie("Test","val");
            test.setMaxAge(4);
            response.addCookie(test);
        }

        if(URL.equals("/")){
            URL=URL+"Homepage";
            response.sendRedirect(URL);
        }else if(cookies==null){
            //Non Utilizzi i cookie
            request.setAttribute("URLRewrite","True");
            URL=URL+";jsessionid="+sessione.getId();
            request.getRequestDispatcher(URL).forward(request,response);
        }else{
            if(CookieUser.equals("false")) {
                if (FormAccept != null) {
                    if (FormAccept.equals("true")) {
                        //Accetti i cookie
                        Cookie User = new Cookie("User", "Accepted");
                        User.setMaxAge(30*60);
                        response.addCookie(User);
                    } else if (FormAccept.equals("false")) {
                        //Non accetti i cookie
                        for (Cookie c : cookies) {
                            String name = c.getName();
                            if (name.equals("User") || name.equals("JSESSIONID")) {
                                c.setMaxAge(0);
                                response.addCookie(c);
                                request.setAttribute("URLRewrite", "True");
                            }
                        }
                    }
                }else{
                    request.setAttribute("Links","Requested");
                    request.setAttribute("URL",URL);
                }
            }
            chain.doFilter(req,resp);
        }




    }

    @Override
    public void destroy() { }
}
