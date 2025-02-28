package org.training.microservice.springplayground.patterns.observer;


import java.util.ArrayList;
import java.util.List;

public class AlarmPublisher implements AlarmNotifier {
    private List<AlarmSubscription> subscriptions = new ArrayList<>();

    public void subscribe(AlarmSubscription alarmSubscriptionParam){
        subscriptions.add(alarmSubscriptionParam);
    }


    @Override
    public void alarmOn(final Alarm alarmParam) {
        subscriptions.forEach(as -> as.alarmOn(alarmParam));
    }

    @Override
    public void alarmOff(final Alarm alarmParam) {
        subscriptions.forEach(as -> as.alarmOff(alarmParam));
    }
}
