package connect.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Check{

	private ArrayList<String> arrayIdList = new ArrayList<>();

	public Check(ResultSet rs) {
		try {
			while (rs.next()) {
				arrayIdList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> getArrayIdList() {
		return arrayIdList;
	}
	
	public boolean returnAreaId(String areaId){
		if(arrayIdList.contains(areaId)) {
			return true;
		}
		return false;
	}
}
