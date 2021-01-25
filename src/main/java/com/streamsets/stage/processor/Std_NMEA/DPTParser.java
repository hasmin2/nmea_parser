package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.DPTSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class DPTParser implements NMEA_Parser{
    private DPTSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (DPTSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map <String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.DEPTH_OFFSET_TRANS, message.getOffset());
        result.put(NMEAParserConstants.DEPTH_WATER_DEPTH, message.getDepth());
        result.put(NMEAParserConstants.ETC_MAX_RANGE_SCALE, message.getMaximum());
        return result;
    }
}
