package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.RMCSentence;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.util.FaaMode;

import java.util.HashMap;
import java.util.Map;

public class RMCParser implements NMEA_Parser{
    private RMCSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (RMCSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        return result;
    }
}
