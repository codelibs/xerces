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

package org.codelibs.xerces.impl.xpath.regex;

/**
 * Regular expression parse exception.
 *
 * @author TAMURA Kent &lt;kent@trl.ibm.co.jp&gt;
 * @version $Id: ParseException.java 572108 2007-09-02 18:48:31Z mrglavas $
 */
public class ParseException extends RuntimeException {

    /** Serialization version. */
    static final long serialVersionUID = -7012400318097691370L;

    /**
     * The location where the parse error occurred.
     */
    final int location;

    /*
    public ParseException(String mes) {
        this(mes, -1);
    }
    */
    /**
     * Constructs a ParseException with the specified message and location.
     * @param mes the error message
     * @param location the location in the regular expression where the error occurred
     */
    public ParseException(String mes, int location) {
        super(mes);
        this.location = location;
    }

    /**
     * Gets the location where the parse error occurred.
     * @return the location, or -1 if location information is not available
     */
    public int getLocation() {
        return this.location;
    }
}
