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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.drools.xml.BaseAbstractHandler;
import org.drools.xml.ExtensibleXmlParser;
import org.drools.xml.Handler;
import org.jbpm.bpmn2.core.CorrelationProperty;
import org.jbpm.bpmn2.core.DataStore;
import org.jbpm.bpmn2.core.Definitions;
import org.jbpm.bpmn2.core.Escalation;
import org.jbpm.bpmn2.core.Interface;
import org.jbpm.bpmn2.core.ItemDefinition;
import org.jbpm.bpmn2.core.Message;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author <a href="mailto:lucazamador@gmail.com">Lucas Amador</a>
 */
public class CorrelationPropertyHandler extends BaseAbstractHandler implements Handler {
	
	@SuppressWarnings("unchecked")
	public CorrelationPropertyHandler() {
		if ((this.validParents == null) && (this.validPeers == null)) {
			this.validParents = new HashSet();
			this.validParents.add(Definitions.class);

			this.validPeers = new HashSet();
			this.validPeers.add(null);
            this.validPeers.add(ItemDefinition.class);
            this.validPeers.add(Message.class);
            this.validPeers.add(Interface.class);
            this.validPeers.add(Escalation.class);
            this.validPeers.add(Error.class);
            this.validPeers.add(DataStore.class);
            this.validPeers.add(CorrelationProperty.class);

			this.allowNesting = false;
		}
	}

    public Object start(final String uri, final String localName,
			            final Attributes attrs, final ExtensibleXmlParser parser)
			throws SAXException {
		parser.startElementBuilder(localName, attrs);

		String id = attrs.getValue("id");

        CorrelationProperty correlationProperty = new CorrelationProperty();
        correlationProperty.setId(id);
        Definitions parent = (Definitions) parser.getParent();
        List<CorrelationProperty> correlationProperties = parent.getCorrelationProperties();
        if (correlationProperties == null) {
            correlationProperties = new  ArrayList<CorrelationProperty>();
            parent.setCorrelationProperties(correlationProperties);
        }
        correlationProperties.add(correlationProperty);
		return correlationProperty;
	}

	public Object end(final String uri, final String localName,
			          final ExtensibleXmlParser parser) throws SAXException {
		parser.endElementBuilder();
		return parser.getCurrent();
	}

	public Class<?> generateNodeFor() {
		return CorrelationProperty.class;
	}

}