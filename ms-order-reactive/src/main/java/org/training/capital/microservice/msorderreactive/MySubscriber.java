package org.training.capital.microservice.msorderreactive;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySubscriber implements Subscriber<String> {

    @Override
    public void onSubscribe(final Subscription s) {
        System.out.println("Subscription : " + s);
    }

    @Override
    public void onNext(final String item) {
        System.out.println("Geldi : " + item);
    }

    @Override
    public void onError(final Throwable throwable) {
        System.err.println("Error : " + throwable);
    }

    @Override
    public void onComplete() {
        System.out.println("complete");
    }
}
