package org.jbpm.bpmn2.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:lucazamador@gmail.com">Lucas Amador</a>
 */
public class Collaboration implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private Map<String, Conversation> conversations = new HashMap<String, Conversation>();
    private Map<String, Participant> participants = new HashMap<String, Participant>();
    private Map<String, MessageFlow> messageFlows = new HashMap<String, MessageFlow>();

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

    public void setParticipants(Map<String, Participant> participants) {
        this.participants = participants;
    }

    public Map<String, Participant> getParticipants() {
        return participants;
    }

    public void setMessageFlows(Map<String, MessageFlow> messageFlows) {
        this.messageFlows = messageFlows;
    }

    public Map<String, MessageFlow> getMessageFlows() {
        return messageFlows;
    }

}