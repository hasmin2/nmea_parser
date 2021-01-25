package com.streamsets.stage.processor.Std_NMEA.AIS_NMEA;

import com.streamsets.pipeline.api.StageException;
import net.sf.marineapi.ais.message.AISMessage;

import java.util.Map;

public class AIS03Parser implements AIS_Parser {
    @Override
    public void init(AISMessage message) throws StageException {

    }

    @Override
    public Map<String, Object> parse() {
        return null;
    }
}
