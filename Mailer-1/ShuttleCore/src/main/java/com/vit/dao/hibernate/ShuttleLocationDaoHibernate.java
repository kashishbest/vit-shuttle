package com.vit.dao.hibernate;

import com.vit.dao.ShuttleLocationDao;
import com.vit.model.ShuttleLocation;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kashishsinghal on 25/03/16.
 */
@Repository("shuttleLocationDao")
public class ShuttleLocationDaoHibernate extends GenericDaoHibernate<ShuttleLocation,Integer> implements ShuttleLocationDao {
    public ShuttleLocationDaoHibernate() {
        super(ShuttleLocation.class);
    }

    @Override
    public List<ShuttleLocation> getLatestShuttleLocations() {
        String query = "SELECT latitude,longitude, car_time carTime,car_id carId FROM shuttle_location where car_time in " +
                "(select max(car_time) car_time from shuttle_location GROUP BY car_id)";
        Query qry = getSession().createSQLQuery(query);
        List<ShuttleLocation> shuttleLocations = qry.setResultTransformer(Transformers.aliasToBean(ShuttleLocation.class)).list();

        return shuttleLocations;
    }
}
