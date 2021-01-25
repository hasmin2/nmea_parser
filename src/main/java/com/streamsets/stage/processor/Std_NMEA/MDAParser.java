package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.MDASentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class MDAParser implements NMEA_Parser{
    private MDASentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (MDASentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.ETC_AIR_TEMP, message.getAirTemperature());
        result.put(NMEAParserConstants.ETC_AIR_HUMID, message.getAbsoluteHumidity());
        result.put(NMEAParserConstants.WIND_ANGLE_TRUE, message.getTrueWindDirection());
        result.put(NMEAParserConstants.WIND_ANGLE_RELATIVE, message.getMagneticWindDirection());
        result.put(NMEAParserConstants.WIND_SPEED_TRUE, message.getWindSpeed());
        result.put(NMEAParserConstants.ETC_AIR_PRESSURE, message.getPrimaryBarometricPressure());
        result.put(NMEAParserConstants.ETC_WATER_TEMP, message.getWaterTemperature());
        return result;
    }
}
