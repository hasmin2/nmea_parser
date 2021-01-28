package com.streamsets.stage.processor.Std_NMEA.AIS_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.ais.message.AISMessage;
import net.sf.marineapi.ais.message.AISMessage01;
import net.sf.marineapi.ais.message.AISMessage04;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AIS04Parser implements AIS_Parser{
    private AISMessage04 message;
    @Override
    public void init(AISMessage message) throws StageException {
        this.message = (AISMessage04) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put(NMEAParserConstants.AIS_MMSI, message.getMMSI());
            if (message.hasLatitude()) {
                result.put(NMEAParserConstants.AIS_LAT, message.getLatitudeInDegrees());
            }
            if (message.hasLongitude()) {
                result.put(NMEAParserConstants.AIS_LON, message.getLongitudeInDegrees());
            }
            result.put(NMEAParserConstants.AIS_LAST_SEEN, getLastTimestamp());
        } catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        return result;
    }

    private Object getLastTimestamp() {
        Date nowDate;
        try{
            String dateString = String.format("%02d", message.getUtcSecond())
                    + String.format("%02d", message.getUtcMinute())
                    + String.format("%02d", message.getUtcHour())
                    + String.format("%02d", message.getUtcDay())
                    + String.format("%02d", message.getUtcMonth())
                    + message.getUtcYear();
            try {
                nowDate = new SimpleDateFormat("ssmmHHddMMyyyy").parse(dateString);
            } catch (ParseException e) {
                //e.printStackTrace();
                nowDate = new Date(0);
            }
        } catch (DataNotAvailableException de){
            log.info("One of NMEA Sentence data field is missing {}", message.getClass());
            nowDate = new Date(0);
        }
        return nowDate;

    }
}
