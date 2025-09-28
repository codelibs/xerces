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
import org.codelibs.xerces.xs.XSImplementation;
import org.codelibs.xerces.xs.XSLoader;
import org.codelibs.xerces.xs.XSModel;
import org.codelibs.xerces.xs.XSNotationDeclaration;
import org.codelibs.xerces.xs.XSObjectList;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests methods getAnnotation and getAnnotations on XSNotationDeclaration
 * XSModel components.
 *
 * @author Neil Delima, IBM
 * @version $Id: XSNotationAnnotationsTest.java 447701 2006-09-19 03:14:44Z mrglavas $
 */
public class XSNotationAnnotationsTest extends TestCase {

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
            System.err.println("SETUP FAILED: XSNotationAnnotationsTest");
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
        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSNotationAnnotationsTest01.xsd"));

        XSNotationDeclaration notation = model.getNotationDeclaration("notation1", "XSNotationAnnotationsTest");
        XSAnnotation annotation = notation.getAnnotation();
        assertNull(annotation, "TEST1_NO_ANNOTATION");

        XSObjectList annotations = notation.getAnnotations();
        assertEquals(0, annotations.getLength(), "TEST1_NO_ANNOTATIONS");

    }

    /**
     * Test #2.
     */
    @Test
    public void testSyntheticAnnotations() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSNotationAnnotationsTest01.xsd"));

        XSNotationDeclaration notation = model.getNotationDeclaration("notation2", "XSNotationAnnotationsTest");
        XSAnnotation annotation = notation.getAnnotation();
        assertNotNull(annotation, "TEST2_ANNOTATION");

        XSObjectList annotations = notation.getAnnotations();
        assertEquals(1, annotations.getLength(), "TEST2_ANNOTATIONS");
    }

    /**
     * Test #3.
     */
    @Test
    public void testNoSyntheticAnnotations() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSNotationAnnotationsTest01.xsd"));

        XSNotationDeclaration notation = model.getNotationDeclaration("notation2", "XSNotationAnnotationsTest");
        XSAnnotation annotation = notation.getAnnotation();
        assertNull(annotation, "TEST3_NO_ANNOTATION");

        XSObjectList annotations = notation.getAnnotations();
        assertEquals(0, annotations.getLength(), "TEST3_NO_ANNOTATIONS");

    }

    /**
     * Test #4.
     */
    @Test
    public void testAnnotations() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);
        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSNotationAnnotationsTest01.xsd"));

        annotationsTest4(model);

        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);
        model = fSchemaLoader.loadURI(getResourceURL("XSNotationAnnotationsTest01.xsd"));

        annotationsTest4(model);
    }

    private void annotationsTest4(XSModel model) {
        String expected =
                "<annotation id=\"ANNOT1\" xmlns=\"http://www.w3.org/2001/XMLSchema\" xmlns:sv=\"XSNotationAnnotationsTest\" xmlns:sn=\"SyntheticAnnotation\" > "
                        + "<appinfo>APPINFO1</appinfo>" + "</annotation>";

        XSNotationDeclaration notation = model.getNotationDeclaration("notation3", "XSNotationAnnotationsTest");
        XSAnnotation annotation = notation.getAnnotation();
        assertNotNull(annotation, "TEST4_ANNOTATION");
        assertEquals(trim(expected), trim(annotation.getAnnotationString()), "TEST4_ANNOTATION_EQ");

        XSObjectList annotations = notation.getAnnotations();
        assertEquals(1, annotations.getLength(), "TEST4_ANNOTATIONS");
        assertEquals(trim(expected), trim(((XSAnnotation) annotations.item(0)).getAnnotationString()), "TEST4_ANNOTATIONS_EQ");
    }

    /**
     * Test #5.
     */
    @Test
    public void testAnnotationsWithSynth() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);
        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSNotationAnnotationsTest01.xsd"));

        annotationsTest5(model);

        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);
        model = fSchemaLoader.loadURI(getResourceURL("XSNotationAnnotationsTest01.xsd"));

        annotationsTest5(model);
    }

    private void annotationsTest5(XSModel model) {
        String expected =
                "<annotation sn:att=\"synth\" id=\"ANNOT2\" xmlns=\"http://www.w3.org/2001/XMLSchema\" xmlns:sv=\"XSNotationAnnotationsTest\" xmlns:sn=\"SyntheticAnnotation\" > "
                        + "<documentation>DOC1</documentation>" + "</annotation>";

        XSNotationDeclaration notation = model.getNotationDeclaration("notation4", "XSNotationAnnotationsTest");
        XSAnnotation annotation = notation.getAnnotation();
        assertNotNull(annotation, "TEST5_ANNOTATION");
        assertEquals(trim(expected), trim(annotation.getAnnotationString()), "TEST5_ANNOTATION_EQ");

        XSObjectList annotations = notation.getAnnotations();
        assertEquals(1, annotations.getLength(), "TEST5_ANNOTATIONS");
        assertEquals(trim(expected), trim(((XSAnnotation) annotations.item(0)).getAnnotationString()), "TEST5_ANNOTATIONS_EQ");
    }

}
