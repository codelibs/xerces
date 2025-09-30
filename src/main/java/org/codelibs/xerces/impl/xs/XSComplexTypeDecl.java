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

import org.codelibs.xerces.impl.dv.XSSimpleType;
import org.codelibs.xerces.impl.dv.xs.XSSimpleTypeDecl;
import org.codelibs.xerces.impl.xs.models.CMBuilder;
import org.codelibs.xerces.impl.xs.models.XSCMValidator;
import org.codelibs.xerces.impl.xs.util.XSObjectListImpl;
import org.codelibs.xerces.xs.XSAttributeUse;
import org.codelibs.xerces.xs.XSComplexTypeDefinition;
import org.codelibs.xerces.xs.XSConstants;
import org.codelibs.xerces.xs.XSNamespaceItem;
import org.codelibs.xerces.xs.XSObjectList;
import org.codelibs.xerces.xs.XSParticle;
import org.codelibs.xerces.xs.XSSimpleTypeDefinition;
import org.codelibs.xerces.xs.XSTypeDefinition;
import org.codelibs.xerces.xs.XSWildcard;
import org.w3c.dom.TypeInfo;

/**
 * XML Schema complex type declaration implementation.
 * This class represents a complex type definition as specified in XML Schema.
 *
 * @author Elena Litani, IBM
 * @author Sandy Gao, IBM
 * @version $Id: XSComplexTypeDecl.java 1151128 2011-07-26 12:31:06Z knoaman $
 */
public class XSComplexTypeDecl implements XSComplexTypeDefinition, TypeInfo {

    /** Name of the complex type */
    public String fName = null;
    /** Target namespace of the complex type */
    public String fTargetNamespace = null;
    /** Base type of the complex type */
    public XSTypeDefinition fBaseType = null;
    /** Derivation method of the complex type */
    public short fDerivedBy = XSConstants.DERIVATION_RESTRICTION;
    /** Final set of the complex type */
    public short fFinal = XSConstants.DERIVATION_NONE;
    /** Block set (prohibited substitution) of the complex type */
    public short fBlock = XSConstants.DERIVATION_NONE;
    /** Miscellaneous flags: whether abstract, contains ID type, has anonymous type */
    public short fMiscFlags = 0;
    /** The attribute group belonging to the complex type */
    public XSAttributeGroupDecl fAttrGrp = null;
    /** The content type of the complex type */
    public short fContentType = CONTENTTYPE_EMPTY;
    /** Simple type for the content when content type is CONTENTTYPE_SIMPLE */
    public XSSimpleType fXSSimpleType = null;
    /** Particle for CONTENTTYPE_ELEMENT or CONTENTTYPE_MIXED content types */
    public XSParticleDecl fParticle = null;
    /** Content model validator for particle validation */
    public volatile XSCMValidator fCMValidator = null;
    /** UPA (Unique Particle Attribution) content model validator */
    public volatile XSCMValidator fUPACMValidator = null;
    /** Annotations associated with the complex type */
    public XSObjectListImpl fAnnotations = null;

    /** The namespace schema information item corresponding to the target namespace */
    public XSNamespaceItem fNamespaceItem = null;

    // record derivation method restriction
    static final int DERIVATION_ANY = 0;
    static final int DERIVATION_RESTRICTION = 1;
    static final int DERIVATION_EXTENSION = 2;
    static final int DERIVATION_UNION = 4;
    static final int DERIVATION_LIST = 8;

    /**
     * Default constructor for complex type declaration.
     */
    public XSComplexTypeDecl() {
        // do-nothing constructor for now.
    }

    /**
     * Sets the values for this complex type declaration.
     * @param name the name of the complex type
     * @param targetNamespace the target namespace
     * @param baseType the base type definition
     * @param derivedBy the derivation method
     * @param schemaFinal the final set
     * @param block the block set
     * @param contentType the content type
     * @param isAbstract whether the type is abstract
     * @param attrGrp the attribute group
     * @param simpleType the simple type content (if any)
     * @param particle the particle (if any)
     * @param annotations the annotations
     */
    public void setValues(String name, String targetNamespace, XSTypeDefinition baseType, short derivedBy, short schemaFinal, short block,
            short contentType, boolean isAbstract, XSAttributeGroupDecl attrGrp, XSSimpleType simpleType, XSParticleDecl particle,
            XSObjectListImpl annotations) {
        fTargetNamespace = targetNamespace;
        fBaseType = baseType;
        fDerivedBy = derivedBy;
        fFinal = schemaFinal;
        fBlock = block;
        fContentType = contentType;
        if (isAbstract)
            fMiscFlags |= CT_IS_ABSTRACT;
        fAttrGrp = attrGrp;
        fXSSimpleType = simpleType;
        fParticle = particle;
        fAnnotations = annotations;
    }

    /**
     * Sets the name of this complex type.
     * @param name the name to set
     */
    public void setName(String name) {
        fName = name;
    }

    public short getTypeCategory() {
        return COMPLEX_TYPE;
    }

    public String getTypeName() {
        return fName;
    }

    /**
     * Gets the final set for this complex type.
     * @return the final set
     */
    public short getFinalSet() {
        return fFinal;
    }

    /**
     * Gets the target namespace of this complex type.
     * @return the target namespace
     */
    public String getTargetNamespace() {
        return fTargetNamespace;
    }

    // flags for the misc flag
    private static final short CT_IS_ABSTRACT = 1;
    private static final short CT_HAS_TYPE_ID = 2;
    private static final short CT_IS_ANONYMOUS = 4;

    /**
     * Checks if this complex type contains an ID type.
     * @return true if it contains ID type, false otherwise
     */
    // methods to get/set misc flag
    public boolean containsTypeID() {
        return ((fMiscFlags & CT_HAS_TYPE_ID) != 0);
    }

    /**
     * Marks this complex type as abstract.
     */
    public void setIsAbstractType() {
        fMiscFlags |= CT_IS_ABSTRACT;
    }

    /**
     * Marks this complex type as containing an ID type.
     */
    public void setContainsTypeID() {
        fMiscFlags |= CT_HAS_TYPE_ID;
    }

    /**
     * Marks this complex type as anonymous.
     */
    public void setIsAnonymous() {
        fMiscFlags |= CT_IS_ANONYMOUS;
    }

    /**
     * Gets the content model validator for this complex type.
     * @param cmBuilder the content model builder
     * @return the content model validator
     */
    public XSCMValidator getContentModel(CMBuilder cmBuilder) {
        return getContentModel(cmBuilder, false);
    }

    /**
     * Gets the content model validator for this complex type.
     * @param cmBuilder the content model builder
     * @param forUPA whether this is for UPA checking
     * @return the content model validator
     */
    public synchronized XSCMValidator getContentModel(CMBuilder cmBuilder, boolean forUPA) {
        if (fCMValidator == null) {
            if (forUPA) {
                if (fUPACMValidator == null) {
                    fUPACMValidator = cmBuilder.getContentModel(this, true);
                    if (fUPACMValidator != null && !fUPACMValidator.isCompactedForUPA()) {
                        fCMValidator = fUPACMValidator;
                    }
                }
                return fUPACMValidator;
            } else {
                fCMValidator = cmBuilder.getContentModel(this, false);
            }
        }
        return fCMValidator;
    }

    /**
     * Gets the attribute group for this complex type.
     * @return the attribute group
     */
    // some utility methods:
    // return the attribute group for this complex type
    public XSAttributeGroupDecl getAttrGrp() {
        return fAttrGrp;
    }

    public String toString() {
        final StringBuilder str = new StringBuilder();
        appendTypeInfo(str);
        return str.toString();
    }

    void appendTypeInfo(StringBuilder str) {
        final String contentType[] = { "EMPTY", "SIMPLE", "ELEMENT", "MIXED" };
        final String derivedBy[] = { "EMPTY", "EXTENSION", "RESTRICTION" };

        str.append("Complex type name='" + fTargetNamespace + "," + getTypeName() + "', ");
        if (fBaseType != null) {
            str.append(" base type name='" + fBaseType.getName() + "', ");
        }
        str.append(" content type='" + contentType[fContentType] + "', ");
        str.append(" isAbstract='" + getAbstract() + "', ");
        str.append(" hasTypeId='" + containsTypeID() + "', ");
        str.append(" final='" + fFinal + "', ");
        str.append(" block='" + fBlock + "', ");
        if (fParticle != null) {
            str.append(" particle='" + fParticle.toString() + "', ");
        }
        str.append(" derivedBy='" + derivedBy[fDerivedBy] + "'. ");

    }

    public boolean derivedFromType(XSTypeDefinition ancestorType, short derivationMethod) {
        // ancestor is null, retur false
        if (ancestorType == null)
            return false;
        // ancestor is anyType, return true
        if (ancestorType == SchemaGrammar.fAnyType)
            return true;
        // recursively get base, and compare it with ancestor
        XSTypeDefinition type = this;
        while (type != ancestorType && // compare with ancestor
                type != SchemaGrammar.fAnySimpleType && // reached anySimpleType
                type != SchemaGrammar.fAnyType) { // reached anyType
            type = type.getBaseType();
        }

        return type == ancestorType;
    }

    public boolean derivedFrom(String ancestorNS, String ancestorName, short derivationMethod) {
        // ancestor is null, retur false
        if (ancestorName == null)
            return false;
        // ancestor is anyType, return true
        if (ancestorNS != null && ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA)
                && ancestorName.equals(SchemaSymbols.ATTVAL_ANYTYPE)) {
            return true;
        }

        // recursively get base, and compare it with ancestor
        XSTypeDefinition type = this;
        while (!(ancestorName.equals(type.getName())
                && ((ancestorNS == null && type.getNamespace() == null) || (ancestorNS != null && ancestorNS.equals(type.getNamespace()))))
                && // compare with ancestor
                type != SchemaGrammar.fAnySimpleType && // reached anySimpleType
                type != SchemaGrammar.fAnyType) { // reached anyType
            type = type.getBaseType();
        }

        return type != SchemaGrammar.fAnySimpleType && type != SchemaGrammar.fAnyType;
    }

    /**
     * Checks if this type is derived from the given type.
     * DOM Level 3 typeInfo interface.
     *
     * Check if this type is derived from the given type.
     * @param typeNamespaceArg
     *      The namspace of the ancestor type declaration
     * @param typeNameArg
     *      The name of the ancestor type declaration
     * @param derivationMethod
     *      The derivation method
     *
     * @return boolean True if this type is derived from
     *      <code>ancestorType</code> using only derivation methods from the
     *      <code>derivationMethod</code>.
     */
    public boolean isDOMDerivedFrom(String typeNamespaceArg, String typeNameArg, int derivationMethod) {
        // ancestor is null, retur false
        if (typeNameArg == null)
            return false;

        // ancestor is anyType, return true
        if (typeNamespaceArg != null && typeNamespaceArg.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA)
                && typeNameArg.equals(SchemaSymbols.ATTVAL_ANYTYPE)
                && (derivationMethod == DERIVATION_RESTRICTION && derivationMethod == DERIVATION_EXTENSION)) {
            return true;
        }

        // restriction
        if ((derivationMethod & DERIVATION_RESTRICTION) != 0) {
            if (isDerivedByRestriction(typeNamespaceArg, typeNameArg, derivationMethod, this)) {
                return true;
            }
        }

        // extension
        if ((derivationMethod & DERIVATION_EXTENSION) != 0) {
            if (isDerivedByExtension(typeNamespaceArg, typeNameArg, derivationMethod, this)) {
                return true;
            }
        }

        // list
        if ((((derivationMethod & DERIVATION_LIST) != 0) || (((derivationMethod & DERIVATION_UNION) != 0)))
                && (((derivationMethod & DERIVATION_RESTRICTION) == 0) && ((derivationMethod & DERIVATION_EXTENSION) == 0))) {

            if (typeNamespaceArg.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && typeNameArg.equals(SchemaSymbols.ATTVAL_ANYTYPE)) {
                typeNameArg = SchemaSymbols.ATTVAL_ANYSIMPLETYPE;
            }

            if (!fName.equals(SchemaSymbols.ATTVAL_ANYSIMPLETYPE) && fTargetNamespace.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA)) {
                if (fBaseType != null && fBaseType instanceof XSSimpleTypeDecl) {

                    return ((XSSimpleTypeDecl) fBaseType).isDOMDerivedFrom(typeNamespaceArg, typeNameArg, derivationMethod);
                } else if (fBaseType != null && fBaseType instanceof XSComplexTypeDecl) {
                    return ((XSComplexTypeDecl) fBaseType).isDOMDerivedFrom(typeNamespaceArg, typeNameArg, derivationMethod);
                }
            }
        }

        // If the value of the parameter is 0 i.e. no specific derivation
        // methods are specified then the function should return true if the
        // type is derived from the ancestor type using any combination of
        // derivation methods.
        if (((derivationMethod & DERIVATION_RESTRICTION) == 0) && (((derivationMethod & DERIVATION_EXTENSION) == 0))
                && (((derivationMethod & DERIVATION_LIST) == 0) && ((derivationMethod & DERIVATION_UNION) == 0))) {
            return isDerivedByAny(typeNamespaceArg, typeNameArg, derivationMethod, this);
        }

        return false;
    }

    /**
     * Checks if there exists a derivation between this type and the
     * ancestor type
     * using any combination of derivation methods.
     *
     * @param ancestorNS
     *      The namspace of the ancestor type declaration
     * @param ancestorName
     *      The name of the ancestor type declaration
     * @param derivationMethod
     *      A short indication the method of derivation
     * @param type
     *      The type definition
     *
     * @return boolean True if <code>type</code> is derived from
     *      <code>ancestorType</code> using any combination of derivation
     *      methods.
     */
    private boolean isDerivedByAny(String ancestorNS, String ancestorName, int derivationMethod, XSTypeDefinition type) {
        XSTypeDefinition oldType = null;
        boolean derivedFrom = false;
        while (type != null && type != oldType) {

            // If the ancestor type is reached or is the same as this type.
            if ((ancestorName.equals(type.getName())) && (ancestorNS == null && type.getNamespace() == null
                    || ancestorNS != null && ancestorNS.equals(type.getNamespace()))) {
                derivedFrom = true;
                break;
            }

            // Check if this type is derived from the base by restriction or
            // extension
            if (isDerivedByRestriction(ancestorNS, ancestorName, derivationMethod, type)) {
                return true;
            } else if (!isDerivedByExtension(ancestorNS, ancestorName, derivationMethod, type)) {
                return true;
            }
            oldType = type;
            type = type.getBaseType();
        }

        return derivedFrom;
    }

    /**
     * Checks if this type is derived by restriction from the given
     * ancestor type.
     * @param ancestorNS the namespace of the ancestor type
     * @param ancestorName the name of the ancestor type
     * @param derivationMethod the derivation method
     * @param type the type to check
     * @return true if derived by restriction
     */
    private boolean isDerivedByRestriction(String ancestorNS, String ancestorName, int derivationMethod, XSTypeDefinition type) {

        XSTypeDefinition oldType = null;
        while (type != null && type != oldType) {

            if (ancestorNS != null && ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA)
                    && ancestorName.equals(SchemaSymbols.ATTVAL_ANYSIMPLETYPE)) {
                return false;
            }

            // ancestor is anyType, return false
            if ((ancestorName.equals(type.getName()) && ancestorNS != null && ancestorNS.equals(type.getNamespace()))
                    || (type.getNamespace() == null && ancestorNS == null)) {

                return true;
            }

            // If the base type is a complexType with simpleContent
            if (type instanceof XSComplexTypeDecl) {
                if (((XSComplexTypeDecl) type).getDerivationMethod() == XSConstants.DERIVATION_RESTRICTION) {
                    type = type.getBaseType();
                }
            } else {
                type = type.getBaseType();
            }
            oldType = type;
        }

        return false;
    }

    /**
     * Checks if this type is derived by extension from the given
     * ancestor type.
     * @param ancestorNS the namespace of the ancestor type
     * @param ancestorName the name of the ancestor type
     * @param derivationMethod the derivation method
     * @param type the type to check
     * @return true if derived by extension
     */
    private boolean isDerivedByExtension(String ancestorNS, String ancestorName, int derivationMethod, XSTypeDefinition type) {

        boolean extension = false;
        XSTypeDefinition oldType = null;
        while (type != null && type != oldType) {
            if (ancestorNS != null && ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA)
                    && ancestorName.equals(SchemaSymbols.ATTVAL_ANYSIMPLETYPE)
                    && SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(type.getNamespace())
                    && SchemaSymbols.ATTVAL_ANYTYPE.equals(type.getName())) {
                break;
            }

            if ((ancestorName.equals(type.getName())) && ((ancestorNS == null && type.getNamespace() == null)
                    || (ancestorNS != null && ancestorNS.equals(type.getNamespace())))) {
                // returns true if atleast one derivation step was extension
                return extension;
            }

            // If the base type is a complexType with simpleContent
            if (type instanceof XSComplexTypeDecl) {
                if (((XSComplexTypeDecl) type).getDerivationMethod() == XSConstants.DERIVATION_EXTENSION) {
                    extension = extension | true;
                }
            }
            oldType = type;
            type = type.getBaseType();
        }

        return false;
    }

    /**
     * Resets this complex type declaration to its initial state.
     */
    public void reset() {
        fName = null;
        fTargetNamespace = null;
        fBaseType = null;
        fDerivedBy = XSConstants.DERIVATION_RESTRICTION;
        fFinal = XSConstants.DERIVATION_NONE;
        fBlock = XSConstants.DERIVATION_NONE;

        fMiscFlags = 0;

        // reset attribute group
        fAttrGrp = null;
        fContentType = CONTENTTYPE_EMPTY;
        fXSSimpleType = null;
        fParticle = null;
        fCMValidator = null;
        fUPACMValidator = null;
        if (fAnnotations != null) {
            // help out the garbage collector
            fAnnotations.clearXSObjectList();
        }
        fAnnotations = null;
    }

    /**
     * Get the type of the object, i.e ELEMENT_DECLARATION.
     */
    public short getType() {
        return XSConstants.TYPE_DEFINITION;
    }

    /**
     * The <code>name</code> of this <code>XSObject</code> depending on the
     * <code>XSObject</code> type.
     */
    public String getName() {
        return getAnonymous() ? null : fName;
    }

    /**
     * A boolean that specifies if the type definition is anonymous.
     * Convenience attribute. This is a field is not part of
     * XML Schema component model.
     */
    public boolean getAnonymous() {
        return ((fMiscFlags & CT_IS_ANONYMOUS) != 0);
    }

    /**
     * The namespace URI of this node, or <code>null</code> if it is
     * unspecified.  defines how a namespace URI is attached to schema
     * components.
     */
    public String getNamespace() {
        return fTargetNamespace;
    }

    /**
     * {base type definition} Either a simple type definition or a complex
     * type definition.
     */
    public XSTypeDefinition getBaseType() {
        return fBaseType;
    }

    /**
     * {derivation method} Either extension or restriction. The valid constant
     * value for this <code>XSComplexTypeDefinition</code>.
     */
    public short getDerivationMethod() {
        return fDerivedBy;
    }

    /**
     * {final} For complex type definition it is a subset of {extension, restriction}.
     * @param derivation  Extension, restriction, or none. Represents derivation
     *   type being the extension, restriction or none.
     * @return True if derivation is in the final set, otherwise false.
     */
    public boolean isFinal(short derivation) {
        return (fFinal & derivation) != 0;
    }

    /**
     * {final} For complex type definition it is a subset of {extension,
     * restriction}.
     *
     * @return A bit flag that represents:
     *         {extension, restriction) or none for complexTypes;
     *         {extension, list, restriction, union} or none for simpleTypes;
     */
    public short getFinal() {
        return fFinal;
    }

    /**
     * {abstract} A boolean. Complex types for which {abstract} is true must
     * not be used as the {type definition} for the validation of element
     * information items.
     */
    public boolean getAbstract() {
        return ((fMiscFlags & CT_IS_ABSTRACT) != 0);
    }

    /**
     *  {attribute uses} A set of attribute uses.
     */
    public XSObjectList getAttributeUses() {
        return fAttrGrp.getAttributeUses();
    }

    /**
     * {attribute wildcard} Optional. A wildcard.
     */
    public XSWildcard getAttributeWildcard() {
        return fAttrGrp.getAttributeWildcard();
    }

    /**
     * {content type} One of empty, a simple type definition (see
     * <code>simpleType</code>, mixed, element-only (see
     * <code>cmParticle</code>).
     */
    public short getContentType() {
        return fContentType;
    }

    /**
     * A simple type definition corresponding to simple content model,
     * otherwise <code>null</code>
     */
    public XSSimpleTypeDefinition getSimpleType() {
        return fXSSimpleType;
    }

    /**
     * A particle for mixed or element-only content model, otherwise
     * <code>null</code>
     */
    public XSParticle getParticle() {
        return fParticle;
    }

    /**
     * {prohibited substitutions} A subset of {extension, restriction}.
     * @param prohibited  extention or restriction constants (defined in
     *   <code>XSConstants</code>).
     * @return True if prohibited is a prohibited substitution, otherwise
     *   false.
     */
    public boolean isProhibitedSubstitution(short prohibited) {
        return (fBlock & prohibited) != 0;
    }

    /**
     * {prohibited substitutions}
     *
     * @return A bit flag corresponding to prohibited substitutions
     */
    public short getProhibitedSubstitutions() {
        return fBlock;
    }

    /**
     * Optional. Annotation.
     */
    public XSObjectList getAnnotations() {
        return (fAnnotations != null) ? fAnnotations : XSObjectListImpl.EMPTY_LIST;
    }

    /**
     *
     * @see org.codelibs.xerces.xs.XSObject#getNamespaceItem()
     */
    public XSNamespaceItem getNamespaceItem() {
        return fNamespaceItem;
    }

    /* (non-Javadoc)
     * @see org.codelibs.xerces.xs.XSComplexTypeDecl#setNamespaceItem(org.codelibs.xerces.xs.XSNamespaceItem)
     */
    void setNamespaceItem(XSNamespaceItem namespaceItem) {
        fNamespaceItem = namespaceItem;
    }

    /**
     * Gets the attribute use for the specified attribute.
     * @param namespace the namespace of the attribute
     * @param name the name of the attribute
     * @return the attribute use, or null if not found
     */
    /* (non-Javadoc)
     * @see org.codelibs.xerces.xs.XSComplexTypeDefinition#getAttributeUse(java.lang.String, java.lang.String)
     */
    public XSAttributeUse getAttributeUse(String namespace, String name) {
        return fAttrGrp.getAttributeUse(namespace, name);
    }

    public String getTypeNamespace() {
        return getNamespace();
    }

    public boolean isDerivedFrom(String typeNamespaceArg, String typeNameArg, int derivationMethod) {
        return isDOMDerivedFrom(typeNamespaceArg, typeNameArg, derivationMethod);
    }

}
