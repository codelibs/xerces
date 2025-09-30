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

import org.codelibs.xerces.impl.dv.XSSimpleType;
import org.codelibs.xerces.util.SymbolHash;

/**
 * Schema datatype validator factory implementation for XML Schema 1.0.
 * This factory creates and manages built-in schema datatype validators
 * and provides functionality to create user-defined simple types.
 *
 * <p>This implementation extends the base factory to provide full support
 * for XML Schema 1.0 built-in types and derivation mechanisms.</p>
 *

 *
 * @author Neeraj Bajaj, Sun Microsystems, inc.
 * @author Sandy Gao, IBM
 * @author Khaled Noaman, IBM
 *
 * @version $Id: SchemaDVFactoryImpl.java 710089 2008-11-03 16:01:16Z knoaman $
 */
public class SchemaDVFactoryImpl extends BaseSchemaDVFactory {

    /**
     * Default constructor.
     * Creates a new instance of the schema datatype validator factory.
     */
    public SchemaDVFactoryImpl() {
        super();
    }

    /** Hash table containing all built-in datatype validators. */
    static final SymbolHash fBuiltInTypes = new SymbolHash();

    static {
        createBuiltInTypes();
    }

    // create all built-in types
    static void createBuiltInTypes() {
        createBuiltInTypes(fBuiltInTypes, XSSimpleTypeDecl.fAnySimpleType);

        // TODO: move specific 1.0 DV implementation from base
    } //createBuiltInTypes()

    /**
     * Get a built-in simple type of the given name
     * REVISIT: its still not decided within the Schema WG how to define the
     *          ur-types and if all simple types should be derived from a
     *          complex type, so as of now we ignore the fact that anySimpleType
     *          is derived from anyType, and pass 'null' as the base of
     *          anySimpleType. It needs to be changed as per the decision taken.
     *
     * @param name  the name of the datatype
     * @return      the datatype validator of the given name
     */
    public XSSimpleType getBuiltInType(String name) {
        return (XSSimpleType) fBuiltInTypes.get(name);
    }

    /**
     * get all built-in simple types, which are stored in a hashtable keyed by
     * the name
     *
     * @return      a hashtable which contains all built-in simple types
     */
    public SymbolHash getBuiltInTypes() {
        return (SymbolHash) fBuiltInTypes.makeClone();
    }

}//SchemaDVFactoryImpl
