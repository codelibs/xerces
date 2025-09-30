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

package org.codelibs.xerces.impl.xs;

import org.codelibs.xerces.xni.QName;

/**
 * Helper class for XML Schema element declarations.
 *
 * @version $Id: XSElementDeclHelper.java 982466 2010-08-05 04:41:01Z mrglavas $
 */
public interface XSElementDeclHelper {

    /**
     * Retrieves a global element declaration by its qualified name.
     *
     * @param element the qualified name of the element to retrieve
     * @return the XSElementDecl for the specified element, or null if not found
     */
    public XSElementDecl getGlobalElementDecl(QName element);
}
