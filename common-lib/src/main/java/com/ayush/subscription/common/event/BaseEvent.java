package com.ayush.subscription.common.event;

import com.ayush.subscription.common.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEvent {


    private UUID eventId;
    private EventType eventType;
    private LocalDateTime occurredAt;

    protected BaseEvent(EventType eventType) {
        this.eventId = UUID.randomUUID();
        this.eventType = eventType;
        this.occurredAt = LocalDateTime.now();
    }




}

