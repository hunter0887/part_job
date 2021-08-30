package connect.test;

public class UrlAddr {
	
	private String url = null;

	public UrlAddr(int stationNumber) {

//		this.url = url;
		String url = null;
		switch (stationNumber) {
			case 1:
				url = "jdbc:oracle:thin:@192.168.10.13:1521:SN001";
				break;
	
			case 2:
				url = "jdbc:oracle:thin:@192.168.16.13:1521:SN002";
				break;
	
			case 3:
				url = "jdbc:oracle:thin:@192.168.17.13:1521:SN003";
				break;
	
			case 4:
				url = "jdbc:oracle:thin:@192.168.21.13:1521:SN004";
				break;
	
			case 5:
				url = "jdbc:oracle:thin:@192.168.11.13:1521:SN005";
				break;
	
			case 6:
				url = "jdbc:oracle:thin:@192.168.111.143:1521:SN006";
				break;
	
			case 7:
				url = "jdbc:oracle:thin:@192.168.18.13:1521:SN007";
				break;
	
			case 8:
				url = "jdbc:oracle:thin:@192.168.14.13:1521:SN008";
				break;
	
			case 9:
				url = "jdbc:oracle:thin:@192.168.20.13:1521:SN009";
				break;
	
			case 10:
				url = "jdbc:oracle:thin:@192.168.20.13:1521:SN010";
				break;
	
			case 12:
				url = "jdbc:oracle:thin:@192.168.28.13:1521:SN012";
				break;
	
			case 13:
				url = "jdbc:oracle:thin:@192.168.29.13:1521:SN013";
				break;
	
			case 14:
				url = "jdbc:oracle:thin:@192.168.30.13:1521:SN014";
				break;
	
			case 16:
				url = "jdbc:oracle:thin:@192.168.27.13:1521:SN016";
				break;
	
			case 18:
				url = "jdbc:oracle:thin:@192.168.26.13:1521:SN018";
				break;
	
			case 21:
				url = "jdbc:oracle:thin:@192.168.31.13:1521:SN021";
				break;
	
			case 22:
				url = "jdbc:oracle:thin:@192.168.32.13:1521:SN022";
				break;
	
			case 23:
				url = "jdbc:oracle:thin:@192.168.33.13:1521:SN023";
				break;
	
			case 24:
				url = "jdbc:oracle:thin:@192.168.37.13:1521:SN024";
				break;
	
			case 25:
				url = "jdbc:oracle:thin:@192.168.34.13:1521:SN025";
				break;
	
			case 26:
				url = "jdbc:oracle:thin:@192.168.35.13:1521:SN026";
				break;
	
			case 27:
				url = "jdbc:oracle:thin:@192.168.36.13:1521:SN027";
				break;
	
			case 29:
				url = "jdbc:oracle:thin:@192.168.41.13:1521:SN029";
				break;
	
			case 30:
				url = "jdbc:oracle:thin:@192.168.43.13:1521:SN030";
				break;
	
			case 31:
				url = "jdbc:oracle:thin:@192.168.40.13:1521:SN031";
				break;
	
			case 34:
				url = "jdbc:oracle:thin:@192.168.46.13:1521:SN034";
				break;
	
			case 35:
				url = "jdbc:oracle:thin:@192.168.48.13:1521:SN035";
				break;
	
			case 36:
				url = "jdbc:oracle:thin:@192.168.44.13:1521:SN036";
				break;
	
			case 37:
				url = "jdbc:oracle:thin:@192.168.38.13:1521:SN037";
				break;
	
			case 38:
				url = "jdbc:oracle:thin:@192.168.47.13:1521:SN038";
				break;
	
			case 39:
				url = "jdbc:oracle:thin:@192.168.50.13:1521:SN039";
				break;
	
			case 40:
				url = "jdbc:oracle:thin:@192.168.51.13:1521:SN040";
				break;
	
			case 41:
				url = "jdbc:oracle:thin:@192.168.52.13:1521:SN041";
				break;
	
			case 42:
				url = "jdbc:oracle:thin:@192.168.53.13:1521:SN042";
				break;
	
			case 43:
				url = "jdbc:oracle:thin:@192.168.49.13:1521:SN043";
				break;
	
			case 44:
				url = "jdbc:oracle:thin:@192.168.54.13:1521:SN044";
				break;
	
			case 45:
				url = "jdbc:oracle:thin:@192.168.55.13:1521:SN045";
				break;
	
			case 46:
				url = "jdbc:oracle:thin:@192.168.57.13:1521:SN046";
				break;
	
			case 47:
				url = "jdbc:oracle:thin:@192.168.56.13:1521:SN047";
				break;
	
			case 48:
				url = "jdbc:oracle:thin:@192.168.58.13:1521:SN048";
				break;
	
			case 49:
				url = "jdbc:oracle:thin:@192.168.59.13:1521:SN049";
				break;
	
			case 50:
				url = "jdbc:oracle:thin:@192.168.60.13:1521:SN050";
				break;
	
			case 51:
				url = "jdbc:oracle:thin:@192.168.61.13:1521:SN051";
				break;
	
			case 53:
				url = "jdbc:oracle:thin:@192.168.63.13:1521:SN053";
				break;
	
			case 56:
				url = "jdbc:oracle:thin:@192.168.66.13:1521:SN056";
				break;
	
			case 57:
				url = "jdbc:oracle:thin:@192.168.69.13:1521:SN057";
				break;
	
			case 58:
				url = "jdbc:oracle:thin:@192.168.71.13:1521:SN058";
				break;
	
			case 59:
				url = "jdbc:oracle:thin:@192.168.72.13:1521:SN059";
				break;
			default :
				return;
		}
		
		if(url == null) {
//			exception ¿œ¿∏≈¥.
		}
		
		this.url =url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
