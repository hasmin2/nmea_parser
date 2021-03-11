package com.streamsets.stage.processor.Custom_NMEA;

import com.streamsets.pipeline.api.Field;
import com.streamsets.stage.lib.NMEAParserConstants;
import com.streamsets.stage.processor.NMEAParser;

import java.util.*;

public class ETLParser implements CustomNMEAParser{
    private String [] inputString;
    private String header;
    @Override
    public void init(Map<String, String> inputValue) {
        for (String key : inputValue.keySet()){
            header = key;
            inputString = inputValue.get(key).split(",");
        }
    }
    @Override
    public Map<String, Field> parse(String message) {
        Map <String, Field> result = new HashMap<>();
        result.put(NMEAParserConstants.ETL_EVENT_TIME, Field.create(inputString[0]));
        result.put(NMEAParserConstants.ETL_MESSAGE_TYPE, Field.create(inputString[1]));
        result.put(NMEAParserConstants.ETL_POSITION_INDICATOR, Field.create(inputString[2]));
        result.put(NMEAParserConstants.ETL_POSITION_INDICATOR_SUB, Field.create(inputString[3]));
        result.put(NMEAParserConstants.ETL_OPERATING_LOCATION, Field.create(inputString[4]));
        result.put(NMEAParserConstants.ETL_NUM_OF_ENGINE_PROP, Field.create(inputString[5]));
        return result;
    }
}
