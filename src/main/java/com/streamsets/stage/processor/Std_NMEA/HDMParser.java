package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import net.sf.marineapi.nmea.sentence.HDMSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class HDMParser implements NMEA_Parser{
    private HDMSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (HDMSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
    message.getHeading();
        Map<String, Object> result = new HashMap<>();

        return result;
    }
}
