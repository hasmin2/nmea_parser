package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.sentence.ZDASentence;

import java.util.HashMap;
import java.util.Map;

public class ZDAParser implements NMEA_Parser{
    private ZDASentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (ZDASentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.TIME_UTC_POS, message.getLocalZoneHours());
        result.put(NMEAParserConstants.TIME_YEAR, message.getDate().getYear());
        result.put(NMEAParserConstants.TIME_MONTH, message.getDate().getMonth());
        result.put(NMEAParserConstants.TIME_DAY, message.getDate().getDay());
        result.put(NMEAParserConstants.TIME_LOCAL_HOURS, message.getLocalZoneHours());
        result.put(NMEAParserConstants.TIME_LOCAL_MINS, message.getLocalZoneMinutes());
        return result;
    }
}
