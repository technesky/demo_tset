package com.sky.design.Observer;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * Created by sky on 2017/3/27.
 */
public class SubscriberObserver implements Observer {
    @Override
    public void update(Observable o, Object param) {
        if (param instanceof News){
            mail2Subscriber((News)param);
        }
    }

    private void mail2Subscriber(News news) {
        System.out.println("Mail to subscriber. A news published with title:" + news.getTitle());
    }
}
