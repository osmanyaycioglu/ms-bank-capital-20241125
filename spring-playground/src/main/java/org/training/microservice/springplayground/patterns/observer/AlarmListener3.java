package org.training.microservice.springplayground.patterns.observer;

public class AlarmListener3 implements AlarmSubscription{
    @Override
    public void alarmOn(final Alarm alarmParam) {
        System.out.println("Alarm on 3 : " + alarmParam);
    }

    @Override
    public void alarmOff(final Alarm alarmParam) {
        System.out.println("Alarm off 3 : " + alarmParam);

    }
}
