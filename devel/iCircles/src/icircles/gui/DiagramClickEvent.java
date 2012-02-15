/*
 *   Project: iCircles
 * 
 * File name: DiagramClickEvent.java
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

import java.awt.event.MouseEvent;
import java.util.EventObject;

/**
 * The base type of all events in the {@link DiagramClickListener}.
 *
 * @author Matej Urbas [matej.urbas@gmail.com]
 */
public class DiagramClickEvent extends EventObject {

    /**
     * Additional mouse click information (the underlying mouse event that
     * triggered this diagram click event). <p><span
     * style="font-weight:bold">Note</span>: the mouse point coordinates are
     * given in the coordinate system of the {@link CirclesPanel2
     * circles panel}. In order to query the {@link CirclesPanel2#getDiagram()
     * diagram} for additional elements, you have to first {@link CirclesPanel2#toDiagramCoordinates(java.awt.Point) transform the point
     * into the diagram's own coordinate system}.</p>
     */
    private final MouseEvent clickInfo;

    /**
     * Initialises a new instance of the diagram click event.
     *
     * @param source the object that invoked this event (usually its a {@link
     * CirclesPanel2 circles panel}).
     * @param clickInfo the additional mouse click information (the underlying
     * mouse event that triggered this diagram click event).
     */
    public DiagramClickEvent(Object source, MouseEvent clickInfo) {
        super(source);
        this.clickInfo = clickInfo;
    }

    /**
     * Gets the additional mouse click information (the underlying mouse event
     * that triggered this diagram click event). <p><span
     * style="font-weight:bold">Note</span>: the mouse point coordinates are
     * given in the coordinate system of the {@link CirclesPanel2
     * circles panel}. In order to query the {@link CirclesPanel2#getDiagram()
     * diagram} for additional elements, you have to first {@link CirclesPanel2#toDiagramCoordinates(java.awt.Point) transform the point
     * into the diagram's own coordinate system}.</p>
     *
     * @return the additional mouse click information (the underlying mouse
     * event that triggered this diagram click event).
     */
    public MouseEvent getClickInfo() {
        return clickInfo;
    }
}
