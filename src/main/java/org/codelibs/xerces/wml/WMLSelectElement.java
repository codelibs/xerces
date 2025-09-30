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
 * <p>'select' element lets user pick from a list of options.
 * (Section 11.6.2.1, WAP WML Version 16-Jun-1999)</p>
 *
 * @version $Id: WMLSelectElement.java 447258 2006-09-18 05:41:23Z mrglavas $
 * @author <a href="mailto:david@topware.com.tw">David Li</a>
 */
public interface WMLSelectElement extends WMLElement {

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
     * 'multiple' indicates whether a list accept multiple selection
     * (Section 11.6.2.1, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the multiple attribute
     */
    public void setMultiple(boolean newValue);

    /**
     * Gets the 'multiple' attribute which indicates multiple selection support.
     *
     * @return the value of the multiple attribute
     */
    public boolean getMultiple();

    /**
     * 'name' specifies the name of variable to be set.
     * (Section 11.6.2.1, WAP WML Version 16-Jun-1999)
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
     * 'value' specifics the default value of the variable of 'name'
     * (Section 11.6.2.1, WAP WML Version 16-Jun-1999)
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
     * 'title' specifies a title for this element
     * (Section 11.6.2.1, WAP WML Version 16-Jun-1999)
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
     * 'iname' specifies name of variable to be set with the index
     * result of selection.
     * (Section 11.6.2.1, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the iname attribute
     */
    public void setIName(String newValue);

    /**
     * Gets the 'iname' attribute which specifies the index variable name.
     *
     * @return the value of the iname attribute
     */
    public String getIName();

    /**
     * 'ivalue' specifies the default of the variable 'iname'
     *
     * @param newValue the new value for the ivalue attribute
     */
    public void setIValue(String newValue);

    /**
     * Gets the 'ivalue' attribute which specifies the default index value.
     *
     * @return the value of the ivalue attribute
     */
    public String getIValue();

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
