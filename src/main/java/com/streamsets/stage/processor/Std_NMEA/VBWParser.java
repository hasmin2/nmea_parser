package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.sentence.VBWSentence;
import net.sf.marineapi.nmea.util.DataStatus;

import java.util.HashMap;
import java.util.Map;

public class VBWParser implements NMEA_Parser{
    private VBWSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (VBWSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        message.getWaterSpeedStatus();
        if(message.getWaterSpeedStatus().equals(DataStatus.ACTIVE)) {
            result.put(NMEAParserConstants.SPEED_WATER_SPD, message.getLongWaterSpeed());

        }
        try{ result.put(NMEAParserConstants.SPEED_TRANS_WATER_SPD, message.getTravWaterSpeed());}
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try{ result.put(NMEAParserConstants.SPEED_TRANS_GROUND_SPD, message.getTravGroundSpeed()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        if(message.getGroundSpeedStatus().equals(DataStatus.ACTIVE)) {
            result.put(NMEAParserConstants.SPEED_GROUND_SPD, message.getLongGroundSpeed());
        }
        if (message.getSternWaterSpeedStatus().equals(DataStatus.ACTIVE)) {
            result.put(NMEAParserConstants.SPEED_STERN_TRANS_WATER_SPD, message.getSternWaterSpeed());
        }
        if(message.getSternGroundSpeedStatus().equals(DataStatus.ACTIVE)) {
            result.put(NMEAParserConstants.SPEED_STERN_TRANS_GROUND_SPD, message.getSternGroundSpeed());
        }
        return result;
    }
}
