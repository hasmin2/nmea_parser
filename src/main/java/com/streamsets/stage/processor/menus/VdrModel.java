package com.streamsets.stage.processor.menus;

import com.streamsets.pipeline.api.Label;
import com.streamsets.stage.lib.NMEAParserConstants;


public enum VdrModel implements Label {
    JRC1800(NMEAParserConstants.JRC_1800),
    JRC1900(NMEAParserConstants.JRC_1900),
    CONSILLIUM(NMEAParserConstants.CONSILLIUM),
    FURUNO(NMEAParserConstants.FURUNO),
    GENERAL(NMEAParserConstants.GENERAL);
    private final String label;

    VdrModel(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
