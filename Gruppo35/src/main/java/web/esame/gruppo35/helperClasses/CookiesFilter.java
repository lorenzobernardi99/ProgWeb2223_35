package web.esame.gruppo35.helperClasses;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CookiesFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        Cookie [] cookies = request.getCookies();
        String FormAccept = request.getParameter("FormAccept");

        String CookieUser = "false";
        String URL = request.getRequestURI();

        if(cookies!=null){
            for(Cookie c:cookies){
                String name=c.getName();
                if(name.equals("User")){
                    CookieUser = "true";
                }
            }
        }

        if(session == null){
            session = request.getSession(true);
        }

        if(session.isNew()){
            //Verifica che il browser accetti i cookies
            Cookie test = new Cookie("Test","val");
            test.setMaxAge(4);
            response.addCookie(test);
        }

        if(URL.equals("/")){
            URL = URL + "Homepage";
            response.sendRedirect(URL);
        }else if(cookies == null || URL.contains("jsessionid")){
            //Non Utilizzi i cookie
            request.setAttribute("URLRewrite",true);
            URL = URL + ";jsessionid=" + session.getId();
            for (Cookie c : cookies != null ? cookies : new Cookie[0]) {
                String name = c.getName();
                if (name.equals("User") || name.equals("JSESSIONID")) {
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
            request.getRequestDispatcher(URL).forward(request,response);
        }else{
            if(CookieUser.equals("false")) {
                if (FormAccept != null) {
                    if (FormAccept.equals("true")) {
                        //Accetti i cookie
                        Cookie User = new Cookie("User", "Accepted");
                        User.setMaxAge(30 * 60);
                        response.addCookie(User);
                    } else if (FormAccept.equals("false")) {
                        //Non accetti i cookie
                        for (Cookie c : cookies) {
                            String name = c.getName();
                            if (name.equals("User") || name.equals("JSESSIONID")) {
                                c.setMaxAge(0);
                                response.addCookie(c);
                                request.setAttribute("URLRewrite", true);
                            }
                        }
                    }
                }else{
                    request.setAttribute("URL", URL);
                    request.setAttribute("showBanner", true);
                }
            }
            chain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {}
}