package xyz.luan.dextra.parking.models.car;

import io.yawp.repository.IdRef;
import io.yawp.repository.shields.Shield;

public class CarShield extends Shield<Car> {

    @Override
    public void defaults() {
        allow(false);
    }

    @Override
    public void index(IdRef<?> parentId) {
        allow(true);
    }

    @Override
    public void show(IdRef<Car> id) {
        allow(true);
    }
}
