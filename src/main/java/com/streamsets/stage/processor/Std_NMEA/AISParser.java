package com.streamsets.stage.processor.Std_NMEA;

import com.streamsets.pipeline.api.StageException;
import com.streamsets.pipeline.api.base.OnRecordErrorException;
import com.streamsets.stage.lib.Errors;
import com.streamsets.stage.lib.NMEAParserConstants;
import com.streamsets.stage.processor.Std_NMEA.AIS_NMEA.*;
import net.sf.marineapi.ais.message.*;
import net.sf.marineapi.ais.parser.AISMessageFactory;
import net.sf.marineapi.nmea.sentence.AISSentence;
import net.sf.marineapi.nmea.sentence.Sentence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.streamsets.stage.processor.NMEAParser.aisIDs;

//TODO AIS require additional decoding
public class AISParser implements NMEA_Parser{
    private AISMessage messageArray;
    private static final Logger log = LoggerFactory.getLogger(AIS_Parser.class);
    @Override
    public void init(Sentence message) throws StageException {
        if (aisIDs.size() > NMEAParserConstants.AIS_MAX_STORE_VESSEL) {//주변 선박 받다만게 500개가 넘으면 초기화
            aisIDs.clear();
        }
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
                aisIDs.remove(id);//다 보내고 나면 초기화하기
            }
            if(aisIDs.get(id).size() > messageSize){
                aisIDs.remove(id);//길이가 안 맞으면 초기화하기
            }
        }
        else{
            messageArray = AISMessageFactory.getInstance().create(message);
        }
        if(messageArray!=null) {
            log.debug("Decoded Input AIS Message is: {}", messageArray);
        }
    }

    @Override
    public Map<String, Object> parse() {
        Map<String, Object> result = new HashMap<>();
        if(messageArray!=null) {//메시지 어레이가 있을때만
            if (messageArray instanceof AISMessage01) {
                result = doParsingJob(new AIS01Parser(), messageArray);
            } else if (messageArray instanceof AISMessage02) {
                result = doParsingJob(new AIS02Parser(), messageArray);
            } else if (messageArray instanceof AISMessage03) {
                result = doParsingJob(new AIS03Parser(), messageArray);
            } else if (messageArray instanceof AISMessage04) {
                result = doParsingJob(new AIS04Parser(), messageArray);
            } else if (messageArray instanceof AISMessage05) {
                result = doParsingJob(new AIS05Parser(), messageArray);
            } else if (messageArray instanceof AISMessage09) {
                result = doParsingJob(new AIS09Parser(), messageArray);
            } else if (messageArray instanceof AISMessage18) {
                result = doParsingJob(new AIS18Parser(), messageArray);
            } else if (messageArray instanceof AISMessage19) {
                result = doParsingJob(new AIS19Parser(), messageArray);
            } else if (messageArray instanceof AISMessage21) {
                result = doParsingJob(new AIS21Parser(), messageArray);
            } else if (messageArray instanceof AISMessage24) {
                result = doParsingJob(new AIS24Parser(), messageArray);
            } else if (messageArray instanceof AISMessage27) {
                result = doParsingJob(new AIS27Parser(), messageArray);
            } else {
                throw new OnRecordErrorException(Errors.NMEA_AIS_NOT_SUPPORTED_SENTENCE, messageArray.toString());
            }
            messageArray = null;  //다 보내고나면 메시지 어레이 비움.
        }
        return result;
    }

    private Map<String, Object> doParsingJob(AIS_Parser parser, AISMessage message){
        parser.init(message);
        return parser.parse();
    }
}
