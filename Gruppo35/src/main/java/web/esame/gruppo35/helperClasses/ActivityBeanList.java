package web.esame.gruppo35.helperClasses;

import web.esame.gruppo35.beans.ActivityBean;

import java.util.Arrays;
import java.util.LinkedList;

public class ActivityBeanList extends LinkedList<ActivityBean> {
    public ActivityBeanList() {}
    public ActivityBeanList(ActivityBean[] activityBeans) {
        this.addAll(Arrays.asList(activityBeans));
    }
}
