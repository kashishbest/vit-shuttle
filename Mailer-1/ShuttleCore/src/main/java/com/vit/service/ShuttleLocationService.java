package com.vit.service;


import com.vit.model.ShuttleLocation;
import org.apache.cxf.annotations.GZIP;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by kashishsinghal on 25/03/16.
 */
@WebService
@Path("/locations")
@GZIP
public interface ShuttleLocationService {

    @GET
    @Path("/getAll")
    @Produces("application/json")
    List<ShuttleLocation> getLatestShuttleLocations();

    @POST
    @Path("/save")
    ShuttleLocation saveShuttleLocationData(@FormParam(value = "location") String object);
}
