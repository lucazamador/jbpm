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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.drools.xml.BaseAbstractHandler;
import org.drools.xml.ExtensibleXmlParser;
import org.drools.xml.Handler;
import org.jbpm.bpmn2.core.Collaboration;
import org.jbpm.bpmn2.core.Conversation;
import org.jbpm.bpmn2.core.MessageFlow;
import org.jbpm.bpmn2.core.Participant;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author <a href="mailto:lucazamador@gmail.com">Lucas Amador</a>
 */
public class MessageFlowHandler extends BaseAbstractHandler implements Handler {
	
	@SuppressWarnings("unchecked")
	public MessageFlowHandler() {
		if ((this.validParents == null) && (this.validPeers == null)) {
			this.validParents = new HashSet();
			this.validParents.add(Collaboration.class);

			this.validPeers = new HashSet();
			this.validPeers.add(null);
            this.validPeers.add(MessageFlow.class);
            this.validPeers.add(Participant.class);
            this.validPeers.add(Conversation.class);

			this.allowNesting = false;
		}
	}

    @SuppressWarnings("unchecked")
    public Object start(final String uri, final String localName,
			            final Attributes attrs, final ExtensibleXmlParser parser)
			throws SAXException {
		parser.startElementBuilder(localName, attrs);

		String id = attrs.getValue("id");
		String messageRef = attrs.getValue("messageRef");
		String sourceRef = attrs.getValue("sourceRef");
		String targetRef = attrs.getValue("targetRef");

		Collaboration collaboration = (Collaboration)parser.getParent();
		Map<String, MessageFlow> messageFlows = collaboration.getMessageFlows();
		if (messageFlows==null) {
		    messageFlows = new HashMap<String, MessageFlow>();
		    collaboration.setMessageFlows(messageFlows);
		}

	    MessageFlow messageFlow = new MessageFlow();
	    messageFlow.setId(id);
	    messageFlow.setMessageRef(messageRef);
	    messageFlow.setSourceRef(sourceRef);
	    messageFlow.setTargetRef(targetRef);

		messageFlows.put(id, messageFlow);

		return messageFlow;
	}

	public Object end(final String uri, final String localName,
			          final ExtensibleXmlParser parser) throws SAXException {
		parser.endElementBuilder();
		return parser.getCurrent();
	}

	public Class<?> generateNodeFor() {
		return MessageFlow.class;
	}

}