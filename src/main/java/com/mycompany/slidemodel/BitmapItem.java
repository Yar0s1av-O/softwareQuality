package com.mycompany.slidemodel;

import com.mycompany.Style;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>The class for a Bitmap item</p>
 * <p>Bitmap items have the responsibility to draw themselves.</p>
 */

public class BitmapItem extends SlideItem {
    private BufferedImage bufferedImage;
    private String imageName;

    protected static final String FILE = "File ";
    protected static final String NOTFOUND = " not found";

    public BitmapItem(int level, String name) {
        super(level);
        this.imageName = name;

        if (name == null || name.trim().isEmpty()) {
            System.err.println("⚠️ Image name is empty");
            return;
        }

        try {
            InputStream imageStream = getClass().getResourceAsStream("/" + name);
            if (imageStream == null) {
                System.err.println(FILE + name + NOTFOUND);
                return;
            }

            this.bufferedImage = ImageIO.read(imageStream);
        } catch (IOException e) {
            System.err.println(FILE + name + NOTFOUND);
        }
    }

    public BitmapItem() {
        this(0, null);
    }

    public String getName() {
        return imageName;
    }

    @Override
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
        if (bufferedImage == null) {
            return new Rectangle(0, 0, 0, 0);
        }

        return new Rectangle(
                (int) (myStyle.indent * scale),
                0,
                (int) (bufferedImage.getWidth(observer) * scale),
                (int) (myStyle.leading * scale) + (int) (bufferedImage.getHeight(observer) * scale)
        );
    }

    @Override
    public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
        if (bufferedImage == null) {
            return; // don't try to draw if the image didn't load
        }

        int drawX = x + (int) (myStyle.indent * scale);
        int drawY = y + (int) (myStyle.leading * scale);

        g.drawImage(bufferedImage, drawX, drawY,
                (int) (bufferedImage.getWidth(observer) * scale),
                (int) (bufferedImage.getHeight(observer) * scale),
                observer);
    }

    @Override
    public String toString() {
        return "BitmapItem[" + getLevel() + "," + imageName + "]";
    }
}
