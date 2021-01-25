package com.streamsets.stage.processor;

import com.streamsets.pipeline.api.Field;
import com.streamsets.pipeline.api.Record;
import com.streamsets.pipeline.api.base.SingleLaneProcessor;
import net.sf.marineapi.ais.message.AISMessage24;

import java.util.HashMap;
import java.util.Map;

import static com.streamsets.pipeline.api.base.SingleLaneProcessor.*;


public class AISRecordGenerator {

   /* private String callSign;
    private double lat, lon;
    public AISRecordGenerator(AisMessage message){
        // Handle AtoN message
        if (message instanceof AisMessage21) {
            AisMessage21 msg21 = (AisMessage21) message;
            lat = msg21.getPos().getLatitude();
            lon = msg21.getPos().getLongitude();
            callSign = msg21.getName();
            msg21.getDimBow();
            msg21.getDimPort();
            msg21.getDimStarboard();
            msg21.getDimStern();
        }
        // Handle position messages 1,2 and 3 (class A) by using their shared parent
        if (message instanceof AisPositionMessage) {
            AisPositionMessage posMessage = (AisPositionMessage)message;
        }
        // Handle position messages 1,2,3 and 18 (class A and B)
        if (message instanceof IVesselPositionMessage) {
            IVesselPositionMessage posMessage = (IVesselPositionMessage) message;
            System.out.println("course over ground: " + posMessage.getCog());
        }
        // Handle static reports for both class A and B vessels (msg 5 + 24)
        if (message instanceof AisStaticCommon) {
            AisStaticCommon staticMessage = (AisStaticCommon) message;
            System.out.println("vessel name: " + staticMessage.getName());
            staticMessage.getName();
            staticMessage.getDimBow();
            staticMessage.getDimPort();

        }*/

}
/*
@Override
public void accept(AisMessage aisMessage) {
	// Handle AtoN message
	if (aisMessage instanceof AisMessage21) {
		AisMessage21 msg21 = (AisMessage21) aisMessage;
		System.out.println("AtoN name: " + msg21.getName());
	}
	// Handle position messages 1,2 and 3 (class A) by using their shared parent
	if (aisMessage instanceof AisPositionMessage) {
		AisPositionMessage posMessage = (AisPositionMessage)aisMessage;
		System.out.println("speed over ground: " + posMessage.getSog());
	}
	// Handle position messages 1,2,3 and 18 (class A and B)
	if (aisMessage instanceof IGeneralPositionMessage) {
		IGeneralPositionMessage posMessage = (IGeneralPositionMessage)aisMessage;
		System.out.println("course over ground: " + posMessage.getCog());
	}
	// Handle static reports for both class A and B vessels (msg 5 + 24)
	if (aisMessage instanceof AisStaticCommon) {
		AisStaticCommon staticMessage = (AisStaticCommon)aisMessage;
		System.out.println("vessel name: " + staticMessage.getName());


 */