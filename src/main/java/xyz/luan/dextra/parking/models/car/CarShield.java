package xyz.luan.dextra.parking.models.car;

import com.google.firebase.auth.FirebaseToken;
import io.yawp.commons.http.HttpException;
import io.yawp.commons.http.annotation.GET;
import io.yawp.commons.http.annotation.PUT;
import io.yawp.repository.IdRef;
import io.yawp.repository.shields.Shield;
import xyz.luan.dextra.parking.ws.AuthHolder;

import java.util.List;

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

    @Override
    public void create(List<Car> objects) {
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

    @PUT
    public void location(IdRef<Car> carId, String location) {
        allow(true);
    }

    @GET
    public void location(IdRef<Car> carId) {
        allow(true);
    }
}
