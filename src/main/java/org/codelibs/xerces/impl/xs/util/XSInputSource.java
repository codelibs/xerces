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

import org.codelibs.xerces.impl.xs.SchemaGrammar;
import org.codelibs.xerces.xni.parser.XMLInputSource;
import org.codelibs.xerces.xs.XSObject;

/**
 * XML Schema input source implementation.
 *
 * @version $Id: XSInputSource.java 789785 2009-06-30 15:10:26Z knoaman $
 */
public final class XSInputSource extends XMLInputSource {

    /** Array of schema grammars associated with this input source. */
    private SchemaGrammar[] fGrammars;
    /** Array of XS components associated with this input source. */
    private XSObject[] fComponents;

    /**
     * Constructs an XSInputSource with the specified schema grammars.
     * @param grammars the array of schema grammars to associate with this input source
     */
    public XSInputSource(SchemaGrammar[] grammars) {
        super(null, null, null);
        fGrammars = grammars;
        fComponents = null;
    }

    /**
     * Constructs an XSInputSource with the specified XS components.
     * @param component the array of XS components to associate with this input source
     */
    public XSInputSource(XSObject[] component) {
        super(null, null, null);
        fGrammars = null;
        fComponents = component;
    }

    /**
     * Returns the array of schema grammars associated with this input source.
     * @return the array of schema grammars, or null if none are set
     */
    public SchemaGrammar[] getGrammars() {
        return fGrammars;
    }

    /**
     * Sets the array of schema grammars for this input source.
     * @param grammars the array of schema grammars to set
     */
    public void setGrammars(SchemaGrammar[] grammars) {
        fGrammars = grammars;
    }

    /**
     * Returns the array of XS components associated with this input source.
     * @return the array of XS components, or null if none are set
     */
    public XSObject[] getComponents() {
        return fComponents;
    }

    /**
     * Sets the array of XS components for this input source.
     * @param components the array of XS components to set
     */
    public void setComponents(XSObject[] components) {
        fComponents = components;
    }
}
