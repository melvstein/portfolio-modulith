package dev.melvstein.spring_portfolio_modulith.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;

@UtilityClass
public class RequestHeaderUtils {

    public HttpServletRequest getRequest() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        return attributes != null ? attributes.getRequest() : null;
    }

    public String getHeader(String name) {
        HttpServletRequest request = getRequest();
        return request != null ? request.getHeader(name) : null;
    }

    public String getUserAgent() {
        return getHeader("User-Agent");
    }

    public String getIpAddress() {
        HttpServletRequest request = getRequest();

        if (request == null) {
            return null;
        }

        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.isBlank()) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.isBlank()) {
            ip = request.getRemoteAddr();
        }

        return ip.contains(",")
                ? ip.split(",")[0].trim()
                : ip;
    }
}