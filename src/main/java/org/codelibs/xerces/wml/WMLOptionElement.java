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
 * <p>'option' element specifies a choice in a 'select' element</p>
 *
 * @version $Id: WMLOptionElement.java 447258 2006-09-18 05:41:23Z mrglavas $
 * @author <a href="mailto:david@topware.com.tw">David Li</a>
 */

public interface WMLOptionElement extends WMLElement {

    /**
     * 'value' specifies the value to used to set the 'name' variable
     * (Section 11.6.2.2, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the value attribute
     */
    public void setValue(String newValue);

    /**
     * Gets the 'value' attribute which specifies the option value.
     *
     * @return the value of the value attribute
     */
    public String getValue();

    /**
     * 'title' specifies a title for this element.
     * (Section 11.6.2.2, WAP WML Version 16-Jun-1999)
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
     * 'onpick' specifies a event to occur when a user select and
     * disselect this choice.
     * (Section 11.6.2.2, WAP WML Version 16-Jun-1999)
     *
     * @param href the new value for the onpick attribute
     */
    public void setOnPick(String href);

    /**
     * Gets the 'onpick' attribute which specifies the event to occur.
     *
     * @return the value of the onpick attribute
     */
    public String getOnPick();

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
