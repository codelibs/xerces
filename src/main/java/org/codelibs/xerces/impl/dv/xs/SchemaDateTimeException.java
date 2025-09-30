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

package org.codelibs.xerces.impl.dv.xs;

/**
 * Exception thrown when an invalid date/time value is encountered during Schema validation.
 * This runtime exception is used to signal errors in date/time format processing.
 *
 * @version $Id: SchemaDateTimeException.java 446745 2006-09-15 21:43:58Z mrglavas $
 */
public class SchemaDateTimeException extends RuntimeException {

    /** Serialization version. */
    static final long serialVersionUID = -8520832235337769040L;

    /**
     * Constructs a new SchemaDateTimeException with no detail message.
     */
    public SchemaDateTimeException() {
        super();
    }

    /**
     * Constructs a new SchemaDateTimeException with the specified detail message.
     * @param s the detail message
     */
    public SchemaDateTimeException(String s) {
        super(s);
    }
}
