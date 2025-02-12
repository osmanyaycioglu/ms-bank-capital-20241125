package org.training.capital.microservice.mscustomeraggregator.integration.retry;

import java.util.function.Predicate;

public class MyResultPredicate implements Predicate<String> {

    @Override
    public boolean test(final String stringParam) {
        if (stringParam.equals("osman")){
            return true;
        }
        return false;
    }
}
