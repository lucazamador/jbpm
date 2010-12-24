package org.jbpm.bpmn2.core;

import java.io.Serializable;

/**
 * @author <a href="mailto:lucazamador@gmail.com">Lucas Amador</a>
 */
public class Participant implements Serializable {

    private static final long serialVersionUID = 4L;

    private String id;
    private String name;
    private String processRef;
    private String interfaceRef;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setProcessRef(String processRef) {
        this.processRef = processRef;
    }

    public String getProcessRef() {
        return processRef;
    }

    public void setInterfaceRef(String interfaceRef) {
        this.interfaceRef = interfaceRef;
    }

    public String getInterfaceRef() {
        return interfaceRef;
    }

}