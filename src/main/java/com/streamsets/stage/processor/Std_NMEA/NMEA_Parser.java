package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.Map;

public interface NMEA_Parser {
    void init(Sentence message) throws StageException;
    Map<String, Object> parse();
}
