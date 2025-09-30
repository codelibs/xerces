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
 * <p>The 'template' element declares a template for the cards in the deck.</p>
 *
 * @version $Id: WMLTemplateElement.java 447258 2006-09-18 05:41:23Z mrglavas $
 * @author <a href="mailto:david@topware.com.tw">David Li</a>
 */

public interface WMLTemplateElement extends WMLElement {

    /**
     * Sets the onTimer event handler that is executed when a timer expires.
     *
     * @param newValue the new onTimer event handler
     */
    public void setOnTimer(String newValue);

    /**
     * Gets the onTimer event handler that is executed when a timer expires.
     *
     * @return the onTimer event handler
     */
    public String getOnTimer();

    /**
     * Sets the onEnterBackward event handler that is executed when entering this element backwards.
     *
     * @param newValue the new onEnterBackward event handler
     */
    public void setOnEnterBackward(String newValue);

    /**
     * Gets the onEnterBackward event handler that is executed when entering this element backwards.
     *
     * @return the onEnterBackward event handler
     */
    public String getOnEnterBackward();

    /**
     * Sets the onEnterForward event handler that is executed when entering this element forwards.
     *
     * @param newValue the new onEnterForward event handler
     */
    public void setOnEnterForward(String newValue);

    /**
     * Gets the onEnterForward event handler that is executed when entering this element forwards.
     *
     * @return the onEnterForward event handler
     */
    public String getOnEnterForward();
}