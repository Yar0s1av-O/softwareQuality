package com.mycompany;

import com.mycompany.slidemodel.Slide;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of subscribers (observers) and notifies them
 * whenever there is an update in the presentation.
 *
 * This class implements the Observer design pattern.
 */
public class PresentationObserverManager {

    // List to store all observers interested in updates
    private final List<MySubscriber> subscribers = new ArrayList<>();

    /**
     * Registers a new observer (subscriber).
     *
     * @param subscriber The object that wants to receive updates.
     */
    public void addObserver(MySubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    /**
     * Unregisters an existing observer.
     *
     * @param subscriber The object that no longer wants updates.
     */
    public void removeObserver(MySubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    /**
     * Notifies all registered observers about an update to the presentation.
     *
     * @param presentation  The current presentation context.
     * @param currentSlide  The slide that was updated or changed.
     */
    public void notifyObservers(Presentation presentation, Slide currentSlide) {
        for (MySubscriber sub : this.subscribers) {
            sub.update(presentation, currentSlide);
        }
    }
}
