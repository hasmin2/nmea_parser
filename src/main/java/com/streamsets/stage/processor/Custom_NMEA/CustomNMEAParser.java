package com.streamsets.stage.processor.Custom_NMEA;

import com.streamsets.pipeline.api.Field;

import java.util.Map;

public interface CustomNMEAParser {
    void init(Map<String, String> inputValue);
    Map <String, Field> parse(String message);
}
