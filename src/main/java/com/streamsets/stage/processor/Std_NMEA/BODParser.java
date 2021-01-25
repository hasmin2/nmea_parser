package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.BODSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class BODParser implements NMEA_Parser{
    private BODSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (BODSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.COURSE_COG_MAG, message.getMagneticBearing());
        result.put(NMEAParserConstants.COURSE_COG_TRUE, message.getTrueBearing());
        return result;
    }
}
