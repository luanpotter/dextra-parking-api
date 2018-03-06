package xyz.luan.dextra.parking.models.car;

import io.yawp.repository.IdRef;
import io.yawp.repository.annotations.Endpoint;
import io.yawp.repository.annotations.Id;
import io.yawp.repository.annotations.Index;
import lombok.Data;

@Data
@Endpoint(path = "/cars")
public class Car {

    @Id
    private IdRef<Car> id;

    @Index(normalize = false)
    private String email;

    private String name;

    private String model;

    private String plate;

    private String location;
}
