package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.sentence.VTGSentence;

import java.util.HashMap;
import java.util.Map;

public class VTGParser implements NMEA_Parser{
    private VTGSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (VTGSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.SPEED_WATER_SPD, message.getSpeedKnots());
        result.put(NMEAParserConstants.COURSE_COG_MAG, message.getMagneticCourse());
        return result;
    }
}
