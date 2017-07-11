import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import Utility.DBUtility;

public class DBUtil {
	
	private String username;
	private String password;
	ArrayList<LinkedHashMap<String, String>> answer = new ArrayList<LinkedHashMap<String,String>>();
	public DBUtil(){

		Scanner s = new Scanner(System.in);
		//String uname = "";
		//String pass = "";
		
		System.out.println("Welcome to the Database!");
		System.out.print("Username: ");
		this.username = s.nextLine();
		
		System.out.print("Password: ");
		this.password = s.nextLine();
		
		//attempt connection
		
		
	}
	
	//what if unam and pword are malicious
	public DBUtil(String uname, String pword){
		this.username = uname;
		this.password = pword;
	}

	public void query() throws IOException{
		
		if(!testConnection()){
			System.out.println("Invalid username or password");
			return;
		}
		
		Scanner s = new Scanner(System.in);

		String query = "";
		answer = new ArrayList<LinkedHashMap<String,String>>();
		
		while(query.compareTo("q") != 0){
			System.out.println("Enter SQL query (q for quitting): ");
			query = s.nextLine();
			String command = "";
			
			if(query.length() > 7){

				command = query.substring(0, 7).toLowerCase();
			
			}
			
			
			if(command.compareTo("select ") == 0){
				
				answer = DBUtility.select(query);
				int m = 0;
				//check 0 records
				if(!answer.isEmpty()){
						System.out.println("(1) Export to file \n(2) Output to console");
						m = s.nextInt();
						
						while(m != 1 && m != 2){
							
							System.out.println("Incorrect option. (1) Export to file \n(2) Output to console");
							m = s.nextInt();
						
						}
						int count = 0;
						if(m == 2){
							
							for(String header: answer.get(0).keySet()){
								System.out.print(header + "\t");
							}
							System.out.println();
							
							for(LinkedHashMap<String, String> e : answer){
								
								if(count > 9){
									break;
								}
								for(String key : e.keySet()){
									System.out.print(e.get(key) + "\t\t");
								}
								System.out.println();
								count++;
							}
						}
						
						if(count > 9 || m == 1){
							File root = new File(Paths.get(".").toAbsolutePath().normalize().toString());
	
							String path = root.getAbsolutePath()+"/";
							
							PrintWriter writer=null;
							try {
								writer = new PrintWriter(new File(path+"output.txt"));
								for(String header: answer.get(0).keySet()){
									writer.print(header + "\t");
								}
								writer.print("\n");
								for(LinkedHashMap<String, String> e : answer){
									
									
									for(String key : e.keySet()){
	
										writer.print(e.get(key) + "\t\t");
									}
									writer.print("\n");
								
								}
							} catch (FileNotFoundException e) {
								System.out.println("File not found");
							}
							writer.close();
							
							System.out.println("Output written to text file with name 'output.txt'");
							
						}
						
						
						
					
				}
				else{
					System.out.println("No records found");
				}
				
				
			}
			//need tp add verification of the number of rows
			else if(command.compareTo("insert ") == 0){
				System.out.println(DBUtility.insert(query) + " row(s) inserted");
			}
			else if(command.compareTo("delete ")==0){
				System.out.println(DBUtility.delete(query) + " row(s) deleted");
			}
			else if(command.compareTo("update ")==0){
				System.out.println(DBUtility.update(query) + " row(s) updated");
			}
		}

		
		
		
		
	}
	
//	
	public boolean testConnection(){
		Connection con = null;
		//Should parse and white list here
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
			String access = "jdbc:oracle:thin:"+username+ "/"+password+"@localhost:1521:orcl";
			
			con = DriverManager.getConnection(access);
			return con.isValid(0);
		}catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
			
			con.close();
			}catch(SQLException e){
				//e.printStackTrace();
				return false;
			}
		}
	}
//	
	public void runSQL(){
		
		Scanner s = new Scanner(System.in);
		System.out.print("Enter SQL Statement: ");
		
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
