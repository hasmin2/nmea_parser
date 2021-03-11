package com.streamsets.stage.processor.Std_NMEA.AIS_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.ais.message.AISMessage;
import net.sf.marineapi.ais.message.AISMessage21;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;

import java.util.HashMap;
import java.util.Map;

public class AIS21Parser implements AIS_Parser{
    private AISMessage21 message;
    @Override
    public void init(AISMessage message) throws StageException {
        this.message = (AISMessage21) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.AIS_MMSI, message.getMMSI());
        try { result.put(NMEAParserConstants.AIS_MMSI, message.getMMSI());}
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.AIS_VESSEL_NAME, message.getName()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.AIS_LON, message.getLongitudeInDegrees()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.AIS_LON, message.getLatitudeInDegrees()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        int vesselLength=0, vesselWidth=0;
        try { vesselWidth = message.getPort() + message.getStarboard(); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { vesselLength = message.getStern() + message.getBow(); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        result.put(NMEAParserConstants.AIS_VSL_LENGTH, vesselLength);
        result.put(NMEAParserConstants.AIS_VSL_WIDTH, vesselWidth);

        return result;
    }
}
