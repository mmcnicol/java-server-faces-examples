package com.mycompany.container.app;

import java.io.Serializable;
import org.jboss.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ContainerAppView implements Serializable {
    private Logger log = Logger.getLogger(ContainerAppView.class);
    private static final long serialVersionUID = 1L;
    private String message;

    public ContainerAppView() {
        log.info("constructor");
        this.message = "Hello World!";
    }

    public String getMessage() {
        log.info("getMessage()");
        return message;
    }

    public void setMessage(String message) {
        log.info("setMessage()");
        this.message = message;
    }

}
