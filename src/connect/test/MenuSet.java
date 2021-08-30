package connect.test;

public class MenuSet {

	private String AREA_ID;
	private String AREA_NM;
	private String ELEC_TYPE;
	private String AREA_ORDER;
	private String AREA_IP;
	
	
	public MenuSet(String aREA_ID) {
		AREA_ID = aREA_ID;
	}

	public MenuSet(String aREA_ID, String aREA_NM, String eLEC_TYPE, String aREA_ORDER, String aREA_IP) {
		AREA_ID = aREA_ID;
		AREA_NM = aREA_NM;
		ELEC_TYPE = eLEC_TYPE;
		AREA_ORDER = aREA_ORDER;
		AREA_IP = aREA_IP;
	}

	public String getAREA_ID() {
		return AREA_ID;
	}

	public void setAREA_ID(String aREA_ID) {
		AREA_ID = aREA_ID;
	}

	public String getAREA_NM() {
		return AREA_NM;
	}

	public void setAREA_NM(String aREA_NM) {
		AREA_NM = aREA_NM;
	}

	public String getELEC_TYPE() {
		return ELEC_TYPE;
	}

	public void setELEC_TYPE(String eLEC_TYPE) {
		ELEC_TYPE = eLEC_TYPE;
	}

	public String getAREA_ORDER() {
		return AREA_ORDER;
	}

	public void setAREA_ORDER(String aREA_ORDER) {
		AREA_ORDER = aREA_ORDER;
	}

	public String getAREA_IP() {
		return AREA_IP;
	}

	public void setAREA_IP(String aREA_IP) {
		AREA_IP = aREA_IP;
	}
	
	
	
	
}
