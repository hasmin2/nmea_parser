package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;
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
        try {result.put(NMEAParserConstants.SPEED_WATER_SPD, message.getSpeedKnots()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.COURSE_COG_MAG, message.getMagneticCourse());}
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        return result;
    }
}
