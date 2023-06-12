<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <footer>
            <% boolean showBanner = request.getAttribute("showBanner") != null;%>
            <script>
                // Seleziona tutti gli elementi <a> con attributo href
                const links = document.querySelectorAll('a[href]');

                // Disabilita gli attributi href dei link
                function disableLinkHrefs() {
                    links.forEach(link => {
                        link.dataset.href = link.getAttribute('href'); // Salva l'attributo href nei dati personalizzati
                        link.removeAttribute('href'); // Rimuove l'attributo href
                    });
                }

                // Riabilita gli attributi href dei link
                function enableLinkHrefs() {
                    links.forEach(link => {
                        const href = link.dataset.href; // Recupera l'attributo href dai dati personalizzati
                        link.setAttribute('href', href); // Ripristina l'attributo href
                        link.removeAttribute('data-href'); // Rimuove il dato personalizzato
                    });
                }
                <%= showBanner ? "disableLinkHrefs();" : "" %>
            </script>
            <% if(showBanner) request.getRequestDispatcher("/CookiesPolicy.jsp").include(request,response);%>
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