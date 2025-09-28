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

import org.codelibs.xerces.xs.XSAnnotation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.codelibs.xerces.xs.XSAttributeDeclaration;

import org.codelibs.xerces.xs.XSImplementation;

import org.codelibs.xerces.xs.XSLoader;

import org.codelibs.xerces.xs.XSModel;

import org.codelibs.xerces.xs.XSObjectList;

import org.w3c.dom.DOMConfiguration;

import org.w3c.dom.bootstrap.DOMImplementationRegistry;

/**
 * Tests methods getAnnotation and getAnnotations on XSAttributeDeclaration
 * XSModel components.
 *
 * @author Neil Delima, IBM
 * @version $Id: XSAttributeAnnotationsTest.java 447701 2006-09-19 03:14:44Z mrglavas $
 */
public class XSAttributeAnnotationsTest extends TestCase {

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
            System.setProperty(DOMImplementationRegistry.PROPERTY, "org.codelibs.xerces.dom.DOMXSImplementationSourceImpl");
            DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();

            XSImplementation impl = (XSImplementation) registry.getDOMImplementation("XS-Loader");

            fSchemaLoader = impl.createXSLoader(null);

            fConfig = fSchemaLoader.getConfig();

            // set validation feature
            fConfig.setParameter("validate", Boolean.TRUE);

        } catch (Exception e) {
            fail("Expecting a NullPointerException");
            System.err.println("SETUP FAILED: XSAttributeAnnotationsTest");
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
    public void testNoAnnotation() {
        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSAttributeAnnotationsTest01.xsd"));

        XSAttributeDeclaration attr = model.getAttributeDeclaration("attr", "XSAttributeAnnotationsTest");
        XSAnnotation annotation = attr.getAnnotation();

        assertNull(annotation, "Annotation should be null");
    }

    /**
     * Test #2.
     */
    @Test
    public void testNoAnnotations() {
        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSAttributeAnnotationsTest01.xsd"));

        XSAttributeDeclaration attr = model.getAttributeDeclaration("attr", "XSAttributeAnnotationsTest");
        XSObjectList annotations = attr.getAnnotations();

        assertEquals(0, annotations.getLength(), "Annotations length should be 0");
    }

    /**
     * Test #3.
     */
    @Test
    public void testAnnotation() {
        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSAttributeAnnotationsTest02.xsd"));

        XSAttributeDeclaration attr = model.getAttributeDeclaration("attr", "XSAttributeAnnotationsTest");
        XSAnnotation annotation = attr.getAnnotation();

        String expectedResult = "<annotation id=\"ANNOT1\" " + "xmlns=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:sv=\"XSAttributeAnnotationsTest\" >" + "<appinfo source=\"None\">" + "<!-- No Appinfo -->"
                + "</appinfo><documentation>ANNOT1 should be seen</documentation>" + "</annotation>";

        String actual = annotation.getAnnotationString();

        assertEquals(trim(expectedResult), trim(actual), "Annotation strings should match");
    }

    /**
     * Test #4.
     */
    @Test
    public void testAnnotations() {
        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSAttributeAnnotationsTest02.xsd"));

        XSAttributeDeclaration attr = model.getAttributeDeclaration("attr", "XSAttributeAnnotationsTest");
        XSObjectList annotations = attr.getAnnotations();

        String expectedResult = "<annotation id=\"ANNOT1\" " + "xmlns=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:sv=\"XSAttributeAnnotationsTest\" >" + "<appinfo source=\"None\">" + "<!-- No Appinfo -->"
                + "</appinfo><documentation>ANNOT1 should be seen</documentation>" + "</annotation>";

        for (int i = 0; i < annotations.getLength(); i++) {
            XSAnnotation annotation = (XSAnnotation) annotations.item(i);
            String actual = annotation.getAnnotationString();
            assertEquals(trim(expectedResult), trim(actual), "Annotation strings should match");
        }

        attr = model.getAttributeDeclaration("attr2", "XSAttributeAnnotationsTest");
        annotations = attr.getAnnotations();

        expectedResult = "<annotation id=\"ANNOT2\" " + "xmlns=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:sv=\"XSAttributeAnnotationsTest\" >" + "</annotation>";

        for (int i = 0; i < annotations.getLength(); i++) {
            XSAnnotation annotation = (XSAnnotation) annotations.item(i);
            String actual = annotation.getAnnotationString();
            assertEquals(trim(expectedResult), trim(actual), "Annotation strings should match");
        }
    }

    /**
     * Test #5.
     */
    @Test
    public void testSyntheticAnnotation() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSAttributeAnnotationsTest03.xsd"));

        XSAttributeDeclaration attr = model.getAttributeDeclaration("attr", "XSAttributeAnnotationsTest");
        XSAnnotation annotation = attr.getAnnotation();

        assertNotNull(annotation, "Synthetic Annotation Null");
    }

    /**
     * Test #6.
     */
    @Test
    public void testSyntheticAnnotation6() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSAttributeAnnotationsTest03.xsd"));

        XSAttributeDeclaration attr = model.getAttributeDeclaration("attr", "XSAttributeAnnotationsTest");
        XSObjectList annotations = attr.getAnnotations();

        assertEquals(1, annotations.getLength(), "Synthetic Annotation Empty");
    }

    /**
     * Test #7
     */
    @Test
    public void testNoSyntheticAnnotation() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSAttributeAnnotationsTest03.xsd"));

        XSAttributeDeclaration attr = model.getAttributeDeclaration("attr", "XSAttributeAnnotationsTest");
        XSAnnotation annotation = attr.getAnnotation();

        assertNull(annotation, "Synthetic Annotation Not Null");
    }

    /**
     * Test #8
     */
    @Test
    public void testSyntheticAnnotationsAbsent() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSAttributeAnnotationsTest03.xsd"));

        XSAttributeDeclaration attr = model.getAttributeDeclaration("attr1", "XSAttributeAnnotationsTest");
        XSObjectList annotations = attr.getAnnotations();

        assertEquals(0, annotations.getLength(), "Synthetic Annotation Empty");
    }

    /**
     *
     * @param args
     */

}
