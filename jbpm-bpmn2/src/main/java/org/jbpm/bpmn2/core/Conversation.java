package org.jbpm.bpmn2.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:lucazamador@gmail.com">Lucas Amador</a>
 */
public class Conversation implements Serializable {

    private static final long serialVersionUID = 4L;

    private String id;
    private Map<String, CorrelationKey> correlationKeys = new HashMap<String, Conversation.CorrelationKey>();
    private Map<String, MessageFlow> messageFlows = new HashMap<String, MessageFlow>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CorrelationKey addCorrelationKey(String id, String name) {
        CorrelationKey ck = new CorrelationKey();
        ck.setId(id);
        ck.setName(name);
        correlationKeys.put(id, ck);
        return ck;
    }

    public void setCorrelationKeys(Map<String, CorrelationKey> correlationKeys) {
        this.correlationKeys = correlationKeys;
    }

    public Map<String, CorrelationKey> getCorrelationKeys() {
        return correlationKeys;
    }

    public void setMessageFlows(Map<String, MessageFlow> messageFlows) {
        this.messageFlows = messageFlows;
    }

    public Map<String, MessageFlow> getMessageFlows() {
        return messageFlows;
    }

    public class CorrelationKey implements Serializable {

        private static final long serialVersionUID = 1L;

        private String id;
        private String name;
        private Map<String, CorrelationProperty> correlationProperties = new HashMap<String, CorrelationProperty>();

        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public void addCorrelationProperty(CorrelationProperty correlationProperty) {
            correlationProperties.put(correlationProperty.getId(), correlationProperty);
        }
        public void setCorrelationProperties(Map<String, CorrelationProperty> correlationProperties) {
            this.correlationProperties = correlationProperties;
        }
        public Map<String, CorrelationProperty> getCorrelationProperties() {
            return correlationProperties;
        }

    }

}