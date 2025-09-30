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

package org.codelibs.xerces.impl.dtd.models;

/**
 * Content model any node.
 *

 *
 * @version $Id: CMAny.java 572057 2007-09-02 18:03:20Z mrglavas $
 */
public class CMAny extends CMNode {

    //
    // Data
    //

    /**
     * The any content model type. This value is one of the following:
     * XMLContentSpec.CONTENTSPECNODE_ANY,
     * XMLContentSpec.CONTENTSPECNODE_ANY_OTHER,
     * XMLContentSpec.CONTENTSPECNODE_ANY_LOCAL.
     */
    private final int fType;

    /**
     * URI of the any content model. This value is set if the type is
     * of the following:
     * XMLContentSpec.CONTENTSPECNODE_ANY,
     * XMLContentSpec.CONTENTSPECNODE_ANY_OTHER.
     */
    private final String fURI;

    /**
     * Part of the algorithm to convert a regex directly to a DFA
     * numbers each leaf sequentially. If its -1, that means its an
     * epsilon node. Zero and greater are non-epsilon positions.
     */
    private int fPosition = -1;

    //
    // Constructors
    //

    /**
     * Constructs a content model any.
     *
     * @param type The any content model type
     * @param uri The URI of the any content model
     * @param position The position of this node in the content model
     */
    public CMAny(int type, String uri, int position) {
        super(type);

        // Store the information
        fType = type;
        fURI = uri;
        fPosition = position;
    }

    //
    // Package methods
    //

    /**
     * Returns the type of this any content model.
     *
     * @return The any content model type
     */
    final int getType() {
        return fType;
    }

    /**
     * Returns the URI of this any content model.
     *
     * @return The URI of the any content model
     */
    final String getURI() {
        return fURI;
    }

    /**
     * Returns the position of this node in the content model.
     *
     * @return The position of this node
     */
    final int getPosition() {
        return fPosition;
    }

    /**
     * Sets the position of this node in the content model.
     *
     * @param newPosition The new position for this node
     */
    final void setPosition(int newPosition) {
        fPosition = newPosition;
    }

    //
    // CMNode methods
    //

    // package

    /**
     * Returns true if this node is nullable (can match zero occurrences).
     *
     * @return true if this node is nullable, false otherwise
     */
    public boolean isNullable() {
        // Leaf nodes are never nullable unless its an epsilon node
        return (fPosition == -1);
    }

    /**
     * Returns a string representation of this content model node.
     *
     * @return A string representation of this node
     */
    public String toString() {
        StringBuffer strRet = new StringBuffer();
        strRet.append('(');
        strRet.append("##any:uri=");
        strRet.append(fURI);
        strRet.append(')');
        if (fPosition >= 0) {
            strRet.append(" (Pos:").append(Integer.toString(fPosition)).append(')');
        }
        return strRet.toString();
    }

    // protected

    /**
     * Calculates the first position set for this node.
     *
     * @param toSet The state set to populate with first positions
     */
    protected void calcFirstPos(CMStateSet toSet) {
        // If we are an epsilon node, then the first pos is an empty set
        if (fPosition == -1)
            toSet.zeroBits();

        // Otherwise, its just the one bit of our position
        else
            toSet.setBit(fPosition);
    }

    /**
     * Calculates the last position set for this node.
     *
     * @param toSet The state set to populate with last positions
     */
    protected void calcLastPos(CMStateSet toSet) {
        // If we are an epsilon node, then the last pos is an empty set
        if (fPosition == -1)
            toSet.zeroBits();

        // Otherwise, its just the one bit of our position
        else
            toSet.setBit(fPosition);
    }

} // class CMAny
