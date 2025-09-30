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

package org.codelibs.xerces.xs;

/**
 *  This interface represents the Notation Declaration schema component.
 */
public interface XSNotationDeclaration extends XSObject {
    /**
     *  The URI reference representing the system identifier for the notation
     * declaration, if present, <code>null</code> otherwise.
     *
     * @return the system identifier URI, or null if not present
     */
    public String getSystemId();

    /**
     *  The string representing the public identifier for this notation
     * declaration, if present; <code>null</code> otherwise.
     *
     * @return the public identifier string, or null if not present
     */
    public String getPublicId();

    /**
     * An annotation if it exists, otherwise <code>null</code>. If not null
     * then the first [annotation] from the sequence of annotations.
     *
     * @return the first annotation, or null if no annotations exist
     */
    public XSAnnotation getAnnotation();

    /**
     * A sequence of [annotations] or an empty <code>XSObjectList</code>.
     *
     * @return a list of annotations, or an empty list if no annotations exist
     */
    public XSObjectList getAnnotations();
}
