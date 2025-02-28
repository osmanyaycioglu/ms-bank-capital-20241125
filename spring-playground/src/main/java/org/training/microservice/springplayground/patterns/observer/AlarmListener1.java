package org.training.microservice.springplayground.patterns.observer;

public class AlarmListener1 implements AlarmSubscription{
    @Override
    public void alarmOn(final Alarm alarmParam) {
        System.out.println("Alarm on 1 : " + alarmParam);
    }

    @Override
    public void alarmOff(final Alarm alarmParam) {
        System.out.println("Alarm off 1 : " + alarmParam);

    }
}
