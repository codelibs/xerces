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

package org.codelibs.xerces.util;

import java.util.Hashtable;

import org.codelibs.xerces.dom.AttrImpl;
import org.codelibs.xerces.dom.DocumentImpl;
import org.codelibs.xerces.impl.xs.opti.ElementImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.ls.LSException;

/**
 * Some useful utility methods.
 * This class was modified in Xerces2 with a view to abstracting as
 * much as possible away from the representation of the underlying
 * parsed structure (i.e., the DOM).  This was done so that, if Xerces
 * ever adopts an in-memory representation more efficient than the DOM
 * (such as a DTM), we should easily be able to convert our schema
 * parsing to utilize it.
 *
 * @version $Id: DOMUtil.java 929493 2010-03-31 12:15:17Z mrglavas $
 */
public class DOMUtil {

    //
    // Constructors
    //

    /** This class cannot be instantiated. */
    protected DOMUtil() {
    }

    //
    // Public static methods
    //

    /**
     * Copies the source tree into the specified place in a destination
     * tree. The source node and its children are appended as children
     * of the destination node.
     * <p>
     * <em>Note:</em> This is an iterative implementation.
     *
     * @param src The source node to copy from.
     * @param dest The destination node to copy into.
     */
    public static void copyInto(Node src, Node dest) throws DOMException {

        // get node factory
        Document factory = dest.getOwnerDocument();
        boolean domimpl = factory instanceof DocumentImpl;

        // placement variables
        Node start = src;
        Node parent = src;
        Node place = src;

        // traverse source tree
        while (place != null) {

            // copy this node
            Node node = null;
            int type = place.getNodeType();
            switch (type) {
            case Node.CDATA_SECTION_NODE: {
                node = factory.createCDATASection(place.getNodeValue());
                break;
            }
            case Node.COMMENT_NODE: {
                node = factory.createComment(place.getNodeValue());
                break;
            }
            case Node.ELEMENT_NODE: {
                Element element = factory.createElement(place.getNodeName());
                node = element;
                NamedNodeMap attrs = place.getAttributes();
                int attrCount = attrs.getLength();
                for (int i = 0; i < attrCount; i++) {
                    Attr attr = (Attr) attrs.item(i);
                    String attrName = attr.getNodeName();
                    String attrValue = attr.getNodeValue();
                    element.setAttribute(attrName, attrValue);
                    if (domimpl && !attr.getSpecified()) {
                        ((AttrImpl) element.getAttributeNode(attrName)).setSpecified(false);
                    }
                }
                break;
            }
            case Node.ENTITY_REFERENCE_NODE: {
                node = factory.createEntityReference(place.getNodeName());
                break;
            }
            case Node.PROCESSING_INSTRUCTION_NODE: {
                node = factory.createProcessingInstruction(place.getNodeName(), place.getNodeValue());
                break;
            }
            case Node.TEXT_NODE: {
                node = factory.createTextNode(place.getNodeValue());
                break;
            }
            default: {
                throw new IllegalArgumentException("can't copy node type, " + type + " (" + place.getNodeName() + ')');
            }
            }
            dest.appendChild(node);

            // iterate over children
            if (place.hasChildNodes()) {
                parent = place;
                place = place.getFirstChild();
                dest = node;
            }

            // advance
            else {
                place = place.getNextSibling();
                while (place == null && parent != start) {
                    place = parent.getNextSibling();
                    parent = parent.getParentNode();
                    dest = dest.getParentNode();
                }
            }

        }

    } // copyInto(Node,Node)

    /**
     * Finds and returns the first child element node.
     *
     * @param parent The parent node.
     * @return The first child element node, or null if not found.
     */
    public static Element getFirstChildElement(Node parent) {

        // search for node
        Node child = parent.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                return (Element) child;
            }
            child = child.getNextSibling();
        }

        // not found
        return null;

    } // getFirstChildElement(Node):Element

    /**
     * Finds and returns the first visible child element node.
     *
     * @param parent The parent node.
     * @return The first visible child element node, or null if not found.
     */
    public static Element getFirstVisibleChildElement(Node parent) {

        // search for node
        Node child = parent.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE && !isHidden(child)) {
                return (Element) child;
            }
            child = child.getNextSibling();
        }

        // not found
        return null;

    } // getFirstChildElement(Node):Element

    /**
     * Finds and returns the first visible child element node.
     *
     * @param parent The parent node.
     * @param hiddenNodes A hashtable of hidden nodes.
     * @return The first visible child element node, or null if not found.
     */
    public static Element getFirstVisibleChildElement(Node parent, Hashtable hiddenNodes) {

        // search for node
        Node child = parent.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE && !isHidden(child, hiddenNodes)) {
                return (Element) child;
            }
            child = child.getNextSibling();
        }

        // not found
        return null;

    } // getFirstChildElement(Node):Element

    /**
     * Finds and returns the last child element node.
     * Overload previous method for non-Xerces node impl.
     *
     * @param parent The parent node.
     * @return The last child element node, or null if not found.
     */
    public static Element getLastChildElement(Node parent) {

        // search for node
        Node child = parent.getLastChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                return (Element) child;
            }
            child = child.getPreviousSibling();
        }

        // not found
        return null;

    } // getLastChildElement(Node):Element

    /**
     * Finds and returns the last visible child element node.
     *
     * @param parent The parent node.
     * @return The last visible child element node, or null if not found.
     */
    public static Element getLastVisibleChildElement(Node parent) {

        // search for node
        Node child = parent.getLastChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE && !isHidden(child)) {
                return (Element) child;
            }
            child = child.getPreviousSibling();
        }

        // not found
        return null;

    } // getLastChildElement(Node):Element

    /**
     * Finds and returns the last visible child element node.
     * Overload previous method for non-Xerces node impl.
     *
     * @param parent The parent node.
     * @param hiddenNodes A hashtable of hidden nodes.
     * @return The last visible child element node, or null if not found.
     */
    public static Element getLastVisibleChildElement(Node parent, Hashtable hiddenNodes) {

        // search for node
        Node child = parent.getLastChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE && !isHidden(child, hiddenNodes)) {
                return (Element) child;
            }
            child = child.getPreviousSibling();
        }

        // not found
        return null;

    } // getLastChildElement(Node):Element

    /**
     * Finds and returns the next sibling element node.
     *
     * @param node The current node.
     * @return The next sibling element node, or null if not found.
     */
    public static Element getNextSiblingElement(Node node) {

        // search for node
        Node sibling = node.getNextSibling();
        while (sibling != null) {
            if (sibling.getNodeType() == Node.ELEMENT_NODE) {
                return (Element) sibling;
            }
            sibling = sibling.getNextSibling();
        }

        // not found
        return null;

    } // getNextSiblingElement(Node):Element

    /**
     * Returns the next visible (un-hidden) sibling element of the given node.
     *
     * @param node The starting node.
     * @return The next visible sibling element, or null if not found.
     */
    public static Element getNextVisibleSiblingElement(Node node) {

        // search for node
        Node sibling = node.getNextSibling();
        while (sibling != null) {
            if (sibling.getNodeType() == Node.ELEMENT_NODE && !isHidden(sibling)) {
                return (Element) sibling;
            }
            sibling = sibling.getNextSibling();
        }

        // not found
        return null;

    } // getNextSiblingdElement(Node):Element

    /**
     * Returns the next visible (un-hidden) sibling element of the given node.
     * This overloaded method works with non-Xerces node implementations using a hidden nodes table.
     *
     * @param node The starting node.
     * @param hiddenNodes The hashtable tracking hidden nodes.
     * @return The next visible sibling element, or null if not found.
     */
    public static Element getNextVisibleSiblingElement(Node node, Hashtable hiddenNodes) {

        // search for node
        Node sibling = node.getNextSibling();
        while (sibling != null) {
            if (sibling.getNodeType() == Node.ELEMENT_NODE && !isHidden(sibling, hiddenNodes)) {
                return (Element) sibling;
            }
            sibling = sibling.getNextSibling();
        }

        // not found
        return null;

    } // getNextSiblingdElement(Node):Element

    /**
     * Marks the given node as hidden by setting it to read-only.
     *
     * @param node The node to hide.
     */
    public static void setHidden(Node node) {
        if (node instanceof org.codelibs.xerces.impl.xs.opti.NodeImpl)
            ((org.codelibs.xerces.impl.xs.opti.NodeImpl) node).setReadOnly(true, false);
        else if (node instanceof org.codelibs.xerces.dom.NodeImpl)
            ((org.codelibs.xerces.dom.NodeImpl) node).setReadOnly(true, false);
    } // setHidden(node):void

    /**
     * Marks the given node as hidden. This overloaded method works with non-Xerces node implementations
     * by adding the node to a hidden nodes table.
     *
     * @param node The node to hide.
     * @param hiddenNodes The hashtable tracking hidden nodes.
     */
    public static void setHidden(Node node, Hashtable hiddenNodes) {
        if (node instanceof org.codelibs.xerces.impl.xs.opti.NodeImpl) {
            ((org.codelibs.xerces.impl.xs.opti.NodeImpl) node).setReadOnly(true, false);
        } else {
            hiddenNodes.put(node, "");
        }
    } // setHidden(node):void

    /**
     * Marks the given node as visible by setting it to non-read-only.
     *
     * @param node The node to make visible.
     */
    public static void setVisible(Node node) {
        if (node instanceof org.codelibs.xerces.impl.xs.opti.NodeImpl)
            ((org.codelibs.xerces.impl.xs.opti.NodeImpl) node).setReadOnly(false, false);
        else if (node instanceof org.codelibs.xerces.dom.NodeImpl)
            ((org.codelibs.xerces.dom.NodeImpl) node).setReadOnly(false, false);
    } // setVisible(node):void

    /**
     * Marks the given node as visible. This overloaded method works with non-Xerces node implementations
     * by removing the node from the hidden nodes table.
     *
     * @param node The node to make visible.
     * @param hiddenNodes The hashtable tracking hidden nodes.
     */
    public static void setVisible(Node node, Hashtable hiddenNodes) {
        if (node instanceof org.codelibs.xerces.impl.xs.opti.NodeImpl) {
            ((org.codelibs.xerces.impl.xs.opti.NodeImpl) node).setReadOnly(false, false);
        } else {
            hiddenNodes.remove(node);
        }
    } // setVisible(node):void

    /**
     * Checks if the given node is hidden by checking its read-only state.
     *
     * @param node The node to check.
     * @return True if the node is hidden, false otherwise.
     */
    public static boolean isHidden(Node node) {
        if (node instanceof org.codelibs.xerces.impl.xs.opti.NodeImpl)
            return ((org.codelibs.xerces.impl.xs.opti.NodeImpl) node).getReadOnly();
        else if (node instanceof org.codelibs.xerces.dom.NodeImpl)
            return ((org.codelibs.xerces.dom.NodeImpl) node).getReadOnly();
        return false;
    } // isHidden(Node):boolean

    /**
     * Checks if the given node is hidden. This overloaded method works with non-Xerces node implementations
     * by checking the hidden nodes table.
     *
     * @param node The node to check.
     * @param hiddenNodes The hashtable tracking hidden nodes.
     * @return True if the node is hidden, false otherwise.
     */
    public static boolean isHidden(Node node, Hashtable hiddenNodes) {
        if (node instanceof org.codelibs.xerces.impl.xs.opti.NodeImpl) {
            return ((org.codelibs.xerces.impl.xs.opti.NodeImpl) node).getReadOnly();
        } else {
            return hiddenNodes.containsKey(node);
        }
    } // isHidden(Node):boolean

    /**
     * Finds and returns the first child node with the given name.
     *
     * @param parent The parent node.
     * @param elemName The element name to search for.
     * @return The first child element with the given name, or null if not found.
     */
    public static Element getFirstChildElement(Node parent, String elemName) {

        // search for node
        Node child = parent.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                if (child.getNodeName().equals(elemName)) {
                    return (Element) child;
                }
            }
            child = child.getNextSibling();
        }

        // not found
        return null;

    } // getFirstChildElement(Node,String):Element

    /**
     * Finds and returns the last child node with the given name.
     *
     * @param parent The parent node.
     * @param elemName The element name to search for.
     * @return The last child element with the given name, or null if not found.
     */
    public static Element getLastChildElement(Node parent, String elemName) {

        // search for node
        Node child = parent.getLastChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                if (child.getNodeName().equals(elemName)) {
                    return (Element) child;
                }
            }
            child = child.getPreviousSibling();
        }

        // not found
        return null;

    } // getLastChildElement(Node,String):Element

    /**
     * Finds and returns the next sibling node with the given name.
     *
     * @param node The current node.
     * @param elemName The element name to search for.
     * @return The next sibling element with the given name, or null if not found.
     */
    public static Element getNextSiblingElement(Node node, String elemName) {

        // search for node
        Node sibling = node.getNextSibling();
        while (sibling != null) {
            if (sibling.getNodeType() == Node.ELEMENT_NODE) {
                if (sibling.getNodeName().equals(elemName)) {
                    return (Element) sibling;
                }
            }
            sibling = sibling.getNextSibling();
        }

        // not found
        return null;

    } // getNextSiblingdElement(Node,String):Element

    /**
     * Finds and returns the first child node with the given qualified name.
     *
     * @param parent The parent node.
     * @param uri The namespace URI.
     * @param localpart The local part of the qualified name.
     * @return The first child element with the given qualified name, or null if not found.
     */
    public static Element getFirstChildElementNS(Node parent, String uri, String localpart) {

        // search for node
        Node child = parent.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                String childURI = child.getNamespaceURI();
                if (childURI != null && childURI.equals(uri) && child.getLocalName().equals(localpart)) {
                    return (Element) child;
                }
            }
            child = child.getNextSibling();
        }

        // not found
        return null;

    } // getFirstChildElementNS(Node,String,String):Element

    /**
     * Finds and returns the last child node with the given qualified name.
     *
     * @param parent The parent node.
     * @param uri The namespace URI.
     * @param localpart The local part of the qualified name.
     * @return The last child element with the given qualified name, or null if not found.
     */
    public static Element getLastChildElementNS(Node parent, String uri, String localpart) {

        // search for node
        Node child = parent.getLastChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                String childURI = child.getNamespaceURI();
                if (childURI != null && childURI.equals(uri) && child.getLocalName().equals(localpart)) {
                    return (Element) child;
                }
            }
            child = child.getPreviousSibling();
        }

        // not found
        return null;

    } // getLastChildElementNS(Node,String,String):Element

    /**
     * Finds and returns the next sibling node with the given qualified name.
     *
     * @param node The current node.
     * @param uri The namespace URI.
     * @param localpart The local part of the qualified name.
     * @return The next sibling element with the given qualified name, or null if not found.
     */
    public static Element getNextSiblingElementNS(Node node, String uri, String localpart) {

        // search for node
        Node sibling = node.getNextSibling();
        while (sibling != null) {
            if (sibling.getNodeType() == Node.ELEMENT_NODE) {
                String siblingURI = sibling.getNamespaceURI();
                if (siblingURI != null && siblingURI.equals(uri) && sibling.getLocalName().equals(localpart)) {
                    return (Element) sibling;
                }
            }
            sibling = sibling.getNextSibling();
        }

        // not found
        return null;

    } // getNextSiblingdElementNS(Node,String,String):Element

    /**
     * Finds and returns the first child node with the given name.
     *
     * @param parent The parent node.
     * @param elemNames Array of element names to search for.
     * @return The first child element matching any of the names, or null if not found.
     */
    public static Element getFirstChildElement(Node parent, String elemNames[]) {

        // search for node
        Node child = parent.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                for (int i = 0; i < elemNames.length; i++) {
                    if (child.getNodeName().equals(elemNames[i])) {
                        return (Element) child;
                    }
                }
            }
            child = child.getNextSibling();
        }

        // not found
        return null;

    } // getFirstChildElement(Node,String[]):Element

    /**
     * Finds and returns the last child node with the given name.
     *
     * @param parent The parent node.
     * @param elemNames Array of element names to search for.
     * @return The last child element matching any of the names, or null if not found.
     */
    public static Element getLastChildElement(Node parent, String elemNames[]) {

        // search for node
        Node child = parent.getLastChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                for (int i = 0; i < elemNames.length; i++) {
                    if (child.getNodeName().equals(elemNames[i])) {
                        return (Element) child;
                    }
                }
            }
            child = child.getPreviousSibling();
        }

        // not found
        return null;

    } // getLastChildElement(Node,String[]):Element

    /**
     * Finds and returns the next sibling node with the given name.
     *
     * @param node The current node.
     * @param elemNames Array of element names to search for.
     * @return The next sibling element matching any of the names, or null if not found.
     */
    public static Element getNextSiblingElement(Node node, String elemNames[]) {

        // search for node
        Node sibling = node.getNextSibling();
        while (sibling != null) {
            if (sibling.getNodeType() == Node.ELEMENT_NODE) {
                for (int i = 0; i < elemNames.length; i++) {
                    if (sibling.getNodeName().equals(elemNames[i])) {
                        return (Element) sibling;
                    }
                }
            }
            sibling = sibling.getNextSibling();
        }

        // not found
        return null;

    } // getNextSiblingdElement(Node,String[]):Element

    /**
     * Finds and returns the first child node with the given qualified name.
     *
     * @param parent The parent node.
     * @param elemNames Array of [uri, localpart] pairs representing qualified names.
     * @return The first child element matching any of the qualified names, or null if not found.
     */
    public static Element getFirstChildElementNS(Node parent, String[][] elemNames) {

        // search for node
        Node child = parent.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                for (int i = 0; i < elemNames.length; i++) {
                    String uri = child.getNamespaceURI();
                    if (uri != null && uri.equals(elemNames[i][0]) && child.getLocalName().equals(elemNames[i][1])) {
                        return (Element) child;
                    }
                }
            }
            child = child.getNextSibling();
        }

        // not found
        return null;

    } // getFirstChildElementNS(Node,String[][]):Element

    /**
     * Finds and returns the last child node with the given qualified name.
     *
     * @param parent The parent node.
     * @param elemNames Array of [uri, localpart] pairs representing qualified names.
     * @return The last child element matching any of the qualified names, or null if not found.
     */
    public static Element getLastChildElementNS(Node parent, String[][] elemNames) {

        // search for node
        Node child = parent.getLastChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                for (int i = 0; i < elemNames.length; i++) {
                    String uri = child.getNamespaceURI();
                    if (uri != null && uri.equals(elemNames[i][0]) && child.getLocalName().equals(elemNames[i][1])) {
                        return (Element) child;
                    }
                }
            }
            child = child.getPreviousSibling();
        }

        // not found
        return null;

    } // getLastChildElementNS(Node,String[][]):Element

    /**
     * Finds and returns the next sibling node with the given qualified name.
     *
     * @param node The current node.
     * @param elemNames Array of [uri, localpart] pairs representing qualified names.
     * @return The next sibling element matching any of the qualified names, or null if not found.
     */
    public static Element getNextSiblingElementNS(Node node, String[][] elemNames) {

        // search for node
        Node sibling = node.getNextSibling();
        while (sibling != null) {
            if (sibling.getNodeType() == Node.ELEMENT_NODE) {
                for (int i = 0; i < elemNames.length; i++) {
                    String uri = sibling.getNamespaceURI();
                    if (uri != null && uri.equals(elemNames[i][0]) && sibling.getLocalName().equals(elemNames[i][1])) {
                        return (Element) sibling;
                    }
                }
            }
            sibling = sibling.getNextSibling();
        }

        // not found
        return null;

    } // getNextSiblingdElementNS(Node,String[][]):Element

    /**
     * Finds and returns the first child node with the given name and
     * attribute name, value pair.
     *
     * @param parent The parent node.
     * @param elemName The element name to search for.
     * @param attrName The attribute name to match.
     * @param attrValue The attribute value to match.
     * @return The first child element matching the criteria, or null if not found.
     */
    public static Element getFirstChildElement(Node parent, String elemName, String attrName, String attrValue) {

        // search for node
        Node child = parent.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) child;
                if (element.getNodeName().equals(elemName) && element.getAttribute(attrName).equals(attrValue)) {
                    return element;
                }
            }
            child = child.getNextSibling();
        }

        // not found
        return null;

    } // getFirstChildElement(Node,String,String,String):Element

    /**
     * Finds and returns the last child node with the given name and
     * attribute name, value pair.
     *
     * @param parent The parent node.
     * @param elemName The element name to search for.
     * @param attrName The attribute name to match.
     * @param attrValue The attribute value to match.
     * @return The last child element matching the criteria, or null if not found.
     */
    public static Element getLastChildElement(Node parent, String elemName, String attrName, String attrValue) {

        // search for node
        Node child = parent.getLastChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) child;
                if (element.getNodeName().equals(elemName) && element.getAttribute(attrName).equals(attrValue)) {
                    return element;
                }
            }
            child = child.getPreviousSibling();
        }

        // not found
        return null;

    } // getLastChildElement(Node,String,String,String):Element

    /**
     * Finds and returns the next sibling node with the given name and
     * attribute name, value pair. Since only elements have attributes,
     * the node returned will be of type Node.ELEMENT_NODE.
     *
     * @param node The current node.
     * @param elemName The element name to search for.
     * @param attrName The attribute name to match.
     * @param attrValue The attribute value to match.
     * @return The next sibling element matching the criteria, or null if not found.
     */
    public static Element getNextSiblingElement(Node node, String elemName, String attrName, String attrValue) {

        // search for node
        Node sibling = node.getNextSibling();
        while (sibling != null) {
            if (sibling.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) sibling;
                if (element.getNodeName().equals(elemName) && element.getAttribute(attrName).equals(attrValue)) {
                    return element;
                }
            }
            sibling = sibling.getNextSibling();
        }

        // not found
        return null;

    } // getNextSiblingElement(Node,String,String,String):Element

    /**
     * Returns the concatenated child text of the specified node.
     * This method only looks at the immediate children of type
     * <code>Node.TEXT_NODE</code> or the children of any child
     * node that is of type <code>Node.CDATA_SECTION_NODE</code>
     * for the concatenation.
     *
     * @param node The node to look at.
     * @return The concatenated text content of the node's children.
     */
    public static String getChildText(Node node) {

        // is there anything to do?
        if (node == null) {
            return null;
        }

        // concatenate children text
        StringBuffer str = new StringBuffer();
        Node child = node.getFirstChild();
        while (child != null) {
            short type = child.getNodeType();
            if (type == Node.TEXT_NODE) {
                str.append(child.getNodeValue());
            } else if (type == Node.CDATA_SECTION_NODE) {
                str.append(getChildText(child));
            }
            child = child.getNextSibling();
        }

        // return text value
        return str.toString();

    } // getChildText(Node):String

    /**
     * Returns the name of the given node.
     *
     * @param node The node.
     * @return The node name.
     */
    public static String getName(Node node) {
        return node.getNodeName();
    } // getLocalName(Element):  String

    /**
     * Returns the local name of the given node if not null, otherwise
     * returns the name of the node.
     *
     * @param node The node.
     * @return The local name if available, otherwise the node name.
     */
    public static String getLocalName(Node node) {
        String name = node.getLocalName();
        return (name != null) ? name : node.getNodeName();
    } // getLocalName(Element):  String

    /**
     * Returns the parent element of the given element.
     *
     * @param elem The element.
     * @return The parent element, or null if the parent is not an element.
     */
    public static Element getParent(Element elem) {
        Node parent = elem.getParentNode();
        if (parent instanceof Element)
            return (Element) parent;
        return null;
    } // getParent(Element):Element

    /**
     * Returns the Document of which the given node is a part.
     *
     * @param node The node.
     * @return The owner document.
     */
    public static Document getDocument(Node node) {
        return node.getOwnerDocument();
    } // getDocument(Node):Document

    /**
     * Returns the root element of the given document.
     *
     * @param doc The document.
     * @return The document element.
     */
    public static Element getRoot(Document doc) {
        return doc.getDocumentElement();
    } // getRoot(Document(:  Element

    // some methods for handling attributes:

    /**
     * Returns the attribute node with the given name.
     *
     * @param elem The element.
     * @param name The attribute name.
     * @return The attribute node.
     */
    public static Attr getAttr(Element elem, String name) {
        return elem.getAttributeNode(name);
    } // getAttr(Element, String):Attr

    /**
     * Returns the attribute node with the given namespace URI and local name.
     *
     * @param elem The element.
     * @param nsUri The namespace URI.
     * @param localName The local name.
     * @return The attribute node.
     */
    public static Attr getAttrNS(Element elem, String nsUri, String localName) {
        return elem.getAttributeNodeNS(nsUri, localName);
    } // getAttrNS(Element, String):Attr

    /**
     * Returns all attributes for the given element.
     *
     * @param elem The element.
     * @return Array of all attribute nodes.
     */
    public static Attr[] getAttrs(Element elem) {
        NamedNodeMap attrMap = elem.getAttributes();
        Attr[] attrArray = new Attr[attrMap.getLength()];
        for (int i = 0; i < attrMap.getLength(); i++)
            attrArray[i] = (Attr) attrMap.item(i);
        return attrArray;
    } // getAttrs(Element):  Attr[]

    /**
     * Returns the value of the given attribute.
     *
     * @param attribute The attribute.
     * @return The attribute value.
     */
    public static String getValue(Attr attribute) {
        return attribute.getValue();
    } // getValue(Attr):String

    // It is noteworthy that, because of the way the DOM specs
    // work, the next two methods return the empty string (not
    // null!) when the attribute with the specified name does not
    // exist on an element.  Beware!

    /**
     * Returns the value of the attribute with the given name from the given element.
     *
     * @param elem The element.
     * @param name The attribute name.
     * @return The attribute value, or empty string if the attribute does not exist.
     */
    public static String getAttrValue(Element elem, String name) {
        return elem.getAttribute(name);
    } // getAttr(Element, String):Attr

    /**
     * Returns the value of the attribute with the given namespace URI and local name from the given element.
     *
     * @param elem The element.
     * @param nsUri The namespace URI.
     * @param localName The local name.
     * @return The attribute value, or empty string if the attribute does not exist.
     */
    public static String getAttrValueNS(Element elem, String nsUri, String localName) {
        return elem.getAttributeNS(nsUri, localName);
    } // getAttrValueNS(Element, String):Attr

    /**
     * Returns the namespace prefix of the given node.
     *
     * @param node The node.
     * @return The namespace prefix.
     */
    public static String getPrefix(Node node) {
        return node.getPrefix();
    }

    /**
     * Returns the namespace URI of the given node.
     *
     * @param node The node.
     * @return The namespace URI.
     */
    public static String getNamespaceURI(Node node) {
        return node.getNamespaceURI();
    }

    /**
     * Returns the annotation associated with the given node if it is an ElementImpl.
     *
     * @param node The node.
     * @return The annotation string, or null if the node is not an ElementImpl or has no annotation.
     */
    public static String getAnnotation(Node node) {
        if (node instanceof ElementImpl) {
            return ((ElementImpl) node).getAnnotation();
        }
        return null;
    }

    /**
     * Returns the synthetic annotation associated with the given node if it is an ElementImpl.
     *
     * @param node The node.
     * @return The synthetic annotation string, or null if the node is not an ElementImpl or has no synthetic annotation.
     */
    public static String getSyntheticAnnotation(Node node) {
        if (node instanceof ElementImpl) {
            return ((ElementImpl) node).getSyntheticAnnotation();
        }
        return null;
    }

    /**
     * Creates a DOMException. On J2SE 1.4 and above the cause for the exception will be set.
     *
     * @param code The DOMException code.
     * @param cause The underlying cause of the exception.
     * @return A new DOMException with the specified code and cause.
     */
    public static DOMException createDOMException(short code, Throwable cause) {
        DOMException de = new DOMException(code, cause != null ? cause.getMessage() : null);
        if (cause != null && ThrowableMethods.fgThrowableMethodsAvailable) {
            try {
                ThrowableMethods.fgThrowableInitCauseMethod.invoke(de, new Object[] { cause });
            }
            // Something went wrong. There's not much we can do about it.
            catch (Exception e) {}
        }
        return de;
    }

    /**
     * Creates an LSException. On J2SE 1.4 and above the cause for the exception will be set.
     *
     * @param code The LSException code.
     * @param cause The underlying cause of the exception.
     * @return A new LSException with the specified code and cause.
     */
    public static LSException createLSException(short code, Throwable cause) {
        LSException lse = new LSException(code, cause != null ? cause.getMessage() : null);
        if (cause != null && ThrowableMethods.fgThrowableMethodsAvailable) {
            try {
                ThrowableMethods.fgThrowableInitCauseMethod.invoke(lse, new Object[] { cause });
            }
            // Something went wrong. There's not much we can do about it.
            catch (Exception e) {}
        }
        return lse;
    }

    /**
     * Holder of methods from java.lang.Throwable.
     */
    static class ThrowableMethods {

        // Method: java.lang.Throwable.initCause(java.lang.Throwable)
        private static java.lang.reflect.Method fgThrowableInitCauseMethod = null;

        // Flag indicating whether or not Throwable methods available.
        private static boolean fgThrowableMethodsAvailable = false;

        private ThrowableMethods() {
        }

        // Attempt to get methods for java.lang.Throwable on class initialization.
        static {
            try {
                fgThrowableInitCauseMethod = Throwable.class.getMethod("initCause", new Class[] { Throwable.class });
                fgThrowableMethodsAvailable = true;
            }
            // ClassNotFoundException, NoSuchMethodException or SecurityException
            // Whatever the case, we cannot use java.lang.Throwable.initCause(java.lang.Throwable).
            catch (Exception exc) {
                fgThrowableInitCauseMethod = null;
                fgThrowableMethodsAvailable = false;
            }
        }
    }

} // class DOMUtil
