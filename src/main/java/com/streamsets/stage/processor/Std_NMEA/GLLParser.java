package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;
import net.sf.marineapi.nmea.sentence.GLLSentence;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.util.Datum;

import java.util.HashMap;
import java.util.Map;

public class GLLParser implements NMEA_Parser{
    private GLLSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (GLLSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        message.getPosition().getLatitude();
        Map<String, Object> result = new HashMap<>();
        try {result.put(NMEAParserConstants.TIME_LOCAL_HOURS, message.getTime().getHour()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.TIME_LOCAL_MINS, message.getTime().getMinutes()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.POSITION_LAT, message.getPosition().getLatitude()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.POSITION_LON, message.getPosition().getLongitude());}
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        return result;
    }
}
