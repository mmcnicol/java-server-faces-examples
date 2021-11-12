package com.sun.faces.facelets.tag.ui;

import com.sun.faces.config.WebConfiguration;
import com.sun.faces.facelets.el.VariableMapperWrapper;
import com.sun.faces.facelets.tag.TagHandlerImpl;
import com.sun.faces.util.FacesLogger;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.VariableMapper;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagAttributeException;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagException;

public final class IncludeHandler extends TagHandlerImpl {

    private static final Logger log = FacesLogger.FACELETS_INCLUDE.getLogger();

    private final TagAttribute src;

    public IncludeHandler(TagConfig config) {
        super(config);
        TagAttribute attr = null;
        attr = getAttribute("src");
        if (null == attr) {
            attr = getAttribute("file");
        }
        if (null == attr) {
            attr = getAttribute("page");
        }
        if (null == attr) {
            throw new TagException(this.tag, "Attribute 'src', 'file' or 'page' is required");
        }
        this.src = attr;
    }

    public void apply(FaceletContext ctx, UIComponent parent) throws IOException {
        log.log(Level.INFO, "debug 1");
        String path = this.src.getValue(ctx);
        if (path == null || path.length() == 0) {
            return;
        }
        log.log(Level.INFO, "path=" + path);
        VariableMapper orig = ctx.getVariableMapper();
        ctx.setVariableMapper((VariableMapper) new VariableMapperWrapper(orig));
        try {
            log.log(Level.INFO, "debug 2");
            this.nextHandler.apply(ctx, null);
            log.log(Level.INFO, "debug 3");
            WebConfiguration webConfig = WebConfiguration.getInstance();
            log.log(Level.INFO, "debug 4");
            if (path.startsWith(webConfig.getOptionValue(WebConfiguration.WebContextInitParameter.WebAppContractsDirectory))) {
                log.log(Level.INFO, "debug 5");
                throw new TagAttributeException(this.tag, this.src, "Invalid src, contract resources cannot be accessed this way : " + path);
            }
            ctx.includeFacelet(parent, path);
        } catch (IOException e) {
            if (log.isLoggable(Level.FINE)) {
                log.log(Level.FINE, e.toString(), e);
            }
            throw new TagAttributeException(this.tag, this.src, "Invalid path : " + path);
        } finally {
            ctx.setVariableMapper(orig);
        }
    }
}
