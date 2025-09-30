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

package org.codelibs.xerces.xml.serialize;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 * Abstract factory for creating serializers for different output methods.
 * This factory provides a registry mechanism for serializer implementations.
 *
 * @deprecated This class was deprecated in Xerces 2.9.0. It is recommended
 * that new applications use the DOM Level 3 LSSerializer or JAXP's Transformation
 * API for XML (TrAX) for serializing XML and HTML. See the Xerces documentation for more
 * information.
 * @version $Revision: 699892 $ $Date: 2008-09-29 02:38:27 +0530 (Mon, 29 Sep 2008) $
 * @author <a href="mailto:Scott_Boag/CAM/Lotus@lotus.com">Scott Boag</a>
 * @author <a href="mailto:arkin@intalio.com">Assaf Arkin</a>
 */
public abstract class SerializerFactory {

    /**
     * Default constructor.
     */
    public SerializerFactory() {
    }

    /** System property name for specifying custom serializer factories. */
    public static final String FactoriesProperty = "org.codelibs.xerces.xml.serialize.factories";

    private static Hashtable _factories = new Hashtable();

    static {
        SerializerFactory factory;
        String list;
        StringTokenizer token;
        String className;

        // The default factories are always registered first,
        // any factory specified in the properties file and supporting
        // the same method will override the default factory.
        factory = new SerializerFactoryImpl(Method.XML);
        registerSerializerFactory(factory);
        factory = new SerializerFactoryImpl(Method.HTML);
        registerSerializerFactory(factory);
        factory = new SerializerFactoryImpl(Method.XHTML);
        registerSerializerFactory(factory);
        factory = new SerializerFactoryImpl(Method.TEXT);
        registerSerializerFactory(factory);

        list = SecuritySupport.getSystemProperty(FactoriesProperty);
        if (list != null) {
            token = new StringTokenizer(list, " ;,:");
            while (token.hasMoreTokens()) {
                className = token.nextToken();
                try {
                    factory = (SerializerFactory) ObjectFactory.newInstance(className, SerializerFactory.class.getClassLoader(), true);
                    if (_factories.containsKey(factory.getSupportedMethod()))
                        _factories.put(factory.getSupportedMethod(), factory);
                } catch (Exception except) {}
            }
        }
    }

    /**
     * Registers a serializer factory, keyed by the given method string.
     *
     * @param factory The serializer factory to register.
     */
    public static void registerSerializerFactory(SerializerFactory factory) {
        String method;

        synchronized (_factories) {
            method = factory.getSupportedMethod();
            _factories.put(method, factory);
        }
    }

    /**
     * Gets a registered serializer factory by method string.
     *
     * @param method The method string identifying the factory.
     * @return The serializer factory for the specified method, or null if not found.
     */
    public static SerializerFactory getSerializerFactory(String method) {
        return (SerializerFactory) _factories.get(method);
    }

    /**
     * Returns the method supported by this factory and used to register
     * the factory. This call is required so factories can be added from
     * a properties file by knowing only the class name. This method is
     * protected, it is only required by this class but must be implemented
     * in derived classes.
     *
     * @return The method string supported by this factory.
     */
    protected abstract String getSupportedMethod();

    /**
     * Creates a new serializer based on the {@link OutputFormat}.
     * If this method is used to create the serializer, the {@link
     * Serializer#setOutputByteStream} or {@link Serializer#setOutputCharStream}
     * methods must be called before serializing a document.
     *
     * @param format The output format configuration.
     * @return A new serializer instance.
     */
    public abstract Serializer makeSerializer(OutputFormat format);

    /**
     * Creates a new serializer, based on the {@link OutputFormat} and
     * using the writer as the output character stream.  If this
     * method is used, the encoding property will be ignored.
     *
     * @param writer The output character stream.
     * @param format The output format configuration.
     * @return A new serializer instance.
     */
    public abstract Serializer makeSerializer(Writer writer, OutputFormat format);

    /**
     * Creates a new serializer, based on the {@link OutputFormat} and
     * using the output byte stream and the encoding specified in the
     * output format.
     *
     * @param output The output byte stream.
     * @param format The output format configuration.
     * @return A new serializer instance.
     * @throws UnsupportedEncodingException If the specified encoding is not supported.
     */
    public abstract Serializer makeSerializer(OutputStream output, OutputFormat format) throws UnsupportedEncodingException;

}
