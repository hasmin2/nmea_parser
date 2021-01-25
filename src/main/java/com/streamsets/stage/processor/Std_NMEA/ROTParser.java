package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.ROTSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class ROTParser implements NMEA_Parser{
    private ROTSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (ROTSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.COURSE_ROT, message.getRateOfTurn());
        return result;
    }
}
