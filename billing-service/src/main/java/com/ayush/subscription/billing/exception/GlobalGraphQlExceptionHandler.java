package com.ayush.subscription.billing.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalGraphQlExceptionHandler {

    @GraphQlExceptionHandler(BillingNotFoundException.class)
    public GraphQLError handleBillingNotFound(
            BillingNotFoundException ex,
            DataFetchingEnvironment env
    ) {

        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .errorType(graphql.ErrorType.DataFetchingException)
                .build();
    }

    @GraphQlExceptionHandler(InvoiceNotFoundException.class)
    public GraphQLError handleInvoiceNotFound(
            InvoiceNotFoundException ex,
            DataFetchingEnvironment env
    ) {

        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .errorType(graphql.ErrorType.DataFetchingException)
                .build();
    }

}
