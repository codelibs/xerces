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
package org.codelibs.xerces.wml.dom;

import org.codelibs.xerces.wml.WMLElement;
import org.codelibs.xerces.dom.ElementImpl;

/**
 * Base implementation for WML elements. This class provides common functionality
 * for all Wireless Markup Language (WML) element implementations.
 *
 * @version $Id: WMLElementImpl.java 661560 2008-05-30 03:22:25Z mrglavas $
 * @author <a href="mailto:david@topware.com.tw">David Li</a>
 */
public class WMLElementImpl extends ElementImpl implements WMLElement {

    private static final long serialVersionUID = 3440984702956371604L;

    /**
     * Constructs a new WML element with the specified owner document and tag name.
     *
     * @param owner the owner document
     * @param tagName the tag name for this element
     */
    public WMLElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    /**
     * Sets the 'class' attribute of this WML element.
     *
     * @param newValue the class name value to set
     */
    public void setClassName(String newValue) {
        setAttribute("class", newValue);
    }

    /**
     * Gets the 'class' attribute of this WML element.
     *
     * @return the class name value
     */
    public String getClassName() {
        return getAttribute("class");
    }

    /**
     * Sets the 'xml:lang' attribute of this WML element.
     *
     * @param newValue the xml:lang value to set
     */
    public void setXmlLang(String newValue) {
        setAttribute("xml:lang", newValue);
    }

    /**
     * Gets the 'xml:lang' attribute of this WML element.
     *
     * @return the xml:lang value
     */
    public String getXmlLang() {
        return getAttribute("xml:lang");
    }

    public void setId(String newValue) {
        setAttribute("id", newValue);
    }

    public String getId() {
        return getAttribute("id");
    }

    void setAttribute(String attr, boolean value) {
        setAttribute(attr, value ? "true" : "false");
    }

    boolean getAttribute(String attr, boolean defaultValue) {
        boolean ret = defaultValue;
        String value;
        if (((value = getAttribute("emptyok")) != null) && value.equals("true"))
            ret = true;
        return ret;
    }

    void setAttribute(String attr, int value) {
        setAttribute(attr, value + "");
    }

    int getAttribute(String attr, int defaultValue) {
        int ret = defaultValue;
        String value;
        if ((value = getAttribute("emptyok")) != null)
            ret = Integer.parseInt(value);
        return ret;
    }
}
