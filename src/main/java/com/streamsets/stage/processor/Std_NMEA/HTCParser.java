package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.HTCSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class HTCParser implements NMEA_Parser{
    private HTCSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (HTCSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        message.getRateOfTurn();
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.COURSE_ROT, message.getRateOfTurn());
        result.put(NMEAParserConstants.COURSE_RUDDER_ANGLE, message.getRudderAngle());
        return result;
    }
}
