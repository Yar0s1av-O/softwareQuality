package com.mycompany;

import com.mycompany.slidemodel.Slide;
import java.util.List;

public class SlideNavigator {
    private final List<Slide> slides;
    private int currentIndex = -1;

    public SlideNavigator(List<Slide> slides) {
        this.slides = slides;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int index) {
        if (index >= -1 && index < slides.size()) {
            this.currentIndex = index;
        }
    }

    public boolean hasNext() {
        return currentIndex < slides.size() - 1;
    }

    public boolean hasPrevious() {
        return currentIndex > 0;
    }

    public void next() {
        if (hasNext()) currentIndex++;
    }

    public void previous() {
        if (hasPrevious()) currentIndex--;
    }

    public Slide getCurrentSlide() {
        if (currentIndex >= 0 && currentIndex < slides.size()) {
            return slides.get(currentIndex);
        }
        return null;
    }
}
