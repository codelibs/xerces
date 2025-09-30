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
 * <p>'img' specifies an image in a text flow
 * (Section 11.9, WAP WML Version 16-Jun-1999)</p>
 *
 * @version $Id: WMLImgElement.java 447258 2006-09-18 05:41:23Z mrglavas $
 * @author <a href="mailto:david@topware.com.tw">David Li</a>
 */
public interface WMLImgElement extends WMLElement {

    /**
     * 'alt' specifies an alternative text for the image
     * (Section 11.9, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the alt attribute
     */
    public void setAlt(String newValue);

    /**
     * Gets the 'alt' attribute which specifies an alternative text for the image.
     *
     * @return the value of the alt attribute
     */
    public String getAlt();

    /**
     * 'src' specifies URI for the source images
     * (Section 11.9, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the src attribute
     */
    public void setSrc(String newValue);

    /**
     * Gets the 'src' attribute which specifies the URI for the source images.
     *
     * @return the value of the src attribute
     */
    public String getSrc();

    /**
     * 'localsrc' specifies an alternative internal representation of
     * the image.
     * (Section 11.9, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the localsrc attribute
     */
    public void setLocalSrc(String newValue);

    /**
     * Gets the 'localsrc' attribute which specifies an alternative internal representation.
     *
     * @return the value of the localsrc attribute
     */
    public String getLocalSrc();

    /**
     * 'vspace' specifies the abount of white space to be inserted
     * above and below
     * (Section 11.9, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the vspace attribute
     */
    public void setVspace(String newValue);

    /**
     * Gets the 'vspace' attribute which specifies the vertical white space.
     *
     * @return the value of the vspace attribute
     */
    public String getVspace();

    /**
     * 'hspace' specifies the abount of white space to be inserted
     * left and right
     * (Section 11.9, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the hspace attribute
     */
    public void setHspace(String newValue);

    /**
     * Gets the 'hspace' attribute which specifies the horizontal white space.
     *
     * @return the value of the hspace attribute
     */
    public String getHspace();

    /**
     * 'align' specifies the alignment of the image within the text
     * flow.
     * (Section 11.8, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the align attribute
     */
    public void setAlign(String newValue);

    /**
     * Gets the 'align' attribute which specifies the image alignment.
     *
     * @return the value of the align attribute
     */
    public String getAlign();

    /**
     * 'width' specifies the width of an image.
     * (Section 11.9, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the width attribute
     */
    public void setWidth(String newValue);

    /**
     * Gets the 'width' attribute which specifies the image width.
     *
     * @return the value of the width attribute
     */
    public String getWidth();

    /**
     * 'height' specifies the height of an image.
     * (Section 11.9, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the new value for the height attribute
     */
    public void setHeight(String newValue);

    /**
     * Gets the 'height' attribute which specifies the image height.
     *
     * @return the value of the height attribute
     */
    public String getHeight();

    /**
     * The xml:lang that specifics the natural or formal language in
     * which the document is written.
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
