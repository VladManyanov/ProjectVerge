/**
 * Copyright (c) 2012-2019 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.corundumstudio.socketio;

import java.util.Iterator;
import java.util.List;

public class MultiTypeArgs implements Iterable<Object> {

    private final List<Object> args;

    public MultiTypeArgs(List<Object> args) {
        super();
        this.args = args;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return args.size();
    }

    public List<Object> getArgs() {
        return args;
    }

    public <T> T first() {
        return get(0);
    }

    public <T> T second() {
        return get(1);
    }

    /**
     * "index out of bounds"-safe method for getting elements
     *
     * @param <T> type of argument
     * @param index to get
     * @return argument
     */
    public <T> T get(int index) {
        if (size() <= index) {
            return null;
        }
        return (T) args.get(index);
    }

    @Override
    public Iterator<Object> iterator() {
        return args.iterator();
    }

}
