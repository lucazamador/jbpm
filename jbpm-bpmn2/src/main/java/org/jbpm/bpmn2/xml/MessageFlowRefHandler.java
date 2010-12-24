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
import org.jbpm.bpmn2.core.Conversation;
import org.jbpm.bpmn2.core.Conversation.CorrelationKey;
import org.jbpm.bpmn2.core.MessageFlow;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author <a href="mailto:lucazamador@gmail.com">Lucas Amador</a>
 */
public class MessageFlowRefHandler extends BaseAbstractHandler implements Handler {
	
	@SuppressWarnings("unchecked")
	public MessageFlowRefHandler() {
		if ((this.validParents == null) && (this.validPeers == null)) {
			this.validParents = new HashSet();
			this.validParents.add(Conversation.class);

			this.validPeers = new HashSet();
			this.validPeers.add(null);
			this.validPeers.add(MessageFlow.class);
			this.validPeers.add(CorrelationKey.class);

			this.allowNesting = false;
		}
	}

    public Object start(final String uri, final String localName,
			            final Attributes attrs, final ExtensibleXmlParser parser)
			throws SAXException {

        parser.startElementBuilder(localName, attrs);

        return parser.getCurrent();
	}

	public Object end(final String uri, final String localName,
			          final ExtensibleXmlParser parser) throws SAXException {
	    Element element = parser.endElementBuilder();
	    String messageFlowRef = element.getTextContent();
	    System.out.println("messageFlowRef: " + messageFlowRef);
	    return new MessageFlow();
	}

	public Class<?> generateNodeFor() {
	    return MessageFlow.class;
	}

}