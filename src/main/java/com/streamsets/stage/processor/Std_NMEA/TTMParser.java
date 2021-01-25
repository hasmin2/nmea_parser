package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.sentence.TTMSentence;

import java.util.HashMap;
import java.util.Map;

public class TTMParser implements NMEA_Parser{
    private TTMSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (TTMSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        result.put(NMEAParserConstants.TIME_LOCAL_HOURS, message.getTime().getHour());
        result.put(NMEAParserConstants.TIME_LOCAL_MINS, message.getTime().getMinutes());
        return result;
    }
}
