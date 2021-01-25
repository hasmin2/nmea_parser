package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.stage.lib.NMEAParserConstants;
import com.streamsets.stage.processor.Std_NMEA.AIS_NMEA.*;
import net.sf.marineapi.ais.message.*;
import net.sf.marineapi.ais.parser.AISMessageFactory;
import net.sf.marineapi.nmea.sentence.AISSentence;
import net.sf.marineapi.nmea.sentence.Sentence;

import java.util.*;

import static com.streamsets.stage.processor.NMEAParser.aisIDs;

//TODO AIS require additional decoding
public class AISParser implements NMEA_Parser{
    private AISMessage messageArray;
    @Override
    public void init(Sentence message) throws StageException {
        combineMessage((AISSentence) message);
    }
    private void combineMessage(AISSentence message){
        if(message.isFragmented()){
            String id=message.getMessageId();
            int messageIndex = message.getFragmentNumber();
            int messageSize = message.getNumberOfFragments();
            if(!aisIDs.containsKey(id)) {//메시지가 전체 구조에 없으면 새로 하나 빈거 만들기
                aisIDs.put(id, new TreeMap<>());
            }
            aisIDs.get(id).put(messageIndex ,message);

            if(aisIDs.get(id).size() == messageSize){// 메시지가 모두 다 들어왔다고 치면
                AISSentence [] resultMessage = new AISSentence [messageSize];
                int i=0;
                for (Integer item : aisIDs.get(id).keySet()){
                    resultMessage[i] = aisIDs.get(id).get(item);
                    i++;
                }
                messageArray = AISMessageFactory.getInstance().create(resultMessage);
                aisIDs.put(id, new TreeMap<>());//다 보내고 나면 초기화하기
            }
            if(aisIDs.get(id).size() > messageSize){
                aisIDs.put(id, new TreeMap<>());//길이가 안 맞으면 초기화하기
            }
        }
        else{
            messageArray = AISMessageFactory.getInstance().create(message);
        }
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        if (messageArray instanceof AISMessage01) {
            result.putAll(doParsingJob(new AIS01Parser(), messageArray));
        } else if (messageArray instanceof AISMessage02) {
            result.putAll(doParsingJob(new AIS02Parser(), messageArray));
        } else if (messageArray instanceof AISMessage03) {
            result.putAll(doParsingJob(new AIS03Parser(), messageArray));
        } else if (messageArray instanceof AISMessage04) {
            result.putAll(doParsingJob(new AIS04Parser(), messageArray));
        } else if (messageArray instanceof AISMessage05) {
            result.putAll(doParsingJob(new AIS05Parser(), messageArray));
        } else if (messageArray instanceof AISMessage09) {
            result.putAll(doParsingJob(new AIS09Parser(), messageArray));
        } else if (messageArray instanceof AISMessage18) {
            result.putAll(doParsingJob(new AIS18Parser(), messageArray));
        } else if (messageArray instanceof AISMessage19) {
            result.putAll(doParsingJob(new AIS19Parser(), messageArray));
        } else if (messageArray instanceof AISMessage21) {
            result.putAll(doParsingJob(new AIS21Parser(), messageArray));
        } else if (messageArray instanceof AISMessage24) {
            result.putAll(doParsingJob(new AIS24Parser(), messageArray));
        } else {
            //TODO not appreciated AIS message 
        }
    }

    private Map<String, Object> doParsingJob(AIS_Parser parser, AISMessage message){
        parser.init(message);
        return parser.parse();
    }
}
