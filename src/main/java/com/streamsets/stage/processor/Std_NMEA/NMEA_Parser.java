package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import net.sf.marineapi.nmea.sentence.Sentence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public interface NMEA_Parser {
    Logger log = LoggerFactory.getLogger(NMEA_Parser.class);
    void init(Sentence message) throws StageException;
    Map<String, Object> parse();
}
