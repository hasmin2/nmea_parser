package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import net.sf.marineapi.nmea.sentence.HTDSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class HTDParser implements NMEA_Parser{
    private HTDSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (HTDSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }
}
