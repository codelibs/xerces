/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codelibs.xerces.impl.xs.util;

/**
 * Pool for reusing XInt objects.
 *
 * @author Henry Zongaro, IBM
 * @version $Id: XIntPool.java 446723 2006-09-15 20:37:45Z mrglavas $
 */
public final class XIntPool {
    private static final short POOL_SIZE = 10;
    private static final XInt[] fXIntPool = new XInt[POOL_SIZE];

    /**
     * Default constructor for XIntPool.
     * Instances are not needed as all methods are static.
     */
    public XIntPool() {
        // Default constructor
    }

    static {
        for (int i = 0; i < POOL_SIZE; i++)
            fXIntPool[i] = new XInt(i);
    }

    /**
     * Gets an XInt object for the given value, reusing pooled instances when possible.
     * @param value the integer value
     * @return an XInt object with the specified value
     */
    public final XInt getXInt(int value) {
        if (value >= 0 && value < fXIntPool.length)
            return fXIntPool[value];
        else
            return new XInt(value);
    }
}
