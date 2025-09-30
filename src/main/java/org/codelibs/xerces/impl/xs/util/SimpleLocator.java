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

import org.codelibs.xerces.xni.XMLLocator;

/**
 * An XMLLocator implementation used for schema error reporting.
 *

 *
 * @author Sandy Gao, IBM
 * @version $Id: SimpleLocator.java 660072 2008-05-26 02:05:15Z mrglavas $
 */
public final class SimpleLocator implements XMLLocator {

    private String lsid;
    private String esid;
    private int line;
    private int column;
    private int charOffset;

    /**
     * Default constructor.
     */
    public SimpleLocator() {
    }

    /**
     * Constructs a simple locator with the specified location information.
     *
     * @param lsid The literal system identifier.
     * @param esid The expanded system identifier.
     * @param line The line number.
     * @param column The column number.
     */
    public SimpleLocator(String lsid, String esid, int line, int column) {
        this(lsid, esid, line, column, -1);
    }

    /**
     * Sets the locator values.
     *
     * @param lsid The literal system identifier.
     * @param esid The expanded system identifier.
     * @param line The line number.
     * @param column The column number.
     */
    public void setValues(String lsid, String esid, int line, int column) {
        setValues(lsid, esid, line, column, -1);
    }

    /**
     * Constructs a simple locator with the specified location information including character offset.
     *
     * @param lsid The literal system identifier.
     * @param esid The expanded system identifier.
     * @param line The line number.
     * @param column The column number.
     * @param offset The character offset.
     */
    public SimpleLocator(String lsid, String esid, int line, int column, int offset) {
        this.line = line;
        this.column = column;
        this.lsid = lsid;
        this.esid = esid;
        charOffset = offset;
    }

    /**
     * Sets the locator values including character offset.
     *
     * @param lsid The literal system identifier.
     * @param esid The expanded system identifier.
     * @param line The line number.
     * @param column The column number.
     * @param offset The character offset.
     */
    public void setValues(String lsid, String esid, int line, int column, int offset) {
        this.line = line;
        this.column = column;
        this.lsid = lsid;
        this.esid = esid;
        charOffset = offset;
    }

    public int getLineNumber() {
        return line;
    }

    public int getColumnNumber() {
        return column;
    }

    public int getCharacterOffset() {
        return charOffset;
    }

    public String getPublicId() {
        return null;
    }

    public String getExpandedSystemId() {
        return esid;
    }

    public String getLiteralSystemId() {
        return lsid;
    }

    public String getBaseSystemId() {
        return null;
    }

    /**
     * Sets the column number.
     *
     * @param col The column number.
     */
    public void setColumnNumber(int col) {
        this.column = col;
    }

    /**
     * Sets the line number.
     *
     * @param line The line number.
     */
    public void setLineNumber(int line) {
        this.line = line;
    }

    /**
     * Sets the character offset.
     *
     * @param offset The character offset.
     */
    public void setCharacterOffset(int offset) {
        charOffset = offset;
    }

    /**
     * Sets the base system identifier (not implemented in this locator).
     *
     * @param systemId The base system identifier.
     * @see org.codelibs.xerces.xni.XMLResourceIdentifier#setBaseSystemId(String)
     */
    public void setBaseSystemId(String systemId) {
    }

    /**
     * Sets the expanded system identifier.
     *
     * @param systemId The expanded system identifier.
     * @see org.codelibs.xerces.xni.XMLResourceIdentifier#setExpandedSystemId(String)
     */
    public void setExpandedSystemId(String systemId) {
        esid = systemId;
    }

    /**
     * Sets the literal system identifier.
     *
     * @param systemId The literal system identifier.
     * @see org.codelibs.xerces.xni.XMLResourceIdentifier#setLiteralSystemId(String)
     */
    public void setLiteralSystemId(String systemId) {
        lsid = systemId;
    }

    /**
     * Sets the public identifier (not implemented in this locator).
     *
     * @param publicId The public identifier.
     * @see org.codelibs.xerces.xni.XMLResourceIdentifier#setPublicId(String)
     */
    public void setPublicId(String publicId) {
    }

    /**
     * Returns the encoding of the current entity.
     * Since these locators are used in the construction of
     * XMLParseExceptions, which know nothing about encodings, there is
     * no point in having this object deal intelligently
     * with encoding information.
     */
    public String getEncoding() {
        return null;
    }

    public String getXMLVersion() {
        return null;
    }
}
