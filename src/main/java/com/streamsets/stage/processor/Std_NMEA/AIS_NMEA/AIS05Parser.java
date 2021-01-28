package com.streamsets.stage.processor.Std_NMEA.AIS_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.ais.message.AISMessage;
import net.sf.marineapi.ais.message.AISMessage01;
import net.sf.marineapi.ais.message.AISMessage05;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AIS05Parser implements AIS_Parser{
    private AISMessage05 message;
    @Override
    public void init(AISMessage message) throws StageException {
        this.message = (AISMessage05) message;
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        try { result.put(NMEAParserConstants.AIS_MMSI, message.getMMSI()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.AIS_CALLSIGN, message.getCallSign()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.AIS_VESSEL_NAME, message.getName()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.AIS_DESTINATION, message.getDestination()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.AIS_IMO_NUMBER, message.getIMONumber()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        result.put(NMEAParserConstants.AIS_ETA, getETA());
        int vesselLength=0, vesselWidth=0;
        try { vesselWidth = message.getPort() + message.getStarboard(); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { vesselLength = message.getStern() + message.getBow(); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        result.put(NMEAParserConstants.AIS_VSL_LENGTH, vesselLength);
        result.put(NMEAParserConstants.AIS_VSL_WIDTH, vesselWidth);
        return result;
    }

    private Object getETA() {
        Date nowDate;
        Date currentDate = new Date();
        String dateString = null;
        LocalDate localDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int monthInt = localDate.getMonthValue();
        int yearInt;
        try {
            if (message.getETAMonth() < monthInt ||
                    (message.getETAMonth() == monthInt && message.getETADay() < localDate.getDayOfMonth())) {
                yearInt = localDate.getYear() + 1;
            } else {
                yearInt = localDate.getYear();
            }
            dateString = String.format("%02d", message.getETAMinute())
                    + String.format("%02d", message.getETAHour())
                    + String.format("%02d", message.getETADay())
                    + String.format("%02d", message.getETAMonth())
                    + yearInt;
        } catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try {
            nowDate = new SimpleDateFormat("mmhhddMMyyyy").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            nowDate = new Date(0);
        }
        return nowDate;

    }
}
