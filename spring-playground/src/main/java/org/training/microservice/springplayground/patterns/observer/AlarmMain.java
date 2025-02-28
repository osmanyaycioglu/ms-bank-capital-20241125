package org.training.microservice.springplayground.patterns.observer;

import java.util.UUID;

public class AlarmMain {
    public static void main(String[] args) {
        AlarmPublisher alarmPublisherLoc = new AlarmPublisher();
        AlarmListener1 listener1Loc = new AlarmListener1();
        AlarmListener2 listener2Loc = new AlarmListener2();
        AlarmListener3 listener3Loc = new AlarmListener3();

        alarmPublisherLoc.subscribe(listener1Loc);
        alarmPublisherLoc.subscribe(listener2Loc);
        alarmPublisherLoc.subscribe(listener3Loc);

        String alarmIdLoc = UUID.randomUUID()
                               .toString();
        alarmPublisherLoc.alarmOn(new Alarm(alarmIdLoc,"FIRE"));
        alarmPublisherLoc.alarmOff(new Alarm(alarmIdLoc,"FIRE"));

    }
}
