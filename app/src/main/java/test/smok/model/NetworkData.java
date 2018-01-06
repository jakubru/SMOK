package test.smok.model;

/**
 * Created by matthew
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "NetworkData")
public class NetworkData {
    @Element
    private String NetworkType;
    @Element
    private String CID;
    @Element
    private String LAC;
    @Element
    private String ARFCN;
    @Element
    private String BSIC;
    @Element
    private String PSC;
    @Element
    private String BasestationID;
    @Element
    private String Latitude;
    @Element
    private String Longitude;
    @Element
    private String NetworkID;
    @Element
    private String SystemID;
    @Element
    private String CDMADBM;
    @Element
    private String CDMAECIO;
    @Element
    private String CDMALevel;
    @Element
    private String EVDODBM;
    @Element
    private String EVDOECIO;
    @Element
    private String EVDOLevel;
    @Element
    private String EVDOSNR;
    @Element
    private String CI;
    @Element
    private String EARFCN;
    @Element
    private String MCC;
    @Element
    private String MNC;
    @Element
    private String PCI;
    @Element
    private String TAC;
    @Element
    private String AsuLevel;
    @Element
    private String CQI;
    @Element
    private String DBM;
    @Element
    private String Level;
    @Element
    private String RSRP;
    @Element
    private String RSRQ;
    @Element
    private String RSSNR;
    @Element
    private String TimingAdvance;

    public NetworkData() {
        NetworkType = "_";
        CID = "_";
        LAC = "_";
        ARFCN = "_";
        MCC = "_";
        BSIC = "_";
        MNC = "_";
        PSC = "_";
        AsuLevel = "_";
        DBM = "_";
        Level = "_";
        TimingAdvance = "_";
        BasestationID = "_";
        RSRP = "_";
        RSRQ = "_";
        RSSNR = "_";
        CQI = "_";
        PCI = "_";
        TAC = "_";
        Latitude = "_";
        Longitude = "_";
        NetworkID = "_";
        SystemID = "_";
        CDMADBM = "_";
        CDMAECIO = "_";
        CDMALevel = "_";
        EVDODBM = "_";
        EVDOECIO = "_";
        EVDOLevel = "_";
        EVDOSNR = "_";
        CI = "_";
        EARFCN = "_";
    }

    public String getNetworkType() {
        return NetworkType;
    }

    public void setNetworkType(String networkType) {
        NetworkType = networkType;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getLAC() {
        return LAC;
    }

    public void setLAC(String LAC) {
        this.LAC = LAC;
    }

    public String getARFCN() {
        return ARFCN;
    }

    public void setARFCN(String ARFCN) {
        this.ARFCN = ARFCN;
    }

    public String getMCC() {
        return MCC;
    }

    public void setMCC(String MCC) {
        this.MCC = MCC;
    }

    public String getBSIC() {
        return BSIC;
    }

    public void setBSIC(String BSIC) {
        this.BSIC = BSIC;
    }

    public String getMNC() {
        return MNC;
    }

    public void setMNC(String MNC) {
        this.MNC = MNC;
    }

    public String getPSC() {
        return PSC;
    }

    public void setPSC(String PSC) {
        this.PSC = PSC;
    }

    public String getAsuLevel() {
        return AsuLevel;
    }

    public void setAsuLevel(String asuLevel) {
        AsuLevel = asuLevel;
    }

    public String getDBM() {
        return DBM;
    }

    public void setDBM(String DBM) {
        this.DBM = DBM;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getTimingAdvance() {
        return TimingAdvance;
    }

    public void setTimingAdvance(String timingAdvance) {
        TimingAdvance = timingAdvance;
    }

    public String getBasestationID() {
        return BasestationID;
    }

    public void setBasestationID(String basestationID) {
        BasestationID = basestationID;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getNetworkID() {
        return NetworkID;
    }

    public void setNetworkID(String networkID) {
        NetworkID = networkID;
    }

    public String getSystemID() {
        return SystemID;
    }

    public void setSystemID(String systemID) {
        SystemID = systemID;
    }

    public String getCDMADBM() {
        return CDMADBM;
    }

    public void setCDMADBM(String CDMADBM) {
        this.CDMADBM = CDMADBM;
    }

    public String getCDMAECIO() {
        return CDMAECIO;
    }

    public void setCDMAECIO(String CDMAECIO) {
        this.CDMAECIO = CDMAECIO;
    }

    public String getCDMALevel() {
        return CDMALevel;
    }

    public void setCDMALevel(String CDMALevel) {
        this.CDMALevel = CDMALevel;
    }

    public String getEVDODBM() {
        return EVDODBM;
    }

    public void setEVDODBM(String EVDODBM) {
        this.EVDODBM = EVDODBM;
    }

    public String getEVDOECIO() {
        return EVDOECIO;
    }

    public void setEVDOECIO(String EVDOECIO) {
        this.EVDOECIO = EVDOECIO;
    }

    public String getEVDOLevel() {
        return EVDOLevel;
    }

    public void setEVDOLevel(String EVDOLevel) {
        this.EVDOLevel = EVDOLevel;
    }

    public String getEVDOSNR() {
        return EVDOSNR;
    }

    public void setEVDOSNR(String EVDOSNR) {
        this.EVDOSNR = EVDOSNR;
    }

    public String getCI() {
        return CI;
    }

    public void setCI(String CI) {
        this.CI = CI;
    }

    public String getEARFCN() {
        return EARFCN;
    }

    public void setEARFCN(String EARFCN) {
        this.EARFCN = EARFCN;
    }

    public String getPCI() {
        return PCI;
    }

    public void setPCI(String PCI) {
        this.PCI = PCI;
    }

    public String getTAC() {
        return TAC;
    }

    public void setTAC(String TAC) {
        this.TAC = TAC;
    }

    public String getCQI() {
        return CQI;
    }

    public void setCQI(String CQI) {
        this.CQI = CQI;
    }

    public String getRSRP() {
        return RSRP;
    }

    public void setRSRP(String RSRP) {
        this.RSRP = RSRP;
    }

    public String getRSRQ() {
        return RSRQ;
    }

    public void setRSRQ(String RSRQ) {
        this.RSRQ = RSRQ;
    }

    public String getRSSNR() {
        return RSSNR;
    }

    public void setRSSNR(String RSSNR) {
        this.RSSNR = RSSNR;
    }
}
