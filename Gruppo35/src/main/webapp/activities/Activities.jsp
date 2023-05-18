<%--  Created by IntelliJ IDEA.
  User: jacobbista
  Date: 10/05/23
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="web.esame.gruppo35.beans.ActivityBean" %>
<%@ include file="../header.jsp"%>
<html lang="it">
    <meta charset="UTF-8">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #0077be;
            color: white;
            padding: 10px;
            text-align: center;
        }
        h1 {
            margin-top: 0;
        }
        section {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            padding: 20px;
        }
        article {
            margin: 10px;
            max-width: 400px;
            text-align: center;
        }
        a {
            color: #0077be;
            font-weight: bold;
            text-decoration: none;
        }
        img {
            border-radius: 50%;
            height: 200px;
            width: 200px;
            transition: all 0.25s ease-in-out;
        }

        img:hover {
            transform: scale(110%);
            cursor: pointer;
            z-index: 1;
            border: 1px solid #0077be;
            border-radius: 50%;
            box-shadow: 0 0 10px #0077be;
        }
        h2 {
            margin-top: 20px;
            transition: all 0.25s ease-in-out;
        }
        h2:hover {
            cursor: pointer;
            color: #0077be;
            text-decoration: underline;
        }
    </style>
    <body>
        <section>
            <article>
                <jsp:useBean id="activityBeanList" class="web.esame.gruppo35.helperClasses.ActivityBeanList" scope="application"/>
                <% for (ActivityBean ab : activityBeanList) { %>
                <div>
                    <a href='Activities?id=<%=ab.getId()%>'>
                        <img src="<%=ab.getImagePath()%>" alt="<%=ab.getName()%>">
                    </a>
                    <br>
                    <h2>
                        <a href='Activities?id=<%=ab.getId()%>'><%=ab.getName()%></a>
                    </h2>
                </div>
                <% } %>
            </article>
        </section>
    </body>
    <%@ include file="../footer.jsp"%>
</html>
