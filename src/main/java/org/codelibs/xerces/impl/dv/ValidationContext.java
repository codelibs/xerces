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

package org.codelibs.xerces.impl.dv;

import java.util.Locale;

/**
 * ValidationContext has all the information required for the
 * validation of: id, idref, entity, notation, qname
 *

 *
 * @author Sandy Gao, IBM
 * @version $Id: ValidationContext.java 713638 2008-11-13 04:42:18Z mrglavas $
 */
public interface ValidationContext {
    /**
     * Indicates whether facet validation is required.
     * @return true if facet checking is needed, false otherwise
     */
    public boolean needFacetChecking();

    /**
     * Whether to do extra id/idref/entity checking.
     * @return true if extra checking is needed, false otherwise
     */
    public boolean needExtraChecking();

    /**
     * Indicates whether value normalization is required.
     * @return true if normalization is needed, false otherwise
     */
    public boolean needToNormalize();

    /**
     * Indicates whether namespaces should be considered in validation.
     * @return true if namespaces are used, false otherwise
     */
    public boolean useNamespaces();

    // entity
    /**
     * Checks if an entity with the specified name has been declared.
     *
     * @param name the name of the entity to check
     * @return true if the entity has been declared, false otherwise
     */
    public boolean isEntityDeclared(String name);

    /**
     * Checks if an entity with the specified name is an unparsed entity.
     *
     * @param name the name of the entity to check
     * @return true if the entity is unparsed, false otherwise
     */
    public boolean isEntityUnparsed(String name);

    // id
    /**
     * Checks if an ID with the specified name has been declared.
     *
     * @param name the name of the ID to check
     * @return true if the ID has been declared, false otherwise
     */
    public boolean isIdDeclared(String name);

    /**
     * Adds an ID to the list of declared IDs.
     *
     * @param name the name of the ID to add
     */
    public void addId(String name);

    // idref
    /**
     * Adds an IDREF to the list of referenced IDs.
     *
     * @param name the name of the IDREF to add
     */
    public void addIdRef(String name);

    /**
     * Get symbol from symbol table.
     * @param symbol the symbol to get
     * @return the symbol from symbol table
     */
    public String getSymbol(String symbol);

    /**
     * Get URI for the given prefix.
     * @param prefix the namespace prefix
     * @return the URI for the given prefix
     */
    public String getURI(String prefix);

    /**
     * Get the locale for formatting messages.
     * @return the locale
     */
    public Locale getLocale();
}
