package com.mycompany;

import com.mycompany.slidemodel.Slide;

import java.util.ArrayList;
import java.util.List;

public class PresentationObserverManager
{
    private final List<MySubscriber> subscribers = new ArrayList<>();

    public void addObserver(MySubscriber subscriber)
    {
        this.subscribers.add(subscriber);
    }

    public void removeObserver(MySubscriber subscriber)
    {
        this.subscribers.remove(subscriber);
    }

    public void notifyObservers(Presentation presentation, Slide currentSlide)
    {
        for (MySubscriber sub : this.subscribers)
        {
            sub.update(presentation, currentSlide);
        }
    }
}
