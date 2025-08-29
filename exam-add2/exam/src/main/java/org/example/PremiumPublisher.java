package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Класс издателя для премиум-событий.
 * Позволяет подписчикам (listeners) получать уведомления о {@link PremiumEvent}.
 */
public class PremiumPublisher {

    /** Список подписчиков на события */
    private List<Consumer<PremiumEvent>> subscribers = new ArrayList<>();

    /**
     * Добавляет нового подписчика.
     *
     * @param subscriber подписчик, реализующий {@link Consumer<PremiumEvent>}
     */
    public void subscribe(Consumer<PremiumEvent> subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * Удаляет подписчика.
     *
     * @param subscriber подписчик, которого нужно удалить
     */
    public void unsubscribe(Consumer<PremiumEvent> subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * Уведомляет всех подписчиков о произошедшем премиум-событии.
     * Создаётся копия списка подписчиков, чтобы избежать
     * ConcurrentModificationException при одновременном изменении списка.
     *
     * @param event событие типа {@link PremiumEvent}, которое нужно отправить подписчикам
     */
    public void notifySubscribers(PremiumEvent event) {
        List<Consumer<PremiumEvent>> subscribersCopy = new ArrayList<>(subscribers);
        subscribersCopy.forEach(subscriber -> subscriber.accept(event));
    }
}
