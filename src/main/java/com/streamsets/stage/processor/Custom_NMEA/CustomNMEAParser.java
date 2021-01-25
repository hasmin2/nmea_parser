package com.streamsets.stage.processor.Custom_NMEA;

import java.util.List;
import java.util.Map;

public interface CustomNMEAParser {
    void init(Map<String, String> inputValue);
    Map <String, Object> parse(String message);
}
