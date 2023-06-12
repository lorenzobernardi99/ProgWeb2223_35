<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <footer>
            <% String links= (String) request.getAttribute("Links");
            if(links=="Requested"){%>
            <script>
                let elements = document.getElementsByClassName("ActivitiesLink");
                let i;
                for(i = 0; i<elements.length; i++){
                    elements.item(i).removeAttribute("href");
                }
                elements = document.getElementsByClassName("nav-link");
                for(i = 0; i<elements.length; i++){
                    elements.item(i).removeAttribute("href");
                }
            </script>
            <%request.getRequestDispatcher("/CookiesPolicy.jsp").include(request,response);}%>
            <div class="footer-content">
                <p class="address">
                    <%=application.getAttribute("organizationName") %><br>
                    <%=application.getAttribute("road") %>, <%= application.getAttribute("postalCode") %><br>
                    <%=application.getAttribute("city") %>, <%= application.getAttribute("country") %>
                </p>
            </div>
        </footer>
    </body>
</html>