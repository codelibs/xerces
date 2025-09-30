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

package org.codelibs.xerces.impl.validation;

import java.util.ArrayList;

/**
 * ValidationManager is a coordinator property for validators in the
 * pipeline. Each validator must know how to interact with
 * this property. Validators are not required to know what kind of
 * other validators present in the pipeline, but should understand
 * that there are others and that some coordination is required.
 *

 *
 * @author Elena Litani, IBM
 * @version $Id: ValidationManager.java 606491 2007-12-22 21:00:53Z mrglavas $
 */
public class ValidationManager {

    /**
     * Default constructor.
     */
    public ValidationManager() {
    }

    /** List of validation states. */
    protected final ArrayList fVSs = new ArrayList();
    /** Flag indicating whether a grammar has been found. */
    protected boolean fGrammarFound = false;

    /**
     * Flag indicating whether a cached DTD is available.
     * Used by the DTD validator to tell other components that it has a
     * cached DTD in hand so there's no reason to
     * scan external subset or entity decls.
     */
    protected boolean fCachedDTD = false;

    /**
     * Each validator should call this method to add its ValidationState into
     * the validation manager.
     * @param vs the validation state to add
     */
    public final void addValidationState(ValidationState vs) {
        fVSs.add(vs);
    }

    /**
     * Set the information required to validate entity values.
     * @param state the entity state to set
     */
    public final void setEntityState(EntityState state) {
        for (int i = fVSs.size() - 1; i >= 0; i--) {
            ((ValidationState) fVSs.get(i)).setEntityState(state);
        }
    }

    /**
     * Sets whether a grammar has been found.
     * @param grammar true if a grammar has been found, false otherwise
     */
    public final void setGrammarFound(boolean grammar) {
        fGrammarFound = grammar;
    }

    /**
     * Checks whether a grammar has been found.
     * @return true if a grammar has been found, false otherwise
     */
    public final boolean isGrammarFound() {
        return fGrammarFound;
    }

    /**
     * Sets whether a cached DTD is available.
     * @param cachedDTD true if a cached DTD is available, false otherwise
     */
    public final void setCachedDTD(boolean cachedDTD) {
        fCachedDTD = cachedDTD;
    } // setCachedDTD(boolean)

    /**
     * Checks whether a cached DTD is available.
     * @return true if a cached DTD is available, false otherwise
     */
    public final boolean isCachedDTD() {
        return fCachedDTD;
    } // isCachedDTD():  boolean

    /**
     * Resets the validation manager to its initial state.
     */
    public final void reset() {
        fVSs.clear();
        fGrammarFound = false;
        fCachedDTD = false;
    }
}
