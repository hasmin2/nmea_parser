package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.pipeline.api.base.OnRecordErrorException;
import com.streamsets.stage.lib.Errors;
import com.streamsets.stage.processor.Std_NMEA.AIS_NMEA.*;
import com.streamsets.stage.processor.Std_NMEA.UBX_NMEA.UBX_Parser;
import com.streamsets.stage.processor.Std_NMEA.UBX_NMEA.UBX00Parser;
import net.sf.marineapi.ais.message.*;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.sentence.UBXSentence;
import net.sf.marineapi.ublox.message.UBXMessage;
import net.sf.marineapi.ublox.message.UBXMessage00;
import net.sf.marineapi.ublox.message.UBXMessage03;
import net.sf.marineapi.ublox.message.parser.UBXMessage03Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

//TODO AIS require additional decoding
public class UBXParser implements NMEA_Parser{
    private UBXSentence message;
    private static final Logger log = LoggerFactory.getLogger(UBX_Parser.class);
    @Override
    public void init(Sentence message) throws StageException {
        this.message = (UBXSentence) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        if (message instanceof UBXMessage00) {
            result = doParsingJob(new UBX00Parser(), message);
        }
        else {
            throw new OnRecordErrorException(Errors.NMEA_AIS_NOT_SUPPORTED_SENTENCE, message.toString());
        }
        return result;
    }

    private Map<String, Object> doParsingJob(UBX_Parser parser, UBXSentence message){
        parser.init(message);
        return parser.parse();
    }
}
