package com.vit.service;

import org.apache.cxf.annotations.GZIP;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by kashishsinghal on 15/03/16.
 */
@WebService
@Path("/test")
@GZIP
public interface TestService {

    @GET
    @Path("/print")
    String print();
}
