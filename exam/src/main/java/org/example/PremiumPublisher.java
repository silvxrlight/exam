package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class PremiumPublisher {
    private List<Consumer<PremiumEvent>> subscribers = new ArrayList<>();

    public void subscribe(Consumer<PremiumEvent> subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Consumer<PremiumEvent> subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers(PremiumEvent event) {
        List<Consumer<PremiumEvent>> subscribersCopy = new ArrayList<>(subscribers);
        subscribersCopy.forEach(subscriber -> subscriber.accept(event));
    }
}