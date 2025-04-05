package com.mycompany;

import com.mycompany.slidemodel.Slide;

public class Presentation implements MyPublisher
{
    private String showTitle;
    private final ISlideManager slideManager;
    private final PresentationObserverManager observerManager = new PresentationObserverManager();

    public Presentation(ISlideManager slideManager)
    {
        this.slideManager = slideManager;
    }

    public Presentation()
    {
        this(new SlideManager());
        clear();
    }

    public Presentation(SlideViewerComponent slideViewerComponent)
    {
        this();
        observerManager.addObserver(slideViewerComponent);
    }

    public int getSize()
    {
        return slideManager.getSize();
    }

    public String getTitle()
    {
        return showTitle;
    }

    public void setTitle(String nt)
    {
        showTitle = nt;
    }

    public int getSlideNumber()
    {
        return slideManager.getCurrentIndex();
    }

    public void setSlideNumber(int number)
    {
        slideManager.setCurrentIndex(number);
        notifyObservers();
    }

    public void prevSlide()
    {
        if (slideManager.hasPrevious())
        {
            slideManager.prevSlide();
            notifyObservers();
        }
    }

    public void nextSlide()
    {
        if (slideManager.hasNext())
        {
            slideManager.nextSlide();
            notifyObservers();
        }
    }

    public void clear()
    {
        slideManager.clear();
        setSlideNumber(-1); // Triggers notifyObservers
    }

    public void append(Slide slide)
    {
        slideManager.addSlide(slide);

        // Automatically set to first slide if this is the first one
        if (slideManager.getSize() == 1) {
            setSlideNumber(0); // triggers notifyObservers too
        }
    }



    public Slide getSlide(int number)
    {
        return slideManager.getSlide(number);
    }

    public Slide getCurrentSlide()
    {
        return slideManager.getCurrentSlide();
    }

    public void exit(int n)
    {
        System.exit(n);
    }

    @Override
    public void addObserver(MySubscriber observer)
    {
        observerManager.addObserver(observer);
    }

    @Override
    public void removeObserver(MySubscriber observer)
    {
        observerManager.removeObserver(observer);
    }

    @Override
    public void notifyObservers()
    {
        observerManager.notifyObservers(this, getCurrentSlide());
    }
}
