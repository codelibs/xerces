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

package org.codelibs.xerces.impl.xs;

/**
 * Collection of symbols used to parse a Schema Grammar.
 *

 *
 * @author jeffrey rodriguez
 * @version $Id: SchemaSymbols.java 446734 2006-09-15 20:51:23Z mrglavas $
 */
public final class SchemaSymbols {

    // strings that's not added to the schema symbol table, because they
    // are not symbols in the schema document.
    // the validator can choose to add them by itself.

    // the following strings (xsi:, xsd) will be added into the
    // symbol table that comes with the parser

    // xsi attributes: in validator
    /** XSI namespace URI constant. */
    public static final String URI_XSI = "http://www.w3.org/2001/XMLSchema-instance".intern();
    /** XSI schemaLocation attribute name. */
    public static final String XSI_SCHEMALOCATION = "schemaLocation".intern();
    /** XSI noNamespaceSchemaLocation attribute name. */
    public static final String XSI_NONAMESPACESCHEMALOCATION = "noNamespaceSchemaLocation".intern();
    /** XSI type attribute name. */
    public static final String XSI_TYPE = "type".intern();
    /** XSI nil attribute name. */
    public static final String XSI_NIL = "nil".intern();

    // schema namespace
    /** XML Schema namespace URI constant. */
    public static final String URI_SCHEMAFORSCHEMA = "http://www.w3.org/2001/XMLSchema".intern();

    // all possible schema element names
    /** XML Schema element name constant: "all". */
    public static final String ELT_ALL = "all".intern();
    /** XML Schema element name constant: "annotation". */
    public static final String ELT_ANNOTATION = "annotation".intern();
    /** XML Schema element name constant: "any". */
    public static final String ELT_ANY = "any".intern();
    /** XML Schema element name constant: "anyAttribute". */
    public static final String ELT_ANYATTRIBUTE = "anyAttribute".intern();
    /** XML Schema element name constant: "appinfo". */
    public static final String ELT_APPINFO = "appinfo".intern();
    /** XML Schema element name constant: "attribute". */
    public static final String ELT_ATTRIBUTE = "attribute".intern();
    /** XML Schema element name constant: "attributeGroup". */
    public static final String ELT_ATTRIBUTEGROUP = "attributeGroup".intern();
    /** XML Schema element name constant: "choice". */
    public static final String ELT_CHOICE = "choice".intern();
    /** XML Schema element name constant: "complexContent". */
    public static final String ELT_COMPLEXCONTENT = "complexContent".intern();
    /** XML Schema element name constant: "complexType". */
    public static final String ELT_COMPLEXTYPE = "complexType".intern();
    /** XML Schema element name constant: "documentation". */
    public static final String ELT_DOCUMENTATION = "documentation".intern();
    /** XML Schema element name constant: "element". */
    public static final String ELT_ELEMENT = "element".intern();
    /** XML Schema element name constant: "enumeration". */
    public static final String ELT_ENUMERATION = "enumeration".intern();
    /** XML Schema element name constant: "extension". */
    public static final String ELT_EXTENSION = "extension".intern();
    /** XML Schema element name constant: "field". */
    public static final String ELT_FIELD = "field".intern();
    /** XML Schema element name constant: "fractionDigits". */
    public static final String ELT_FRACTIONDIGITS = "fractionDigits".intern();
    /** XML Schema element name constant: "group". */
    public static final String ELT_GROUP = "group".intern();
    /** XML Schema element name constant: "import". */
    public static final String ELT_IMPORT = "import".intern();
    /** XML Schema element name constant: "include". */
    public static final String ELT_INCLUDE = "include".intern();
    /** XML Schema element name constant: "key". */
    public static final String ELT_KEY = "key".intern();
    /** XML Schema element name constant: "keyref". */
    public static final String ELT_KEYREF = "keyref".intern();
    /** XML Schema element name constant: "length". */
    public static final String ELT_LENGTH = "length".intern();
    /** XML Schema element name constant: "list". */
    public static final String ELT_LIST = "list".intern();
    /** XML Schema element name constant: "maxExclusive". */
    public static final String ELT_MAXEXCLUSIVE = "maxExclusive".intern();
    /** XML Schema element name constant: "maxInclusive". */
    public static final String ELT_MAXINCLUSIVE = "maxInclusive".intern();
    /** XML Schema element name constant: "maxLength". */
    public static final String ELT_MAXLENGTH = "maxLength".intern();
    /** XML Schema element name constant: "minExclusive". */
    public static final String ELT_MINEXCLUSIVE = "minExclusive".intern();
    /** XML Schema element name constant: "minInclusive". */
    public static final String ELT_MININCLUSIVE = "minInclusive".intern();
    /** XML Schema element name constant: "minLength". */
    public static final String ELT_MINLENGTH = "minLength".intern();
    /** XML Schema element name constant: "notation". */
    public static final String ELT_NOTATION = "notation".intern();
    /** XML Schema element name constant: "pattern". */
    public static final String ELT_PATTERN = "pattern".intern();
    /** XML Schema element name constant: "redefine". */
    public static final String ELT_REDEFINE = "redefine".intern();
    /** XML Schema element name constant: "restriction". */
    public static final String ELT_RESTRICTION = "restriction".intern();
    /** XML Schema element name constant: "schema". */
    public static final String ELT_SCHEMA = "schema".intern();
    /** XML Schema element name constant: "selector". */
    public static final String ELT_SELECTOR = "selector".intern();
    /** XML Schema element name constant: "sequence". */
    public static final String ELT_SEQUENCE = "sequence".intern();
    /** XML Schema element name constant: "simpleContent". */
    public static final String ELT_SIMPLECONTENT = "simpleContent".intern();
    /** XML Schema element name constant: "simpleType". */
    public static final String ELT_SIMPLETYPE = "simpleType".intern();
    /** XML Schema element name constant: "totalDigits". */
    public static final String ELT_TOTALDIGITS = "totalDigits".intern();
    /** XML Schema element name constant: "union". */
    public static final String ELT_UNION = "union".intern();
    /** XML Schema element name constant: "unique". */
    public static final String ELT_UNIQUE = "unique".intern();
    /** XML Schema element name constant: "whiteSpace". */
    public static final String ELT_WHITESPACE = "whiteSpace".intern();

    /**
     * XML Schema attribute name constant: "abstract".
     * Defines whether a complex type or element is abstract.
     */
    public static final String ATT_ABSTRACT = "abstract".intern();
    /** XML Schema attribute name constant: "attributeFormDefault". */
    public static final String ATT_ATTRIBUTEFORMDEFAULT = "attributeFormDefault".intern();
    /** XML Schema attribute name constant: "base". */
    public static final String ATT_BASE = "base".intern();
    /** XML Schema attribute name constant: "block". */
    public static final String ATT_BLOCK = "block".intern();
    /** XML Schema attribute name constant: "blockDefault". */
    public static final String ATT_BLOCKDEFAULT = "blockDefault".intern();
    /** XML Schema attribute name constant: "default". */
    public static final String ATT_DEFAULT = "default".intern();
    /** XML Schema attribute name constant: "elementFormDefault". */
    public static final String ATT_ELEMENTFORMDEFAULT = "elementFormDefault".intern();
    /** XML Schema attribute name constant: "final". */
    public static final String ATT_FINAL = "final".intern();
    /** XML Schema attribute name constant: "finalDefault". */
    public static final String ATT_FINALDEFAULT = "finalDefault".intern();
    /** XML Schema attribute name constant: "fixed". */
    public static final String ATT_FIXED = "fixed".intern();
    /** XML Schema attribute name constant: "form". */
    public static final String ATT_FORM = "form".intern();
    /** XML Schema attribute name constant: "id". */
    public static final String ATT_ID = "id".intern();
    /** XML Schema attribute name constant: "itemType". */
    public static final String ATT_ITEMTYPE = "itemType".intern();
    /** XML Schema attribute name constant: "maxOccurs". */
    public static final String ATT_MAXOCCURS = "maxOccurs".intern();
    /** XML Schema attribute name constant: "memberTypes". */
    public static final String ATT_MEMBERTYPES = "memberTypes".intern();
    /** XML Schema attribute name constant: "minOccurs". */
    public static final String ATT_MINOCCURS = "minOccurs".intern();
    /** XML Schema attribute name constant: "mixed". */
    public static final String ATT_MIXED = "mixed".intern();
    /** XML Schema attribute name constant: "name". */
    public static final String ATT_NAME = "name".intern();
    /** XML Schema attribute name constant: "namespace". */
    public static final String ATT_NAMESPACE = "namespace".intern();
    /** XML Schema attribute name constant: "nillable". */
    public static final String ATT_NILLABLE = "nillable".intern();
    /** XML Schema attribute name constant: "processContents". */
    public static final String ATT_PROCESSCONTENTS = "processContents".intern();
    /** XML Schema attribute name constant: "ref". */
    public static final String ATT_REF = "ref".intern();
    /** XML Schema attribute name constant: "refer". */
    public static final String ATT_REFER = "refer".intern();
    /** XML Schema attribute name constant: "schemaLocation". */
    public static final String ATT_SCHEMALOCATION = "schemaLocation".intern();
    /** XML Schema attribute name constant: "source". */
    public static final String ATT_SOURCE = "source".intern();
    /** XML Schema attribute name constant: "substitutionGroup". */
    public static final String ATT_SUBSTITUTIONGROUP = "substitutionGroup".intern();
    /** XML Schema attribute name constant: "system". */
    public static final String ATT_SYSTEM = "system".intern();
    /** XML Schema attribute name constant: "public". */
    public static final String ATT_PUBLIC = "public".intern();
    /** XML Schema attribute name constant: "targetNamespace". */
    public static final String ATT_TARGETNAMESPACE = "targetNamespace".intern();
    /** XML Schema attribute name constant: "type". */
    public static final String ATT_TYPE = "type".intern();
    /** XML Schema attribute name constant: "use". */
    public static final String ATT_USE = "use".intern();
    /** XML Schema attribute name constant: "value". */
    public static final String ATT_VALUE = "value".intern();
    /** XML Schema attribute name constant: "version". */
    public static final String ATT_VERSION = "version".intern();
    /** XML Schema attribute name constant: "xml:lang". */
    public static final String ATT_XML_LANG = "xml:lang".intern();
    /** XML Schema attribute name constant: "xpath". */
    public static final String ATT_XPATH = "xpath".intern();

    // all possible schema attribute values
    /** XML Schema attribute value constant: "##any". */
    public static final String ATTVAL_TWOPOUNDANY = "##any";
    /** XML Schema attribute value constant: "##local". */
    public static final String ATTVAL_TWOPOUNDLOCAL = "##local";
    /** XML Schema attribute value constant: "##other". */
    public static final String ATTVAL_TWOPOUNDOTHER = "##other";
    /** XML Schema attribute value constant: "##targetNamespace". */
    public static final String ATTVAL_TWOPOUNDTARGETNS = "##targetNamespace";
    /** XML Schema attribute value constant: "#all". */
    public static final String ATTVAL_POUNDALL = "#all";
    /** XML Schema attribute value constant: "0" (false). */
    public static final String ATTVAL_FALSE_0 = "0";
    /** XML Schema attribute value constant: "1" (true). */
    public static final String ATTVAL_TRUE_1 = "1";
    /** XML Schema built-in type name constant: "anySimpleType". */
    public static final String ATTVAL_ANYSIMPLETYPE = "anySimpleType";
    /** XML Schema built-in type name constant: "anyType". */
    public static final String ATTVAL_ANYTYPE = "anyType";
    /** XML Schema built-in type name constant: "anyURI". */
    public static final String ATTVAL_ANYURI = "anyURI";
    /** XML Schema built-in type name constant: "base64Binary". */
    public static final String ATTVAL_BASE64BINARY = "base64Binary";
    /** XML Schema built-in type name constant: "boolean". */
    public static final String ATTVAL_BOOLEAN = "boolean";
    /** XML Schema built-in type name constant: "byte". */
    public static final String ATTVAL_BYTE = "byte";
    /** XML Schema whitespace facet value constant: "collapse". */
    public static final String ATTVAL_COLLAPSE = "collapse";
    /** XML Schema built-in type name constant: "date". */
    public static final String ATTVAL_DATE = "date";
    /** XML Schema built-in type name constant: "dateTime". */
    public static final String ATTVAL_DATETIME = "dateTime";
    /** XML Schema built-in type name constant: "gDay". */
    public static final String ATTVAL_DAY = "gDay";
    /** XML Schema built-in type name constant: "decimal". */
    public static final String ATTVAL_DECIMAL = "decimal";
    /** XML Schema built-in type name constant: "double". */
    public static final String ATTVAL_DOUBLE = "double";
    /** XML Schema built-in type name constant: "duration". */
    public static final String ATTVAL_DURATION = "duration";
    /** XML Schema built-in type name constant: "ENTITY". */
    public static final String ATTVAL_ENTITY = "ENTITY";
    /** XML Schema built-in type name constant: "ENTITIES". */
    public static final String ATTVAL_ENTITIES = "ENTITIES";
    /** XML Schema derivation method constant: "extension". */
    public static final String ATTVAL_EXTENSION = "extension";
    /** XML Schema boolean value constant: "false". */
    public static final String ATTVAL_FALSE = "false";
    /** XML Schema built-in type name constant: "float". */
    public static final String ATTVAL_FLOAT = "float";
    /** XML Schema built-in type name constant: "hexBinary". */
    public static final String ATTVAL_HEXBINARY = "hexBinary";
    /** XML Schema built-in type name constant: "ID". */
    public static final String ATTVAL_ID = "ID";
    /** XML Schema built-in type name constant: "IDREF". */
    public static final String ATTVAL_IDREF = "IDREF";
    /** XML Schema built-in type name constant: "IDREFS". */
    public static final String ATTVAL_IDREFS = "IDREFS";
    /** XML Schema built-in type name constant: "int". */
    public static final String ATTVAL_INT = "int";
    /** XML Schema built-in type name constant: "integer". */
    public static final String ATTVAL_INTEGER = "integer";
    /** XML Schema built-in type name constant: "language". */
    public static final String ATTVAL_LANGUAGE = "language";
    /** XML Schema processContents value constant: "lax". */
    public static final String ATTVAL_LAX = "lax";
    /** XML Schema simple type variety constant: "list". */
    public static final String ATTVAL_LIST = "list";
    /** XML Schema built-in type name constant: "long". */
    public static final String ATTVAL_LONG = "long";
    /** XML Schema built-in type name constant: "Name". */
    public static final String ATTVAL_NAME = "Name";
    /** XML Schema built-in type name constant: "negativeInteger". */
    public static final String ATTVAL_NEGATIVEINTEGER = "negativeInteger";
    /** XML Schema built-in type name constant: "gMonth". */
    public static final String ATTVAL_MONTH = "gMonth";
    /** XML Schema built-in type name constant: "gMonthDay". */
    public static final String ATTVAL_MONTHDAY = "gMonthDay";
    /** XML Schema built-in type name constant: "NCName". */
    public static final String ATTVAL_NCNAME = "NCName";
    /** XML Schema built-in type name constant: "NMTOKEN". */
    public static final String ATTVAL_NMTOKEN = "NMTOKEN";
    /** XML Schema built-in type name constant: "NMTOKENS". */
    public static final String ATTVAL_NMTOKENS = "NMTOKENS";
    /** XML Schema built-in type name constant: "nonNegativeInteger". */
    public static final String ATTVAL_NONNEGATIVEINTEGER = "nonNegativeInteger";
    /** XML Schema built-in type name constant: "nonPositiveInteger". */
    public static final String ATTVAL_NONPOSITIVEINTEGER = "nonPositiveInteger";
    /** XML Schema built-in type name constant: "normalizedString". */
    public static final String ATTVAL_NORMALIZEDSTRING = "normalizedString";
    /** XML Schema built-in type name constant: "NOTATION". */
    public static final String ATTVAL_NOTATION = "NOTATION";
    /** XML Schema attribute use value constant: "optional". */
    public static final String ATTVAL_OPTIONAL = "optional";
    /** XML Schema built-in type name constant: "positiveInteger". */
    public static final String ATTVAL_POSITIVEINTEGER = "positiveInteger";
    /** XML Schema whitespace facet value constant: "preserve". */
    public static final String ATTVAL_PRESERVE = "preserve";
    /** XML Schema attribute use value constant: "prohibited". */
    public static final String ATTVAL_PROHIBITED = "prohibited";
    /** XML Schema built-in type name constant: "QName". */
    public static final String ATTVAL_QNAME = "QName";
    /** XML Schema form value constant: "qualified". */
    public static final String ATTVAL_QUALIFIED = "qualified";
    /** XML Schema whitespace facet value constant: "replace". */
    public static final String ATTVAL_REPLACE = "replace";
    /** XML Schema attribute use value constant: "required". */
    public static final String ATTVAL_REQUIRED = "required";
    /** XML Schema derivation method constant: "restriction". */
    public static final String ATTVAL_RESTRICTION = "restriction";
    /** XML Schema built-in type name constant: "short". */
    public static final String ATTVAL_SHORT = "short";
    /** XML Schema processContents value constant: "skip". */
    public static final String ATTVAL_SKIP = "skip";
    /** XML Schema processContents value constant: "strict". */
    public static final String ATTVAL_STRICT = "strict";
    /** XML Schema built-in type name constant: "string". */
    public static final String ATTVAL_STRING = "string";
    /** XML Schema block/final value constant: "substitution". */
    public static final String ATTVAL_SUBSTITUTION = "substitution";
    /** XML Schema built-in type name constant: "time". */
    public static final String ATTVAL_TIME = "time";
    /** XML Schema built-in type name constant: "token". */
    public static final String ATTVAL_TOKEN = "token";
    /** XML Schema boolean value constant: "true". */
    public static final String ATTVAL_TRUE = "true";
    /** XML Schema maxOccurs value constant: "unbounded". */
    public static final String ATTVAL_UNBOUNDED = "unbounded";
    /** XML Schema simple type variety constant: "union". */
    public static final String ATTVAL_UNION = "union";
    /** XML Schema form value constant: "unqualified". */
    public static final String ATTVAL_UNQUALIFIED = "unqualified";
    /** XML Schema built-in type name constant: "unsignedByte". */
    public static final String ATTVAL_UNSIGNEDBYTE = "unsignedByte";
    /** XML Schema built-in type name constant: "unsignedInt". */
    public static final String ATTVAL_UNSIGNEDINT = "unsignedInt";
    /** XML Schema built-in type name constant: "unsignedLong". */
    public static final String ATTVAL_UNSIGNEDLONG = "unsignedLong";
    /** XML Schema built-in type name constant: "unsignedShort". */
    public static final String ATTVAL_UNSIGNEDSHORT = "unsignedShort";
    /** XML Schema built-in type name constant: "gYear". */
    public static final String ATTVAL_YEAR = "gYear";
    /** XML Schema built-in type name constant: "gYearMonth". */
    public static final String ATTVAL_YEARMONTH = "gYearMonth";

    // form qualified/unqualified
    /** Form value for unqualified elements or attributes. */
    public static final short FORM_UNQUALIFIED = 0;
    /** Form value for qualified elements or attributes. */
    public static final short FORM_QUALIFIED = 1;

    // attribute use
    /** Attribute use value for optional attributes. */
    public static final short USE_OPTIONAL = 0;
    /** Attribute use value for required attributes. */
    public static final short USE_REQUIRED = 1;
    /** Attribute use value for prohibited attributes. */
    public static final short USE_PROHIBITED = 2;

    // maxOccurs = "unbounded"
    /** Constant representing unbounded occurrences. */
    public static final int OCCURRENCE_UNBOUNDED = -1;

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private SchemaSymbols() {
    }

}
