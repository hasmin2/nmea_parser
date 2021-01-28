package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;
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
        try { result.put(NMEAParserConstants.COURSE_COG_TRUE, message.getTrueDirection());}
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.COURSE_COG_MAG, message.getMagneticDirection());}
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try {result.put(NMEAParserConstants.SPEED_WATER_SPD, message.getSpeed()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        return result;
    }
}
