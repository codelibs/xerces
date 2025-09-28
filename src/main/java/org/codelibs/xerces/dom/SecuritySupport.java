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

package org.codelibs.xerces.dom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * This class is duplicated for each subpackage so keep it in sync.
 * It is package private and therefore is not exposed as part of any API.
 *
 * Updated for Java 17: Removed AccessController usage as it has been removed in Java 17.
 *
 * @xerces.internal
 *
 * @version $Id: SecuritySupport.java 950361 2010-06-02 04:12:35Z mrglavas $
 */
final class SecuritySupport {

    static ClassLoader getContextClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (SecurityException ex) {}
        return cl;
    }

    static ClassLoader getSystemClassLoader() {
        ClassLoader cl = null;
        try {
            cl = ClassLoader.getSystemClassLoader();
        } catch (SecurityException ex) {}
        return cl;
    }

    static ClassLoader getParentClassLoader(final ClassLoader cl) {
        ClassLoader parent = null;
        try {
            parent = cl.getParent();
        } catch (SecurityException ex) {}

        return (parent == cl) ? null : parent;
    }

    static String getSystemProperty(final String propName) {
        return System.getProperty(propName);
    }

    static FileInputStream getFileInputStream(final File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    static InputStream getResourceAsStream(final ClassLoader cl, final String name) {
        InputStream ris;
        if (cl == null) {
            ris = ClassLoader.getSystemResourceAsStream(name);
        } else {
            ris = cl.getResourceAsStream(name);
        }
        return ris;
    }

    static boolean getFileExists(final File f) {
        return f.exists();
    }

    static long getLastModified(final File f) {
        return f.lastModified();
    }

    private SecuritySupport() {
    }
}