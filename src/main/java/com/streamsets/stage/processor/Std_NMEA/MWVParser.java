package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.MWDSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class MWVParser implements NMEA_Parser{
    private MWDSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (MWDSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.WIND_ANGLE_TRUE, message.getTrueWindDirection());
        result.put(NMEAParserConstants.WIND_ANGLE_REF, message.getMagneticWindDirection());
        result.put(NMEAParserConstants.WIND_SPEED_TRUE, message.getWindSpeedKnots());
        return result;
    }
}
