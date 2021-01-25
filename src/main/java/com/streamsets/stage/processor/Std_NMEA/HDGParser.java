package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.HDGSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class HDGParser implements NMEA_Parser{
    private HDGSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (HDGSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        message.getDeviation();
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.COURSE_HDG_DIFF, message.getVariation());
        return result;
    }
}
