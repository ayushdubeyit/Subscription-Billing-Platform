package com.ayush.subscription.subscription.schedular;


import com.ayush.subscription.subscription.service.SubscriptionRenewalService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionRenewalScheduler {

    private final SubscriptionRenewalService renewalService;

    @Scheduled(cron = "0 */1 * * * *")
    public void renewSubscriptions() {


        renewalService.processRenewals();

    }
}
