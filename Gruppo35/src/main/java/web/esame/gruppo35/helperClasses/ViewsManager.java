package web.esame.gruppo35.helperClasses;

public class ViewsManager {

    static int totalViews;
    static int homepageViews;
    static int whoWeAreViews;
    static int activitiesViews;
    static int contactUsViews;
    static int signInViews;
    static int loginViews;

    public static synchronized int getTotalViews() {
        return totalViews;
    }

    public static synchronized int getHomepageViews() {
        return homepageViews;
    }

    public static synchronized int getWhoWeAreViews() {
        return whoWeAreViews;
    }

    public static synchronized int getActivitiesViews() {
        return activitiesViews;
    }

    public static synchronized int getContactUsViews() {
        return contactUsViews;
    }

    public static synchronized int getSignInViews() {
        return signInViews;
    }

    public static synchronized int getLoginViews() {
        return loginViews;
    }

    public static synchronized void setTotalViews(int totalViews) {
        ViewsManager.totalViews = totalViews;
    }

    public static synchronized void setHomepageViews(int homepageViews) {
        ViewsManager.homepageViews = homepageViews;
    }

    public static synchronized void setWhoWeAreViews(int whoWeAreViews) {
        ViewsManager.whoWeAreViews = whoWeAreViews;
    }

    public static synchronized void setActivitiesViews(int activitiesViews) {
        ViewsManager.activitiesViews = activitiesViews;
    }

    public static synchronized void setContactUsViews(int contactUsViews) {
        ViewsManager.contactUsViews = contactUsViews;
    }

    public static synchronized void setSignInViews(int signInViews) {
        ViewsManager.signInViews = signInViews;
    }

    public static synchronized void setLoginViews(int loginViews) {
        ViewsManager.loginViews = loginViews;
    }
}
