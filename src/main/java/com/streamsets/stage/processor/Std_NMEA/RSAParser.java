package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.RSASentence;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.util.Side;

import java.util.HashMap;
import java.util.Map;

public class RSAParser implements NMEA_Parser{
    private RSASentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (RSASentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.COURSE_RUDDER_ANGLE, message.getRudderAngle(Side.PORT));
        return result;
    }
}
