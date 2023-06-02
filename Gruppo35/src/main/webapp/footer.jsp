<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <footer>
            <script>
                var links='${Links}';
                if(links=="true"){
                    var elements=document.getElementsByClassName("ActivitiesLink");
                    for(var i=0; i<elements.length;i++){
                        elements.item(i).removeAttribute("href");
                    }
                    var elements=document.getElementsByClassName("nav-link");
                    for(var i=0; i<elements.length;i++){
                        elements.item(i).removeAttribute("href");
                    }
                    elements=document.getElementsByClassName("logo-link");
                    elements.item(0).removeAttribute("href");
                }
            </script>
            <div class="footer-content">
                <%=session.getId()%>
                <p class="address">
                    <%=application.getAttribute("organizationName") %><br>
                    <%=application.getAttribute("road") %>, <%= application.getAttribute("postalCode") %><br>
                    <%=application.getAttribute("city") %>, <%= application.getAttribute("country") %>
                </p>
            </div>
        </footer>
    </body>
</html>