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
package org.apache.html.dom;

import org.w3c.dom.html.HTMLHeadElement;

/**
 * @xerces.internal
 * @version $Revision: 1029415 $ $Date: 2010-10-31 22:32:22 +0530 (Sun, 31 Oct 2010) $
 * @author <a href="mailto:arkin@exoffice.com">Assaf Arkin</a>
 * @see org.w3c.dom.html.HTMLHeadElement
 * @see org.apache.xerces.dom.ElementImpl
 */
public class HTMLHeadElementImpl
    extends HTMLElementImpl
    implements HTMLHeadElement
{

    private static final long serialVersionUID = 6438668473721292232L;

    public String getProfile()
    {
        return getAttribute( "profile" );
    }
    
    
    public void setProfile( String profile )
    {
        setAttribute( "profile", profile );
    }

    
    /**
     * Constructor requires owner document.
     * 
     * @param owner The owner HTML document
     */
    public HTMLHeadElementImpl( HTMLDocumentImpl owner, String name )
    {
        super( owner, name );
    }


}

