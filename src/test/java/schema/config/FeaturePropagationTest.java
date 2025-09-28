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

package schema.config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 * @author Peter McCracken, IBM
 * @version $Id: FeaturePropagationTest.java 447700 2006-09-19 03:13:41Z mrglavas $
 */
public class FeaturePropagationTest extends BaseTest {

    public final String FEATURE_STRING_DEFAULT_FALSE = "http://apache.org/xml/features/honour-all-schemaLocations";
    public final String FEATURE_STRING_DEFAULT_TRUE = "http://apache.org/xml/features/validation/schema-full-checking";
    public final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";

    protected String getXMLDocument() {
        return "base.xml";
    }

    protected String getSchemaFile() {
        return "base.xsd";
    }

    @Test
    public void testPropertyReset() throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = makeSchema(factory, null);
        Validator validator = schema.newValidator();
        Object beforeReset = validator.getProperty(SECURITY_MANAGER);
        validator.setProperty(SECURITY_MANAGER, null);
        Object changed = validator.getProperty(SECURITY_MANAGER);
        assertFalse(beforeReset != changed, "Property value should have changed after calling setProperty().");
        validator.reset();
        Object afterReset = validator.getProperty(SECURITY_MANAGER);
        assertTrue(beforeReset == afterReset, "Property value should be the same after calling reset()");
    }

    @Test
    public void testFeatureReset() throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = makeSchema(factory, null);
        Validator validator = schema.newValidator();
        validator.setFeature(FEATURE_STRING_DEFAULT_TRUE, false);
        validator.setFeature(FEATURE_STRING_DEFAULT_FALSE, true);
        validator.reset();
        boolean value = validator.getFeature(FEATURE_STRING_DEFAULT_TRUE);
        assertTrue(value, "After reset, value of feature on Validator should be true.");
        value = validator.getFeature(FEATURE_STRING_DEFAULT_FALSE);
        assertFalse(value, "After reset, value of feature on Validator should be false.");
    }

    @Test
    public void testSecureProcessingFeaturePropagationAndReset() throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        boolean value;
        value = factory.getFeature(XMLConstants.FEATURE_SECURE_PROCESSING);
        assertFalse(value, "Default value of feature on SchemaFactory should have been false.");
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        Schema schema = makeSchema(factory, null);
        Validator validator = schema.newValidator();
        value = validator.getFeature(XMLConstants.FEATURE_SECURE_PROCESSING);
        assertTrue(value, "Value of feature on Validator should have been true.");
        validator.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
        value = validator.getFeature(XMLConstants.FEATURE_SECURE_PROCESSING);
        assertFalse(value, "Value of feature on Validator should have been false.");
        validator.reset();
        value = validator.getFeature(XMLConstants.FEATURE_SECURE_PROCESSING);
        assertTrue(value, "After reset, value of feature on Validator should be true.");
    }

    /*
     * Using four basically identical tests to try out the different
     * instance classes of Schema.  They shouldn't differ, because the relevant
     * code is in a common base class.
     */

    @Test
    public void testFeaturePropagationNull() throws Exception {
        checkFeaturesOnValidator(null);
    }

    @Test
    public void testFeaturePropagationEmpty() throws Exception {
        checkFeaturesOnValidator(new Source[] {});
    }

    @Test
    public void testFeaturePropagationSingle() throws Exception {
        checkFeaturesOnValidator(new Source[] { makeSource("base.xsd") });
    }

    @Test
    public void testFeaturePropagationMultiple() throws Exception {
        checkFeaturesOnValidator(new Source[] { makeSource("base.xsd"), makeSource("idc.xsd") });
    }

    private void checkFeaturesOnValidator(Source[] sources) throws Exception {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = makeSchema(factory, sources);
            Validator validator = schema.newValidator();
            boolean value;
            value = validator.getFeature(FEATURE_STRING_DEFAULT_TRUE);
            assertTrue(value, "Default value of feature on Validator should have been true.");
            value = validator.getFeature(FEATURE_STRING_DEFAULT_FALSE);
            assertFalse(value, "Default value of feature on Validator should have been false.");

            // checking that the value propagates to the validator
            factory.setFeature(FEATURE_STRING_DEFAULT_TRUE, false);
            factory.setFeature(FEATURE_STRING_DEFAULT_FALSE, true);
            schema = makeSchema(factory, sources);
            validator = schema.newValidator();
            value = validator.getFeature(FEATURE_STRING_DEFAULT_TRUE);
            assertFalse(value, "Value of feature on Validator should have been false.");
            value = validator.getFeature(FEATURE_STRING_DEFAULT_FALSE);
            assertTrue(value, "Value of feature on Validator should have been true.");

            // checking that the validator contains a copy of the features, not a reference
            factory.setFeature(FEATURE_STRING_DEFAULT_TRUE, true);
            factory.setFeature(FEATURE_STRING_DEFAULT_FALSE, false);
            value = validator.getFeature(FEATURE_STRING_DEFAULT_TRUE);
            assertFalse(value, "Value of feature on Validator should have stayed false.");
            value = validator.getFeature(FEATURE_STRING_DEFAULT_FALSE);
            assertTrue(value, "Value of feature on Validator should have stayed true.");
        } catch (SAXNotRecognizedException e) {
            fail(e.getMessage());
        } catch (SAXNotSupportedException e) {
            fail(e.getMessage());
        }
    }

    private Schema makeSchema(SchemaFactory factory, Source[] sources) throws SAXException {
        if (sources == null) {
            return factory.newSchema();
        } else {
            return factory.newSchema(sources);
        }
    }

    private Source makeSource(String xsd) throws FileNotFoundException {
        String packageDir = this.getClass().getPackage().getName().replace('.', File.separatorChar);
        String schemaPath = packageDir + File.separatorChar + xsd;
        URL schemaURL = ClassLoader.getSystemResource(schemaPath);
        if (schemaURL == null) {
            throw new FileNotFoundException("Couldn't find schema file: " + schemaPath);
        }
        return new StreamSource(schemaURL.toExternalForm());
    }
}
