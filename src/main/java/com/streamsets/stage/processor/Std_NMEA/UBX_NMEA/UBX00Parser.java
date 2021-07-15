package com.streamsets.stage.processor.Std_NMEA.UBX_NMEA;


import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;
import net.sf.marineapi.nmea.sentence.UBXSentence;
import net.sf.marineapi.ublox.message.UBXMessage00;

import java.util.HashMap;
import java.util.Map;

public class UBX00Parser implements UBX_Parser {
    private UBXMessage00 message;

    @Override
    public void init(UBXSentence message) throws StageException {
        this.message = (UBXMessage00) message;
    }

    @Override
    public Map<String, Object> parse(){
        Map <String, Object> result = new HashMap<>();
        try{ result.put(NMEAParserConstants.COURSE_COG_MAG, message.getCourseOverGround()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try{ result.put(NMEAParserConstants.SPEED_GROUND_SPD, message.getSpeedOverGround()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try{ result.put(NMEAParserConstants.ETC_NUM_SATELLITES, message.getNumberOfSatellitesUsed()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try{ result.put(NMEAParserConstants.TIME_UTC, message.getUtcTime()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try{ result.put(NMEAParserConstants.POSITION_LAT, message.getPosition().getLatitude()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try{ result.put(NMEAParserConstants.POSITION_LON, message.getPosition().getLongitude()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        return result;
    }
}
