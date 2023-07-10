package web.esame.gruppo35.helperClasses;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.SQLException;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        // Currently not used
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        try {
            DatabaseSessionManager.closeConnection(session);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
