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
import java.util.List;

import org.drools.xml.BaseAbstractHandler;
import org.drools.xml.ExtensibleXmlParser;
import org.drools.xml.Handler;
import org.jbpm.bpmn2.core.Conversation.CorrelationKey;
import org.jbpm.bpmn2.core.CorrelationProperty;
import org.jbpm.bpmn2.core.Definitions;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author <a href="mailto:lucazamador@gmail.com">Lucas Amador</a>
 */
public class CorrelationPropertyRefHandler extends BaseAbstractHandler implements Handler {
	
	@SuppressWarnings("unchecked")
	public CorrelationPropertyRefHandler() {
		if ((this.validParents == null) && (this.validPeers == null)) {
			this.validParents = new HashSet();
			this.validParents.add(CorrelationKey.class);

			this.validPeers = new HashSet();
			this.validPeers.add(null);
			this.validPeers.add(CorrelationProperty.class);

			this.allowNesting = false;
		}
	}

    public Object start(final String uri, final String localName,
			            final Attributes attrs, final ExtensibleXmlParser parser)
			throws SAXException {
		parser.startElementBuilder(localName, attrs);
        return null;
	}

    public Object end(final String uri, final String localName,
			          final ExtensibleXmlParser parser) throws SAXException {

	    Element element = parser.endElementBuilder();

		String correlationPropertyRef = element.getTextContent();
		String correlationPropertyId = correlationPropertyRef.substring(correlationPropertyRef.indexOf(":") + 1);

		Definitions definitions = (Definitions) parser.getParent(Definitions.class);
		List<CorrelationProperty> correlationProperties = definitions.getCorrelationProperties();
		for (CorrelationProperty correlationProperty : correlationProperties) {
            if (correlationProperty.getId().equals(correlationPropertyId)) {
                CorrelationKey correlationKey = (CorrelationKey)parser.getParent();
                correlationKey.addCorrelationProperty(correlationProperty);
                return correlationProperty;
            }
        }
		throw new IllegalArgumentException("unable to find correlation key with id " + correlationPropertyId);
	}

	public Class<?> generateNodeFor() {
		return CorrelationProperty.class;
	}

}