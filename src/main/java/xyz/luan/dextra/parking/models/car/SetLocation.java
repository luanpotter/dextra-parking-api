package xyz.luan.dextra.parking.models.car;

import io.yawp.commons.http.annotation.GET;
import io.yawp.commons.http.annotation.PUT;
import io.yawp.repository.IdRef;
import io.yawp.repository.actions.Action;

public class SetLocation extends Action<Car> {

    @PUT
    public Car location(IdRef<Car> carId, String location) {
        Car car = carId.fetch();
        car.setLocation(location);
        return yawp.save(car);
    }

    @GET
    public String location(IdRef<Car> carId) {
        return carId.fetch().getLocation();
    }
}
