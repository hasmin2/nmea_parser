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
        nmeaString.put("$YRSA", "rsa,course,heading,alpha,bravo,charlie");
        ProcessorRunner runner = new ProcessorRunner.Builder(NMEADParser.class)
                .addConfiguration("nmeaMap", nmeaString)
                .addConfiguration("vdrModel", VdrModel.GENERAL)
                .addOutputLane("output")
                .build();

        runner.runInit();
        try {
            Record record = RecordCreator.create();
            //record.set(Field.create("$YRSA,0,333,340,A,B,C"));
            List<Field> inputA = new ArrayList<>();
            //inputA.add(Field.create("$GPGGA,210230,3855.4487,N,09446.0071,W,1,07,1.1,370.5,M,-29.5,M,,*7A"));
            inputA.add(Field.create("!AIVDM,2,1,8,A,56;OaD02B8EL990b221`P4v1T4pN0HDpN2222216HHN>B6U30A2hCDhD`888,0*4D"));
            inputA.add(Field.create("!AIVDM,2,2,8,A,88888888880,2*2C"));
            //record.set(Field.create("$GPGGA,210230,3855.4487,N,09446.0071,W,1,07,1.1,370.5,M,-29.5,M,,*7A"));
            record.set(Field.create(inputA));
            StageRunner.Output output = runner.runProcess(Arrays.asList(record));
            Assert.assertEquals(1, output.getRecords().get("output").size());
            Assert.assertEquals(true, output.getRecords().get("output").get(0).get().getValueAsBoolean());


        } finally {
            runner.runDestroy();
        }
    }
}
