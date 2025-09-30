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
 * <p>'card' element is the basic display unit of WML. A WML decks
 * contains a collection of cards.
 * (Section 11.5, WAP WML Version 16-Jun-1999)</p>
 *
 * @version $Id: WMLCardElement.java 447258 2006-09-18 05:41:23Z mrglavas $
 * @author <a href="mailto:david@topware.com.tw">David Li</a>
 */

public interface WMLCardElement extends WMLElement {

    /**
     * Sets the 'onenterbackward' attribute which specifies the event to occur when a user
     * agent enters a card using a 'go' task.
     * (Section 11.5.1, WAP WML Version 16-Jun-1999)
     *
     * @param href the onenterbackward event value to set
     */
    public void setOnEnterBackward(String href);

    /**
     * Gets the 'onenterbackward' attribute which specifies the event to occur when a user
     * agent enters a card using a 'go' task.
     * (Section 11.5.1, WAP WML Version 16-Jun-1999)
     *
     * @return the onenterbackward event value
     */
    public String getOnEnterBackward();

    /**
     * Sets the 'onenterforward' attribute which specifies the event to occur when a user
     * agent enters a card using a 'prev' task.
     * (Section 11.5.1, WAP WML Version 16-Jun-1999)
     *
     * @param href the onenterforward event value to set
     */
    public void setOnEnterForward(String href);

    /**
     * Gets the 'onenterforward' attribute which specifies the event to occur when a user
     * agent enters a card using a 'prev' task.
     * (Section 11.5.1, WAP WML Version 16-Jun-1999)
     *
     * @return the onenterforward event value
     */
    public String getOnEnterForward();

    /**
     * Sets the 'ontimer' attribute which specifies the event to occur when a timer expires.
     * (Section 11.5.1, WAP WML Version 16-Jun-1999)
     *
     * @param href the ontimer event value to set
     */
    public void setOnTimer(String href);

    /**
     * Gets the 'ontimer' attribute which specifies the event to occur when a timer expires.
     * (Section 11.5.1, WAP WML Version 16-Jun-1999)
     *
     * @return the ontimer event value
     */
    public String getOnTimer();

    /**
     * Sets the 'title' attribute which specifies advisory info about the card.
     * (Section 11.5.2, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the title value to set
     */
    public void setTitle(String newValue);

    /**
     * Gets the 'title' attribute which specifies advisory info about the card.
     * (Section 11.5.2, WAP WML Version 16-Jun-1999)
     *
     * @return the title value
     */
    public String getTitle();

    /**
     * Sets the 'newcontext' attribute which specifies whether a browser context should be
     * re-initialized upon entering the card. Default to be false.
     * (Section 11.5.2, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the newcontext value to set
     */
    public void setNewContext(boolean newValue);

    /**
     * Gets the 'newcontext' attribute which specifies whether a browser context should be
     * re-initialized upon entering the card. Default to be false.
     * (Section 11.5.2, WAP WML Version 16-Jun-1999)
     *
     * @return the newcontext value
     */
    public boolean getNewContext();

    /**
     * Sets the 'ordered' attribute which specifies a hint to user agent about the
     * organization of the card's content.
     * (Section 11.5.2, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the ordered value to set
     */
    public void setOrdered(boolean newValue);

    /**
     * Gets the 'ordered' attribute which specifies a hint to user agent about the
     * organization of the card's content.
     * (Section 11.5.2, WAP WML Version 16-Jun-1999)
     *
     * @return the ordered value
     */
    public boolean getOrdered();

    /**
     * Sets the 'xml:lang' attribute which specifies the natural or formal language in which
     * the document is written.
     * (Section 8.8, WAP WML Version 16-Jun-1999)
     *
     * @param newValue the xml:lang value to set
     */
    public void setXmlLang(String newValue);

    /**
     * Gets the 'xml:lang' attribute which specifies the natural or formal language in which
     * the document is written.
     * (Section 8.8, WAP WML Version 16-Jun-1999)
     *
     * @return the xml:lang value
     */
    public String getXmlLang();
}
