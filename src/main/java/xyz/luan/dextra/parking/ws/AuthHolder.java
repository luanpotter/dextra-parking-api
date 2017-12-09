package xyz.luan.dextra.parking.ws;

import io.yawp.commons.http.HttpException;

public class AuthHolder {
    public static final ThreadLocal<String> email = new ThreadLocal<>();

    public static String extractDomain(String email) {
        String[] parts = email.split("@");
        if (parts.length != 2) {
            throw new HttpException(403, "Unsupported email format, single @ required.");
        }
        return parts[1];
    }
}
