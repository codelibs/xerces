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

package org.codelibs.xerces.impl.xs.opti;

/**
 * Node implementation for XML Schema processing with namespace support.
 * This class extends DefaultNode and adds namespace-aware functionality.
 *
 * @author Rahul Srivastava, Sun Microsystems Inc.
 *
 * @version $Id: NodeImpl.java 705596 2008-10-17 13:05:10Z mrglavas $
 */
public class NodeImpl extends DefaultNode {

    String prefix;
    String localpart;
    String rawname;
    String uri;
    short nodeType;
    boolean hidden;

    /**
     * Default constructor for NodeImpl.
     */
    public NodeImpl() {
    }

    /**
     * Constructs a NodeImpl with the specified properties.
     * @param prefix the namespace prefix
     * @param localpart the local name
     * @param rawname the raw qualified name
     * @param uri the namespace URI
     * @param nodeType the node type
     */
    public NodeImpl(String prefix, String localpart, String rawname, String uri, short nodeType) {
        this.prefix = prefix;
        this.localpart = localpart;
        this.rawname = rawname;
        this.uri = uri;
        this.nodeType = nodeType;
    }

    public String getNodeName() {
        return rawname;
    }

    public String getNamespaceURI() {
        return uri;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getLocalName() {
        return localpart;
    }

    public short getNodeType() {
        return nodeType;
    }

    // other methods

    /**
     * Sets the read-only state of this node.
     * @param hide true to make this node read-only, false otherwise
     * @param deep if true, applies recursively to descendants
     */
    public void setReadOnly(boolean hide, boolean deep) {
        hidden = hide;
    }

    /**
     * Gets the read-only state of this node.
     * @return true if this node is read-only, false otherwise
     */
    public boolean getReadOnly() {
        return hidden;
    }

    /** NON-DOM method for debugging convenience. */
    public String toString() {
        return "[" + getNodeName() + ": " + getNodeValue() + "]";
    }
}