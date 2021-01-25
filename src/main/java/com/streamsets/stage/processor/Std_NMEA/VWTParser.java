package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.sentence.VWTSentence;

import java.util.HashMap;
import java.util.Map;

public class VWTParser implements NMEA_Parser{
    private VWTSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (VWTSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.WIND_ANGLE_TRUE, message.getWindAngle());
        result.put(NMEAParserConstants.WIND_SPEED_TRUE, message.getSpeedKnots());
        return result;
    }
}
