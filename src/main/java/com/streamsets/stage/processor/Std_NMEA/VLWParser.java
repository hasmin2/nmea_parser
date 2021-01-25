package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.sentence.VLWSentence;

import java.util.HashMap;
import java.util.Map;

public class VLWParser implements NMEA_Parser{
    private VLWSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (VLWSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.NAV_TOTAL_DIST, message.getTotal());
        return result;
    }
}
