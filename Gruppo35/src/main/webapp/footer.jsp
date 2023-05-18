<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <footer>
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