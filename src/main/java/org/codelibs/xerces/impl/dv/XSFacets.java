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

import java.util.Vector;

import org.codelibs.xerces.impl.xs.util.XSObjectListImpl;
import org.codelibs.xerces.xs.XSAnnotation;
import org.codelibs.xerces.xs.XSObjectList;

/**
 * The class used to pass all facets to {@link XSSimpleType#applyFacets}.
 *
 * @author Sandy Gao, IBM
 *
 * @version $Id: XSFacets.java 699892 2008-09-28 21:08:27Z mrglavas $
 */
public class XSFacets {

    /**
     * Creates a new instance of XSFacets.
     */
    public XSFacets() {
        // Default constructor
    }

    /**
     * value of length facet.
     */
    public int length;

    /**
     * value of minLength facet.
     */
    public int minLength;

    /**
     * value of maxLength facet.
     */
    public int maxLength;

    /**
     * value of whiteSpace facet.
     */
    public short whiteSpace;

    /**
     * value of totalDigits facet.
     */
    public int totalDigits;

    /**
     * value of fractionDigits facet.
     */
    public int fractionDigits;

    /**
     * string containing value of pattern facet, for multiple patterns values
     * are ORed together.
     */
    public String pattern;

    /**
     * Vector containing values of Enumeration facet, as String's.
     */
    public Vector enumeration;

    /**
     * An array parallel to "Vector enumeration". It contains namespace context
     * of each enumeration value. Elements of this vector are NamespaceContext
     * objects.
     */
    public Vector enumNSDecls;

    /**
     * value of maxInclusive facet.
     */
    public String maxInclusive;

    /**
     * value of maxExclusive facet.
     */
    public String maxExclusive;

    /**
     * value of minInclusive facet.
     */
    public String minInclusive;

    /**
     * value of minExclusive facet.
     */
    public String minExclusive;

    /**
     * annotation for length facet.
     */
    public XSAnnotation lengthAnnotation;

    /**
     * annotation for minLength facet.
     */
    public XSAnnotation minLengthAnnotation;

    /**
     * annotation for maxLength facet.
     */
    public XSAnnotation maxLengthAnnotation;

    /**
     * annotation for whiteSpace facet.
     */
    public XSAnnotation whiteSpaceAnnotation;

    /**
     * annotation for totalDigits facet.
     */
    public XSAnnotation totalDigitsAnnotation;

    /**
     * annotation for fractionDigits facet.
     */
    public XSAnnotation fractionDigitsAnnotation;

    /**
     * annotations for pattern facets.
     */
    public XSObjectListImpl patternAnnotations;

    /**
     * annotations for enumeration facets.
     */
    public XSObjectList enumAnnotations;

    /**
     * annotation for maxInclusive facet.
     */
    public XSAnnotation maxInclusiveAnnotation;

    /**
     * annotation for maxExclusive facet.
     */
    public XSAnnotation maxExclusiveAnnotation;

    /**
     * annotation for minInclusive facet.
     */
    public XSAnnotation minInclusiveAnnotation;

    /**
     * annotation for minExclusive facet.
     */
    public XSAnnotation minExclusiveAnnotation;

    /**
     * Reset all annotation fields to null.
     */
    public void reset() {
        lengthAnnotation = null;
        minLengthAnnotation = null;
        maxLengthAnnotation = null;
        whiteSpaceAnnotation = null;
        totalDigitsAnnotation = null;
        fractionDigitsAnnotation = null;
        patternAnnotations = null;
        enumAnnotations = null;
        maxInclusiveAnnotation = null;
        maxExclusiveAnnotation = null;
        minInclusiveAnnotation = null;
        minExclusiveAnnotation = null;
    }
}
