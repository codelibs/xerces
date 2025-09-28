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

import org.codelibs.xerces.dom.PSVIElementNSImpl;
import org.codelibs.xerces.xs.ItemPSVI;
import org.xml.sax.SAXException;

/**
 * @author Peter McCracken, IBM
 * @version $Id: UnparsedEntityCheckingTest.java 447700 2006-09-19 03:13:41Z mrglavas $
 */
public class UnparsedEntityCheckingTest extends BaseTest {

    public static final String UNDECLARED_ENTITY = "UndeclaredEntity";

    protected String getXMLDocument() {
        return "unparsedEntity.xml";
    }

    protected String getSchemaFile() {
        return "base.xsd";
    }

    protected String[] getRelevantErrorIDs() {
        return new String[] { UNDECLARED_ENTITY };
    }

    @Test
    public void testDefaultValid() {
        try {
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        checkDefault();
    }

    @Test
    public void testSetFalseValid() {
        try {
            fValidator.setFeature(UNPARSED_ENTITY_CHECKING, false);
        } catch (SAXException e) {
            fail("Error setting feature.");
        }
        try {
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        checkDefault();
    }

    @Test
    public void testSetTrueValid() {
        try {
            fValidator.setFeature(UNPARSED_ENTITY_CHECKING, true);
        } catch (SAXException e) {
            fail("Error setting feature.");
        }
        try {
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        checkDefault();
    }

    @Test
    public void testDefaultInvalid() {
        ((PSVIElementNSImpl) fRootNode).setAttributeNS(null, "unparsedEntityAttr", "invalid");
        try {
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        checkInvalid();
    }

    @Test
    public void testSetFalseInvalid() {
        ((PSVIElementNSImpl) fRootNode).setAttributeNS(null, "unparsedEntityAttr", "invalid");
        try {
            fValidator.setFeature(UNPARSED_ENTITY_CHECKING, false);
        } catch (SAXException e) {
            fail("Error setting feature.");
        }
        try {
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        checkDefault();
    }

    @Test
    public void testSetTrueInvalid() {
        ((PSVIElementNSImpl) fRootNode).setAttributeNS(null, "unparsedEntityAttr", "invalid");
        try {
            fValidator.setFeature(UNPARSED_ENTITY_CHECKING, true);
        } catch (SAXException e) {
            fail("Error setting feature.");
        }
        try {
            validateDocument();
        } catch (Exception e) {
            fail("Validation failed: " + e.getMessage());
        }

        checkInvalid();
    }

    private void checkDefault() {
        assertNoError(UNDECLARED_ENTITY);
        assertValidity(ItemPSVI.VALIDITY_VALID, fRootNode.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, fRootNode.getValidationAttempted());
        assertElementName("A", fRootNode.getElementDeclaration().getName());
        assertTypeName("X", fRootNode.getTypeDefinition().getName());
    }

    private void checkInvalid() {
        assertError(UNDECLARED_ENTITY);
        assertValidity(ItemPSVI.VALIDITY_INVALID, fRootNode.getValidity());
        assertValidationAttempted(ItemPSVI.VALIDATION_FULL, fRootNode.getValidationAttempted());
        assertElementName("A", fRootNode.getElementDeclaration().getName());
        assertTypeName("X", fRootNode.getTypeDefinition().getName());
    }
}
