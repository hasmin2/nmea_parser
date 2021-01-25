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

import com.streamsets.pipeline.api.*;
import com.streamsets.stage.lib.NMEAParserConstants;
import com.streamsets.stage.processor.menus.VdrModel;
import com.streamsets.stage.processor.menus.VdrModelChooserValues;

import java.util.Map;

@StageDef(
        version = 1,
        label = "NMEA Parser",
        description = "NMEA Parser w/ AIS decoded message, Should separate AIS data",
        icon = "default.png",
        onlineHelpRefUrl = ""
)
@ConfigGroups(Groups.class)
@GenerateResourceBundle
public class NMEADParser extends NMEAParser {

    @ConfigDef(
            required = true,
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

    public String getVDRModel() {
        return vdrModel.getLabel();
    }


}
