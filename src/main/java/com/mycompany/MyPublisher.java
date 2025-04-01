package com.mycompany;

public interface MyPublisher
{
    void addObserver(MySubscriber observer);

    void removeObserver(MySubscriber observer);

    void notifyObservers();
}
