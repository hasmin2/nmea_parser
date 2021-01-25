package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.sentence.GSASentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class GSAParser implements NMEA_Parser{
    private GSASentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (GSASentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        message.getHorizontalDOP();
        Map<String, Object> result = new HashMap<>();
        return result;
    }
}
