package com.mycompany;

import com.mycompany.slidemodel.Slide;
import java.util.ArrayList;
import java.util.List;

public class SlideManager implements ISlideManager
{
    private final List<Slide> slides = new ArrayList<>();
    private int currentIndex = -1;

    public void addSlide(Slide slide)
    {
        slides.add(slide);
    }

    public void clear()
    {
        slides.clear();
        currentIndex = -1;
    }

    public Slide getSlide(int index)
    {
        if (index >= 0 && index < slides.size())
        {
            return slides.get(index);
        }
        return null;
    }

    public Slide getCurrentSlide()
    {
        return getSlide(currentIndex);
    }

    public int getSize()
    {
        return slides.size();
    }

    public int getCurrentIndex()
    {
        return currentIndex;
    }

    public void setCurrentIndex(int index)
    {
        if (index >= -1 && index < slides.size())
        {
            this.currentIndex = index;
        }
    }

    public boolean hasNext()
    {
        return currentIndex < slides.size() - 1;
    }

    public boolean hasPrevious()
    {
        return currentIndex > 0;
    }

    public void nextSlide()
    {
        if (hasNext())
        {
            currentIndex++;
        }
    }

    public void prevSlide()
    {
        if (hasPrevious())
        {
            currentIndex--;
        }
    }
}
