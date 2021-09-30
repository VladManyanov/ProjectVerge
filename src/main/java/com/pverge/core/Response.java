package com.pverge.core;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("/contacts")
public class Response {

    private Logger logger = Logger.getLogger("ContactsResource");

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contact> getContacts() {
        List<Contact> retval = new ArrayList<>();
        retval.add( new Contact(990L, "Carl", "Walker", "Bekwam, Inc"));
        return retval;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Contact getContact(@PathParam("id") Long id) {
        return new Contact(990L, "Carl", "Walker", "Bekwam, Inc");
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateContact(@PathParam("id") Long id, Contact c) {
        c.setContactId(id);
        logger.info("updating id=" + id + " with c=" + c);
    }

    @DELETE
    @Path("/{id}")
    public void deleteContact(@PathParam("id") Long id) {}

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Contact addContact(@Valid Contact c) {
        c.setContactId(10990L);
        return c;
    }
}