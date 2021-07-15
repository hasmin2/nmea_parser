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

import com.streamsets.pipeline.api.*;
import com.streamsets.stage.lib.NMEAParserConstants;
import com.streamsets.stage.processor.menus.VdrModel;
import com.streamsets.stage.processor.menus.VdrModelChooserValues;

import java.util.Map;

@StageDef(
        version = 1,
        label = NMEAParserConstants.STAGE_LABEL,
        description = NMEAParserConstants.STAGE_DESC,
        icon = NMEAParserConstants.STAGE_ICON,
        onlineHelpRefUrl = NMEAParserConstants.STAGE_HELP_URL
)
@ConfigGroups(Groups.class)
@GenerateResourceBundle
public class NMEADParser extends NMEAParser {

    @ConfigDef(
            required = false,
            type = ConfigDef.Type.MAP,
            defaultValue = "",
            label = NMEAParserConstants.NMEA_PARSER_LABEL,
            description = NMEAParserConstants.NMEA_PARSER_DESC,
            displayPosition = 10,
            group = NMEAParserConstants.NMEA_PARSER_MENUSTRING
    )
    public Map<String, String> nmeaMap;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.STRING,
            defaultValue = "/text",
            label = NMEAParserConstants.INPUT_FIELD_LABEL,
            description = NMEAParserConstants.INPUT_FIELD_DESC,
            displayPosition = 10,
            group = NMEAParserConstants.NMEA_PARSER_MENUSTRING
    )
    public String inputFieldName;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.BOOLEAN,
            defaultValue = "true",
            label = NMEAParserConstants.AIS_DECODE_LABEL,
            description = NMEAParserConstants.AIS_DECODE_DESC,
            displayPosition = 1,
            group = NMEAParserConstants.NMEA_PARSER_MENUSTRING
    )
    public boolean decodeAIS;
    @ConfigDef(
            required = true,
            type = ConfigDef.Type.MODEL,
            defaultValue = "GENERAL",
            label = NMEAParserConstants.VDR_MODEL_LABEL,
            displayPosition = 10,
            group = NMEAParserConstants.VDR_MODEL_MENUSTRING,
            description = NMEAParserConstants.VDR_MODEL_DESC
    )
    @ValueChooserModel(VdrModelChooserValues.class)
    public VdrModel vdrModel;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getNMEAMap() {
        return nmeaMap;
    }
    @Override
    public VdrModel getVDRModel() {
        return vdrModel;
    }
    @Override
    public String getInputFieldName() { return inputFieldName; }
    @Override
    public boolean getDecodeAIS() { return decodeAIS; }


}
