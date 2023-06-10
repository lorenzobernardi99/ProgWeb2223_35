package web.esame.gruppo35.beans;

import web.esame.gruppo35.helperClasses.UserRole;
import java.io.Serializable;
import java.util.Date;

public class DonationBean implements Serializable {
    private UserBean contributor;
    private Float amount;
    private Date date;

    public DonationBean(){};

    public DonationBean(UserBean contributor, Float amount, Date date) {
        this.contributor = contributor;
        this.amount = amount;
        this.date = date;
    }

    public UserBean getContributor() {
        return contributor;
    }
    public Float getAmount() {
        return amount;
    }
    public Date getDate() {
        return date;
    }
    public void setContributor(UserBean contributor) {
        this.contributor = contributor;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
