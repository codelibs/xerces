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
package org.codelibs.xerces.xs.datatypes;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Interface to expose the values for all date-time related types. The following
 * table shows the methods defined for various XML Schema 1.0 built-in types. 'X'
 * marks whether a particular method is defined for a particular type. Accessing undefined
 * methods may return unexpected values.
 * </p>
 * <table border="1">
 * <caption>Date-Time Type Methods</caption>
 * <tr>
 * <td> XML Schema Datatype </td>
 * <td> getYears() </td>
 * <td> getMonths() </td>
 * <td> getDays() </td>
 * <td> getHours() </td>
 * <td> getMinutes() </td>
 * <td> getSeconds() </td>
 * <td> getTimeZoneHours() </td>
 * <td> getTimeZoneMinutes() </td>
 * <td> getXMLGregorianCalendar() </td>
 * <td> getDuration() </td>
 * <td> hasTimeZone() </td>
 * <td> normalize() </td>
 * <td> isNormalized() </td>
 * <td> getLexicalValue() </td>
 * </tr>
 * <tr>
 * <td> gYear </td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> gMonth </td>
 * <td>-</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> gDay </td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> gYearMonth </td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> gMonthDay </td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> date </td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> time </td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> datetime </td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>-</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * <td>X</td>
 * </tr>
 * <tr>
 * <td> duration </td>
 * <td>-</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * <td>-</td>
 * <td>-</td>
 * <td>-</td>
 * <td>X</td>
 * </tr>
 * </table>
 *
 * @author Ankit Pasricha, IBM
 *
 * @version $Id: XSDateTime.java 447250 2006-09-18 05:27:17Z mrglavas $
 */
public interface XSDateTime {

    /**
     * Gets the years component.
     * @return years - can be negative for date-time related types
     */
    public int getYears();

    /**
     * Gets the months component.
     * @return months - can be negative only for duration types;
     *                  For duration types, it returns years*12 + months
     */
    public int getMonths();

    /**
     * Gets the days component.
     * @return days - cannot be negative
     */
    public int getDays();

    /**
     * Gets the hours component.
     * @return hours - cannot be negative
     */
    public int getHours();

    /**
     * Gets the minutes component.
     * @return minutes - cannot be negative
     */
    public int getMinutes();

    /**
     * Gets the seconds component.
     * @return seconds - can be negative only for durations;
     *                   For duration types, it returns days*24*3600 + hours*3600
     *                                                  + minutes*60 + seconds
     */
    public double getSeconds();

    /**
     * Checks if timezone is specified.
     * @return boolean (true when timezone is specified in the original lexical value)
     */
    public boolean hasTimeZone();

    /**
     * Gets the timezone hours.
     * @return timezone hours (for GMT-xx:xx this will be negative)
     */
    public int getTimeZoneHours();

    /**
     * Gets the timezone minutes.
     * @return timezone minutes (for GMT-xx:xx this will be negative)
     */
    public int getTimeZoneMinutes();

    /**
     * Gets the original lexical value.
     * @return the original lexical value
     */
    public String getLexicalValue();

    /**
     * Normalizes the date-time value.
     * @return a new date-time related object with normalized values
     *         (has no effect on objects already normalized)
     */
    public XSDateTime normalize();

    /**
     * Checks if the date-time value is normalized.
     * @return whether a date-time related object is normalized or not
     *         (value is not useful for types where timezone is not specified)
     */
    public boolean isNormalized();

    /**
     * Gets the XMLGregorianCalendar representation.
     * @return an un-normalized XMLGregorianCalendar (if applicable otherwise null)
     */
    public XMLGregorianCalendar getXMLGregorianCalendar();

    /**
     * Gets the Duration representation.
     * @return a Duration (if applicable otherwise null)
     */
    public Duration getDuration();
}
