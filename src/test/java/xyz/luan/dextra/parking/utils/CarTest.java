package xyz.luan.dextra.parking.utils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CarTest extends EndpointTestCase {

    @Test
    public void listAll() {
        String get = get("/cars");
        assertThat(get, is(equalTo("[]")));
    }
}
