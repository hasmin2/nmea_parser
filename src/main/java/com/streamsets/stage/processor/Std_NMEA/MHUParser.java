package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.MHUSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class MHUParser implements NMEA_Parser{
    private MHUSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (MHUSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.ETC_AIR_HUMID, message.getAbsoluteHumidity());

        return result;
    }
}
