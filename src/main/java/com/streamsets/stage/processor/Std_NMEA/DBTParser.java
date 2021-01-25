package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.DBTSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class DBTParser implements NMEA_Parser{
    private DBTSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (DBTSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.DEPTH_WATER_DEPTH, message.getDepth());
        return result;
    }
}
