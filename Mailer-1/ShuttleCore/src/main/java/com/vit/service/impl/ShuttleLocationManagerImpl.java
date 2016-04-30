package com.vit.service.impl;

import com.google.gson.Gson;
import com.vit.dao.ShuttleLocationDao;
import com.vit.model.CarData;
import com.vit.model.Product;
import com.vit.model.ShuttleLocation;
import com.vit.service.ShuttleLocationManager;
import com.vit.service.ShuttleLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import java.util.Collections;
import java.util.List;

/**
 * Created by kashishsinghal on 25/03/16.
 */
@Service("shuttleManager")
@WebService(serviceName = "shuttleService", endpointInterface = "com.vit.service.ShuttleLocationService")
@Transactional
public class ShuttleLocationManagerImpl extends GenericManagerImpl<ShuttleLocation,Integer> implements ShuttleLocationManager,ShuttleLocationService {

    ShuttleLocationDao shuttleLocationDao;

    @Autowired
    public void setShuttleLocationDao(ShuttleLocationDao shuttleLocationDao) {
        this.dao = shuttleLocationDao;
        this.shuttleLocationDao = shuttleLocationDao;
    }

    @Override
    public List<ShuttleLocation> getLatestShuttleLocations() {
        return shuttleLocationDao.getLatestShuttleLocations();
    }

    @Override
    public ShuttleLocation saveShuttleLocationData(String locationString) {

        ShuttleLocation location = new Gson().fromJson(locationString, ShuttleLocation.class);
        return shuttleLocationDao.save(location);

    }


    public static void main(String[] args) {
        String str = "{\"products\":[{\"id\":1,\"name\":\"Baleno\",\"brand\":\"Maruti Suzuki\",\"thumbnail\":\"http://www.zigcdn.com/media/model/2015/Oct/maruti_suzuki_baleno_front_view_640x480.jpg\",\"details\":{\"price\":\"700000\",\"colors\":\"blue, white,red\",\"desc\":\"The Maruti Suzuki Baleno has achieved another milestone, as the car is now the most exported vehicle in Marutiâ€™s portfolio. It exported 3,400 units of the premium hatch in February. The car will be taken to more than 100 countries\",\"image\":\"https://marutistoragenew.blob.core.windows.net/nexaexperience/img/baleno-price-img.png\"}},{\"id\":\"2\",\"name\":\"Celerio\",\"brand\":\"Maruti Suzuki\",\"thumbnail\":\"https:/images0.cardekho.com/images/carexteriorimages/large/Maruti/Maruti-Celerio/118.jpg\",\"details\":{\"price\":\"600000\",\"colors\":\"blue, white,red\",\"desc\":\"Maruti Suzuki Celerio will get dual airbags and ABS as an option, including the base version. Celerio is also the first car in the Maruti Suzuki stable to get the compact 800 cc diesel engine, making the Celerio the only car in its segment to come in three fuel options - Petrol, Diesel and CNG. The petrol Celerio is powered by a 1.0-litre, 3-cylinder engine putting out 67 hp at 6000 rpm and a maximum torque of 90 Nm at 500 rpm. The 800-cc diesel engine makes 47 hp at 3500 rpm and 125 Nm at 2000 rpm. A 5-speed manual transmission is available for all the three engine options while a 4-speed AMT is available only for the petrol version\",\"image\":\"http:/newcarlaunches.in/wp-content/uploads/2014/02/Indians-Liking-Maruti-Suzuki-Celerio-Automatic-Transmission-More-Than-Manual.jpg\"}},{\"idâ€\u009D:3, â€œname\":\"Tiago\",\"brand\":\"Tata\",\"thumbnail\":\"http:/www.motorbeam.com/wp-content/uploads/Tata-Zica-Front-Profile.jpg\",\"details\":{\"price\":\"400000\",\"colors\":\"yellow, white,red\",\"desc\":\"Tata Motors has launched its new hatchback - the Tiago at a starting price of INR 3.20 Lakhs for the petrol and INR 3.94 Lakhs for the diesel (ex-showroom, New Delhi). Bookings for the Tata Tiago commenced earlier at a cost of INR 10,000.The latest hatchback from Tata has been crafted on a new platform with fresh design philosophy, never seen before in Tata cars. The Tiago will be coming with a diesel, as well as a petrol mill. The former is a 1.05-litre unit, capable of generating 69bhp of power with a peak torque of 140Nm and the latter is a 1.2-litre engine, churning out 84bhp and 114Nm.\",\"image\":\"http:/imgd2.aeplcdn.com/0x0/cw/ec/20880/Tata-Tiago-Right-Front-Three-Quarter-67620.jpg?wm=0\"}},{\"id\":4,\"name\":\"NuvoSport\",\"brand\":\"Mahindra\",\"thumbnail\":\"http:/www.thehindu.com/multimedia/dynamic/02803/mahindra_2803595f.jpg\",\"details\":{\"price\":\"900000\",\"colors\":\"yellow, white,red\",\"desc\":\"Mahindra has plans to produce 18,000 units of the NuvoSport annually. The Indian carmaker has launched the SUV at a price of Rs. 7.35 Lacs (ex-showroom, Thane). It is the second sub-4-metre SUV in Mahindra's lineup after the TUV300. The NuvoSport replaces the Quanto and sits above the TUV300 in terms of pricing and features. Mechanically, it is powered by a 1.5-litre diesel from the outgoing Quanto. The diesel motor churns out 100bhp of power and 240Nm of torque. The vehicle will return a certified mileage of 17.45 kmpl. Transmission options include a 5-Speed manual and an optional AMT (automated-manual transmission).\",\"image\":\"http:/gaadiwaadi.com/wp-content/uploads/2016/04/mahindra-nuvosport-front.jpg\"}},{\"id\":5,\"name\":\"Benz c class\",\"brand\":\"Mercedes\",\"thumbnail\":\"http:/s1.cdn.autoevolution.com/images/gallery/MERCEDES-BENZ-C-Class--W205--5080_14.jpg\",\"details\":{\"price\":\"4000000\",\"colors\":\"blue, white,red\",\"desc\":\"German automaker, Mercedes-Benz launched the 250d variant of the C-Class. The diesel motor generates 204hp and 500nm of torque, making it the most powerful diesel engine in its class. Mated to a 9 speed automatic, the ARAI claimed efficiency is rated at 19.71km/l. The C250d goes up against the BMW 3-Series, the Audi A4 and the Volvo S60 / S60 CrossCountry. Last month, the premium automaker unveiled its AMG C43 Coupe. That was Mercâ€™s second addition to both of its coupe and 6-cylinder AMG model lineups.\",\"image\":\"http://imgd5.aeplcdn.com/642x361/cw/ec/22729/MercedesBenz-CClass-Exterior-69827.jpg?wm=1\"}}]}";

        Product cars = new Gson().fromJson(str,Product.class);

        CarData[] carDatas = cars.getProducts();

        for(int i = 0;i<carDatas.length;i++) {
            System.out.println(carDatas[i].getName());
        }
    }
}
