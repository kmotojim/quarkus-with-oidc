package org.acme.security.openid.connect.template;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/web")
public class WebResource {

    @Inject
    Template hello;

    @Inject
    Template privatePage;


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

}