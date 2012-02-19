/*
 *   Project: iCircles
 * 
 * File name: ContourClickedEvent.java
 *    Author: Matej Urbas [matej.urbas@gmail.com]
 * 
 *  Copyright © 2012 Matej Urbas
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package icircles.gui;

import icircles.concreteDiagram.CircleContour;
import java.awt.event.MouseEvent;

/**
 * This is the event descriptor of the {@link DiagramClickListener#contourClicked(icircles.gui.ContourClickedEvent)
 * contour clicked event}. <p>This event descriptor contains the clicked {@link
 * CircleContour contour}.</p>
 *
 * @author Matej Urbas [matej.urbas@gmail.com]
 */
public class ContourClickedEvent extends DiagramClickEvent {

    /**
     * The clicked contour.
     */
    private final CircleContour contour;

    /**
     * Creates the descriptor of the {@link DiagramClickListener#contourClicked(icircles.gui.ContourClickedEvent) 
     * contour clicked event}.
     *
     * @param source the object that invoked this event (usually its a {@link
     * CirclesPanel2 circles panel}).
     * @param contour the contour that has been clicked.
     * @param clickInfo the additional mouse click information (the underlying mouse event that triggered
     * this diagram click event).
     */
    public ContourClickedEvent(Object source, CircleContour contour, MouseEvent clickInfo) {
        super(source, clickInfo);
        this.contour = contour;
    }

    /**
     * Returns the contour that was clicked by the user.
     *
     * @return the contour that was clicked by the user.
     */
    public CircleContour getContour() {
        return contour;
    }
}
