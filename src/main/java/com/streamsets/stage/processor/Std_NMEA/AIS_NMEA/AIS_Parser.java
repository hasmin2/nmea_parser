package com.streamsets.stage.processor.Std_NMEA.AIS_NMEA;

import com.streamsets.pipeline.api.StageException;
import net.sf.marineapi.ais.message.AISMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public interface AIS_Parser {
    Logger log = LoggerFactory.getLogger(AIS_Parser.class);
    void init(AISMessage message) throws StageException;
    Map<String, Object> parse();
}
