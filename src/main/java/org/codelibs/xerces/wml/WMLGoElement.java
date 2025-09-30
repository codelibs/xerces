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
 * @version $Id: WMLGoElement.java 447258 2006-09-18 05:41:23Z mrglavas $
 * @author <a href="mailto:david@topware.com.tw">David Li</a>
 */

public interface WMLGoElement extends WMLElement {

    /**
     * Sets the 'sendreferer' attribute which indicates whether to send the referrer information.
     *
     * @param newValue the new value for the sendreferer attribute
     */
    public void setSendreferer(String newValue);

    /**
     * Gets the 'sendreferer' attribute which indicates whether to send the referrer information.
     *
     * @return the value of the sendreferer attribute
     */
    public String getSendreferer();

    /**
     * Sets the 'accept-charset' attribute which specifies accepted character encodings.
     *
     * @param newValue the new value for the accept-charset attribute
     */
    public void setAcceptCharset(String newValue);

    /**
     * Gets the 'accept-charset' attribute which specifies accepted character encodings.
     *
     * @return the value of the accept-charset attribute
     */
    public String getAcceptCharset();

    /**
     * Sets the 'href' attribute which specifies the destination URL.
     *
     * @param newValue the new value for the href attribute
     */
    public void setHref(String newValue);

    /**
     * Gets the 'href' attribute which specifies the destination URL.
     *
     * @return the value of the href attribute
     */
    public String getHref();

    /**
     * Sets the 'method' attribute which specifies the HTTP method (GET or POST).
     *
     * @param newValue the new value for the method attribute
     */
    public void setMethod(String newValue);

    /**
     * Gets the 'method' attribute which specifies the HTTP method (GET or POST).
     *
     * @return the value of the method attribute
     */
    public String getMethod();

}
