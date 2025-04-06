package com.mycompany;

import javax.swing.JOptionPane;
import java.io.IOException;

/**
 * JabberPoint Main Program
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 *
 * Starts the JabberPoint presentation system, sets up styles, loads presentation data,
 * and initializes the UI.
 */
public class JabberPoint
{
    protected static final String IOERR = "IO Error: ";
    protected static final String JABERR = "Jabberpoint Error ";
    protected static final String JABVERSION = "Jabberpoint 1.6 - OU version";

    public static void main(String[] args)
    {
        // Initialize slide styles (font, color, indent etc.)
        Style.createStyles();

        // Create a new presentation model
        Presentation presentation = new Presentation();

        // Initialize the main UI frame with the presentation
        new SlideViewerFrame(JABVERSION, presentation);

        try
        {
            if (args.length == 0)
            {
                // Load default demo presentation if no file is specified
                Accessor.getDemoAccessor().loadFile(presentation, "");
            }
            else
            {
                // Load presentation from specified XML file
                new XMLAccessor().loadFile(presentation, args[0]);
            }

            // Start from the first slide
            presentation.setSlideNumber(0);
        }
        catch (IOException ex)
        {
            // Show error dialog if file loading fails
            JOptionPane.showMessageDialog(null, IOERR + ex, JABERR, JOptionPane.ERROR_MESSAGE);
        }
    }
}
