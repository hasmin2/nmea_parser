package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.sentence.VWRSentence;

import java.util.HashMap;
import java.util.Map;

public class VWRParser implements NMEA_Parser{
    private VWRSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (VWRSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        try { result.put(NMEAParserConstants.SOG_KNOTS, message.getSpeedKnots()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.SOG_KM, message.getSpeedKmh()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.WIND_ANGLE_RELATIVE, message.getWindAngle()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        return result;
    }
}
