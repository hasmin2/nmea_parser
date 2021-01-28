package com.streamsets.stage.processor.Std_NMEA.AIS_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import net.sf.marineapi.ais.message.AISMessage;
import net.sf.marineapi.ais.message.AISMessage01;
import net.sf.marineapi.ais.message.AISMessage24;
import net.sf.marineapi.nmea.parser.DataNotAvailableException;

import java.util.HashMap;
import java.util.Map;

public class AIS24Parser implements AIS_Parser{
    private AISMessage24 message;
    @Override
    public void init(AISMessage message) throws StageException {
        this.message = (AISMessage24) message;
    }
//    Message ID                  6       Identifier for Message 24; always 24
//    Repeat indicator            2       Used by the repeater to indicate how many times a message has been repeated. 0 = default; 3 = do not repeat any more
//    User ID                     30      MMSI number
//    Part number                 2       Identifier for the message part number; always 1 for Part B
//    Type of ship and cargo type 8       0 = not available or no ship = default
//                                        1-99 = as defined in § 3.3.2
//                                        100-199 = reserved, for regional use
//                                        200-255 = reserved, for future use
//                                        Not applicable to SAR aircraft
//    Vendor ID                   42      Unique identification of the Unit by a number as defined by the manufacturer (option; "@@@@@@@" = not available = default)
//                                            See vendor Identification field table below
//    Call sign                   42      Call sign of the MMSI-registered vessel. 7 X 6 bit ASCII characters, "@@@@@@@" = not available = default
//                                        Craft associated with a parent vessel should use “A” followed by the last 6 digits of the MMSI of the parent vessel. Examples of these craft include towed vessels, rescue boats, tenders, lifeboats and life rafts
//    Dimension of ship/
//    reference for position.     30      Dimensions of ship in meters and reference point for reported position.
//                                        For SAR aircraft, the use of this field may be decided by the responsible administration. If used it should indicate the maximum dimensions of the craft. As default should A = B = C = D be set to “0”.
//    Type of electronic position
//    fixing device           	4	    0 = Undefined (default); 1 = GPS, 2 = GLONASS, 3 = combined GPS/GLONASS, 4 = Loran-C, 5 = Chayka, 6 = integrated navigation system, 7 = surveyed; 8 = Galileo, 9-14 = not used, 15 = internal GNSS
//    Number of bits              168     Occupies one-time period

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        try { result.put(NMEAParserConstants.AIS_MMSI, message.getMMSI());}
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.AIS_CALLSIGN, message.getCallSign()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { result.put(NMEAParserConstants.AIS_VESSEL_NAME, message.getName()); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        int vesselLength=0, vesselWidth=0;
        try { vesselWidth = message.getPort() + message.getStarboard(); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        try { vesselLength = message.getStern() + message.getBow(); }
        catch (DataNotAvailableException de){log.info("One of NMEA Sentence data field is missing {}", message.getClass());}
        result.put(NMEAParserConstants.AIS_VSL_LENGTH, vesselLength);
        result.put(NMEAParserConstants.AIS_VSL_WIDTH, vesselWidth);
        return result;
    }
}
