package org.training.microservice.springplayground.patterns.observer;

public interface AlarmSubscription {

    void alarmOn(Alarm alarmParam);

    void alarmOff(Alarm alarmParam);


}
