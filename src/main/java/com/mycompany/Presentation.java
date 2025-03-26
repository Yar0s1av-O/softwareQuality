package com.mycompany;

import com.mycompany.slidemodel.Slide;
import java.util.ArrayList;

public class Presentation implements MyPublisher {
    private String showTitle;
    private ArrayList<Slide> showList;
    private final PresentationObserverManager observerManager = new PresentationObserverManager();
    private SlideNavigator navigator;

    public Presentation() {
        clear();
    }

    public Presentation(SlideViewerComponent slideViewerComponent) {
        this();
        observerManager.addObserver(slideViewerComponent);
    }

    public int getSize() {
        return showList.size();
    }

    public String getTitle() {
        return showTitle;
    }

    public void setTitle(String nt) {
        showTitle = nt;
    }

    public int getSlideNumber() {
        return navigator.getCurrentIndex();
    }

    public void setSlideNumber(int number) {
        navigator.setCurrentIndex(number);
        notifyObservers();
    }

    public void prevSlide() {
        if (navigator.hasPrevious()) {
            navigator.previous();
            notifyObservers();
        }
    }

    public void nextSlide() {
        if (navigator.hasNext()) {
            navigator.next();
            notifyObservers();
        }
    }

    public void clear() {
        showList = new ArrayList<>();
        navigator = new SlideNavigator(showList);
        setSlideNumber(-1); // this also triggers notifyObservers
    }

    public void append(Slide slide) {
        showList.add(slide);
    }

    public Slide getSlide(int number) {
        if (number < 0 || number >= getSize()) return null;
        return showList.get(number);
    }

    public Slide getCurrentSlide() {
        return navigator.getCurrentSlide();
    }

    public void exit(int n) {
        System.exit(n);
    }

    @Override
    public void addObserver(MySubscriber observer) {
        observerManager.addObserver(observer);
    }

    @Override
    public void removeObserver(MySubscriber observer) {
        observerManager.removeObserver(observer);
    }

    @Override
    public void notifyObservers() {
        observerManager.notifyObservers(this, getCurrentSlide());
    }
}
