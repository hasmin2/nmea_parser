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
import com.streamsets.pipeline.sdk.ProcessorRunner;
import com.streamsets.pipeline.sdk.RecordCreator;
import com.streamsets.pipeline.sdk.StageRunner;
import com.streamsets.stage.processor.menus.VdrModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class TestNMEAParser {
    @Test
    @SuppressWarnings("unchecked")
    public void testProcessor() throws StageException {
        Map<String, String> nmeaString = new HashMap<>();
        nmeaString.put("$PTKM", "rsa,course,heading,alpha,bravo");
        ProcessorRunner runner = new ProcessorRunner.Builder(NMEADParser.class)
                .addConfiguration("nmeaMap", nmeaString)
                .addConfiguration("decodeAIS", true)
                .addConfiguration("inputFieldName", "/text")
                .addConfiguration("vdrModel", VdrModel.JRC1800)
                .addOutputLane("output")
                .build();

        runner.runInit();
        try {
            Record record = RecordCreator.create();
            Record record2 = RecordCreator.create();
            //record.set(Field.create("$YRSA,0,333,340,A,B,C"));
            Map<String, Field> inputA = new HashMap<>();
            inputA.put("text", Field.create("2015/12/04 23:23:02.888,4,$VDVBW,0.0,,A,,,V,,V,,V*68"));
            //inputA.put("text", Field.create("!AIVDM,2,2,8,A,88888888880,2*2C"));
            record.set(Field.create(inputA));
            Map<String, Field> inputB = new HashMap<>();
           // inputB.put("text", Field.create("!AIVDM,2,2,8,A,88888888880,2*2C"));
            //record2.set(Field.create(inputB));
            record.set(Field.create(inputA));
            Record [] recordset = new Record[2];
            recordset[0] = record;
            //recordset[1] = record2;
            StageRunner.Output output = runner.runProcess(Arrays.asList(record));

            //Assert.assertEquals(1, output.getRecords().get("output").size());
            System.out.println(output.getRecords().get("output"));
            //Assert.assertEquals(true, output.getRecords().get("output").get(0).get().getValueAsString());


        } finally {
            runner.runDestroy();
        }
    }
}

