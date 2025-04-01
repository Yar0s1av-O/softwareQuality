package com.mycompany;

import com.mycompany.slidemodel.Slide;

import java.util.List;

public class SlideNavigator
{
    private final List<Slide> slides;
    private int currentIndex = -1;

    public SlideNavigator(List<Slide> slides)
    {
        this.slides = slides;
    }

    public int getCurrentIndex()
    {
        return this.currentIndex;
    }

    public void setCurrentIndex(int index)
    {
        if (index >= -1 && index < this.slides.size())
        {
            this.currentIndex = index;
        }
    }

    public boolean hasNext()
    {
        return this.currentIndex < this.slides.size() - 1;
    }

    public boolean hasPrevious()
    {
        return this.currentIndex > 0;
    }

    public void next()
    {
        if (this.hasNext())
        {
            this.currentIndex++;
        }
    }

    public void previous()
    {
        if (this.hasPrevious())
        {
            this.currentIndex--;
        }
    }

    public Slide getCurrentSlide()
    {
        if (this.currentIndex >= 0 && this.currentIndex < this.slides.size())
        {
            return this.slides.get(this.currentIndex);
        }

        return null;
    }
}
