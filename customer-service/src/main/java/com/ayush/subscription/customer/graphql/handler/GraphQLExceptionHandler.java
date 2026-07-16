package com.ayush.subscription.customer.graphql.handler;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.jspecify.annotations.Nullable;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;


@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {
    @Override
    protected @Nullable GraphQLError
    resolveToSingleError
            (Throwable ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .build();
    }
}
