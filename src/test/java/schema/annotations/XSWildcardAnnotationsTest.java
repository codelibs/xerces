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
import org.apache.xerces.xs.XSAttributeGroupDefinition;

import org.apache.xerces.xs.XSComplexTypeDefinition;

import org.apache.xerces.xs.XSElementDeclaration;

import org.apache.xerces.xs.XSImplementation;

import org.apache.xerces.xs.XSLoader;

import org.apache.xerces.xs.XSModel;

import org.apache.xerces.xs.XSModelGroup;

import org.apache.xerces.xs.XSObjectList;

import org.apache.xerces.xs.XSParticle;

import org.apache.xerces.xs.XSWildcard;

import org.w3c.dom.DOMConfiguration;

import org.w3c.dom.bootstrap.DOMImplementationRegistry;

/**
 * Tests methods getAnnotation and getAnnotations on XSWildcard XSModel
 * components.
 *
 * @author Neil Delima, IBM
 * @version $Id: XSWildcardAnnotationsTest.java 447701 2006-09-19 03:14:44Z mrglavas $
 */
public class XSWildcardAnnotationsTest extends TestCase {

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
            System.err.println("SETUP FAILED: XSWildcardTest");
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
    public void testAttrWCNoAnnotation() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSWildcardTest01.xsd"));

        XSElementDeclaration elem = model.getElementDeclaration("test", "XSWildcardTest");
        XSWildcard attrWC = ((XSComplexTypeDefinition) elem.getTypeDefinition()).getAttributeWildcard();

        XSAnnotation annotation = attrWC.getAnnotation();
        assertNull(annotation, "TEST1_ATTRWC_NO_ANNOTATION");

        XSObjectList annotations = attrWC.getAnnotations();
        assertEquals(0, annotations.getLength(), "TEST1_ATTRWC_NO_ANNOTATIONS");

        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        model = fSchemaLoader.loadURI(getResourceURL("XSWildcardTest01.xsd"));

        elem = model.getElementDeclaration("test", "XSWildcardTest");
        attrWC = ((XSComplexTypeDefinition) elem.getTypeDefinition()).getAttributeWildcard();

        annotation = attrWC.getAnnotation();
        assertNotNull(annotation, "TEST1_ATTRWC_SYNTH_ANNOTATION");

        annotations = attrWC.getAnnotations();
        assertEquals(1, annotations.getLength(), "TEST1_ATTRWC_SYNTHO_ANNOTATIONS");
    }

    /**
     * Test #2.
     */
    @Test
    public void testAttrWCAnnotation() {
        attrWCAnnotationTest(false);
        attrWCAnnotationTest(true);
    }

    private XSModel getXSModel(boolean synthetic) {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSWildcardTest01.xsd"));

        return model;
    }

    private void attrWCAnnotationTest(boolean synthetic) {
        String expected = trim("<annotation sn:attr=\"SYNTH\" " + "id=\"ANNOT4\" xmlns=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:sv=\"XSWildcardTest\" xmlns:sn=\"SyntheticAnnotation\" > "
                + "<appinfo>APPINFO</appinfo><documentation>DOC</documentation>" + "</annotation>");

        XSModel model = getXSModel(synthetic);

        XSElementDeclaration elem = model.getElementDeclaration("root", "XSWildcardTest");
        XSWildcard attrWC = ((XSComplexTypeDefinition) elem.getTypeDefinition()).getAttributeWildcard();

        XSAnnotation annotation = attrWC.getAnnotation();
        assertEquals(expected, trim(annotation.getAnnotationString()), "TEST2_ATTRWC_ANNOTATION_" + synthetic);

        XSObjectList annotations = attrWC.getAnnotations();
        assertEquals(expected, trim(((XSAnnotation) annotations.item(0)).getAnnotationString()), "TEST2_ATTRWC_ANNOTATIONS_" + synthetic);
    }

    /**
     * Test #3.
     */
    @Test
    public void testAttGpWCAnnotations() {
        attrgpWCAnnotationTest(false);
        attrgpWCAnnotationTest(true);
    }

    private void attrgpWCAnnotationTest(boolean synthetic) {
        String expected = trim("<annotation sn:attr=\"SYNTH\" " + "id=\"ANNOT5\" xmlns=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:sv=\"XSWildcardTest\" xmlns:sn=\"SyntheticAnnotation\" > "
                + "<appinfo>APPINFO</appinfo><documentation>DOC</documentation>" + "</annotation>");

        XSModel model = getXSModel(synthetic);

        XSAttributeGroupDefinition attgp = model.getAttributeGroup("attgrp", "XSWildcardTest");
        XSWildcard attrWC = attgp.getAttributeWildcard();

        XSAnnotation annotation = attrWC.getAnnotation();
        assertEquals(expected, trim(annotation.getAnnotationString()), "TEST3_ATTRWC_ANNOTATION_" + synthetic);

        XSObjectList annotations = attrWC.getAnnotations();
        assertEquals(expected, trim(((XSAnnotation) annotations.item(0)).getAnnotationString()), "TEST3_ATTRWC_ANNOTATIONS_" + synthetic);
    }

    /**
     * Test #4.
     */
    @Test
    public void testElemWCNoAnnotation() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSWildcardTest01.xsd"));

        XSElementDeclaration elem = model.getElementDeclaration("root", "XSWildcardTest");
        XSParticle seq = ((XSComplexTypeDefinition) elem.getTypeDefinition()).getParticle();
        XSModelGroup mg = (XSModelGroup) seq.getTerm();
        XSParticle anyPart = (XSParticle) mg.getParticles().item(1);
        XSWildcard elemWC = (XSWildcard) anyPart.getTerm();

        XSAnnotation annotation = elemWC.getAnnotation();
        assertNull(annotation, "TEST4_ELEMWC_NO_ANNOTATION");

        XSObjectList annotations = elemWC.getAnnotations();
        assertEquals(0, annotations.getLength(), "TEST4_ELEMWC_NO_ANNOTATIONS");
    }

    /**
     * Test #5.
     */
    @Test
    public void testElemWCSynthAnnotation() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSWildcardTest01.xsd"));

        XSElementDeclaration elem = model.getElementDeclaration("root", "XSWildcardTest");
        XSParticle seq = ((XSComplexTypeDefinition) elem.getTypeDefinition()).getParticle();
        XSModelGroup mg = (XSModelGroup) seq.getTerm();
        XSParticle anyPart = (XSParticle) mg.getParticles().item(2);
        XSWildcard elemWC = (XSWildcard) anyPart.getTerm();

        XSAnnotation annotation = elemWC.getAnnotation();
        assertNotNull(annotation, "TEST5_ELEMWC_SYNTH_ANNOTATION");

        XSObjectList annotations = elemWC.getAnnotations();
        assertEquals(1, annotations.getLength(), "TEST5_ELEMWC_SYNTH_ANNOTATIONS");
    }

    /**
     * Test #6.
     */
    @Test
    public void testElemWCAnnotation() {
        String expected = trim("<annotation id=\"ANNOT1\" " + "xmlns=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:sv=\"XSWildcardTest\" xmlns:sn=\"SyntheticAnnotation\" >" + "</annotation>");
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSWildcardTest01.xsd"));

        XSElementDeclaration elem = model.getElementDeclaration("root", "XSWildcardTest");
        XSParticle seq = ((XSComplexTypeDefinition) elem.getTypeDefinition()).getParticle();
        XSModelGroup mg = (XSModelGroup) seq.getTerm();
        XSParticle anyPart = (XSParticle) mg.getParticles().item(3);
        XSWildcard elemWC = (XSWildcard) anyPart.getTerm();

        XSAnnotation annotation = elemWC.getAnnotation();
        assertEquals(expected, trim(annotation.getAnnotationString()), "TEST6_ELEMWC_ANNOTATION");

        XSObjectList annotations = elemWC.getAnnotations();
        assertEquals(expected, trim(((XSAnnotation) annotations.item(0)).getAnnotationString()), "TEST6_ELEMWC_ANNOTATIONS");
    }

    /**
     * Test #7.
     */
    @Test
    public void testElemWCAnnotationnoSynth() {
        String expected = trim("<annotation sn:attr=\"SYNTH\"  id=\"ANNOT2\" " + "xmlns=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:sv=\"XSWildcardTest\" xmlns:sn=\"SyntheticAnnotation\" >"
                + "<appinfo>APPINFO</appinfo><documentation>DOC</documentation>" + "</annotation>");

        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSWildcardTest01.xsd"));

        XSElementDeclaration elem = model.getElementDeclaration("root", "XSWildcardTest");
        XSParticle seq = ((XSComplexTypeDefinition) elem.getTypeDefinition()).getParticle();
        XSModelGroup mg = (XSModelGroup) seq.getTerm();
        XSParticle anyPart = (XSParticle) mg.getParticles().item(4);
        XSWildcard elemWC = (XSWildcard) anyPart.getTerm();

        XSAnnotation annotation = elemWC.getAnnotation();
        assertEquals(expected, trim(annotation.getAnnotationString()), "TEST7_ELEMWC_ANNOTATION");

        XSObjectList annotations = elemWC.getAnnotations();
        assertEquals(expected, trim(((XSAnnotation) annotations.item(0)).getAnnotationString()), "TEST7_ELEMWC_ANNOTATIONS");
    }

}
