package com.streamsets.stage.processor.Std_NMEA.UBX_NMEA;

import com.streamsets.pipeline.api.StageException;
import net.sf.marineapi.ais.message.AISMessage;
import net.sf.marineapi.nmea.sentence.UBXSentence;
import net.sf.marineapi.ublox.message.UBXMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public interface UBX_Parser {
    Logger log = LoggerFactory.getLogger(UBX_Parser.class);
    void init(UBXSentence message) throws StageException;
    Map<String, Object> parse();
}
