package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;
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
        try {result.put(NMEAParserConstants.DEPTH_OFFSET_TRANS, message.getOffset()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.DEPTH_WATER_DEPTH, message.getDepth());  }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.ETC_MAX_RANGE_SCALE, message.getMaximum()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        return result;
    }
}
