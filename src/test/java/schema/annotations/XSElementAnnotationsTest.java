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
import org.codelibs.xerces.xs.XSElementDeclaration;
import org.codelibs.xerces.xs.XSImplementation;
import org.codelibs.xerces.xs.XSLoader;
import org.codelibs.xerces.xs.XSModel;
import org.codelibs.xerces.xs.XSObjectList;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests methods getAnnotation and getAnnotations on XSElementDeclaration
 * XSModel components.
 *
 * @author Neil Delima, IBM
 * @version $Id: XSElementAnnotationsTest.java 447701 2006-09-19 03:14:44Z mrglavas $
 */
public class XSElementAnnotationsTest extends TestCase {

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
            System.err.println("SETUP FAILED: XSElementTest");
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
    public void testElem1Annotation() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSElementTest01.xsd"));
        XSElementDeclaration elem = model.getElementDeclaration("elem1", "XSElement");

        XSAnnotation annotation = elem.getAnnotation();
        assertNull(annotation, "TEST1_NO_ANNOTATION");
        XSObjectList annotations = elem.getAnnotations();
        assertEquals(0, annotations.getLength(), "TEST1_NO_ANNOTATIONS");
    }

    /**
     * Test #2.
     */
    @Test
    public void testElem2Annotation() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSElementTest01.xsd"));
        XSElementDeclaration elem = model.getElementDeclaration("elem2", "XSElement");

        XSAnnotation annotation = elem.getAnnotation();
        assertNull(annotation, "TEST2_NO_ANNOTATION");
        XSObjectList annotations = elem.getAnnotations();
        assertEquals(0, annotations.getLength(), "TEST2_NO_ANNOTATIONS");
    }

    /**
     * Test #3.
     */
    @Test
    public void testElem2SynthAnnotation() {
        String expected = trim("<annotation sn:att=\"SYNTH\" " + "xmlns=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:sv=\"XSElement\" xmlns:sn=\"SyntheticAnnotation\" >" + "<documentation>SYNTHETIC_ANNOTATION</documentation>"
                + "</annotation>");

        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSElementTest01.xsd"));
        XSElementDeclaration elem = model.getElementDeclaration("elem2", "XSElement");

        XSAnnotation annotation = elem.getAnnotation();
        assertEquals(expected, trim(annotation.getAnnotationString()), "TEST3_ANNOTATION");
        XSObjectList annotations = elem.getAnnotations();
        assertEquals(expected, trim(((XSAnnotation) annotations.item(0)).getAnnotationString()), "TEST3_ANNOTATIONS");
    }

    /**
     * Test #4.
     */
    @Test
    public void testElem3Annotation() {
        String expected = trim("<annotation id=\"ANNOT1\" " + "xmlns=\"http://www.w3.org/2001/XMLSchema\" xmlns:sv=\"XSElement\" "
                + "xmlns:sn=\"SyntheticAnnotation\" >" + "</annotation>");

        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSElementTest01.xsd"));
        XSElementDeclaration elem = model.getElementDeclaration("elem3", "XSElement");

        XSAnnotation annotation = elem.getAnnotation();
        assertEquals(expected, trim(annotation.getAnnotationString()), "TEST3_ANNOTATION");
        XSObjectList annotations = elem.getAnnotations();
        assertEquals(expected, trim(((XSAnnotation) annotations.item(0)).getAnnotationString()), "TEST3_ANNOTATIONS");
    }

    /**
     * Test #5.
     */
    @Test
    public void testElem4Annotation() {
        String expected =
                trim("<annotation sn:att=\"SYNTH\"  id=\"ANNOT2\" " + "xmlns=\"http://www.w3.org/2001/XMLSchema\" xmlns:sv=\"XSElement\" "
                        + "xmlns:sn=\"SyntheticAnnotation\" >" + "</annotation>");

        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSElementTest01.xsd"));
        XSElementDeclaration elem = model.getElementDeclaration("elem4", "XSElement");

        XSAnnotation annotation = elem.getAnnotation();
        assertEquals(expected, trim(annotation.getAnnotationString()), "TEST4_NO_SYNTH_ANNOTATION");
        XSObjectList annotations = elem.getAnnotations();
        assertEquals(expected, trim(((XSAnnotation) annotations.item(0)).getAnnotationString()), "TEST4_NO_SYNTH_ANNOTATIONS");
    }

    /**
     * Test #6.
     */
    @Test
    public void testElem5Annotation() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSElementTest01.xsd"));
        XSElementDeclaration elem = model.getElementDeclaration("elem5", "XSElement");

        XSAnnotation annotation = elem.getAnnotation();
        assertNull(annotation, "TEST5_NO_ANNOTATION");
        XSObjectList annotations = elem.getAnnotations();
        assertEquals(0, annotations.getLength(), "TEST5_NO_ANNOTATIONS");
    }

    /**
     * Test #6.
     */
    @Test
    public void testElem6Annotation() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSElementTest01.xsd"));
        XSElementDeclaration elem = model.getElementDeclaration("elem6", "XSElement");

        XSAnnotation annotation = elem.getAnnotation();
        assertNull(annotation, "TEST5_NO_ANNOTATION");
        XSObjectList annotations = elem.getAnnotations();
        assertEquals(0, annotations.getLength(), "TEST5_NO_ANNOTATIONS");
    }

}
