package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeesDAO {
	
	private final String JDBC_URL = "jdbc:mysql://localhost/subject_management";
	private final String DB_USER = "root";
	private final String DB_PASS = "kcsf";
	
	public List<Employee> findAll(){
		List<Employee> empList = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			
			
			//SELECT分の準備
			String sql = "SELECT ID, NAME, AGE FROM EMPLOYEES";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SELECT分の実行及び結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			/*結果票に格納されたレコードの内容を
			 * Employeeインスタンスに設定しArrayListインスタンスに追加
			 */
			
			while(rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				Employee employee = new Employee(id, name, age);
				empList.add(employee);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return empList;
	
	}
	
}
