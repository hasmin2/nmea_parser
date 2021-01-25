package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.RMBSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class RMBParser implements NMEA_Parser{
    private RMBSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (RMBSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.NAV_DESTINATION, message.getDestination().getId());
        result.put(NMEAParserConstants.NAV_DEST_ETA, message.getDestination().getTimeStamp());
        return result;
    }
}
