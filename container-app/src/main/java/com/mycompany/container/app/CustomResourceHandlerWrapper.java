package com.mycompany.container.app;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.application.ResourceHandlerWrapper;
import javax.faces.application.ViewResource;
import javax.faces.context.FacesContext;
import org.jboss.logging.Logger;

public class CustomResourceHandlerWrapper extends ResourceHandlerWrapper {
    private Logger log = Logger.getLogger(CustomResourceHandlerWrapper.class);
    private final ResourceHandler wrapped;

    public CustomResourceHandlerWrapper(ResourceHandler wrapped) {
        log.info("constructor");
        this.wrapped = wrapped;
    }

    @Override
    public ResourceHandler getWrapped() {
        log.info("getWrapped");
        return this.wrapped;
    }

    @Override
    public Resource createResource(String resourceName) {
        log.info("createResource " + resourceName);
        Resource resource = super.createResource(resourceName);
        return resource;
    }
    
    @Override
    public Resource createResource(String resourceName, String libraryName) {
        log.info("createResource " + resourceName + " " + libraryName);
        final Resource resource = super.createResource(resourceName, libraryName);
        return resource;
    }
    
    @Override
    public ViewResource createViewResource(FacesContext context, String resourceName) {
        log.info("createViewResource ctx " + resourceName);
        //return getWrapped().createViewResource(context, resourceName);
        
        ViewResource resource = null;
        if(resourceName.contains("mini")) {
            log.info("using mini-app-war");
            resource = new CustomViewResource(resourceName);
        } else {
            log.info("using default");
            resource = getWrapped().createViewResource(context, resourceName);
        }
        return resource;
    }
    
}
