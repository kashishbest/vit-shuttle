package com.vit.dao;

import com.vit.model.ShuttleLocation;

import java.util.List;

/**
 * Created by kashishsinghal on 25/03/16.
 */
public interface ShuttleLocationDao extends GenericDao<ShuttleLocation,Integer>{

    List<ShuttleLocation> getLatestShuttleLocations();
}
