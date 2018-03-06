package xyz.luan.dextra.parking.models.car;

import io.yawp.commons.http.HttpException;
import io.yawp.repository.shields.Shield;
import xyz.luan.dextra.parking.ws.AuthHolder;

public class CarShield extends Shield<Car> {

    @Override
    public void defaults() {
        String email = AuthHolder.email.get();
        if (email == null) {
            throw new HttpException(403, "Must pass a firebase-token header to do that.");
        }
        boolean isDextra = AuthHolder.extractDomain(email).equals("dextra-sw.com");
        if (!isDextra) {
            throw new HttpException(403, "Not authorized");
        }
        allow(true);
    }
}
