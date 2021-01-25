package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import net.sf.marineapi.nmea.sentence.RTESentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class RTEParser implements NMEA_Parser{
    private RTESentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (RTESentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }
}
