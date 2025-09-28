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

package schema.annotations;

import org.apache.xerces.xs.XSAnnotation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.xerces.xs.XSAttributeUse;

import org.apache.xerces.xs.XSComplexTypeDefinition;

import org.apache.xerces.xs.XSImplementation;

import org.apache.xerces.xs.XSLoader;

import org.apache.xerces.xs.XSModel;

import org.apache.xerces.xs.XSObjectList;

import org.w3c.dom.DOMConfiguration;

import org.w3c.dom.bootstrap.DOMImplementationRegistry;

/**
 * Tests methods getAnnotations on XSAttributeUse XSModel components.
 *
 * @author Neil Delima, IBM
 * @version $Id: XSAttributeUseAnnotationsTest.java 447701 2006-09-19 03:14:44Z mrglavas $
 */
public class XSAttributeUseAnnotationsTest extends TestCase {

    /**
     * Members that are initialized by setUp() and cleaned up by tearDown().
     * <p>
     *
     * Note that setUp() and tearDown() are called for <em>each</em> test.
     * Different tests do <em>not</em> share the same instance member.
     */
    private XSLoader fSchemaLoader;

    private DOMConfiguration fConfig;

    /**
     * This method is called before every test case method, to set up the test
     * fixture.
     */
    @BeforeEach
    protected void setUp() {
        try {
            // get DOM Implementation using DOM Registry
            System.setProperty(DOMImplementationRegistry.PROPERTY, "org.apache.xerces.dom.DOMXSImplementationSourceImpl");
            DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();

            XSImplementation impl = (XSImplementation) registry.getDOMImplementation("XS-Loader");

            fSchemaLoader = impl.createXSLoader(null);

            fConfig = fSchemaLoader.getConfig();

            // set validation feature
            fConfig.setParameter("validate", Boolean.TRUE);

        } catch (Exception e) {
            fail("Expecting a NullPointerException");
            System.err.println("SETUP FAILED: XSAttributeUseAnnotationsTest");
        }
    }

    /**
     * This method is called before every test case method, to tears down the
     * test fixture.
     */
    @AfterEach
    protected void tearDown() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);
    }

    /**
     * Test #1.
     */
    @Test
    public void testAttrUseNoAnnotations() {
        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSAttributeUseAnnotationsTest01.xsd"));

        XSComplexTypeDefinition ct = (XSComplexTypeDefinition) model.getTypeDefinition("CT", "XSAttributeUseAnnotationsTest");

        XSObjectList attrUses = ct.getAttributeUses();

        // Attr ref
        XSAttributeUse attr = (XSAttributeUse) attrUses.item(0);
        XSObjectList annotations = attr.getAnnotations();
        assertEquals(0, annotations.getLength(), "REF");

        // local attr
        attr = (XSAttributeUse) attrUses.item(1);
        annotations = attr.getAnnotations();
        assertEquals(0, annotations.getLength(), "LOCAL");

        // attr grp ref
        attr = (XSAttributeUse) attrUses.item(2);
        annotations = attr.getAnnotations();
        assertEquals(0, annotations.getLength(), "GROUP");
    }

    /**
     * Test #2.
     */
    @Test
    public void testAttrUseNoSynthAnnotations() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSAttributeUseAnnotationsTest01.xsd"));

        XSComplexTypeDefinition ct = (XSComplexTypeDefinition) model.getTypeDefinition("CT", "XSAttributeUseAnnotationsTest");

        XSObjectList attrUses = ct.getAttributeUses();

        // Attr ref
        XSAttributeUse attr = (XSAttributeUse) attrUses.item(0);
        XSObjectList annotations = attr.getAnnotations();
        assertEquals(0, annotations.getLength(), "REF");

        // local attr
        attr = (XSAttributeUse) attrUses.item(1);
        annotations = attr.getAnnotations();
        assertEquals(0, annotations.getLength(), "LOCAL");

        // attr grp ref
        attr = (XSAttributeUse) attrUses.item(2);
        annotations = attr.getAnnotations();
        assertEquals(0, annotations.getLength(), "GROUP");
    }

    /**
     * Test #3
     */
    @Test
    public void testAttrUseSynthAnnotations() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSAttributeUseAnnotationsTest01.xsd"));

        XSComplexTypeDefinition ct = (XSComplexTypeDefinition) model.getTypeDefinition("CT", "XSAttributeUseAnnotationsTest");

        XSObjectList attrUses = ct.getAttributeUses();

        // Attr ref
        XSAttributeUse attr = (XSAttributeUse) attrUses.item(0);
        XSObjectList annotations = attr.getAnnotations();
        assertEquals(1, annotations.getLength(), "REF");

        // local attr
        attr = (XSAttributeUse) attrUses.item(1);
        annotations = attr.getAnnotations();
        assertEquals(1, annotations.getLength(), "LOCAL");

        // attr grp ref
        attr = (XSAttributeUse) attrUses.item(2);
        annotations = attr.getAnnotations();
        assertEquals(0, annotations.getLength(), "GROUP");

    }

    /**
     * Test #4.
     */
    @Test
    public void testAttrUseAnnotations() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSAttributeUseAnnotationsTest02.xsd"));

        XSComplexTypeDefinition ct = (XSComplexTypeDefinition) model.getTypeDefinition("CT", "XSAttributeUseAnnotationsTest");

        XSObjectList attrUses = ct.getAttributeUses();

        // Attr ref
        XSAttributeUse attr = (XSAttributeUse) attrUses.item(0);
        XSObjectList annotations = attr.getAnnotations();
        assertEquals(1, annotations.getLength(), "REF");

        // local attr
        attr = (XSAttributeUse) attrUses.item(1);
        annotations = attr.getAnnotations();
        assertEquals(1, annotations.getLength(), "LOCAL");

        // attr grp ref
        // The attribute in the group has an annotation element
        attr = (XSAttributeUse) attrUses.item(2);
        annotations = attr.getAnnotations();
        assertEquals(1, annotations.getLength(), "GROUP");
    }

    /**
     * Test #5.
     */
    @Test
    public void testAttrUseAnnotationsSynthetic() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSAttributeUseAnnotationsTest02.xsd"));

        XSComplexTypeDefinition ct = (XSComplexTypeDefinition) model.getTypeDefinition("CT", "XSAttributeUseAnnotationsTest");

        XSObjectList attrUses = ct.getAttributeUses();

        // Attr ref
        XSAttributeUse attr = (XSAttributeUse) attrUses.item(0);
        XSObjectList annotations = attr.getAnnotations();
        assertEquals(1, annotations.getLength(), "REF");

        // local attr
        attr = (XSAttributeUse) attrUses.item(1);
        annotations = attr.getAnnotations();
        assertEquals(1, annotations.getLength(), "LOCAL");

        // attr grp ref
        // The attribute in the group has an annotation element
        attr = (XSAttributeUse) attrUses.item(2);
        annotations = attr.getAnnotations();
        assertEquals(1, annotations.getLength(), "GROUP");
    }

    /**
     * Test #6.
     */
    @Test
    public void testWildAttrUseAnnotationsSynthetic() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSAttributeUseAnnotationsTest03.xsd"));

        XSComplexTypeDefinition ct = (XSComplexTypeDefinition) model.getTypeDefinition("CT", "XSAttributeUseAnnotationsTest");

        XSObjectList attrUses = ct.getAttributeUses();

        // Attr ref
        XSAttributeUse attr = (XSAttributeUse) attrUses.item(0);
        XSObjectList annotations = attr.getAnnotations();
        assertEquals(1, annotations.getLength(), "REF");

        XSAnnotation annotation = attr.getAttrDeclaration().getAnnotation();
        String expected =
                "<annotation sn:att=\"ATT1\"  id=\"ANNOT1\" xmlns=\"http://www.w3.org/2001/XMLSchema\" xmlns:sv=\"XSAttributeUseAnnotationsTest\" xmlns:sn=\"SyntheticAnnotationNS\" > "
                        + "<appinfo>APPINFO1</appinfo>" + "</annotation>";
        assertEquals(trim(expected), trim(annotation.getAnnotationString()), "REF_STRING");

        annotations = attr.getAttrDeclaration().getAnnotations();
        assertEquals(trim(expected), trim(((XSAnnotation) annotations.item(0)).getAnnotationString()), "REF_STRING_ANNOTATIONS");

        // local attr
        attr = (XSAttributeUse) attrUses.item(1);
        annotations = attr.getAnnotations();
        assertEquals(1, annotations.getLength(), "LOCAL");

        annotation = attr.getAttrDeclaration().getAnnotation();
        expected =
                "<annotation sn:att=\"ATT11\"  id=\"ANNOT6\" xmlns=\"http://www.w3.org/2001/XMLSchema\" xmlns:sv=\"XSAttributeUseAnnotationsTest\" xmlns:sn=\"SyntheticAnnotationNS\" > "
                        + "<appinfo>APPINFO6</appinfo>" + "</annotation>";
        assertEquals(trim(expected), trim(annotation.getAnnotationString()), "LOCAL_STRING");

        annotations = attr.getAttrDeclaration().getAnnotations();
        assertEquals(trim(expected), trim(((XSAnnotation) annotations.item(0)).getAnnotationString()), "LOCAL_STRING_ANNOTATIONS");

        // attr grp ref
        // The attribute in the group has an annotation element
        attr = (XSAttributeUse) attrUses.item(2);
        annotations = attr.getAnnotations();
        assertEquals(1, annotations.getLength(), "GROUP");

        annotation = attr.getAttrDeclaration().getAnnotation();
        expected =
                "<annotation id=\"ANNOT3\" xmlns=\"http://www.w3.org/2001/XMLSchema\" xmlns:sv=\"XSAttributeUseAnnotationsTest\" xmlns:sn=\"SyntheticAnnotationNS\" > "
                        + "<appinfo>APPINFO3</appinfo>" + "</annotation>";
        assertEquals(trim(expected), trim(annotation.getAnnotationString()), "GROUP_STRING");

        annotations = attr.getAttrDeclaration().getAnnotations();
        assertEquals(trim(expected), trim(((XSAnnotation) annotations.item(0)).getAnnotationString()), "GROUP_STRING_ANNOTATIONS");

    }

}
