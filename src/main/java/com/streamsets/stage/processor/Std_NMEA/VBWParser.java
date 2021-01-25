package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.sentence.VBWSentence;

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
        result.put(NMEAParserConstants.SPEED_WATER_SPD, message.getLongWaterSpeed());
        result.put(NMEAParserConstants.SPEED_TRANS_WATER_SPD, message.getTravWaterSpeed());
        result.put(NMEAParserConstants.SPEED_WATER_SPD_STATUS, message.getWaterSpeedStatus().toChar());
        result.put(NMEAParserConstants.SPEED_GROUND_SPD, message.getLongGroundSpeed());
        result.put(NMEAParserConstants.SPEED_TRANS_GROUND_SPD, message.getTravGroundSpeed());
        result.put(NMEAParserConstants.SPEED_GROUND_SPD_STATUS, message.getGroundSpeedStatus().toChar());
        result.put(NMEAParserConstants.SPEED_STERN_TRANS_WATER_SPD, message.getSternWaterSpeed());
        result.put(NMEAParserConstants.SPEED_STERN_WATER_SPD_STATUS, message.getSternWaterSpeedStatus().toChar());
        result.put(NMEAParserConstants.SPEED_STERN_TRANS_GROUND_SPD, message.getSternGroundSpeed());
        result.put(NMEAParserConstants.SPEED_STERN_GROUND_SPD_STATUS, message.getSternGroundSpeedStatus());
        return result;
    }
}
