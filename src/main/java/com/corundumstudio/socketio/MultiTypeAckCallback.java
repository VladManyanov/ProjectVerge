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

/**
 * Multi type ack callback used in case of multiple ack arguments
 *
 */
public abstract class MultiTypeAckCallback extends AckCallback<MultiTypeArgs> {

    private Class<?>[] resultClasses;

    public MultiTypeAckCallback(Class<?> ... resultClasses) {
        super(MultiTypeArgs.class);
        this.resultClasses = resultClasses;
    }

    public Class<?>[] getResultClasses() {
        return resultClasses;
    }

}
