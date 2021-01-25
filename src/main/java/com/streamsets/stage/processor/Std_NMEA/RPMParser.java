package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.RPMSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class RPMParser implements NMEA_Parser{
    private RPMSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (RPMSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.RPM_ENG_SPEED, message.getRPM());
        return result;
    }
}
