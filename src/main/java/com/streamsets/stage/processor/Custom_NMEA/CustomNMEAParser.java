package com.streamsets.stage.processor.Custom_NMEA;

import com.streamsets.pipeline.api.Field;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.Map;

public interface CustomNMEAParser {
    void init(Map<String, String> inputValue);
    Map <String, Field> parse(String message);
}
