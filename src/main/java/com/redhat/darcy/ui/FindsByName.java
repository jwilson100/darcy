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

import java.util.List;

public interface FindsByName {
    <T> List<T> findAllByName(Class<T> type, String name);
    default <T> T findByName(Class<T> type, String name) {
        List<T> found = findAllByName(type, name);
        
        if (found.isEmpty()) {
            throw new NotFoundException(type, By.name(name));
        }
        
        return found.get(0);
    }
}
