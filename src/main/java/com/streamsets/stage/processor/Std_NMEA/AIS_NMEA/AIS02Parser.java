package com.streamsets.stage.processor.Std_NMEA.AIS_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.ais.message.AISMessage;
import net.sf.marineapi.ais.message.AISMessage02;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;

import java.util.HashMap;
import java.util.Map;

public class AIS02Parser implements AIS_Parser{
    private AISMessage02 message;
    @Override
    public void init(AISMessage message) throws StageException {
        this.message = (AISMessage02) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put(NMEAParserConstants.AIS_MMSI, message.getMMSI());
            if (message.hasCourseOverGround()) {
                result.put(NMEAParserConstants.AIS_COG, message.getCourseOverGround());
            }
            if (message.hasLatitude()) {
                result.put(NMEAParserConstants.AIS_LAT, message.getLatitudeInDegrees());
            }
            if (message.hasLongitude()) {
                result.put(NMEAParserConstants.AIS_LON, message.getLongitudeInDegrees());
            }
            if (message.hasSpeedOverGround()) {
                result.put(NMEAParserConstants.AIS_SOG, message.getSpeedOverGround());
            }
            if (message.hasTrueHeading()) {
                result.put(NMEAParserConstants.AIS_HEADING, message.getTrueHeading());
            }
        } catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}

        return result;
    }
}
