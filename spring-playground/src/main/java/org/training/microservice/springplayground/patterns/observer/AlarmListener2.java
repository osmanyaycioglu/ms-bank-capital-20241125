package org.training.microservice.springplayground.patterns.observer;

public class AlarmListener2 implements AlarmSubscription{
    @Override
    public void alarmOn(final Alarm alarmParam) {
        System.out.println("Alarm on 2 : " + alarmParam);
    }

    @Override
    public void alarmOff(final Alarm alarmParam) {
        System.out.println("Alarm off 2 : " + alarmParam);

    }
}
