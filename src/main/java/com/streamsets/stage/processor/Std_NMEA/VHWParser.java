package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.sentence.VHWSentence;

import java.util.HashMap;
import java.util.Map;

public class VHWParser implements NMEA_Parser{
    private VHWSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (VHWSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.COURSE_HDG_TRUE, message.getMagneticHeading());
        result.put(NMEAParserConstants.SOG_KNOTS, message.getSpeedKnots());
        result.put(NMEAParserConstants.SOG_KM, message.getSpeedKmh());
        return result;
    }
}
