package xyz.luan.dextra.parking.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import io.yawp.commons.http.HttpException;
import org.junit.Test;
import xyz.luan.dextra.parking.ws.AuthHolder;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class CarTest extends EndpointTestCase {

    @Test(expected = HttpException.class)
    public void listAllDisabledTest() {
        get("/cars/1");
    }

    @Test
    public void listAllTest() {
        AuthHolder.email.set("test@dextra-sw.com");
        String get = get("/cars");
        assertThat(get, is(equalTo("[]")));
    }

    @Test(expected = HttpException.class)
    public void createDiabledTest() {
        AuthHolder.email.set("test@gmail.com");
        post("/cars", "{\"email\":\"carro1@dextra-sw.com\"}");
    }

    @Test
    public void createTest() {
        AuthHolder.email.set("test@dextra-sw.com");
        post("/cars", "{\"email\":\"carro1@dextra-sw.com\"}");

        JsonArray array = new JsonParser().parse(get("/cars")).getAsJsonArray();
        assertThat(array.size(), is(equalTo(1)));
        assertThat(array.get(0).getAsJsonObject().get("email").getAsString(), is(startsWith("carro1")));
    }

    @Test
    public void updateLocationTest() {
        AuthHolder.email.set("test@dextra-sw.com");
        String json = post("/cars", "{\"email\":\"carro1@dextra-sw.com\", \"location\":\"my house\"}");
        String id = new JsonParser().parse(json).getAsJsonObject().get("id").getAsString();

        assertThat(get(id + "/location"), is(equalTo("\"my house\"")));

        put(id + "/location", "dextra");
        assertThat(get(id + "/location"), is(equalTo("\"dextra\"")));
    }
}
