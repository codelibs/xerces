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
package org.codelibs.xerces.wml;

/**
 * <p>The interface is modeled after DOM1 Spec for HTML from W3C.
 * The DTD used in this DOM model is from
 * <a href="http://www.wapforum.org/DTD/wml_1.1.xml">
 * http://www.wapforum.org/DTD/wml_1.1.xml</a></p>
 *
 * <p>'input' element specifies a text entry object.
 * (Section 11.6.3, WAP WML Version 16-Jun-1999)</p>
 *
 * @version $Id: WMLInputElement.java 447258 2006-09-18 05:41:23Z mrglavas $
 * @author <a href="mailto:david@topware.com.tw">David Li</a>
 */

public interface WMLInputElement extends WMLElement {

    /**
     * 'name' specifies the name of a variable after the user enters the text.
     * (Section 11.6.3, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the name attribute
     */
    public void setName(String newValue);

    /**
     * Gets the 'name' attribute which specifies the variable name.
     *
     * @return the value of the name attribute
     */
    public String getName();

    /**
     * 'value' specifies the default value of the variable in 'name' attribute
     * (Section 11.6.3, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the value attribute
     */
    public void setValue(String newValue);

    /**
     * Gets the 'value' attribute which specifies the default value.
     *
     * @return the value of the value attribute
     */
    public String getValue();

    /**
     * 'type' specifies the type of text input area.
     * Two values are allowed: 'text' and 'password' and default is 'text'
     * (Section 11.6.3, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the type attribute
     */
    public void setType(String newValue);

    /**
     * Gets the 'type' attribute which specifies the input type.
     *
     * @return the value of the type attribute
     */
    public String getType();

    /**
     * 'format' specifies the input mask for user input.
     * (Section 11.6.3, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the format attribute
     */
    public void setFormat(String newValue);

    /**
     * Gets the 'format' attribute which specifies the input mask.
     *
     * @return the value of the format attribute
     */
    public String getFormat();

    /**
     * 'emptyok' specifies whether a empty input is allowed when a
     * non-empty 'format' is specified. Default to be 'false'
     * (Section 11.6.3, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the emptyok attribute
     */
    public void setEmptyOk(boolean newValue);

    /**
     * Gets the 'emptyok' attribute which indicates if empty input is allowed.
     *
     * @return the value of the emptyok attribute
     */
    public boolean getEmptyOk();

    /**
     * 'size' specifies the width of the input in characters
     * (Section 11.6.3, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the size attribute
     */
    public void setSize(int newValue);

    /**
     * Gets the 'size' attribute which specifies the input width.
     *
     * @return the value of the size attribute
     */
    public int getSize();

    /**
     * 'maxlength' specifies the maximum number of characters to be
     * enter.
     * (Section 11.6.3, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the maxlength attribute
     */
    public void setMaxLength(int newValue);

    /**
     * Gets the 'maxlength' attribute which specifies the maximum input length.
     *
     * @return the value of the maxlength attribute
     */
    public int getMaxLength();

    /**
     * 'title' specifies a title for this element
     * (Section 11.6.3, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the title attribute
     */
    public void setTitle(String newValue);

    /**
     * Gets the 'title' attribute which specifies the element title.
     *
     * @return the value of the title attribute
     */
    public String getTitle();

    /**
     * 'tabindex' specifies the tabbing position of the element
     * (Section 11.6.1, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the tabindex attribute
     */
    public void setTabIndex(int newValue);

    /**
     * Gets the 'tabindex' attribute which specifies the tab position.
     *
     * @return the value of the tabindex attribute
     */
    public int getTabIndex();

    /**
     * 'xml:lang' specifics the natural or formal language in which
     * the document is written.
     * (Section 8.8, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the xml:lang attribute
     */
    public void setXmlLang(String newValue);

    /**
     * Gets the xml:lang attribute which specifies the document language.
     *
     * @return the value of the xml:lang attribute
     */
    public String getXmlLang();
}
