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
import org.apache.xerces.xs.XSImplementation;
import org.apache.xerces.xs.XSLoader;
import org.apache.xerces.xs.XSModel;
import org.apache.xerces.xs.XSModelGroupDefinition;
import org.apache.xerces.xs.XSObjectList;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests methods getAnnotation and getAnnotations on XSModelGroupDefinition
 * XSModel components.
 *
 * @author Neil Delima, IBM
 * @version $Id: XSModelGroupDefinitionAnnotationsTest.java 447701 2006-09-19 03:14:44Z mrglavas $
 */
public class XSModelGroupDefinitionAnnotationsTest extends TestCase {

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
            System.err.println("SETUP FAILED: XSModelGroupDefinitionTest");
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
    public void testGroup1Annotation() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSModelGroupDefinitionTest01.xsd"));
        XSModelGroupDefinition group = model.getModelGroupDefinition("group1", "XSModelGroupDefn");

        XSAnnotation annotation = group.getAnnotation();
        assertNull(annotation, "TEST1_NO_ANNOTATION");
        XSObjectList annotations = group.getAnnotations();
        assertEquals(0, annotations.getLength(), "TEST1_NO_ANNOTATIONS");
    }

    /**
     * Test #2.
     */
    @Test
    public void testGroup2Annotation() {
        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSModelGroupDefinitionTest01.xsd"));
        XSModelGroupDefinition group = model.getModelGroupDefinition("group2", "XSModelGroupDefn");

        XSAnnotation annotation = group.getAnnotation();
        assertNull(annotation, "TEST2_NO_ANNOTATION");
        XSObjectList annotations = group.getAnnotations();
        assertEquals(0, annotations.getLength(), "TEST2_NO_ANNOTATIONS");
    }

    /**
     * Test #3.
     */
    @Test
    public void testGroup2SynthAnnotation() {
        String expected = trim("<annotation sn:att=\"ANNOT1\" " + "xmlns=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:sv=\"XSModelGroupDefn\" xmlns:sn=\"SyntheticAnnotation\" >" + "<documentation>SYNTHETIC_ANNOTATION</documentation>"
                + "</annotation>");

        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSModelGroupDefinitionTest01.xsd"));
        XSModelGroupDefinition group = model.getModelGroupDefinition("group2", "XSModelGroupDefn");

        XSAnnotation annotation = group.getAnnotation();
        assertEquals(expected, trim(annotation.getAnnotationString()), "TEST3_ANNOTATION");
        XSObjectList annotations = group.getAnnotations();
        assertEquals(expected, trim(((XSAnnotation) annotations.item(0)).getAnnotationString()), "TEST3_ANNOTATIONS");
    }

    /**
     * Test #4.
     */
    @Test
    public void testGroup3Annotation() {
        String expected = trim("<annotation id=\"ANNOT1\" " + "xmlns=\"http://www.w3.org/2001/XMLSchema\" xmlns:sv=\"XSModelGroupDefn\" "
                + "xmlns:sn=\"SyntheticAnnotation\" >" + "</annotation>");

        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSModelGroupDefinitionTest01.xsd"));
        XSModelGroupDefinition group = model.getModelGroupDefinition("group3", "XSModelGroupDefn");

        XSAnnotation annotation = group.getAnnotation();
        assertEquals(expected, trim(annotation.getAnnotationString()), "TEST3_ANNOTATION");
        XSObjectList annotations = group.getAnnotations();
        assertEquals(expected, trim(((XSAnnotation) annotations.item(0)).getAnnotationString()), "TEST3_ANNOTATIONS");
    }

    /**
     * Test #5.
     */
    @Test
    public void testGroup4Annotation() {
        String expected = trim("<annotation sn:att=\"ANNOT2\"  id=\"ANNOT2\" "
                + "xmlns=\"http://www.w3.org/2001/XMLSchema\" xmlns:sv=\"XSModelGroupDefn\" " + "xmlns:sn=\"SyntheticAnnotation\" >"
                + "</annotation>");

        fConfig.setParameter("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.TRUE);

        XSModel model = fSchemaLoader.loadURI(getResourceURL("XSModelGroupDefinitionTest01.xsd"));
        XSModelGroupDefinition group = model.getModelGroupDefinition("group4", "XSModelGroupDefn");

        XSAnnotation annotation = group.getAnnotation();
        assertEquals(expected, trim(annotation.getAnnotationString()), "TEST4_NO_SYNTH_ANNOTATION");
        XSObjectList annotations = group.getAnnotations();
        assertEquals(expected, trim(((XSAnnotation) annotations.item(0)).getAnnotationString()), "TEST4_NO_SYNTH_ANNOTATIONS");
    }

}
