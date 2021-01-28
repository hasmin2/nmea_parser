package com.streamsets.stage.processor.Custom_NMEA;

import com.streamsets.pipeline.api.Field;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.*;

public class StreamsetsUIMapParser implements CustomNMEAParser{
    private Map<String,List<String>> parsingHeader;
    @Override
    public void init(Map<String, String> inputValue) {
        List<String> parsingSentence= new ArrayList<>();
        parsingHeader = new HashMap<>();
        for (String key : inputValue.keySet()){
            Collections.addAll(parsingSentence, inputValue.get(key).split(","));
            parsingHeader.put(key, parsingSentence);
        }
    }
  //  Pxxx    Proprietary (Vendor specific)
    @Override
    public Map<String, Field> parse(String message) {
        Map<String, Field> result = new HashMap<>();
        for(String key : parsingHeader.keySet()){
            String [] messageArray = message.split(",");
            if(messageArray[0].equalsIgnoreCase(key)){
                for(int i=0;i<messageArray.length-1;i++){
                    result.put(parsingHeader.get(key).get(i), Field.create(messageArray[i+1]));
                }
            }
        }
        return result;
    }
}
