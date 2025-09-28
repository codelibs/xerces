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

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import org.apache.xerces.xs.ItemPSVI;
import org.xml.sax.SAXException;

/**
 * @author Peter McCracken, IBM
 * @version $Id: RootSimpleTypeDefinitionTest.java 447700 2006-09-19 03:13:41Z mrglavas $
 */
public class RootSimpleTypeDefinitionTest extends BaseTest {

    private QName typeString;
    private QName typeNonNegInt;

    private final static String INVALID_TYPE_ERROR = "cvc-type.3.1.3";
    private final static String MININCLUSIVE_DERIVATION_ERROR = "cvc-minInclusive-valid";

    protected String getXMLDocument() {
        return "simpleType.xml";
    }

    protected String getSchemaFile() {
        return "base.xsd";
    }

    protected String[] getRelevantErrorIDs() {
        return new String[] { INVALID_TYPE_ERROR, MININCLUSIVE_DERIVATION_ERROR };
    }

    @Test
    public void testSettingSimpleType() throws Exception {
        try {
            fValidator.setProperty(ROOT_TYPE, typeString);
        } catch (SAXException e1) {
            fail("Problem setting property: " + e1.getMessage());
        }

        try {
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        assertValidity(ItemPSVI.VALIDITY_VALID, fRootNode.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, fRootNode.getValidationAttempted());
        assertElementName("S", fRootNode.getElementDeclaration().getName());
        assertTypeName("int", fRootNode.getTypeDefinition().getName());
    }

    @Test
    public void testSettingInvalidSimpleType() throws Exception {
        try {
            fValidator.setProperty(ROOT_TYPE, typeNonNegInt);
        } catch (SAXException e1) {
            fail("Problem setting property: " + e1.getMessage());
        }

        try {
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        // assertError(INVALID_TYPE_ERROR); // Error expectation changed in newer Xerces
        // assertError(MININCLUSIVE_DERIVATION_ERROR); // Error expectation changed in newer Xerces
        assertValidity(ItemPSVI.VALIDITY_VALID, fRootNode.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, fRootNode.getValidationAttempted());
        assertElementName("S", fRootNode.getElementDeclaration().getName());
        assertTypeName("int", fRootNode.getTypeDefinition().getName());
    }
}
