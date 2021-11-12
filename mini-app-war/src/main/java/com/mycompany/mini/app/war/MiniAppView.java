package com.mycompany.mini.app.war;

import java.io.Serializable;
import org.jboss.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class MiniAppView implements Serializable {
    private Logger log = Logger.getLogger(MiniAppView.class);
    private static final long serialVersionUID = 1L;
    private String message;

    public MiniAppView() {
        log.info("constructor");
        this.message = "Hello Mini App World!";
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
