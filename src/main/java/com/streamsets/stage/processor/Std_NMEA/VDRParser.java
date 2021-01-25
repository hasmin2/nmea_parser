package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.sentence.VDRSentence;

import java.util.HashMap;
import java.util.Map;

public class VDRParser implements NMEA_Parser{
    private VDRSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (VDRSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.COURSE_COG_TRUE, message.getTrueDirection());
        result.put(NMEAParserConstants.COURSE_COG_MAG, message.getMagneticDirection());
        result.put(NMEAParserConstants.SPEED_WATER_SPD, message.getSpeed());
        return result;
    }
}
