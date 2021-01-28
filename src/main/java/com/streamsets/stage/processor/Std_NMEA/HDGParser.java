package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;
import net.sf.marineapi.nmea.sentence.HDGSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class HDGParser implements NMEA_Parser{
    private HDGSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (HDGSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        try { result.put(NMEAParserConstants.COURSE_HDG_DIFF, message.getVariation()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        return result;
    }
}
