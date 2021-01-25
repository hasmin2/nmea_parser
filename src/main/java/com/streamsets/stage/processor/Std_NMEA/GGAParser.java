package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.GGASentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class GGAParser implements NMEA_Parser{
    private GGASentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (GGASentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.TIME_LOCAL_HOURS, message.getTime().getHour());
        result.put(NMEAParserConstants.TIME_LOCAL_MINS, message.getTime().getMinutes());
        result.put(NMEAParserConstants.ETC_NUM_SATELLITES, message.getSatelliteCount());
        result.put(NMEAParserConstants.POSITION_LAT, message.getPosition().getLatitude());
        result.put(NMEAParserConstants.POSITION_LON, message.getPosition().getLongitude());
        result.put(NMEAParserConstants.ETC_GPS_QUALITY_INDICATOR, message.getFixQuality().toInt());
        return result;
    }
}
