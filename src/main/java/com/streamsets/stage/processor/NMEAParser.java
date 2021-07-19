/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * “License”); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an “AS IS” BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.stage.processor;

import com.streamsets.pipeline.api.Field;
import com.streamsets.pipeline.api.Record;
import com.streamsets.pipeline.api.StageException;
import com.streamsets.pipeline.api.base.OnRecordErrorException;
import com.streamsets.pipeline.api.base.SingleLaneRecordProcessor;
import com.streamsets.stage.lib.Errors;
import com.streamsets.stage.processor.Custom_NMEA.CustomNMEAParser;
import com.streamsets.stage.processor.Custom_NMEA.ETLParser;
import com.streamsets.stage.processor.Custom_NMEA.StreamsetsUIMapParser;
import com.streamsets.stage.processor.Std_NMEA.*;
import com.streamsets.stage.processor.menus.VdrModel;
import net.sf.marineapi.nmea.parser.SentenceFactory;
import net.sf.marineapi.nmea.parser.UnsupportedSentenceException;
import net.sf.marineapi.nmea.sentence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class NMEAParser extends SingleLaneRecordProcessor {
    ////////////////////////////////////////////
    public static Map<String, TreeMap<Integer, AISSentence>> aisIDs;
    private static final Logger log = LoggerFactory.getLogger(NMEAParser.class);
    private boolean decodeAIS;
    /////////////////////////////////////////////////////
    /**
     * Gives access to the UI configuration of the stage provided by the {@link NMEADParser} class.
     */
    public abstract Map<String, String> getNMEAMap();

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ConfigIssue> init() {
        // Validate configuration values and open any required resources.
        aisIDs = new HashMap<>();
        decodeAIS = getDecodeAIS();
        List<ConfigIssue> issues = super.init();
        if(!getNMEAMap().isEmpty()) {
            for(String key : getNMEAMap().keySet()) {
                if (getNMEAMap().get(key).split(",").length == 1) {
                    issues.add( getContext().createConfigIssue( Groups.NMEAPARSER.name(), "nmeaMap", Errors.NMEA_MAP_INVALID, "Here's what's wrong..."));
                }
            }
        }
        // If issues is not empty, the UI will inform the user of each configuration issue in the list.
        return issues;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        aisIDs = null;
        super.destroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(Record record, SingleLaneBatchMaker batchMaker) throws StageException {
        String message = extractFromRaw(record.get(getInputFieldName()).getValueAsString());
        Sentence nmeaMessage;
        Map <String, Field> result = new HashMap<>();
        try {
            nmeaMessage = SentenceFactory.getInstance().createParser(message);
            if (decodeAIS && nmeaMessage instanceof AISSentence) {
                result = doParsingJob(new AISParser(), nmeaMessage);
            } else if (nmeaMessage instanceof APBSentence) {
                result = doParsingJob(new APBParser(), nmeaMessage);
            } else if (nmeaMessage instanceof BODSentence) {
                result = doParsingJob(new BODParser(), nmeaMessage);
            } else if (nmeaMessage instanceof CURSentence) {
                result = doParsingJob(new CURParser(), nmeaMessage);
            } else if (nmeaMessage instanceof DBTSentence) {
                result = doParsingJob(new DBTParser(), nmeaMessage);
            } else if (nmeaMessage instanceof DPTSentence) {
                result = doParsingJob(new DPTParser(), nmeaMessage);
            } else if (nmeaMessage instanceof GGASentence) {
                result = doParsingJob(new GGAParser(), nmeaMessage);
            } else if (nmeaMessage instanceof GLLSentence) {
                result = doParsingJob(new GLLParser(), nmeaMessage);
            } else if (nmeaMessage instanceof GSASentence) {
                result = doParsingJob(new GSAParser(), nmeaMessage);
            } else if (nmeaMessage instanceof HDGSentence) {
                result = doParsingJob(new HDGParser(), nmeaMessage);
            } else if (nmeaMessage instanceof HDMSentence) {
                result = doParsingJob(new HDMParser(), nmeaMessage);
            } else if (nmeaMessage instanceof HDTSentence) {
                result = doParsingJob(new HDTParser(), nmeaMessage);
            } else if (nmeaMessage instanceof MDASentence) {
                result = doParsingJob(new MDAParser(), nmeaMessage);
            } else if (nmeaMessage instanceof MHUSentence) {
                result = doParsingJob(new MHUParser(), nmeaMessage);
            } else if (nmeaMessage instanceof MMBSentence) {
                result = doParsingJob(new MMBParser(), nmeaMessage);
            } else if (nmeaMessage instanceof MTASentence) {
                result = doParsingJob(new MTAParser(), nmeaMessage);
            } else if (nmeaMessage instanceof MTWSentence) {
                result = doParsingJob(new MTWParser(), nmeaMessage);
            } else if (nmeaMessage instanceof MWDSentence) {
                result = doParsingJob(new MWDParser(), nmeaMessage);
            } else if (nmeaMessage instanceof MWVSentence) {
                result = doParsingJob(new MWVParser(), nmeaMessage);
            } else if (nmeaMessage instanceof RMBSentence) {
                result = doParsingJob(new RMBParser(), nmeaMessage);
            } else if (nmeaMessage instanceof RMCSentence) {
                result = doParsingJob(new RMCParser(), nmeaMessage);
            } else if (nmeaMessage instanceof ROTSentence) {
                result = doParsingJob(new ROTParser(), nmeaMessage);
            } else if (nmeaMessage instanceof RPMSentence) {
                result = doParsingJob(new RPMParser(), nmeaMessage);
            } else if (nmeaMessage instanceof RSASentence) {
                result = doParsingJob(new RSAParser(), nmeaMessage);
            } else if (nmeaMessage instanceof RTESentence) {
                result = doParsingJob(new RTEParser(), nmeaMessage);
            } else if (nmeaMessage instanceof TTMSentence) {
                result = doParsingJob(new TTMParser(), nmeaMessage);
            } else if (nmeaMessage instanceof VBWSentence) {
                result = doParsingJob(new VBWParser(), nmeaMessage);
            } else if (nmeaMessage instanceof VDRSentence) {
                result = doParsingJob(new VDRParser(), nmeaMessage);
            } else if (nmeaMessage instanceof VHWSentence) {
                result = doParsingJob(new VHWParser(), nmeaMessage);
            } else if (nmeaMessage instanceof VLWSentence) {
                result = doParsingJob(new VLWParser(), nmeaMessage);
            } else if (nmeaMessage instanceof VTGSentence) {
                result = doParsingJob(new VTGParser(), nmeaMessage);
            } else if (nmeaMessage instanceof VWRSentence) {
                result = doParsingJob(new VWRParser(), nmeaMessage);
            } else if (nmeaMessage instanceof VWTSentence) {
                result = doParsingJob(new VWTParser(), nmeaMessage);
            } else if (nmeaMessage instanceof ZDASentence) {
                result = doParsingJob(new ZDAParser(), nmeaMessage);
            } else if (nmeaMessage instanceof UBXSentence) {
                result = doParsingJob(new UBXParser(), nmeaMessage);
            }

        } catch (UnsupportedSentenceException use){
            String header = message.split(",")[0];
            if(header.contains("ETL")){
                CustomNMEAParser customParser = new ETLParser();
                Map<String, String> inputMap = new HashMap<>();
                inputMap.put(header, message.substring(message.indexOf(',')+1, message.length()-1));
                customParser.init(inputMap);
                result = customParser.parse(message);
            }
            else if(getNMEAMap()!=null && !getNMEAMap().isEmpty()) { doCustomParsing(message); }
            else {
                throw new OnRecordErrorException(Errors.CUSTOMPARSER_NONE_EXIST, message);
            }
        } catch (IllegalArgumentException ie){
            throw new OnRecordErrorException(Errors.NMEA_NOT_SUPPORTED_SENTENCE, ie.getMessage());
        } catch (IllegalStateException ise){
            if(getNMEAMap()!=null && !getNMEAMap().isEmpty()) { result = doCustomParsing(message); }
            else { throw new OnRecordErrorException(Errors.NMEA_CHECKSUM_ERROR, message); }
        }
        if(!result.isEmpty()) {
            result.put("timestamp", Field.createDate(new Date()));
            record.set(Field.create(result));
            batchMaker.addRecord(record);
        }
    }
    private Map<String, Field> doCustomParsing(String message) throws OnRecordErrorException{
        CustomNMEAParser customParser = new StreamsetsUIMapParser();
        customParser.init(getNMEAMap());
        Map <String, Field> result;
        result = customParser.parse(message);
        if (result.isEmpty()){
            throw new OnRecordErrorException(Errors.UNSUPPORTED_SENTENCE_INPUT, message);
        }
        return result;
    }
    private String extractFromRaw(String inputValue) {
        String result = null;
        switch (getVDRModel()){
            case JRC1800:
            case JRC1900:
                int findstartIndex = inputValue.indexOf(Sentence.BEGIN_CHAR);
                int findaisIndex = inputValue.indexOf(Sentence.ALTERNATIVE_BEGIN_CHAR);
                if(findstartIndex > -1) {
                    result =  inputValue.substring(findstartIndex);
                }
                if(findaisIndex > -1){
                    result = inputValue.substring(findaisIndex);
                }
                break;
            case CONSILLIUM:
                result = inputValue;
                break;
            case FURUNO:
                result = inputValue;
                break;
            case GENERAL:
                result = inputValue;
                break;
        }

        return result;
    }

    private Map<String, Field> doParsingJob(NMEA_Parser parser, Sentence message){
        parser.init(message);
        Map<String, Field> result = new HashMap<>();
        Map<String, Object>  parseResult = parser.parse();
        for(String key : parseResult.keySet()){
            Object value = parseResult.get(key);
            if(value instanceof Boolean){
                result.put(key, Field.create((boolean)value));
            } else if(value instanceof Integer){
                result.put(key, Field.create((int)value));
            } else if(value instanceof Float){
                result.put(key, Field.create((float)value));
            } else if(value instanceof Double){
                result.put(key, Field.create((double)value));
            } else if(value instanceof String){
                result.put(key, Field.create((String)value));
            } else if(value instanceof Long){
                result.put(key, Field.create((long)value));
            } else if(value instanceof Character){
                result.put(key, Field.create((char) value));
            } else if(value instanceof Date){
                result.put(key, Field.createDate((Date) value));
            } else {
                try {
                    throw new OnRecordErrorException(Errors.NOT_SUPPORT_DATA_TYPE, value.getClass());
                } catch (NullPointerException ne){
                    result.put(key, Field.create("N/A"));
                }
            }
        }
        return result;
    }

    public abstract VdrModel getVDRModel();

    public abstract String getInputFieldName();

    public abstract boolean getDecodeAIS();
}
