/*
 Copyright 2014 Red Hat, Inc. and/or its affiliates.

 This file is part of darcy.

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.redhat.darcy.ui;

import com.redhat.darcy.ui.elements.Element;

import java.util.List;

/**
 * A context which can find UI elements.
 */
public interface ElementContext extends Context {
    default ElementSelection element() {
        return new ProxyElementSelection(this);
    }
    
    @Deprecated
    default <T extends Element> List<T> findElements(Class<T> type, Locator locator) {
        return element().listOfType(type, locator);
    }
    
    @Deprecated
    default <T extends Element> T findElement(Class<T> type, Locator locator) {
        return element().ofType(type, locator);
    }
    /**
     * Creates a {@link Transition}, which will create a {@link TransitionEvent} based on some View,
     * that you can wait for.
     * @return
     */
    default Transition transition() {
        return new SimpleTransition(this);
    }
}
