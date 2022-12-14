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
package com.streamsets.stage.lib;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {

    NMEA_MAP_INVALID("Custom input NMEA parser is invalid 'CSV missing' expected : {}"),
    NMEA_NOT_SUPPORTED_SENTENCE("Invalid NMEA Sentence type or VDR model trying to change model input value is : {}"),
    NMEA_CHECKSUM_ERROR("NMEA checksum error. Input value is: {}"),
    CUSTOMPARSER_NONE_EXIST("Custom NMEA Parser is empty for the input: {}"),
    UNSUPPORTED_SENTENCE_INPUT("Unsupported Sentence, custom parser should be exist for the message: {}"),
    NOT_SUPPORT_DATA_TYPE( "Not support Data type after parsing, ask developer to support this type: {}"),
    NMEA_AIS_NOT_SUPPORTED_SENTENCE("Not figure out AIS Sentence type, supposed to broken message or not support: {}"),
    JRC_INVALID_NMEA_SENTENCE("Could not find begin char '$' check input value for: {}");
    private final String msg;

    Errors(String msg) {
        this.msg = msg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCode() {
        return name();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return msg;
    }
}
