package live.gavesh.trueperf.util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import live.gavesh.trueperf.event.impl.LogEvent;
import live.gavesh.trueperf.event.listeners.LogListener;

public class Logger {

	private static LogListener logListener;
	private static final String LOG_FILE = "log-trueperf.txt";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static void log(String level, String message) {
		String entry = String.format("[%s] [%s] %s%n", LocalDateTime.now().format(FORMATTER), level, message);
		System.out.print(entry);
		try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
			writer.write(entry);
		} catch (IOException exception) {
			System.err.println("[ERROR] Failed to write log: " + exception.getMessage());
		}
		if (logListener != null)
			logListener.onLogMessage(new LogEvent(Logger.class, level, message));
	}

	public static void info(String message) {
		log("INFO", message);

	}

	public static void warn(String message) {
		log("WARNING", message);
	}

	public static void error(String message) {
		log("ERROR", message);
	}

	public static void setLogListener(LogListener listener) {
		logListener = listener;
	}

}
