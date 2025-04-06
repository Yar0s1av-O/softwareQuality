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
    private BufferedImage bufferedImage; // Holds the image data
    private String imageName; // Name of the image file

    protected static final String FILE = "File ";
    protected static final String NOTFOUND = " not found";

    /**
     * Constructs a BitmapItem with the given level and image filename.
     *
     * @param level The hierarchical level of the item.
     * @param name  The name of the image file to load.
     */
    public BitmapItem(int level, String name) {
        super(level);
        this.imageName = name;

        if (name == null || name.trim().isEmpty()) {
            System.err.println("Image name is empty");
            return;
        }

        try {
            // Attempt to load the image from the resources folder
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

    /**
     * Default constructor used when no image is specified.
     */
    public BitmapItem() {
        this(0, null);
    }

    /**
     * @return the name of the image file
     */
    public String getName() {
        return imageName;
    }

    /**
     * Calculates and returns the bounding box of the image.
     */
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

    /**
     * Renders the image at the specified position using the style's indentation and leading.
     */
    @Override
    public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
        if (bufferedImage == null) {
            return;
        }

        int drawX = x + (int) (myStyle.indent * scale);
        int drawY = y + (int) (myStyle.leading * scale);

        g.drawImage(bufferedImage, drawX, drawY,
                (int) (bufferedImage.getWidth(observer) * scale),
                (int) (bufferedImage.getHeight(observer) * scale),
                observer);
    }

    /**
     * @return String representation of the BitmapItem with level and image name
     */
    @Override
    public String toString() {
        return "BitmapItem[" + getLevel() + "," + imageName + "]";
    }
}
