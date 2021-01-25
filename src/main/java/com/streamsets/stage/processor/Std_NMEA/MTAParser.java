package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.MTASentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;
//Air temperature
public class MTAParser implements NMEA_Parser{
    private MTASentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (MTASentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.ETC_AIR_TEMP, message.getTemperature());
        return result;
    }
}
