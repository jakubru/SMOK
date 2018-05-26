package test.smok.logic;

/**
 * Created by Maxim on 5/22/2018.
 */

public class Arfcn {
    /*
       * Sending request to RIL
       */
//    public void onRun(View view) {
//        // Get the checked button
//        int idButtonChecked = mRadioGroupAPI.getCheckedRadioButtonId();
//
//        byte[] oemhook = null;
//        switch (idButtonChecked) {
//            case R.id.radio_api1:
//                oemhook = new BigInteger("0000000008000000010000005f000000", 16).toByteArray();//hexStringToBytes("0000000008000000010000005f000000");
//                break;
//            case R.id.radio_api2:
//                oemhook = new BigInteger("0100000000000000", 16).toByteArray();
//                break;
//            case R.id.radio_api3:
//                oemhook = new BigInteger("0200000000000000", 16).toByteArray();
//                break;
//            case R.id.radio_api4:
//                // Send OEM/AT command string given by user
//                break;
//            default:
//                log("unknown button selected");
//                break;
//        }
//
//        if (idButtonChecked != R.id.radio_api4) {
//            Message msg = mHandler
//                    .obtainMessage(EVENT_RIL_OEM_HOOK_CMDRAW_COMPLETE);
//            mPhone.invokeOemRilRequestRaw(oemhook, msg);
//            responseTextView.setText("");
//        } else {
//            // Copy string from EditText and add carriage return
//            String oemhookstring = ((EditText) findViewById(R.id.edit_cmdstr))
//                    .getText().toString() + '\r';
//
//            String[] commandString = new String[2];
//            commandString[0] = "UNIAT"; //In 1 of post, citus told to add {"UNIAT", "AT$GSM?\r"};
//            commandString[1] = oemhookstring; //AT-command/String from user input
//            // Create message
//            Message msg = mHandler
//                    .obtainMessage(EVENT_RIL_OEM_HOOK_CMDSTR_COMPLETE);
//            // Send request
//            mPhone.invokeOemRilRequestStrings(commandString, msg);
//            responseTextView.setText("---Wait response---");
//        }
//    }

    /*
     * Parsing the response coming from RIL
     */

//    void displayRILRawResponse(byte[] byteArray) {
//
//        ByteBuffer bb = ByteBuffer.wrap(byteArray);
//        bb.order(ByteOrder.LITTLE_ENDIAN);
//
//        System.out.println("ARFCN: " + bb.getInt());
//        System.out.println("LAC: " + getStringFromBytes(bb, 20));
//        System.out.println("RAC: " + getStringFromBytes(bb, 20));
//        System.out.println("MNC/MCC:" + getStringFromBytes(bb, 20));
//        System.out.println("RSSI:" + bb.getInt());
//        System.out.println("Ncell Info1:" + getStringFromBytes(bb, 20));
//        System.out.println("Ncell Info2:" + getStringFromBytes(bb, 20));
//        System.out.println("Ncell Info3:" + getStringFromBytes(bb, 20));
//        System.out.println("Ncell Info4:" + getStringFromBytes(bb, 20));
//        System.out.println("Ncell Info4:" + getStringFromBytes(bb, 20));
//        System.out.println("Ncell Info6:" + getStringFromBytes(bb, 20));
//        System.out.println("RX Quality:" + bb.getInt());
//        System.out.println("Frequent Hopping:" + bb.getInt());
//        System.out.println("Last registered network:"
//                + getStringFromBytes(bb, 20));
//        System.out.println("TMSI:" + getStringFromBytes(bb, 20));
//        System.out.println("Periodic Location Update Value:" + bb.getInt());
//        System.out.println("BAND:" + bb.getInt());
//        System.out.println("Channel In USe:" + bb.getInt());
//        System.out.println("RSSI 1:" + getStringFromBytes(bb, 20));
//        System.out.println("Last cell release cause:" + bb.getInt());
//    }
}
