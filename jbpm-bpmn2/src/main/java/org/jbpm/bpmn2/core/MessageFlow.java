package org.jbpm.bpmn2.core;

import java.io.Serializable;

/**
 * @author <a href="mailto:lucazamador@gmail.com">Lucas Amador</a>
 */
public class MessageFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String messageRef;
    private String sourceRef;
    private String targetRef;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageRef() {
        return messageRef;
    }

    public String getSourceRef() {
        return sourceRef;
    }

    public String getTargetRef() {
        return targetRef;
    }

    public void setMessageRef(String messageRef) {
        this.messageRef = messageRef;
    }

    public void setSourceRef(String sourceRef) {
        this.sourceRef = sourceRef;
    }

    public void setTargetRef(String targetRef) {
        this.targetRef = targetRef;
    }

}