package org.jbpm.bpmn2.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:lucazamador@gmail.com">Lucas Amador</a>
 */
public class Collaboration implements Serializable {

    private static final long serialVersionUID = 4L;

    private String id;
    private Map<String, Conversation> conversations = new HashMap<String, Conversation>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setConversations(Map<String, Conversation> conversations) {
        this.conversations = conversations;
    }

    public Map<String, Conversation> getConversations() {
        return conversations;
    }

}