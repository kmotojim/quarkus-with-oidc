package org.acme.security.openid.connect.template;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.oidc.IdToken;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/web")
public class WebResource {

    @Inject
    @IdToken
    JsonWebToken idToken;

    @Inject
    Template hello;

    @Inject
    Template privatePage;

    @Inject
    Template protectedPage;

    @Inject
    Template notProtectedPage;


    @GET
    @Path("/hello-page")
    public TemplateInstance getHelloPage(@QueryParam("myname") final String myName) {

        log.info("This is hello-page");
        log.info("myName: {}",myName);
        
        return hello
                .data("myName", myName)
                ;  
    }

    @GET
    @Path("/private-page")
    public TemplateInstance getPrivatePage(@QueryParam("myname") final String myName) {

        log.info("This is private-page");
        log.info("myName: {}",myName);
        
        return privatePage
                .data("myName", myName)
                ;   
    }

    @GET
    @Path("/protected-page")
    public TemplateInstance getProtectedPage() {

        log.info("This is protected-page");
        log.info("idToken: {}",idToken);
        
        return protectedPage
                .data("pageType", "Protected")
                .data("idToken", idToken)
                ;  
    }

    @GET
    @Path("/not-protected-page")
    public TemplateInstance getNotProtectedPage() {

        log.info("This is not-protected-page");
        
        return notProtectedPage
                .data("pageType", "NotProtected")
                ;  
    }
}