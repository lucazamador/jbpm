/**
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.bpmn2.xml;

import java.util.HashSet;

import org.drools.xml.BaseAbstractHandler;
import org.drools.xml.ExtensibleXmlParser;
import org.drools.xml.Handler;
import org.jbpm.bpmn2.core.CorrelationProperty.CorrelationPropertyRetrievalExpression;
import org.jbpm.bpmn2.core.Message;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author <a href="mailto:lucazamador@gmail.com">Lucas Amador</a>
 */
public class MessagePathHandler extends BaseAbstractHandler implements Handler {
	
	@SuppressWarnings("unchecked")
	public MessagePathHandler() {
		if ((this.validParents == null) && (this.validPeers == null)) {
			this.validParents = new HashSet();
			this.validParents.add(CorrelationPropertyRetrievalExpression.class);

			this.validPeers = new HashSet();
			this.validPeers.add(null);

			this.allowNesting = false;
		}
	}

    public Object start(final String uri, final String localName,
			            final Attributes attrs, final ExtensibleXmlParser parser)
			throws SAXException {
		parser.startElementBuilder(localName, attrs);
		return null;
	}

	@SuppressWarnings("unchecked")
    public Object end(final String uri, final String localName,
			          final ExtensibleXmlParser parser) throws SAXException {
		Element element = parser.endElementBuilder();
		String messagePath = element.getTextContent();
        CorrelationPropertyRetrievalExpression cpre = (CorrelationPropertyRetrievalExpression) parser.getParent();
        cpre.setMessagePath(messagePath);
	    return parser.getCurrent();
	}

	public Class<?> generateNodeFor() {
		return Message.class;
	}

}