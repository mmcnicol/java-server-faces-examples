package com.mycompany.container.app;

import java.net.MalformedURLException;
import java.net.URL;
import javax.faces.application.ViewResource;
import org.jboss.logging.Logger;

public class CustomViewResource extends ViewResource {
    private Logger log = Logger.getLogger(CustomViewResource.class);
    private String resourceName;

    public CustomViewResource(String resourceName) {
        this.resourceName = resourceName;
    }
    
    @Override
    public URL getURL() {
        log.info("using getURL()");
        URL url = null;
        try {
            url = new URL("http://localhost:8080/mini-app-war" + resourceName);
        } catch (MalformedURLException ex) {
            log.error(ex);
        }
        return url;
    }
    
}
