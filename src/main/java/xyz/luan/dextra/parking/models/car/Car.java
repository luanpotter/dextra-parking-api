package xyz.luan.dextra.parking.models.car;

import io.yawp.repository.IdRef;
import io.yawp.repository.annotations.Endpoint;

@Endpoint(path = "/cars")
public class Car {

    private IdRef<Car> id;

    private String email;
}
