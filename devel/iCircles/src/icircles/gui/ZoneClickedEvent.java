/*
 *   Project: iCircles
 * 
 * File name: ZoneClickedEvent.java
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

import icircles.concreteDiagram.ConcreteDiagram;
import icircles.concreteDiagram.ConcreteZone;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * This is the event descriptor of the {@link DiagramClickListener#zoneClicked(icircles.gui.ZoneClickedEvent)
 * zone clicked event}. <p>This event descriptor contains the clicked {@link
 * ConcreteZone zone}.</p>
 *
 * @author Matej Urbas [matej.urbas@gmail.com]
 */
public class ZoneClickedEvent extends DiagramClickEvent {

    /**
     * The clicked zone.
     */
    private final ConcreteZone zone;

    /**
     * Creates the descriptor of the {@link DiagramClickListener#zoneClicked(icircles.gui.ZoneClickedEvent)
     * zone clicked event}.
     *
     * @param source the {@link CirclesPanel2 circles panel} that is the origin
     * of this event.
     * @param zone the zone that has been clicked.
     * @param diagram the diagram which has been clicked.
     * @param clickInfo the additional mouse click information (the underlying mouse event that triggered
     * this diagram click event).
     * @param diagramCoordinates the coordinates of the click in diagram's local
     * coordinates.
     */
    public ZoneClickedEvent(CirclesPanel2 source, ConcreteDiagram diagram, MouseEvent clickInfo, Point diagramCoordinates, ConcreteZone zone) {
        super(source, diagram, clickInfo, diagramCoordinates);
        if (zone == null) {
            throw new IllegalArgumentException(icircles.i18n.Translations.i18n("GERR_NULL_ARGUMENT", "zone"));
        }
        this.zone = zone;
    }

    /**
     * Returns the zone that was clicked by the user.
     *
     * @return the zone that was clicked by the user.
     */
    public ConcreteZone getZone() {
        return zone;
    }
}
