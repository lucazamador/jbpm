package org.jbpm.bpmn2.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:lucazamador@gmail.com">Lucas Amador</a>
 */
public class CorrelationProperty implements Serializable {

    private static final long serialVersionUID = 4L;

    private String id;
    private Map<String, CorrelationPropertyRetrievalExpression> retrievalExpressions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public CorrelationPropertyRetrievalExpression addCorrelationPropertyRetrievalExpression(Message messageRef) {
        CorrelationPropertyRetrievalExpression cpre = new CorrelationPropertyRetrievalExpression();
        cpre.setMessageRef(messageRef);
        if (retrievalExpressions==null) {
            retrievalExpressions = new HashMap<String, CorrelationProperty.CorrelationPropertyRetrievalExpression>();
        }
        retrievalExpressions.put(messageRef.getId(), cpre);
        return cpre;
    }

    public void setRetrievalExpressions(Map<String, CorrelationPropertyRetrievalExpression> retrievalExpressions) {
        this.retrievalExpressions = retrievalExpressions;
    }

    public Map<String, CorrelationPropertyRetrievalExpression> getRetrievalExpressions() {
        return retrievalExpressions;
    }

    public class CorrelationPropertyRetrievalExpression implements Serializable {

        private static final long serialVersionUID = 4L;

        private Message messageRef;
        private String messagePath;

        public void setMessageRef(Message messageRef) {
            this.messageRef = messageRef;
        }
        public Message getMessageRef() {
            return messageRef;
        }

        public void setMessagePath(String messagePath) {
            this.messagePath = messagePath;
        }

        public String getMessagePath() {
            return messagePath;
        }
    }

}