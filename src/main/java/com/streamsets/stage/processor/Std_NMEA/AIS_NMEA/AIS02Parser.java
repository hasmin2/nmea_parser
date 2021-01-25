package com.streamsets.stage.processor.Std_NMEA.AIS_NMEA;

import com.streamsets.pipeline.api.StageException;
import net.sf.marineapi.ais.message.AISMessage;
import net.sf.marineapi.ais.message.AISMessage02;

import java.util.HashMap;
import java.util.Map;

public class AIS02Parser implements AIS_Parser{
    private AISMessage02 message;
    @Override
    public void init(AISMessage message) throws StageException {
        this.message = (AISMessage02) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        message.getMMSI();
        return result;
    }
}
