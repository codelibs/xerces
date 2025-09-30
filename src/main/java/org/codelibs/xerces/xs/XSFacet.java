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

package org.codelibs.xerces.xs;

/**
 * Describes a constraining facet. Enumeration and pattern facets are exposed
 * via <code>XSMultiValueFacet</code> interface.
 */
public interface XSFacet extends XSObject {
    /**
     * The name of the facet, e.g. <code>FACET_LENGTH, FACET_TOTALDIGITS</code>
     *  (see <code>XSSimpleTypeDefinition</code>).
     * @return the facet kind as a short value
     */
    public short getFacetKind();

    /**
     * A value of this facet.
     * @return the lexical representation of the facet value
     */
    public String getLexicalFacetValue();

    /**
     * If this facet is length, minLength, maxLength, totalDigits, or
     * fractionDigits, and if the value can fit in "int", then return the value
     * of the facet as an int. If the value can't fit, return -1. Use
     * getActualFacetValue() to get the BigInteger representation. For all other
     * facets, return 0.
     * @return the integer value of the facet, or -1 if it doesn't fit in an int, or 0 for non-numeric facets
     */
    public int getIntFacetValue();

    /**
     * If this facet is minInclusive, maxInclusive, minExclusive, or
     * maxExclusive, then return the actual value of the facet. If this facet
     * is length, minLength, maxLength, totalDigits, or fractionDigits, then
     * return a BigInteger representation of the value. If this facet is
     * whiteSpace, then return the String representation of the facet.
     * @return the actual value of the facet as an Object
     */
    public Object getActualFacetValue();

    /**
     * [Facets]: check whether a facet is fixed.
     * @return true if the facet is fixed, false otherwise
     */
    public boolean getFixed();

    /**
     * An annotation if it exists, otherwise <code>null</code>. If not null
     * then the first [annotation] from the sequence of annotations.
     * @return the first annotation, or null if no annotations exist
     */
    public XSAnnotation getAnnotation();

    /**
     * A sequence of [annotations] or an empty <code>XSObjectList</code>.
     * @return a list of annotations, or an empty list if no annotations exist
     */
    public XSObjectList getAnnotations();
}
