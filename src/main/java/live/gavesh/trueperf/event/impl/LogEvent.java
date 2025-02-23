package live.gavesh.trueperf.event.impl;

import java.util.EventObject;

@SuppressWarnings("all")
public class LogEvent extends EventObject {

	private String logMessage;
	private String logLevel;

	public LogEvent(Object source, String logLevel, String logMessage) {
		super(source);
		this.logMessage = logMessage;
		this.logLevel = logLevel;
	}

	public String getLogMessage() {
		return logMessage;
	}
	
	public String getLogLevel() {
		return logLevel;
	}
	
}
