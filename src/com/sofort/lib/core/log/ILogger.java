package com.sofort.lib.core.log;

public interface ILogger {

    void log(String message);

    void debug(String message);

    void info(String message);

    void warn(String message);
}
