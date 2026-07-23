package com.ayush.subscription.gateway.filter;

import com.ayush.subscription.gateway.config.RateLimitConfig;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final Map<String, Bucket> buckets;
    private final RateLimitConfig rateLimitConfig;


    public RateLimitingFilter(Map<String, Bucket> buckets,
                              RateLimitConfig rateLimitConfig) {
        this.buckets = buckets;
        this.rateLimitConfig = rateLimitConfig;
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        return !request.getServletPath().equals("/graphql");
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String ip = request.getRemoteAddr();

        Bucket bucket = buckets.computeIfAbsent(
                ip,
                key -> rateLimitConfig.createNewBucket()
        );
        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response);
        } else {

            response.setStatus(429);
            response.setContentType("application/json");

            response.getWriter().write("""
                    {
                      "status":429,
                      "error":"Too Many Requests",
                      "message":"Rate limit exceeded. Try again after one minute."
                    }
                    """);
        }
    }
}
