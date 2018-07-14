package com.sky.design.Observer;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * Created by sky on 2017/3/27.
 */
public class ManagerObserver implements Observer {
    @Override
    public void update(Observable o, Object param) {
        if (param instanceof News){
            mail2Manager((News)param);
        }
    }

    private void mail2Manager(News news) {
        System.out.println("Mail to Manager. A news published with title:" + news.getTitle());
    }
}
