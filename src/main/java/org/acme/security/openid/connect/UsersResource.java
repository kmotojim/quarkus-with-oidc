/**
 * Copyright 2019 Red Hat, Inc, and individual contributors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.acme.security.openid.connect;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.reactive.NoCache;

import io.quarkus.oidc.IdToken;
import io.quarkus.security.identity.SecurityIdentity;

/**
 * @author <a href="mailto:psilva@redhat.com">Pedro Igor</a>
 */
@Path("/api/users")
public class UsersResource {

    @Inject
    SecurityIdentity identity;

    @Inject
    @IdToken
    JsonWebToken idToken;

    // @Inject
    // JsonWebToken accessToken;

    @GET
    // @RolesAllowed("user")
    @Path("/me")
    @NoCache
    public User me() {
        return new User(idToken);
    }

    public static class User {

        private final String userName;
        // private final Long userName;

        // User(SecurityIdentity identity) {
        //     // this.userName = identity.getPrincipal().getName();
        //     this.userName = idToken
        // }

        User(JsonWebToken idToken) {

            Instant instant = Instant.ofEpochSecond(idToken.getExpirationTime());
            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
            System.out.println(zonedDateTime);

            // this.userName = identity.getPrincipal().getName();
            // this.userName = idToken.getSubject();
            this.userName = zonedDateTime.toString();
        }
        

        public String getUserName() {
            return userName;
        }
    }
}
