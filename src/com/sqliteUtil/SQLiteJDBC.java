package com.sqliteUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLiteJDBC
{
  private static String jdbc_url="jdbc:sqlite:c:/sqlite.db";
  private static Connection c = null;
  private static Statement stmt = null;
  public static void main( String args[] )
  {
//    Connection c = null;
//    try {
//      Class.forName("org.sqlite.JDBC");
//      c = DriverManager.getConnection("jdbc:sqlite:d:/sqlite/test.db");
//    } catch ( Exception e ) {
//      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//      System.exit(0);
//    }
//    System.out.println("Opened database successfully");
	  
//	  createTable();
	  
//	  insert("aaa","22","update","333");
	  
	  List<Map> list = query();
  }
  
  public static void createTable()
  {
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection(jdbc_url);
      System.out.println("Opened database successfully");

//      stmt = c.createStatement();
//      String sql = "CREATE TABLE QD ( NAME  TEXT UNIQUE,NUMS TEXT,UnitType TEXT,UpdateTime TEXT)"; 
//      stmt.executeUpdate(sql);
//      stmt.close();
//      
//      stmt = c.createStatement();
//      String sqloperadd = "CREATE TABLE OPERATE ( NAME  TEXT ,NUMS TEXT,OPERATE TEXT,UpdateTime TEXT)";
//      stmt.executeUpdate(sqloperadd);
//      stmt.close();
      
      stmt = c.createStatement();
      String sqloperadd = "CREATE TABLE WORKBENCH ( ID TEXT,NAME  TEXT UNIQUE,VALUE TEXT,REMARK TEXT,FWIP TEXT,STATUS_ TEXT)";
      stmt.executeUpdate(sqloperadd);
      stmt.close();
     
      
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      try {stmt.close();c.close();} catch (SQLException e1) {}
    }
    System.out.println("Table created successfully");
  }
  
  
  /**
   * 服务主机运行状态
   * @param value
   * @return
   */
  public static String insertStatus(String value){
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(jdbc_url);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      //当前时间
	      Date date = new Date();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String now = sdf.format(date);
		  //当前时间
		  stmt = c.createStatement();
		  String sql="";
		  sql = " INSERT INTO FWSTATUS (VALUE) VALUES ('"+value+"'); "; 
	      
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      try {stmt.close();c.close();} catch (SQLException e1) {}
	      return Common.ERROR;
	    }
	    System.out.println("Records created successfully");
	    return Common.SUCCESS;
}
  
	  /**
	   * 清空表数据
	   * @return
	   */
	  public static String clearAllStatus(){
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(jdbc_url);
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");
		      //当前时间
		      Date date = new Date();
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  String now = sdf.format(date);
			  //当前时间
			  stmt = c.createStatement();
			  String sql="";
			  sql = " delete from fwstatus"; 
		      stmt.executeUpdate(sql);
		      stmt.close();
		      c.commit();
		      c.close();
		    } catch ( Exception e ) {
		      e.printStackTrace();
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      try {stmt.close();c.close();} catch (SQLException e1) {}
		      return Common.ERROR;
		    }
		    System.out.println("Records created successfully");
		    return Common.SUCCESS;
	}
	  /**
	   * 查询状态
	   * @return
	   */
	  public static List<Map> queryAllStatus(){
		  try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(jdbc_url);
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");
		      List<Map> list=new ArrayList();
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT VALUE FROM FWSTATUS;" );
		      while ( rs.next() ) {
			     String  value = rs.getString("VALUE");
		    	 Map map=new HashMap();
		    	 map.put("VALUE", value);
		         list.add(map);
//		         System.out.println(name+nums+unit+updatetime);
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		      return list;
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      try {stmt.close();c.close();} catch (SQLException e1) {}
		      return null;
		    }
	}
	  /**
	   * 查询状态
	   * @return
	   */
	  public static List<Map> queryAllFWIP(){
		  try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection(jdbc_url);
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");
		      List<Map> list=new ArrayList();
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT FWIP FROM WORKBENCH GROUP BY FWIP;" );
		      while ( rs.next() ) {
			     String  value = rs.getString(Common.FWIP);
		    	 Map map=new HashMap();
		    	 map.put(Common.FWIP, value);
		         list.add(map);
//		         System.out.println(name+nums+unit+updatetime);
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		      return list;
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      try {stmt.close();c.close();} catch (SQLException e1) {}
		      return null;
		    }
	}
	  
  /**
   * 查询当前清单
   */
  public static List<Map> query(){
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(jdbc_url);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      List<Map> list=new ArrayList();
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM WORKBENCH;" );
	      while ( rs.next() ) {
	    	 String  id = rs.getString("ID");
	    	 String  name = rs.getString("NAME");
		     String  value = rs.getString("VALUE");
		     String  remark = rs.getString("REMARK");
		     String  fwip = rs.getString("FWIP");
		     String  status = rs.getString("STATUS");
		     String  serverip = rs.getString("SERVERIP");
	    	 Map map=new HashMap();
	    	 map.put("ID", id);
	    	 map.put("NAME", name);
	    	 map.put("VALUE", value);
	    	 map.put("REMARK", remark);
	    	 map.put("FWIP", fwip);
	    	 map.put("STATUS", status);
	    	 map.put("SERVERIP", serverip);
	         list.add(map);
//	         System.out.println(name+nums+unit+updatetime);
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	      return list;
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      try {stmt.close();c.close();} catch (SQLException e1) {}
	      return null;
	    }
  }
  
  /**
   * 根据ID查询
   * @param param
   * @return
   */
  public static List<Map> query(String param){
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(jdbc_url);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      List<Map> list=new ArrayList();
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM WORKBENCH where id='"+param+"'" );
	      while ( rs.next() ) {
	    	 String  id = rs.getString("ID");
	    	 String  name = rs.getString("NAME");
		     String  value = rs.getString("VALUE");
		     String  remark = rs.getString("REMARK");
		     String  fwip = rs.getString("FWIP");
		     String  serverip = rs.getString("SERVERIP");
	    	 Map map=new HashMap();
	    	 map.put("ID", id);
	    	 map.put("NAME", name);
	    	 map.put("VALUE", value);
	    	 map.put("REMARK", remark);
	    	 map.put("FWIP", fwip);
	    	 map.put("SERVERIP", serverip);
	         list.add(map);
//	         System.out.println(name+nums+unit+updatetime);
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	      return list;
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      try {stmt.close();c.close();} catch (SQLException e1) {}
	      return null;
	    }
}
  
  /**
   * 根据ID查询
   * @param param
   * @return
   */
  public static List<Map> queryAllServer(){
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(jdbc_url);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      List<Map> list=new ArrayList();
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT FWIP FROM WORKBENCH GROUP BY FWIP" );
	      while ( rs.next() ) {
		     String  fwip = rs.getString("FWIP");
	    	 Map map=new HashMap();
	    	 map.put("FWIP", fwip);
	         list.add(map);
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	      return list;
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      try {stmt.close();c.close();} catch (SQLException e1) {}
	      return null;
	    }
}
  
  /**
   * 新增清单
   * @param name
   * @param unit
   */
  public static String insert(String id,String name,String value,String remark,String fwip){
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(jdbc_url);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      //当前时间
	      Date date = new Date();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String now = sdf.format(date);
		  //当前时间
		  stmt = c.createStatement();
		  String sql="";
//		  if(flag.equals("save")){
			  sql = " INSERT INTO WORKBENCH (ID,NAME,VALUE,REMARK,FWIP) VALUES ('"+id+"','"+name+"','"+value+"','"+remark+"','"+fwip+"'); "; 
//		  }else{
//			  sql = " UPDATE QD set NUMS = '"+nums+"',UpdateTime='"+now+"' where NAME='"+name+"'; ";
//		  }
	      
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      try {stmt.close();c.close();} catch (SQLException e1) {}
	      return Common.ERROR;
	    }
	    System.out.println("Records created successfully");
	    return Common.SUCCESS;
  }
  
  public static String update(String id,String name,String value,String remark,String fwip){
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(jdbc_url);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      //当前时间
	      Date date = new Date();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String now = sdf.format(date);
		  //当前时间
		  stmt = c.createStatement();
		  String sql="";
//		  if(flag.equals("save")){
			  sql = " INSERT INTO WORKBENCH (ID,NAME,VALUE,REMARK) VALUES ('"+id+"','"+name+"','"+value+"','"+remark+"','"+fwip+"'); "; 
//		  }else{
			  sql = " UPDATE WORKBENCH set NAME = '"+name+"',VALUE='"+value+"',REMARK='"+remark+"',FWIP='"+fwip+"' where ID='"+id+"' ";
//		  }
	      
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      try {stmt.close();c.close();} catch (SQLException e1) {}
	      return Common.ERROR;
	    }
	    System.out.println("Records created successfully");
	    return Common.SUCCESS;
}
  
  public static String commonUpdate(String sql){
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(jdbc_url);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      //当前时间
	      Date date = new Date();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String now = sdf.format(date);
		  //当前时间
		  stmt = c.createStatement();
//		  String sql="";
//		  if(flag.equals("save")){
//			  sql = " INSERT INTO WORKBENCH (ID,NAME,VALUE,REMARK) VALUES ('"+id+"','"+name+"','"+value+"','"+remark+"','"+fwip+"'); "; 
//		  }else{
//			  sql = " UPDATE WORKBENCH set NAME = '"+name+"',VALUE='"+value+"',REMARK='"+remark+"',FWIP='"+fwip+"' where ID='"+id+"' ";
//		  }
	      
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      try {stmt.close();c.close();} catch (SQLException e1) {}
	      return Common.ERROR;
	    }
	    System.out.println("Records created successfully");
	    return Common.SUCCESS;
}
  /**
   * 删除清单
   * @param name
   */
  public static void delete(String id){
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(jdbc_url);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "DELETE from WORKBENCH where ID='"+id+"';";
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      try {stmt.close();c.close();} catch (SQLException e1) {}
	    }
	    System.out.println("Operation done successfully");
  }
  ////////////////////////////////OPERATE////////////////////////////////////////
  /**
   * Invalid
   * 新增清单
   * @param name
   * @param unit
   */
  public static String insertOperate(String name,String nums,String operate){
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(jdbc_url);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      //当前时间
	      Date date = new Date();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String now = sdf.format(date);
		  //当前时间
		  stmt = c.createStatement();
		  String sql = " INSERT INTO OPERATE (NAME,NUMS,OPERATE,UpdateTime) VALUES ('"+name+"','"+nums+"','"+operate+"','"+now+"'); "; 
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      try {stmt.close();c.close();} catch (SQLException e1) {}
	      return Common.ERROR;
	    }
	    System.out.println("Records created successfully");
	    return Common.SUCCESS;
  }

  /**
   * Invalid
   * 查询当前清单
   */
  public static List<Map> queryAdd(){
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(jdbc_url);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      List<Map> list=new ArrayList();
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM QD;" );
	      while ( rs.next() ) {
	    	 String  name = rs.getString("name");
		     String  nums = rs.getString("nums");
		     String  unittype = rs.getString("unittype");
		     String  updatetime = rs.getString("updatetime");
	    	 Map map=new HashMap();
	    	 map.put("name", name);
	    	 map.put("nums", nums);
	    	 map.put("unittype", unittype);
	    	 map.put("updatetime", updatetime);
	         list.add(map);
//	         System.out.println(name+nums+unit+updatetime);
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	      return list;
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      try {stmt.close();c.close();} catch (SQLException e1) {}
	      return null;
	    }
  }
  
}