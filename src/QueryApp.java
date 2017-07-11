import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import Utility.DBUtility;

public class QueryApp {

	public static void main(String[] args) {
		
		DBUtil dbUtil = new DBUtil();
		try {
			dbUtil.query();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		DBUtil db = new DBUtil();
////		
//		ArrayList<LinkedHashMap<String, String>> answer = new ArrayList<>();
//		try {
//			answer = DBUtility.select("select * from customers where rownum <= 5");
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		System.out.println("-------------------------");
//		
//		//PROBLEM with order of columns since keyset of hashmap
//		///use linked hashmap
//		//can simplify
//		for(HashMap<String, String> e : answer){
//			
//			for(String key : e.keySet()){
//				System.out.println(key + ": " +e.get(key));
//			}
//			
//			System.out.println("-------------------------");
//		}
//		
//		try {
//			System.out.println("Rows affected: " + DBUtility.update("update cities set city = 'Limbo' where citiesid < 395") + "");
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		System.out.println(DBUtility.getColumnCount());
	}

}
