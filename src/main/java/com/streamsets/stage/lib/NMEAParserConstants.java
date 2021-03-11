package com.streamsets.stage.lib;

public final class NMEAParserConstants {
    public static final String H2_URL = "jdbc:h2:mem:nmeaparser";
    public static final String VDR_MODEL_DESC = "Set VDR model to fit the prefix of NMEA sentence";
    public static final String NMEA_PARSER_DESC = "Put NMEA sentence that to be parsed";
    //////////////////////
    //VDM property strings
    //////////////////////
    public static final String AIS_MMSI = "AisMmsi";
    public static final String AIS_DRAFT = "AisDraft";
    public static final String AIS_ETA = "AisEta";
    public static final String AIS_PORT = "AisPort";
    public static final String AIS_LAT = "AisLat";
    public static final String AIS_LON = "AisLon";
    public static final String AIS_SOG = "AisSog";
    public static final String AIS_SPEED = "AisSPEED";
    public static final String AIS_DEPTH = "AisWaterDepth";
    public static final String AIS_HEADING = "AisHeading";
    public static final String AIS_COG = "AisCog";
    public static final String AIS_CALLSIGN = "AisCallsign";
    public static final String AIS_VESSEL_NAME = "AisVslName";
    public static final String AIS_DESTINATION = "AisDest";
    public static final String AIS_IMO_NUMBER = "AisImo";
    public static final String AIS_LAST_SEEN = "AisLastseen";
    public static final String AIS_VESSEL_FLAG = "AisVslFlag";
    public static final String AIS_VSL_LENGTH = "AisVslLength";
    public static final String AIS_VSL_WIDTH = "AisVslWidth";
    public static final String ALTITUDE = "altitude";
    public static final String RPM_ENG_SPEED = "RpmEngineSpd";
    public static final String TIME_UTC_POS = "TimeUtcPos";
    public static final String TIME_UTC = "TimeUtc";
    public static final String TIME_DAY = "TimeDay";
    public static final String TIME_MONTH = "TimeMonth";
    public static final String TIME_YEAR = "TimeYear";
    public static final String TIME_LOCAL_HOURS = "TimeLocalHours";
    public static final String TIME_LOCAL_MINS = "TimeLocalMin";
    public static final String POSITION_LAT = "PositionLat";
    public static final String POSITION_LON = "PositionLon";
    public static final String COURSE_COG_TRUE = "CourseCogTrue";
    public static final String COURSE_COG_MAG = "CourseCogMag";
    public static final String COURSE_HDG_TRUE = "CourseHdgTrue";
    public static final String COURSE_ROT = "CourseRot";
    public static final String COURSE_RUDDER_ANGLE = "CourseRudderAngle";
    public static final String SOG_KNOTS = "SpeedSogKnots";
    public static final String SOG_KM = "SpeedSogKm";
    public static final String SPEED_WATER_SPD = "SpeedLonWaterSpd";
    public static final String SPEED_TRANS_WATER_SPD = "SpeedTransWaterSpd";
    public static final String SPEED_WATER_SPD_STATUS = "SpeedWaterSpdStatus";
    public static final String SPEED_GROUND_SPD = "SpeedLonGroundSpd";
    public static final String SPEED_TRANS_GROUND_SPD = "SpeedTransGroundSpd";
    public static final String SPEED_GROUND_SPD_STATUS = "SpeedGroundSpdStatus";
    public static final String SPEED_STERN_TRANS_WATER_SPD = "SpeedSternTransWaterSpd";
    public static final String SPEED_STERN_WATER_SPD_STATUS = "SpeedSternWaterSpdStatus";
    public static final String SPEED_STERN_TRANS_GROUND_SPD = "SpeedSternTransGroundSpd";
    public static final String SPEED_STERN_GROUND_SPD_STATUS = "SpeedSternGroundSpdStatus";
    public static final String WIND_ANGLE_TRUE = "WindAngleTrue";
    public static final String WIND_ANGLE_REF = "WindAngleReference";
    public static final String WIND_SPEED_TRUE = "WindSpeedTrue";
    public static final String WIND_SPEED_TRUE_UNITS = "WindSpeedTrueUnits";
    public static final String WIND_SPEED_TRUE_STATUS = "WindSpeedTrueStatus";
    public static final String WIND_ANGLE_RELATIVE = "WindAngleRelative";
    public static final String WIND_SPEED_RELATIVE = "WindSpeedRelative";
    public static final String WIND_SPEED_RELATIVE_UNITS = "WindSpeedRelativeUnits";
    public static final String WIND_SPEED_RELATIVE_STATUS = "WindSpeedRelativeStatus";
    public static final String DEPTH_WATER_DEPTH = "DepthWaterDpt";
    public static final String DEPTH_OFFSET_TRANS = "DepthOffsetTrans";
    public static final String ETC_MAX_RANGE_SCALE = "EtcMaxRangeScale";
    public static final String ETC_GPS_QUALITY_INDICATOR = "EtcGpsQualityIndicator";
    public static final String ETC_NUM_SATELLITES = "EtcNumSatellites";
    public static final String ETC_GLL_STATUS = "EtcGllStatus";
    public static final String ETC_GLL_MODE_INDICATOR = "EtcGllModeIndicator";
    public static final String ETL_MESSAGE_TYPE = "EtlMessageType";
    public static final String ETL_EVENT_TIME = "EtlEventTime";
    public static final String ETC_AIR_TEMP = "EtcAirTemp";
    public static final String ETC_AIR_HUMID = "EtcAirHumid";
    public static final String ETL_POSITION_INDICATOR = "EtlPositionIndicator";
    public static final String ETL_POSITION_INDICATOR_SUB = "EtlPositionIndicatorSub";
    public static final String ETL_OPERATING_LOCATION = "EtlOperatingLocation";
    public static final String ETL_NUM_OF_ENGINE_PROP = "EtlNumOfEnginePropeller";
    public static final String COURSE_HDG_DIFF = "CourseHdgDiff";
    public static final String ETC_AIR_PRESSURE = "EtcAirPressure";
    public static final String ETC_WATER_TEMP = "EtcWaterTemp";
    public static final String NAV_DESTINATION = "NavDestination";
    public static final String NAV_DEST_ETA = "NavDestEta";
    public static final String NAV_TOTAL_DIST = "NavTotalDist";

    ////////////
    //labels
    ////////
    public static final String VDR_MODEL_LABEL = "VDR Model";
    public static final String NMEA_PARSER_LABEL = "NMEA Parser";
    public static final String INPUT_FIELD_LABEL = "Input Field Name";
    public static final String AIS_DECODE_LABEL = "AIS Output";
    //////////////////////
    //Descriptions
    //////////////////////
    public static final String AIS_DECODE_DESC = "Decodes AIS input if checked. can be increases output  with unnecessary data field";
    public static final String STAGE_ICON = "nmea.png";
    public static final String STAGE_HELP_URL = "";
    public static final String STAGE_LABEL = "NMEA Parser";
    public static final String STAGE_DESC = "NMEA Parser w/ AIS decoded message, Should separate AIS data";
    public static final String INPUT_FIELD_DESC = "Input Field name for parsing";

    //MENU values
    public static final String JRC_1800 = "JRC 1800";
    public static final String JRC_1900 = "JRC 1900";
    public static final String FURUNO = "FURUNO";
    public static final String CONSILLIUM = "Consillium";
    public static final String GENERAL = "Generic (no add nmea prefix)";
    //MENUS
    public static final String NMEA_PARSER_MENU = "NMEA Parser";
    public static final String VDR_MODEL_MENU = "VDR Model";
    public static final String NMEA_PARSER_MENUSTRING = "NMEAPARSER";
    public static final String VDR_MODEL_MENUSTRING = "VDRMODEL";



}
