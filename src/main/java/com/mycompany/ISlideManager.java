package com.mycompany;

import com.mycompany.slidemodel.Slide;

public interface ISlideManager
{
    void addSlide(Slide slide);
    Slide getSlide(int index);
    Slide getCurrentSlide();
    int getSize();
    int getCurrentIndex();
    void setCurrentIndex(int index);
    boolean hasNext();
    boolean hasPrevious();
    void nextSlide();
    void prevSlide();
    void clear();
}
