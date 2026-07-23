package com.ayush.subscription.gateway.dto.subscription;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlanInput {

    private String name;

    private String description;

    private Double price;

    private BillingCycle billingCycle;

}
