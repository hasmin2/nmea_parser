/*
 * Copyright 2017 StreamSets Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.stage.processor;

import com.streamsets.pipeline.api.Field;
import com.streamsets.pipeline.api.Record;
import com.streamsets.pipeline.api.StageException;
import com.streamsets.pipeline.api.base.SingleLaneRecordProcessor;
import com.streamsets.stage.lib.Errors;
import com.streamsets.stage.processor.Custom_NMEA.CustomNMEAParser;
import com.streamsets.stage.processor.Custom_NMEA.StreamsetsUIMapParser;
import com.streamsets.stage.processor.Std_NMEA.*;
import net.sf.marineapi.nmea.parser.SentenceFactory;
import net.sf.marineapi.nmea.parser.UnsupportedSentenceException;
import net.sf.marineapi.nmea.sentence.*;

import java.util.*;

public abstract class NMEAParser extends SingleLaneRecordProcessor {
    ////////////////////////////////////////////
    public static Map<String, TreeMap<Integer, AISSentence>> aisIDs = new HashMap<>();
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
        List<ConfigIssue> issues = super.init();
        if (getNMEAMap().isEmpty()) {
            issues.add(
                    getContext().createConfigIssue(
                            Groups.NMEAPARSER.name(), "config", Errors.SAMPLE_00, "Here's what's wrong..."
                    )
            );
        }
        // If issues is not empty, the UI will inform the user of each configuration issue in the list.
        return issues;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        // Clean up any open resources.
        super.destroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(Record record, SingleLaneBatchMaker batchMaker) throws StageException {
        String message;// = record.get().getValueAsString();
        List <Field> list = record.get().getValueAsList();
        for(Field item: list){
            message = item.getValueAsString();
        SentenceFactory sf = SentenceFactory.getInstance();
        CustomNMEAParser customParser = new StreamsetsUIMapParser();
        if(getNMEAMap()!=null && !getNMEAMap().isEmpty()) {
            customParser.init(getNMEAMap());
        }
        try {
            if (sf.createParser(message) instanceof AISSentence) {
                doParsingJob(new AISParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof APBSentence) {
                doParsingJob(new APBParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof BODSentence) {
                doParsingJob(new BODParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof CURSentence) {
                doParsingJob(new CURParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof DBTSentence) {
                doParsingJob(new DBTParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof GGASentence) {
                doParsingJob(new GGAParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof GLLSentence) {
                doParsingJob(new GLLParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof GSASentence) {
                doParsingJob(new GSAParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof HDGSentence) {
                doParsingJob(new HDGParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof HDMSentence) {
                doParsingJob(new HDMParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof HDTSentence) {
                doParsingJob(new HDTParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof MDASentence) {
                doParsingJob(new MDAParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof MHUSentence) {
                doParsingJob(new MHUParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof MMBSentence) {
                doParsingJob(new MMBParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof MTASentence) {
                doParsingJob(new MTAParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof MTASentence) {
                doParsingJob(new MTAParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof MTWSentence) {
                doParsingJob(new MTWParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof MWDSentence) {
                doParsingJob(new MWDParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof MWVSentence) {
                doParsingJob(new MWVParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof RMBSentence) {
                doParsingJob(new RMBParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof RMCSentence) {
                doParsingJob(new RMCParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof ROTSentence) {
                doParsingJob(new ROTParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof RPMSentence) {
                doParsingJob(new RPMParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof RSASentence) {
                doParsingJob(new RSAParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof RTESentence) {
                doParsingJob(new RTEParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof TTMSentence) {
                doParsingJob(new TTMParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof VBWSentence) {
                doParsingJob(new VBWParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof VDRSentence) {
                doParsingJob(new VDRParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof VHWSentence) {
                doParsingJob(new VHWParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof VLWSentence) {
                doParsingJob(new VLWParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof VTGSentence) {
                doParsingJob(new VTGParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof VWRSentence) {
                doParsingJob(new VWRParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof VWTSentence) {
                doParsingJob(new VWTParser(), sf.createParser(message));
            } else if (sf.createParser(message) instanceof ZDASentence) {
                doParsingJob(new ZDAParser(), sf.createParser(message));
            }
        }
        catch (Exception use){
            //TODO ANY EXCEPTION should be checked
            use.printStackTrace();
            try {
                customParser.parse(message);
            } catch (NullPointerException ne){
                ne.printStackTrace(); //TODO 널포인트 익셉션에 대한 온레코드에러 메시지 출력필요
            }
        }
        batchMaker.addRecord(record);
        }
    }
    private void doParsingJob(NMEA_Parser parser, Sentence message){
        parser.init(message);
        parser.parse();
    }
}
