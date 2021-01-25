package com.streamsets.stage.processor.Std_NMEA.AIS_NMEA;

import com.streamsets.pipeline.api.StageException;
import net.sf.marineapi.ais.message.AISMessage;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.Map;

public interface AIS_Parser {
    void init(AISMessage message) throws StageException;
    Map<String, Object> parse();
}
