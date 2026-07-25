package com.ayush.subscription.customer.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.language.Field;
import graphql.language.OperationDefinition;
import graphql.parser.Parser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GraphQlPublicOperationFilter extends OncePerRequestFilter {

    public static final String PUBLIC_OPERATION_ATTRIBUTE =
            GraphQlPublicOperationFilter.class.getName() + ".PUBLIC_OPERATION";

    private final ObjectMapper objectMapper;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !"POST".equalsIgnoreCase(request.getMethod())
                || !"/graphql".equals(request.getRequestURI())
                || request.getContentType() == null
                || !request.getContentType().startsWith(MediaType.APPLICATION_JSON_VALUE);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        byte[] requestBody = request.getInputStream().readAllBytes();
        CachedBodyHttpServletRequest wrappedRequest =
                new CachedBodyHttpServletRequest(request, requestBody);

        if (isPublicAuthenticationOperation(requestBody)) {
            wrappedRequest.setAttribute(PUBLIC_OPERATION_ATTRIBUTE, true);
        }

        filterChain.doFilter(wrappedRequest, response);
    }

    private boolean isPublicAuthenticationOperation(byte[] requestBody) {
        try {
            JsonNode payload = objectMapper.readTree(requestBody);
            JsonNode query = payload.get("query");

            if (query == null || !query.isTextual()) {
                return false;
            }

            List<String> operationNames = Parser.parse(query.asText())
                    .getDefinitionsOfType(OperationDefinition.class)
                    .stream()
                    .filter(definition -> definition.getOperation()
                            == OperationDefinition.Operation.MUTATION)
                    .flatMap(definition -> definition.getSelectionSet()
                            .getSelectionsOfType(Field.class).stream())
                    .map(Field::getName)
                    .toList();

            return !operationNames.isEmpty()
                    && operationNames.stream()
                    .allMatch(name -> name.equals("login") || name.equals("register"));
        } catch (RuntimeException | IOException ignored) {
            return false;
        }
    }

    private static class CachedBodyHttpServletRequest
            extends HttpServletRequestWrapper {

        private final byte[] body;

        private CachedBodyHttpServletRequest(HttpServletRequest request, byte[] body) {
            super(request);
            this.body = body;
        }

        @Override
        public ServletInputStream getInputStream() {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(body);

            return new ServletInputStream() {
                @Override
                public int read() {
                    return inputStream.read();
                }

                @Override
                public boolean isFinished() {
                    return inputStream.available() == 0;
                }

                @Override
                public boolean isReady() {
                    return true;
                }

                @Override
                public void setReadListener(ReadListener readListener) {
                }
            };
        }
    }
}
