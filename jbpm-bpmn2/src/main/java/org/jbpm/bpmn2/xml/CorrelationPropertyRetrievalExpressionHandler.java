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
import java.util.Map;

import org.drools.xml.BaseAbstractHandler;
import org.drools.xml.ExtensibleXmlParser;
import org.drools.xml.Handler;
import org.jbpm.bpmn2.core.CorrelationProperty;
import org.jbpm.bpmn2.core.CorrelationProperty.CorrelationPropertyRetrievalExpression;
import org.jbpm.bpmn2.core.Message;
import org.jbpm.compiler.xml.ProcessBuildData;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author <a href="mailto:lucazamador@gmail.com">Lucas Amador</a>
 */
public class CorrelationPropertyRetrievalExpressionHandler extends BaseAbstractHandler implements Handler {
	
	@SuppressWarnings("unchecked")
	public CorrelationPropertyRetrievalExpressionHandler() {
		if ((this.validParents == null) && (this.validPeers == null)) {
			this.validParents = new HashSet();
			this.validParents.add(CorrelationProperty.class);

			this.validPeers = new HashSet();
			this.validPeers.add(null);

			this.allowNesting = false;
		}
	}

    public Object start(final String uri, final String localName,
			            final Attributes attrs, final ExtensibleXmlParser parser)
			throws SAXException {
		parser.startElementBuilder(localName, attrs);

		String messageRef = attrs.getValue("messageRef");
		String messageId = messageRef.substring(messageRef.indexOf(":") + 1);

		CorrelationProperty cp = (CorrelationProperty) parser.getParent();
		Map<String, Message> messages = (Map<String, Message>) ((ProcessBuildData) parser.getData()).getMetaData("Messages");
		if (messages == null) {
		    throw new IllegalArgumentException("No messages found");
		}
		Message message = messages.get(messageId);
		if (message == null) {
		    throw new IllegalArgumentException("Could not find message " + messageId);
		}
		
        CorrelationPropertyRetrievalExpression cpre = cp.addCorrelationPropertyRetrievalExpression(message);

		return cpre;
	}

	public Object end(final String uri, final String localName,
			          final ExtensibleXmlParser parser) throws SAXException {
		parser.endElementBuilder();
		return parser.getCurrent();
	}

	public Class<?> generateNodeFor() {
		return CorrelationPropertyRetrievalExpression.class;
	}

}