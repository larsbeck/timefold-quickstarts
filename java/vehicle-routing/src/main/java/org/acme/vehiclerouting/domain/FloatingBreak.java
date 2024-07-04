package org.acme.vehiclerouting.domain;

import java.time.Duration;
import java.time.LocalDateTime;

public class FloatingBreak {
    public FloatingBreak(LocalDateTime triggerTime, Duration duration) {
        this.triggerTime = triggerTime;
        this.duration = duration;
    }

    private LocalDateTime triggerTime;
    private Duration duration;

    public java.time.Duration getDuration() {
        return duration;
    }

    public void setDuration(java.time.Duration duration) {
        this.duration = duration;
    }

    public LocalDateTime getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(LocalDateTime triggerTime) {
        this.triggerTime = triggerTime;
    }
}
