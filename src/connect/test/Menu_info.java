package connect.test;

import java.io.FileWriter;

import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Menu_info implements Comparable<MenuSet>{

	public static void main(String[] args) {

		String driver = "oracle.jdbc.driver.OracleDriver";

		System.out.print("접속하실 역의 번호를 입력해주세요 : ");
		Scanner sc = new Scanner(System.in);
		int station_num = sc.nextInt();
		System.out.println("역 번호 " + station_num);
		UrlAddr urlAddr = new UrlAddr(station_num);
		System.out.println(urlAddr.getUrl());
		sc.close();

		// 디비 id, pw
		String user = "dams";
		String password = "admin";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String array_1[][] = new String[99][99];

		int index;
		ArrayList<MenuSet> list = new ArrayList<MenuSet>();

		try {

			Class.forName(driver);
			System.out.println("드라이버 연결 성공!");

			conn = DriverManager.getConnection(urlAddr.getUrl(), user, password);
			System.out.println("데이터베이스 접송 성공!");

			stmt = conn.createStatement();

/////////////////////////////////////////////////////////////////////////
			// AREA 에서 기본 정보 받아옴
			String loadarea = "select * from area";

			rs = stmt.executeQuery(loadarea);
			while (rs.next()) {
				// 0 or 1 ??
				index = 1; // 1부터 시작
				String AREA_ID = rs.getString(index++);
				String AREA_NM = rs.getString(index++);
				String ELEC_TYPE = rs.getString(index++);
				String AREA_ORDER = rs.getString(index++);
				String AREA_IP = rs.getString(index++);
				list.add(new MenuSet(AREA_ID, AREA_NM, ELEC_TYPE, AREA_ORDER, AREA_IP));
			}
			rs = null;
			// AREA_ID 순으로 정렬
//			list.sort(null);

			int data_count = list.size() - 1;
			int array_size = data_count + (data_count * 8) + 21;

			// 최종 메뉴테이블
			String[][] arr_menu_data = new String[array_size][7];

///////////////////////////////////////////////////////////////////////////

			// AREA_NM 받아옴
			int j = 0;
			for (int i = 2; i < (data_count + 3); i++) {
				array_1[0][i] = (list.get(j).getAREA_NM());
				array_1[1][i] = (list.get(j).getAREA_ID()); // 모든 AREA_ID가 들어가있음
				j++;
			}

			for (int i = 2; i < data_count + 3; i++) {
				arr_menu_data[i][1] = array_1[0][i];
			}

			// AREA_ID가 0이면 그 행 삭제

			for (int i = 2; i < data_count + 3; i++) {

				if (array_1[1][i].equals("0")) {
					for (int k = i; k < data_count + 2; k++) {
						arr_menu_data[k][1] = arr_menu_data[k + 1][1];
						array_1[1][k] = array_1[1][k + 1];
					}
				}
			}

///////////////////////////////////////////////////////////////////////////////////

			// gswitch_strt 에서 AREA_ID 받아옴/ 선로전환기_1
			String searchdata = "SELECT DISTINCT(AREA_ID) FROM gswitch_strt";

			rs = stmt.executeQuery(searchdata);
			Check strtPmd = new Check(rs);

			// array_1[1] = [1,2,3,4,5,6];
			// strtPmd = [2,4,6];
			for (String areaId : array_1[1]) {
				if (strtPmd.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 10 + data_count;
					arr_menu_data[y][6] = "Y";
				}
			}

			rs = null;
			searchdata = null;
/////////////////////////////////////////////////////////////////////////////////////

			// gswitch4_type_list에서 받아옴/ 선로전환기_2
			searchdata = "SELECT DISTINCT(AREA_ID) FROM gswitch4_type_list";

			rs = stmt.executeQuery(searchdata);
			Check typeListPmd = new Check(rs);

			for (String areaId : array_1[1]) {
				if (typeListPmd.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 10 + data_count;
					arr_menu_data[y][6] = "Y";
				}
			}

			rs = null;
			searchdata = null;
//////////////////////////////////////////////////////////////////////////////////////////

			// 선로전환기 mj_f
			searchdata = "SELECT distinct(area_id) FROM gswitch4_real_mj_f";

			rs = stmt.executeQuery(searchdata);
			Check real_mj_Pmd = new Check(rs);

			for (String areaId : array_1[1]) {
				if (real_mj_Pmd.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 10 + data_count;
					arr_menu_data[y][3] = "/device/pmd/" + x + "/100PMD_MJ";
				}
			}

			rs = null;
			searchdata = null;			
//////////////////////////////////////////////////////////////////////////////////////////
			
			// 선로전환기 dt204_mj
			searchdata = "SELECT distinct(area_id) FROM dt204_mj";
			
			rs = stmt.executeQuery(searchdata);
			Check dt204_mj_Pmd = new Check(rs);
			
			for (String areaId : array_1[1]) {
				if (dt204_mj_Pmd.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 10 + data_count;
					arr_menu_data[y][3] = "/device/pmd/" + x + "/100PMD_MJ";
				}
			}

			rs = null;
			searchdata = null;			 
//////////////////////////////////////////////////////////////////////////////////////////
			  
			// 선로전환기 tswitch_mj
			searchdata = "SELECT distinct(area_id) FROM tswitch_dt_mj_f";
			  
			rs = stmt.executeQuery(searchdata);
			Check tswitch_mj_Pmd = new Check(rs);
				
			for (String areaId : array_1[1]) {
				if (tswitch_mj_Pmd.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 10 + data_count;
					arr_menu_data[y][3] = "/device/pmd/" + x + "/100PMD_MJ";
				}
			}

			rs = null;
			searchdata = null;
//////////////////////////////////////////////////////////////////////////////////////////			  
			  
			// 선로전환기 real_ns
			searchdata = "SELECT distinct(area_id) FROM gswitch4_real_ns_f";
				  
			rs = stmt.executeQuery(searchdata);
			Check real_ns_Pmd = new Check(rs);
					
			for (String areaId : array_1[1]) {
				if (real_ns_Pmd.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 10 + data_count;
					arr_menu_data[y][3] = "/device/pmd/" + x + "/100PMD_NS";
				}
			}

			rs = null;
			searchdata = null;				  
//////////////////////////////////////////////////////////////////////////////////////////
			  
			// 선로전환기 dt204_event
			searchdata = "SELECT distinct(area_id) FROM dt204";
					  
			rs = stmt.executeQuery(searchdata);
			Check dt204_ns_Pmd = new Check(rs);
						
			for (String areaId : array_1[1]) {
				if (dt204_ns_Pmd.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 10 + data_count;
					arr_menu_data[y][3] = "/device/pmd/" + x + "/100PMD_NS";
				}
			}

			rs = null;
			searchdata = null;
//////////////////////////////////////////////////////////////////////////////////////////
			  
			// 선로전환기 ns_f
			searchdata = "SELECT distinct(area_id) FROM tswitch_dt_ns_f";
			
			rs = stmt.executeQuery(searchdata);
			Check tswitch_ns_Pmd = new Check(rs);
						
			for (String areaId : array_1[1]) {
				if (tswitch_ns_Pmd.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 10 + data_count;
					arr_menu_data[y][3] = "/device/pmd/" + x + "/100PMD_NS";
				}
			}

			rs = null;
			searchdata = null;			  
			
			
			
////////////////////////////////////////////////////////////////////////////////////////// 
			
			//선로전환기 NULL일때 값 입력해야함
			
//////////////////////////////////////////////////////////////////////////////////////////
			
			// protocol_spec에서 받아옴/ 궤도회로 
			searchdata = "SELECT DISTINCT(AREA_ID) FROM protocol_spec WHERE sys_id = 102";
			
			rs = stmt.executeQuery(searchdata);
			Check id102_Tlds = new Check(rs);
			
			for (String areaId : array_1[1]) {
				if (id102_Tlds.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 11 + data_count;
					arr_menu_data[y][6] = "Y";
				}
			}

			rs = null;
			searchdata = null;
////////////////////////////////////////////////////////////////////////////////////////
			
			// 궤도회로 확인 100_TLDS_IMP_2
			  
			searchdata = "SELECT DISTINCT(AREA_ID) FROM protocol_spec where sys_id = 102 AND sys_type_id = 0 AND data_type_id = 5 AND D_GROUP_1_ID = 1";
			  
			rs = stmt.executeQuery(searchdata);
			Check Tlds_IMP2 = new Check(rs);
			
			for (String areaId : array_1[1]) {
				if (Tlds_IMP2.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 11 + data_count;
					arr_menu_data[y][3] = "/device/tlds/" + x + "/100_TLDS_IMP";
				}
			}

			rs = null;
			searchdata = null;
			
////////////////////////////////////////////////////////////////////////////////////////
			// 궤도회로 확인 100_TLDS_IMP_1
			  
			searchdata = "SELECT DISTINCT(AREA_ID) FROM protocol_spec where sys_id = 102 AND sys_type_id != 0  AND D_GROUP_1_ID = 1";
			  
			rs = stmt.executeQuery(searchdata);
			Check Tlds_IMP1 = new Check(rs);
			
			for (String areaId : array_1[1]) {
				if (Tlds_IMP1.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 11 + data_count;
					arr_menu_data[y][3] = "/device/tlds/" + x + "/100_TLDS_IMP";
				}
			}

			rs = null;
			searchdata = null;
					
//////////////////////////////////////////////////////////////////////////////////////
			
			// 궤도회로 확인 100_TLDS_IMP
			searchdata = "SELECT DISTINCT(AREA_ID) FROM protocol_spec where sys_id = 102 AND sys_type_id = 0 AND data_type_id != 5 AND D_GROUP_1_ID = 1"; 
			  
			rs = stmt.executeQuery(searchdata);
			Check Tlds_IMP = new Check(rs);
			  
			for (String areaId : array_1[1]) {
				if (Tlds_IMP.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 11 + data_count;
					arr_menu_data[y][3] = "/device/tlds/" + x + "/100_TLDS_IMP";
				}
			}

			rs = null;
			searchdata = null;
//////////////////////////////////////////////////////////////////////////////////////
				
			// 궤도회로 확인 100_TLDS_AF_2
			searchdata = "SELECT DISTINCT(AREA_ID) FROM protocol_spec where sys_id = 102 AND sys_type_id = 0 AND data_type_id = 5 AND D_GROUP_1_ID = 2";
				  
			rs = stmt.executeQuery(searchdata);
			Check Tlds_AF2 = new Check(rs);
			
			for (String areaId : array_1[1]) {
				if (Tlds_AF2.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 11 + data_count;
					arr_menu_data[y][3] = "/device/tlds/" + x + "/100_TLDS_AF";
				}
			}

			rs = null;
			searchdata = null;
//////////////////////////////////////////////////////////////////////////////////////			
				
			// 궤도회로 확인 100_TLDS_AF_1
			searchdata = "SELECT DISTINCT(AREA_ID) FROM protocol_spec where sys_id = 102 AND sys_type_id != 0  AND D_GROUP_1_ID = 2";
			  
			rs = stmt.executeQuery(searchdata);
			Check Tlds_AF1 = new Check(rs);
			
			for (String areaId : array_1[1]) {
				if (Tlds_AF1.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 11 + data_count;
					arr_menu_data[y][3] = "/device/tlds/" + x + "/100_TLDS_AF";
				}
			}

			rs = null;
			searchdata = null;
//////////////////////////////////////////////////////////////////////////////////////
			
			// 궤도회로 확인 100_TLDS_AF
			searchdata = "SELECT DISTINCT(AREA_ID) FROM protocol_spec where sys_id = 102 AND sys_type_id = 0 AND data_type_id != 5 AND D_GROUP_1_ID = 2";
			
			rs = stmt.executeQuery(searchdata);
			Check Tlds_AF = new Check(rs);
						
			for (String areaId : array_1[1]) {
				if (Tlds_AF.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 11 + data_count;
					arr_menu_data[y][3] = "/device/tlds/" + x + "/100_TLDS_AF";
				}
			}

			rs = null;
			searchdata = null;
			
//////////////////////////////////////////////////////////////////////////////////////
			// protocol_spec에서 받아옴/ 전원설비_1 
			searchdata = "SELECT DISTINCT(AREA_ID) FROM protocol_spec WHERE sys_id = 104";
			  
			rs = stmt.executeQuery(searchdata);
			Check id104_PE = new Check(rs);
			  			  
			for (String areaId : array_1[1]) {
				if (id104_PE.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 11 + data_count;
					arr_menu_data[y][6] = "Y";
				}
			}

			rs = null;
			searchdata = null;
//////////////////////////////////////////////////////////////////////////////////////////
			  
			// protocol_spec에서 받아옴/ 전원설비_2 
			searchdata = "SELECT DISTINCT(AREA_ID) FROM protocol_spec WHERE sys_id = 105";
			
			rs = stmt.executeQuery(searchdata);
			Check id105_PE = new Check(rs);
					
			for (String areaId : array_1[1]) {
				if (id105_PE.returnAreaId(areaId)) {
					int x = Integer.parseInt(areaId);
					int y = ((x - 1) * 8) + 11 + data_count;
					arr_menu_data[y][6] = "Y";
				}
			}

			rs = null;
			searchdata = null;					
/////////////////////////////////////////////////////////////////////////////////////////
			  
			

			 
			 Add_data_1(arr_menu_data, data_count);
			 Add_data_2(arr_menu_data, data_count, array_1);
			 Add_data_3(arr_menu_data, data_count); 
			 Add_data_4(arr_menu_data, data_count);
			 Add_data_5(arr_menu_data, data_count);
			 Add_data_final(arr_menu_data, data_count, array_1);
			 NULL_to_null(arr_menu_data);
			 print(arr_menu_data, data_count);


////////////////////////////////////////////////////////////////////////////////////////////

			// csv로 저장
			if (station_num <= 9) {
				FileWriter file = new FileWriter("./SN00" + station_num + ".csv");
				PrintWriter write = new PrintWriter(file);
				for (String[] row : arr_menu_data) {

					String str = String.join(",", new ArrayList<String>(Arrays.asList(row)));

					write.println(str);
				}
				write.close();
			} else {
				FileWriter file = new FileWriter("./SN0" + station_num + ".csv");
				PrintWriter write = new PrintWriter(file);
				for (String[] row : arr_menu_data) {

					String str = String.join(",", new ArrayList<String>(Arrays.asList(row)));

					write.println(str);
				}
				write.close();
			}
		}

		catch (IOException exe) {
			System.out.println("Cannot create file");
		}

//////////////////////////////////////////////////////////////////////////////////////////////

		catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException se) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException se) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException se) {
				}
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////
	public static void Add_data_1(String arr[][], int data_count) {

		arr[0][1] = "본부";
		arr[0][2] = "1";
		arr[1][1] = "사업소";
		arr[1][2] = "2";

		int k = data_count + 2;
		arr[k][1] = "사업소 관리";
		arr[k][6] = "Y";

		arr[k + 1][1] = "시스템관리";
		arr[k + 1][6] = "Y";

		arr[k + 2][1] = "신호설비 상태추이 현황";
		arr[k + 2][2] = "1";
		arr[k + 2][3] = "/repair2";
		arr[k + 2][6] = "Y";

		arr[k + 3][1] = "열화진단 보수교체";
		arr[k + 3][2] = "2";
		arr[k + 3][3] = "/repair/0";
		arr[k + 3][6] = "Y";

		arr[k + 4][1] = "(임시)이상상세조회";
		arr[k + 4][2] = "3";
		arr[k + 4][3] = "/AbnormalQueryAnalysis";
		arr[k + 4][6] = "N";

		arr[k + 5][1] = "보수/교체 상세";
		arr[k + 5][2] = "4";
		arr[k + 5][3] = "/RepairDetail";
		arr[k + 5][6] = "Y";

		arr[k + 6][1] = "진단 유형 증감 현황";
		arr[k + 6][2] = "5";
		arr[k + 6][3] = "/IncDecDetail";
		arr[k + 6][6] = "Y";

		arr[k + 7][1] = "분석 리플레이";
		arr[k + 7][2] = "5";
		arr[k + 7][3] = "/AnalyReplay";
		arr[k + 7][6] = "N";

		for (int i = 0; i < 6; i++) {
			arr[k + i + 2][4] = "MENU_02";
		}
	}

	public static void Add_data_2(String arr[][], int data_count, String array[][]) {

		int z = data_count + 10;
		int w = data_count + 10;

		for (int i = 0; i < arr.length; i++) {

			if (i < 9) {
				arr[i][0] = "MENU_0" + (i + 1);
			} else {
				arr[i][0] = "MENU_" + (i + 1);
			}
		}

		for (int k = 3; k < data_count + 3; k++) {
			for (int i = w; i < w + 8; i++) {

				if (k < 10) {
					arr[i][4] = "MENU_0" + k;
				} else {
					arr[i][4] = "MENU_" + k;
				}
			}
			w += 8;
		}

		for (int i = 0; i < data_count; i++) {
			int y = Integer.parseInt(array[1][i + 2]); // y = AREA_ID

			arr[z][1] = "선로전환기";
			arr[z][2] = "1";

			arr[z + 1][1] = "궤도회로";
			arr[z + 1][2] = "2";

			arr[z + 2][1] = "전원설비";
			arr[z + 2][2] = "3";
			arr[z + 2][3] = "/device/powerEquipment/" + y;

			arr[z + 3][1] = "안전설비";
			arr[z + 3][2] = "4";
			arr[z + 3][3] = "/device/safetyEquipment/" + y;
			arr[z + 3][6] = "N";

			arr[z + 4][1] = "전자연동장치";
			arr[z + 4][2] = "5";
			arr[z + 4][3] = "/device/electronicLinkage/" + y;
			arr[z + 4][6] = "N";

			arr[z + 5][1] = "ATC장치";
			arr[z + 5][2] = "6";
			arr[z + 5][3] = arr[z + 2][3];
			arr[z + 5][6] = "N";

			arr[z + 6][1] = "선로변기능모듈";
			arr[z + 6][2] = "7";
			arr[z + 6][3] = arr[z + 3][3];
			arr[z + 6][6] = "N";

			arr[z + 7][1] = "출입자관리";
			arr[z + 7][2] = "6";
			arr[z + 7][3] = "/EntranceMgmt/" + y;
			arr[z + 7][6] = "Y";

			z += 8;
		}
	}

	public static void Add_data_3(String arr[][], int data_count) {

		int w = data_count + (data_count) * 8 + 10;

		arr[w][1] = "장치기본정보관리(평가기준)";
		arr[w][2] = "1";
		arr[w][3] = "/SignalEvaluationStd";
		arr[w][6] = "Y";

		arr[w + 1][1] = "유지보수관리";
		arr[w + 1][2] = "2";
		arr[w + 1][3] = "/RepairTagging";
		arr[w + 1][6] = "Y";

		arr[w + 2][1] = "통합검색";
		arr[w + 2][2] = "3";
		arr[w + 2][3] = "/TotalSearch";
		arr[w + 2][6] = "Y";

		arr[w + 3][1] = "사용자관리";
		arr[w + 3][2] = "4";
		arr[w + 3][3] = "/ManageUser";
		arr[w + 3][6] = "N";

		arr[w + 4][1] = "보고서";
		arr[w + 4][2] = "5";
		arr[w + 4][3] = "/Report";
		arr[w + 4][6] = "i";

		arr[w + 5][1] = "모델관리";
		arr[w + 5][2] = "1";
		arr[w + 5][3] = "/ScenarioManage";
		arr[w + 5][6] = "Y";

		arr[w + 6][1] = "로그기록";
		arr[w + 6][2] = "2";
		arr[w + 6][3] = "/LookupLog";
		arr[w + 6][6] = "Y";

		arr[w + 7][1] = "메뉴얼";
		arr[w + 7][2] = "3";
		arr[w + 7][3] = "/Manual";
		arr[w + 7][6] = "N";

		arr[w + 8][1] = "시스템설정";
		arr[w + 8][2] = "4";
		arr[w + 8][3] = "/SystemSetting";
		arr[w + 8][6] = "i";

		arr[w + 9][1] = "진단 이력 조회";
		arr[w + 9][2] = "4";
		arr[w + 9][3] = "/DiagnosisHistory";
		arr[w + 9][6] = "Y";

		arr[w + 10][1] = "유지보수";
		arr[w + 10][2] = "5";
		arr[w + 10][3] = "Maintenance";
		arr[w + 10][6] = "";
	}

	public static void Add_data_4(String arr[][], int data_count) {
		int w = data_count + data_count * 8 + 10;

		for (int i = w; i < w + 5; i++) {
			arr[i][4] = "MENU_" + (data_count + 3);
		}
		for (int j = w + 5; j < w + 9; j++) {
			arr[j][4] = "MENU_" + (data_count + 4);
		}
		for (int k = w + 9; k < w + 11; k++) {
			arr[k][4] = "MENU_" + (data_count + 3);
		}
	}

	public static void Add_data_5(String arr[][], int data_count) {

		arr[0][6] = "N";
		arr[0][3] = "/hq";
		arr[1][6] = "Y";

	}

	public static void Add_data_final(String arr[][], int data_count, String array[][]) {

		int point = data_count + 10;
		int point_1 = data_count + 11;
		int point_2 = data_count + 12;
		
		for (int i = 2; i < data_count + 2; i++) {
			arr[i][6] = "Y";
		}
		for (int i = 2; i < data_count + 2; i++) {

			if (arr[point][6] == null) {
				arr[point][6] = "N";
			}
			if (arr[point][3] == null) {
				int y = Integer.parseInt(array[1][i]);
				arr[point][3] = "/device/pmd/" + y + "/NULL";
			}

			if (arr[point_1][6] == null) {
				arr[point_1][6] = "N";

			}
			if (arr[point_2][6] == null) {
				arr[point_2][6] = "N";

			}

			if (arr[point_1][3] == null) {
				int y = Integer.parseInt(array[1][i]);
				arr[point_1][3] = "/device/tlds/" + y + "/NULL"; // y = AREA_ID
			}
			point += 8;
			point_1 += 8;
			point_2 += 8;
		}

		for (int i = 2; i < data_count + 4; i++) {
			arr[i][2] = Integer.toString(i + 1);
		}

	}

	public static void NULL_to_null(String arr[][]) {
		for (int i = 0; i < arr.length; i++) {

			if (arr[i][3] == null) {
				arr[i][3] = "";
			}
			if (arr[i][4] == null) {
				arr[i][4] = "";
			}
			if (arr[i][5] == null) {
				arr[i][5] = "";
			}
		}
	}

	public static void print(String arr[][], int data_count) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	@Override
	public int compareTo(MenuSet o) {
		// TODO Auto-generated method stub
		return Integer.parseInt(o.getAREA_ID()) - Integer.parseInt(o.getAREA_ID());
	}										
}
