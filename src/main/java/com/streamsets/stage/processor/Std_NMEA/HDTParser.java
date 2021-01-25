package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.HDTSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class HDTParser implements NMEA_Parser{
    private HDTSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (HDTSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        message.getHeading();
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.COURSE_HDG_TRUE, message.getHeading());
        return result;
    }
}
