package com.mycompany;

import com.mycompany.slidemodel.Slide;

public interface MySubscriber
{
    void update(Presentation presentation, Slide data);
}
