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

package org.codelibs.xerces.impl.xs.models;

import org.codelibs.xerces.impl.Constants;
import org.codelibs.xerces.impl.XMLErrorReporter;
import org.codelibs.xerces.impl.dtd.models.CMNode;
import org.codelibs.xerces.impl.xs.XSMessageFormatter;
import org.codelibs.xerces.util.SecurityManager;
import org.codelibs.xerces.xni.parser.XMLComponentManager;
import org.codelibs.xerces.xni.parser.XMLConfigurationException;

/**
 * Factory for creating content model nodes.
 *
 * @author  Neeraj Bajaj
 *
 * @version $Id: CMNodeFactory.java 911823 2010-02-19 14:38:42Z mrglavas $
 */
public class CMNodeFactory {

    /** Property identifier: error reporter. */
    private static final String ERROR_REPORTER = Constants.XERCES_PROPERTY_PREFIX + Constants.ERROR_REPORTER_PROPERTY;

    /** property identifier: security manager. */
    private static final String SECURITY_MANAGER = Constants.XERCES_PROPERTY_PREFIX + Constants.SECURITY_MANAGER_PROPERTY;

    private static final boolean DEBUG = false;

    //
    private static final int MULTIPLICITY = 1;

    //count of number of nodes created
    private int nodeCount = 0;

    //No. of nodes allowed.
    private int maxNodeLimit;

    /**
     * Error reporter. This property identifier is:
     * http://apache.org/xml/properties/internal/error-reporter
     */
    private XMLErrorReporter fErrorReporter;

    // stores defaults for different security holes (maxOccurLimit in current context) if it has
    // been set on the configuration.
    private SecurityManager fSecurityManager = null;

    /** default constructor */
    public CMNodeFactory() {
    }

    /**
     * Resets the node factory with the specified component manager.
     *
     * @param componentManager The component manager to retrieve properties from
     */
    public void reset(XMLComponentManager componentManager) {
        fErrorReporter = (XMLErrorReporter) componentManager.getProperty(ERROR_REPORTER);
        try {
            fSecurityManager = (SecurityManager) componentManager.getProperty(SECURITY_MANAGER);
            reset();
        } catch (XMLConfigurationException e) {
            fSecurityManager = null;
        }

    }//reset()

    /**
     * Resets the node factory, recalculating node limits from the security manager.
     */
    public void reset() {
        // we are setting the limit of number of nodes to 3 times the maxOccurs value.
        if (fSecurityManager != null) {
            maxNodeLimit = fSecurityManager.getMaxOccurNodeLimit() * MULTIPLICITY;
        }
    }

    /**
     * Creates a content model leaf node.
     *
     * @param type The type of the leaf node
     * @param leaf The leaf value (element or wildcard)
     * @param id The particle identifier
     * @param position The position in the content model
     * @return The created leaf node
     */
    public CMNode getCMLeafNode(int type, Object leaf, int id, int position) {
        nodeCountCheck();
        return new XSCMLeaf(type, leaf, id, position);
    }

    /**
     * Creates a repeating content model leaf node.
     *
     * @param type The type of the leaf node
     * @param leaf The leaf value (element or wildcard)
     * @param minOccurs The minimum occurrences
     * @param maxOccurs The maximum occurrences
     * @param id The particle identifier
     * @param position The position in the content model
     * @return The created repeating leaf node
     */
    public CMNode getCMRepeatingLeafNode(int type, Object leaf, int minOccurs, int maxOccurs, int id, int position) {
        nodeCountCheck();
        return new XSCMRepeatingLeaf(type, leaf, minOccurs, maxOccurs, id, position);
    }

    /**
     * Creates a unary operation content model node.
     *
     * @param type The type of unary operation (?, *, +)
     * @param childNode The child node
     * @return The created unary operation node
     */
    public CMNode getCMUniOpNode(int type, CMNode childNode) {
        nodeCountCheck();
        return new XSCMUniOp(type, childNode);
    }

    /**
     * Creates a binary operation content model node.
     *
     * @param type The type of binary operation (choice or sequence)
     * @param leftNode The left child node
     * @param rightNode The right child node
     * @return The created binary operation node
     */
    public CMNode getCMBinOpNode(int type, CMNode leftNode, CMNode rightNode) {
        nodeCountCheck();
        return new XSCMBinOp(type, leftNode, rightNode);
    }

    /**
     * Checks if the node count exceeds the maximum limit and reports an error if so.
     */
    public void nodeCountCheck() {
        if (fSecurityManager != null && nodeCount++ > maxNodeLimit) {
            if (DEBUG) {
                System.out.println("nodeCount = " + nodeCount);
                System.out.println("nodeLimit = " + maxNodeLimit);
            }
            fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "maxOccurLimit", new Object[] { new Integer(maxNodeLimit) },
                    XMLErrorReporter.SEVERITY_FATAL_ERROR);
            // similarly to entity manager behaviour, take into accont
            // behaviour if continue-after-fatal-error is set.
            nodeCount = 0;
        }

    }//nodeCountCheck()

    /**
     * Resets the node count to zero.
     */
    public void resetNodeCount() {
        nodeCount = 0;
    }

    /**
    * Sets the value of a property. This method is called by the component
    * manager any time after reset when a property changes value.
    * <p>
    * <strong>Note:</strong> Components should silently ignore properties
    * that do not affect the operation of the component.
    *
    * @param propertyId The property identifier.
    * @param value      The value of the property.
    *
    * @throws XMLConfigurationException if the property identifier is not
    *                                   recognized or the value is not supported.
    */
    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {

        // Xerces properties
        if (propertyId.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            final int suffixLength = propertyId.length() - Constants.XERCES_PROPERTY_PREFIX.length();

            if (suffixLength == Constants.SECURITY_MANAGER_PROPERTY.length() && propertyId.endsWith(Constants.SECURITY_MANAGER_PROPERTY)) {
                fSecurityManager = (SecurityManager) value;
                maxNodeLimit = (fSecurityManager != null) ? fSecurityManager.getMaxOccurNodeLimit() * MULTIPLICITY : 0;
                return;
            }
            if (suffixLength == Constants.ERROR_REPORTER_PROPERTY.length() && propertyId.endsWith(Constants.ERROR_REPORTER_PROPERTY)) {
                fErrorReporter = (XMLErrorReporter) value;
                return;
            }
        }

    } // setProperty(String,Object)

}//CMNodeFactory()
