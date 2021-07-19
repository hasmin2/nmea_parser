package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;
import net.sf.marineapi.nmea.sentence.MWVSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.HashMap;
import java.util.Map;

public class MWVParser implements NMEA_Parser{
    private MWVSentence message;
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (MWVSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        if(message.isTrue()) {
            try {
                result.put(NMEAParserConstants.WIND_ANGLE_TRUE, message.getAngle());
            } catch (DataNotAvailableException de) {
                log.info("One of NMEA Sentence data field is missing {}", message.getClass());
            }
            try {
                result.put(NMEAParserConstants.WIND_SPEED_TRUE, message.getSpeed());
            } catch (DataNotAvailableException de) {
                log.info("One of NMEA Sentence data field is missing {}", message.getClass());
            }
            try {
                result.put(NMEAParserConstants.WIND_SPEED_TRUE_STATUS, message.getStatus());
            } catch (DataNotAvailableException de) {
                log.info("One of NMEA Sentence data field is missing {}", message.getClass());
            }
        }
        else {
            {
                try {
                    result.put(NMEAParserConstants.WIND_ANGLE_RELATIVE, message.getAngle());
                } catch (DataNotAvailableException de) {
                    log.info("One of NMEA Sentence data field is missing {}", message.getClass());
                }
                try {
                    result.put(NMEAParserConstants.WIND_SPEED_RELATIVE, message.getSpeed());
                } catch (DataNotAvailableException de) {
                    log.info("One of NMEA Sentence data field is missing {}", message.getClass());
                }
                try {
                    result.put(NMEAParserConstants.WIND_SPEED_RELATIVE_STATUS, message.getStatus());
                } catch (DataNotAvailableException de) {
                    log.info("One of NMEA Sentence data field is missing {}", message.getClass());
                }
            }
        }
        return result;
    }
}
