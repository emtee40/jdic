/*
 * Copyright (C) 2004 Sun Microsystems, Inc. All rights reserved. Use is
 * subject to license terms.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 */ 

package org.jdesktop.jdic.browser;

import java.awt.Rectangle;

/**
 * An internal class that declares an event class used to be dispatched from WebBrowser to NativeEventThread.
 *
 * @see WebBrowser
 * @see NativeEventThread
 *
 * @author Kyle Yuan
 * @version 0.1, 03/07/17
 */
class NativeEventData
{
    final static int EVENT_INIT              = 0;
    final static int EVENT_CREATEWINDOW      = 1;
    final static int EVENT_DESTROYWINDOW     = 2;
    final static int EVENT_SHUTDOWN          = 3;
    final static int EVENT_SET_BOUNDS        = 4;
    final static int EVENT_NAVIGATE          = 5;
    final static int EVENT_NAVIGATE_POST     = 6;
    final static int EVENT_NAVIGATE_POSTDATA = 7;
    final static int EVENT_GOBACK            = 8;
    final static int EVENT_GOFORWARD         = 9;
    final static int EVENT_REFRESH           = 10;
    final static int EVENT_STOP              = 11;
    final static int EVENT_GETURL            = 12;
    final static int EVENT_FOCUSGAINED       = 13;
    final static int EVENT_FOCUSLOST         = 14;

    int instance;
    int type;
    Rectangle rectValue;
    String stringValue;

    NativeEventData (int instance, int type)
    {
        this.instance = instance;
        this.type = type;
    }

    NativeEventData (int instance, int type, Rectangle rectValue)
    {
        this.instance = instance;
        this.type = type;
        this.rectValue = rectValue;
    }

    NativeEventData (int instance, int type, String stringValue)
    {
        this.instance = instance;
        this.type = type;
        this.stringValue = stringValue;
    }
} // end of class NativeEventData
