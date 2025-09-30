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

import java.security.AccessController;
import java.security.PrivilegedAction;

import org.codelibs.xerces.impl.dv.InvalidDatatypeValueException;
import org.codelibs.xerces.impl.dv.ValidationContext;
import org.codelibs.xerces.util.XMLChar;

/**
 * Abstract base class for type validators.
 * All primitive types plus ID/IDREF/ENTITY/INTEGER are derived from this abstract
 * class. It provides extra information XSSimpleTypeDecl requires from each
 * type: allowed facets, converting String to actual value, check equality,
 * comparison, etc.
 *

 *
 * @author Neeraj Bajaj, Sun Microsystems, inc.
 * @author Sandy Gao, IBM
 *
 * @version $Id: TypeValidator.java 1375610 2012-08-21 15:36:30Z mrglavas $
 */
public abstract class TypeValidator {

    /**
     * Default constructor.
     */
    public TypeValidator() {
    }

    private static final boolean USE_CODE_POINT_COUNT_FOR_STRING_LENGTH = AccessController.doPrivileged(new PrivilegedAction() {
        public Object run() {
            try {
                return Boolean.getBoolean("org.codelibs.xerces.impl.dv.xs.useCodePointCountForStringLength") ? Boolean.TRUE : Boolean.FALSE;
            } catch (SecurityException ex) {}
            return Boolean.FALSE;
        }
    }) == Boolean.TRUE;

    /**
     * Returns which facets are allowed for this datatype.
     *
     * @return a bitmask representing the allowed facets
     */
    public abstract short getAllowedFacets();

    /**
     * Converts a string to an actual value. For example, for number types (decimal, double, float,
     * and types derived from them), gets the BigDecimal, Double, Float object. For some types
     * (string and derived), just returns the string itself.
     *
     * @param content The lexical representation to convert
     * @param context The validation context
     * @return The actual value object
     * @throws InvalidDatatypeValueException if the content is not a valid lexical representation
     */
    public abstract Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException;

    /**
     * Performs additional validation rules specific to certain datatypes.
     * For ID/IDREF/ENTITY types, this method does extra checking after the value is
     * validated with respect to both lexical representation and facets.
     *
     * @param value the value to check for extra rules
     * @param context the validation context
     * @throws InvalidDatatypeValueException if the value violates type-specific rules
     */
    public void checkExtraRules(Object value, ValidationContext context) throws InvalidDatatypeValueException {
    }

    // the following methods might not be supported by every DV.
    // but XSSimpleTypeDecl should know which type supports which methods,
    // and it's an *internal* error if a method is called on a DV that
    // doesn't support it.

    //order constants
    /** Constant indicating first value is less than second value. */
    public static final short LESS_THAN = -1;
    /** Constant indicating values are equal. */
    public static final short EQUAL = 0;
    /** Constant indicating first value is greater than second value. */
    public static final short GREATER_THAN = 1;
    /** Constant indicating values cannot be compared. */
    public static final short INDETERMINATE = 2;

    /**
     * Checks whether the two values are identical.
     * Where there is a distinction between identity and equality, this method
     * will be overridden. For example, this distinguishes -0.0 from 0.0.
     *
     * @param value1 the first value to compare
     * @param value2 the second value to compare
     * @return true if the values are identical, false otherwise
     */
    public boolean isIdentical(Object value1, Object value2) {
        return value1.equals(value2);
    }

    /**
     * Checks the order relation between the two values.
     * The parameters are in compiled form (from getActualValue).
     *
     * @param value1 the first value to compare
     * @param value2 the second value to compare
     * @return LESS_THAN, EQUAL, GREATER_THAN, or INDETERMINATE
     */
    public int compare(Object value1, Object value2) {
        return -1;
    }

    /**
     * Get the length of the value.
     * The parameters are in compiled form (from getActualValue).
     *
     * @param value the value to get the length of
     * @return the length of the value
     */
    public int getDataLength(Object value) {
        if (value instanceof String) {
            final String str = (String) value;
            if (!USE_CODE_POINT_COUNT_FOR_STRING_LENGTH) {
                return str.length();
            }
            return getCodePointLength(str);
        }
        return -1;
    }

    /**
     * Gets the number of digits of the value.
     * The parameters are in compiled form (from getActualValue).
     *
     * @param value The value to get total digits for
     * @return The total number of digits, or -1 if not applicable
     */
    public int getTotalDigits(Object value) {
        return -1;
    }

    /**
     * Gets the number of fraction digits of the value.
     * The parameters are in compiled form (from getActualValue).
     *
     * @param value The value to get fraction digits for
     * @return The number of fraction digits, or -1 if not applicable
     */
    public int getFractionDigits(Object value) {
        return -1;
    }

    // Returns the length of the string in Unicode code points.
    private int getCodePointLength(String value) {
        // Count the number of surrogate pairs, and subtract them from
        // the total length.
        final int len = value.length();
        int surrogatePairCount = 0;
        for (int i = 0; i < len - 1; ++i) {
            if (XMLChar.isHighSurrogate(value.charAt(i))) {
                if (XMLChar.isLowSurrogate(value.charAt(++i))) {
                    ++surrogatePairCount;
                } else {
                    --i;
                }
            }
        }
        return len - surrogatePairCount;
    }

    /**
     * Check whether the character is in the range 0x30 ~ 0x39.
     * @param ch the character to check
     * @return true if the character is a digit, false otherwise
     */
    public static final boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    /**
     * If the character is in the range 0x30 ~ 0x39, return its int value (0~9),
     * otherwise, return -1.
     * @param ch the character to convert to digit
     * @return the digit value (0-9) or -1 if not a digit
     */
    public static final int getDigit(char ch) {
        return isDigit(ch) ? ch - '0' : -1;
    }

} // interface TypeValidator
