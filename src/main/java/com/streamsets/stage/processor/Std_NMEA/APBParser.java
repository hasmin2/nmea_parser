package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.APBSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class APBParser implements NMEA_Parser{
    private APBSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (APBSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map <String, Object> result = new HashMap<>();
        return result;
    }
}
