package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;
import net.sf.marineapi.nmea.sentence.MTWSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;
//Water temperature
public class MTWParser implements NMEA_Parser{
    private MTWSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (MTWSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        try { result.put(NMEAParserConstants.ETC_WATER_TEMP, message.getTemperature()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        return result;
    }
}
