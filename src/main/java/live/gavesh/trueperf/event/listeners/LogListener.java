package live.gavesh.trueperf.event.listeners;

import java.util.EventListener;

import live.gavesh.trueperf.event.impl.LogEvent;

public interface LogListener extends EventListener {
    void onLogMessage(LogEvent event);
}