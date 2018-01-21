package com.yc.Tool;
/**
 *Good Luck
 *
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 数据库表转换成javaBean对象小工具,
 * 要求：数据库表名为xx_xx，转换后的bean名称为XxXx;
 * bean属性按原始数据库字段经过去掉下划线,并大写处理首字母等等.
 */
public class BeanUtil {
    private String EntityName = "";
    private String SoureEntityName="";
    private String[] colnames;
    private String[] colTypes;
    private int[] colSizes; // 列名大小
    private int[] colScale; // 列名小数精度
    private boolean importUtil = false;
    private boolean importSql = false;
    private boolean importMath = false;
    private String ServerName="192.168.0.2:3303";//localhost:3306IP和端口号
    private String BaseName="wuliu_bak";//数据库名称
    private String UserName="XSLCloud";//用户名
    private String PassWord="123456";//密码
    private String rootPath="E://JavaBean/";
    //包名
    private String packageName="com.yc.";
    private String EntityPackge=packageName+"Entity";
    private String SvervicePackge=packageName+"Service";
    private String ControllerPackge=packageName+"Controller";
    private String DaoPackge=packageName+"Dao";
    private String ToolPackge=packageName+"Tool";
    //private String AssignTable="yc_";
    Connection conn = null;
    ResultSetMetaData rsmd=null;
    ResultSet rs=null;
    /**
     * 构造加载conn链接
     * Author:FENG
     */
    BeanUtil(){
    	try {
			conn=DriverManager.getConnection("jdbc:mysql://"+ServerName+"/"+BaseName+"?"
			        + "user="+UserName+"&password="+PassWord+"&useUnicode=true&characterEncoding=UTF8");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * 获取metaData数据集
     * Author:FENG
     * 2016年5月11日
     * @param sql
     * @return
     */
    public ResultSetMetaData getRs(String sql){
    	//获取连接
    	try {
	        //实现sql
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.executeQuery();
	        ResultSetMetaData rsmd = pstmt.getMetaData();
	        return rsmd;
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    /**
     * 获取rsultSet数据集
     * Author:FENG
     * 2016年5月11日
     * @param sql
     * @return
     */
    public ResultSet getRset(String sql){
    	//获取连接
    	try {
    		//实现sql
    		Statement sm = conn.createStatement();
    		ResultSet rs= sm.executeQuery(sql);
    		return rs;
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return null;
    }
    /**
     * @param args
     * Author:FENG
     * @throws ClassNotFoundException 
     */
    public void tableToEntity() throws ClassNotFoundException {
    	//加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        
        try {
        	
        	//获取数据库表名集合的sql
            String te="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='"+BaseName+"'";
            //若要指定生成某一张表，则设置这个为true
            Boolean isSingle=true;
            String SingleTableName="app_version_info";
            //实现sql
            rs=getRset(te);
//            if(!rs.next())return;
            //查看共有多少张表
            while(rs.next()){
            	//获取表名
            	SoureEntityName=rs.getString(1);
            	//指定生成的表名规则
            	/*if(AssignTable!=""){
            		if(SoureEntityName.indexOf(AssignTable)==-1){
            			continue;
            		}
            	}*/
            	//指定单个表
            	if(isSingle){ SoureEntityName=SingleTableName; }
            	EntityName=initcap(SoureEntityName);
            	System.out.println("表名："+EntityName);
                String strsql = "SELECT * FROM " + SoureEntityName +" limit 0,1";//+" WHERE ROWNUM=1" 读一行记录;
                //获取此表的列名
                rsmd=getRs(strsql);
                int size=rsmd.getColumnCount();
                colnames = new String[size];
                colTypes = new String[size];
                colSizes = new int[size];
                colScale = new int[size];
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                	rsmd.getCatalogName(i + 1);
                	colnames[i] = rsmd.getColumnName(i + 1);
                	colTypes[i] = rsmd.getColumnTypeName(i + 1).toLowerCase();
                	colScale[i] = rsmd.getScale(i + 1);
                	if ("datetime".equals(colTypes[i])) {
                		importUtil = true;
                	}
                	if ("image".equals(colTypes[i]) || "text".equals(colTypes[i])) {
                		importSql = true;
                	}
                	if(colScale[i]>0){
                		importMath = true;
                	}
                	colSizes[i] = rsmd.getPrecision(i + 1);
                }
                String content = parse(colnames, colTypes, colSizes);
                String dao=parseDao();
                String service=parseIService();
                String controller=parseController();
                String ServiceImpl=parseServiceImpl();
                String mapper=parseMapper();
//                System.out.println(dao);
//                System.out.println(service);
//                System.out.println(controller);
//                System.out.println(ServiceImpl);
//                System.out.println(mapper);
                try {
                	String DaoPath=rootPath+"Dao";
                	String IServicePath=rootPath+"IService";
                	String ImplPath=rootPath+"IService/Impl";
                	String ControllerPath=rootPath+"Controller";
                	String EntityPath=rootPath+"Entity";
                	String MapperPath=rootPath+"MappingConfig";
                	File file=new File(rootPath);
                	file.mkdir();
                	file=new File(DaoPath);
                	file.mkdir();
                	file=new File(IServicePath);
                	file.mkdir();
                	file=new File(ImplPath);
                	file.mkdir();
                	file=new File(ControllerPath);
                	file.mkdir();
                	file=new File(EntityPath);
                	file.mkdir();
                	file=new File(MapperPath);
                	file.mkdir();
                    FileWriter fw = new FileWriter(EntityPath+"/"+EntityName + ".java");
                    PrintWriter pw = new PrintWriter(fw);
                    pw.println(content);
                    pw.flush();
                    fw=new FileWriter(IServicePath+"/"+"I"+EntityName+"Service" + ".java");
                    pw = new PrintWriter(fw);
                    pw.println(service);
                    pw.flush();
                    fw=new FileWriter(DaoPath+"/"+"I"+EntityName+"Dao" + ".java");
                    pw = new PrintWriter(fw);
                    pw.println(dao);
                    pw.flush();
                    fw=new FileWriter(ControllerPath+"/"+EntityName+"Controller" + ".java");
                    pw = new PrintWriter(fw);
                    pw.println(controller);
                    pw.flush();
                    fw=new FileWriter(ImplPath+"/"+EntityName+"ServiceImpl" + ".java");
                    pw = new PrintWriter(fw);
                    pw.println(ServiceImpl);
                    pw.flush();
                    fw=new FileWriter(MapperPath+"/"+EntityName+"Mapper" + ".xml");
                    pw = new PrintWriter(fw);
                    pw.println(mapper);
                    pw.flush();
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(isSingle)break;
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn!=null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
     /**
      * 生成dao文件
      * Author:FENG
      * 2016年5月11日
      * @param colNames
      * @param colTypes
      * @param colSizes
      * @return
      */
    private String parseDao(){
    	StringBuffer sb = new StringBuffer();
    	sb.append("package "+DaoPackge+"; \r\n");
    	sb.append("import "+EntityPackge+"."+EntityName+"; \r\n");
    	sb.append("import "+ToolPackge+".ISqlDao; \r\n");
    	sb.append("/** \r\n");
    	sb.append("* "+EntityName+"数据层 \r\n");
    	sb.append("* Auther:FENG \r\n");
    	sb.append("*/ \r\n");
    	sb.append("public interface I"+EntityName+"Dao extends ISqlDao<"+EntityName+"> {  \r\n\n");
    	sb.append("}");
    	return sb.toString();
    }
    private String parseIService(){
    	StringBuffer sb = new StringBuffer();
    	sb.append("package "+SvervicePackge+"; \r\n");
    	sb.append("import "+EntityPackge+"."+EntityName+"; \r\n");
    	sb.append("import "+ToolPackge+".ISqlServer; \r\n");
    	sb.append("/** \r\n");
    	sb.append("* "+EntityName+"服务接口层 \r\n");
    	sb.append("* Auther:FENG \r\n");
    	sb.append("*/ \r\n");
    	sb.append("public interface I"+EntityName+"Service extends ISqlServer<"+EntityName+"> {  \r\n\n");
    	sb.append("}");
    	return sb.toString();
    }
    private String parseServiceImpl(){
    	StringBuffer sb = new StringBuffer();
    	sb.append("package "+SvervicePackge+".Impl; \r\n");
    	sb.append("import "+EntityPackge+"."+EntityName+"; \r\n");
    	sb.append("import "+ToolPackge+".Pager; \r\n");
    	sb.append("import "+DaoPackge+".I"+EntityName+"Dao; \r\n");
    	sb.append("import "+SvervicePackge+".I"+EntityName+"Service; \r\n");
    	sb.append("import org.springframework.stereotype.Service; \r\n");
    	sb.append("import org.springframework.beans.factory.annotation.Autowired; \r\n");
    	sb.append("import java.util.Map; \r\n");
    	sb.append("/** \r\n");
    	sb.append("* "+EntityName+"服务层 \r\n");
    	sb.append("* Auther:FENG \r\n");
    	sb.append("*/ \r\n");
    	sb.append("@Service \n");
    	sb.append("public class "+EntityName+"ServiceImpl implements I"+EntityName+"Service { \r\n\n");
    	sb.append("\t@Autowired\r\n");
    	sb.append("\tprivate I"+EntityName+"Dao i"+EntityName+"Dao;\r\n\n\r");
    	sb.append("\t/** \r\n");
    	sb.append("\t* 获取单页记录 \r\n");
    	sb.append("\t* Auther:FENG \r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@Override\r\n");
    	sb.append("\tpublic "+EntityName+" getSingleInfo("+EntityName+" "+EntityName.toLowerCase()+") {\r\n");
    	sb.append("\t\t//TODO Auto-generated method stub\r\n");
    	sb.append("\t\treturn i"+EntityName+"Dao.getSingleInfo("+EntityName.toLowerCase()+");\r\n");
    	sb.append("\t}\r\n");
    	sb.append("\t/** \r\n");
    	sb.append("\t* 获取分页记录 \r\n");
    	sb.append("\t* Auther:FENG \r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@Override\r\n");
    	sb.append("\tpublic Pager<"+EntityName+"> getListPagerInfo(Pager<"+EntityName+"> pager,"+EntityName+" "+EntityName.toLowerCase()+") {\r\n");
    	sb.append("\t\t//TODO Auto-generated method stub\r\n");
    	sb.append("\t\tInteger sum=i"+EntityName+"Dao.getSumCount("+EntityName.toLowerCase()+");\r\n");
    	sb.append("\t\tMap<String,Object> map=pager.getElestMap("+EntityName.toLowerCase()+");\r\n");
    	sb.append("\t\tpager.setObjectList(i"+EntityName+"Dao.getListPagerInfo(map));\r\n");
    	sb.append("\t\tpager.setRecordCount(sum);\r\n");
    	sb.append("\t\treturn pager;\r\n");
    	sb.append("\t}\r\n");
    	sb.append("\t/** \r\n");
    	sb.append("\t* 删除记录 \r\n");
    	sb.append("\t* Auther:FENG \r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@Override\r\n");
    	sb.append("\tpublic Integer delSingleInfo("+EntityName+" "+EntityName.toLowerCase()+") {\r\n");
    	sb.append("\t\t//TODO Auto-generated method stub\r\n");
    	sb.append("\t\treturn i"+EntityName+"Dao.delSingleInfo("+EntityName.toLowerCase()+");\r\n");
    	sb.append("\t}\r\n");
    	sb.append("\t/** \r\n");
    	sb.append("\t* 获取总记录数 \r\n");
    	sb.append("\t* Auther:FENG \r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@Override\r\n");
    	sb.append("\tpublic Integer getSumCount("+EntityName+" "+EntityName.toLowerCase()+") {\r\n");
    	sb.append("\t\t//TODO Auto-generated method stub\r\n");
    	sb.append("\t\treturn i"+EntityName+"Dao.getSumCount("+EntityName.toLowerCase()+");\r\n");
    	sb.append("\t}\r\n");
    	sb.append("\t/** \r\n");
    	sb.append("\t* 修改信息 \r\n");
    	sb.append("\t* Auther:FENG \r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@Override\r\n");
    	sb.append("\tpublic Integer modSingleInfo("+EntityName+" "+EntityName.toLowerCase()+") {\r\n");
    	sb.append("\t\t//TODO Auto-generated method stub\r\n");
    	sb.append("\t\treturn i"+EntityName+"Dao.modSingleInfo("+EntityName.toLowerCase()+");\r\n");
    	sb.append("\t}\r\n");
    	sb.append("\t/** \r\n");
    	sb.append("\t* 添加信息 \r\n");
    	sb.append("\t* Auther:FENG \r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@Override\r\n");
    	sb.append("\tpublic Integer addSingleInfo("+EntityName+" "+EntityName.toLowerCase()+") {\r\n");
    	sb.append("\t\t//TODO Auto-generated method stub\r\n");
    	sb.append("\t\treturn i"+EntityName+"Dao.addSingleInfo("+EntityName.toLowerCase()+");\r\n");
    	sb.append("\t/** \r\n");
    	sb.append("\t* 多条删除 \r\n");
    	sb.append("\t* Auther:FENG \r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@Override\r\n");
    	sb.append("\tpublic Integer delSelect(List<"+EntityName+"> list) {\r\n");
    	sb.append("\t\t//TODO Auto-generated method stub\r\n");
    	sb.append("\t\treturn i"+EntityName+"Dao.delSelect("+EntityName.toLowerCase()+");\r\n");
   
    	sb.append("\t}\r\n");
    	
    	
    	sb.append("}");
    	return sb.toString();
    }
    
    private String parseController(){
    	StringBuffer sb = new StringBuffer();
    	sb.append("package "+ControllerPackge+"; \r\n");
    	sb.append("import javax.servlet.http.HttpServletRequest; \r\n");
    	sb.append("import javax.servlet.http.HttpServletResponse; \r\n");
    	sb.append("import org.springframework.stereotype.Controller; \r\n");
    	sb.append("import org.springframework.web.bind.annotation.RequestMapping;   \r\n");
    	sb.append("import "+EntityPackge+"."+EntityName+"; \r\n");
    	sb.append("import "+SvervicePackge+".I"+EntityName+"Service; \r\n");
    	sb.append("import org.springframework.beans.factory.annotation.Autowired;   \r\n\n");
    	sb.append("/** \r\n");
    	sb.append("* "+EntityName+"控制器 \r\n");
    	sb.append("* Auther:FENG \r\n");
    	sb.append("*/ \r\n");
    	sb.append("@Controller \r\n");
    	sb.append("public class "+EntityName+"Controller {  \r\n");
    	sb.append("\t@Autowired \r\n");
    	sb.append("\tprivate I"+EntityName+"Service i"+EntityName+"Service; \r\n");
    	sb.append("\r\n\n");
    	sb.append("\t/** \r\n");
    	sb.append("\t* 获取单条记录 \r\n");
    	sb.append("\t* Auther:FENG \r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@RequestMapping(\"get"+EntityName+"Single\") \r\n");
    	sb.append("\tpublic void "+"get"+EntityName+"Single"+"(HttpServletRequest request,HttpServletResponse response) {  \r\n");
    	sb.append("\t\ttry{ \r\n");
    	sb.append("\r\n");
    	sb.append("\t\t}catch(Exception e){ \r\n");
    	sb.append("\r\n");
    	sb.append("\t\t} \r\n");
    	sb.append("\t} \r\n");
    	sb.append("\t/** \r\n");
    	sb.append("\t* 获取分页记录 \r\n");
    	sb.append("\t* Auther:FENG \r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@RequestMapping(\"get"+EntityName+"List\") \r\n");
    	sb.append("\tpublic void "+"get"+EntityName+"List"+"(HttpServletRequest request,HttpServletResponse response,"+EntityName+" crt) {  \r\n");
    	sb.append("\t\ttry{ \r\n");
    	sb.append("\r\n");
    	sb.append("\t\t}catch(Exception e){ \r\n");
    	sb.append("\r\n");
    	sb.append("\t\t} \r\n");
    	sb.append("\t} \r\n");
    	sb.append("\t/** \r\n");
    	sb.append("\t* 列表页面 \r\n");
    	sb.append("\t* Auther:FENG \r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@RequestMapping(\"toList"+EntityName+"Page\") \r\n");
    	sb.append("\tpublic String "+"toList"+EntityName+"Page(HttpServletRequest request,HttpServletResponse response) {  \r\n");
    	sb.append("\t\treturn\""+EntityName+"/list_"+SoureEntityName+"\";\r\n");
    	sb.append("\t} \r\n");
      	sb.append("\t/** \r\n");
    	sb.append("\t* 添加方法 \r\n");
    	sb.append("\t* Auther:FENG \r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@RequestMapping(\"add"+EntityName+"\") \r\n");
    	sb.append("\tpublic void "+"add"+EntityName+"(HttpServletRequest request,HttpServletResponse response) {  \r\n");
    	sb.append("\t\ttry{ \r\n");
    	sb.append("\r\n");
    	sb.append("\t\t}catch(Exception e){ \r\n");
    	sb.append("\r\n");
    	sb.append("\t\t} \r\n");
    	sb.append("\t} \r\n");
    	sb.append("\t/** \r\n");
    	sb.append("\t* 添加页面 \r\n");
    	sb.append("\t* Auther:FENG \r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@RequestMapping(\"toAdd"+EntityName+"Page\") \r\n");
    	sb.append("\tpublic String "+"toAdd"+EntityName+"Page(HttpServletRequest request,HttpServletResponse response) {  \r\n");
    	sb.append("\t\treturn \""+EntityName+"/add_"+SoureEntityName+"\"; \r\n");
    	sb.append("\t} \r\n");
    	sb.append("\t/** \r\n");
    	sb.append("\t* 修改方法 \r\n");
    	sb.append("\t* Auther:FENG \r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@RequestMapping(\"mod"+EntityName+"\") \r\n");
    	sb.append("\tpublic void "+"mod"+EntityName+"(HttpServletRequest request,HttpServletResponse response) {  \r\n");
    	sb.append("\t\ttry{ \r\n");
    	sb.append("\r\n");
    	sb.append("\t\t}catch(Exception e){ \r\n");
    	sb.append("\r\n");
    	sb.append("\t\t} \r\n");
    	sb.append("\t} \r\n");
    	sb.append("\t/** \r\n");
    	sb.append("\t* 修改页面 \r\n");
    	sb.append("\t* Auther:FENG \r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@RequestMapping(\"toMod"+EntityName+"Page\") \r\n");
    	sb.append("\tpublic String "+"toMod"+EntityName+"Page(HttpServletRequest request,HttpServletResponse response) {  \r\n");
    	sb.append("\t\treturn \""+EntityName+"/mod_"+SoureEntityName+"\"; \r\n");
    	sb.append("\t} \r\n");
    	sb.append("\t/** \r\n");
    	sb.append("\t* 删除方法 \r\n");
    	sb.append("\t* Auther:FENG \r\n");
    	sb.append("\t*/ \r\n");
    	sb.append("\t@RequestMapping(\"del"+EntityName+"\") \r\n");
    	sb.append("\tpublic void "+"del"+EntityName+"(HttpServletRequest request,HttpServletResponse response) {  \r\n");
    	sb.append("\t\ttry{ \r\n");
    	sb.append("\r\n");
    	sb.append("\t\t}catch(Exception e){ \r\n");
    	sb.append("\r\n");
    	sb.append("\t\t} \r\n");
    	sb.append("\t} \r\n");
    	sb.append("}");
    	return sb.toString();
    }
    private String parseMapper(){
    	StringBuffer sb = new StringBuffer();
    	sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>  \r\n");
    	sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" > \r\n");
    	sb.append("<mapper namespace=\""+DaoPackge+".I"+EntityName+"Dao\"> \r\n");
    	sb.append("\t<resultMap id=\"BaseResultMap\" type=\""+EntityPackge+"."+EntityName+"\"></resultMap>   \r\n");
    	sb.append("\t<sql id=\"Base_Column_List\"> \r\n");
    	sb.append("\t\t"+getSumString(colnames)+"\r\n");
    	sb.append("\t</sql> \r\n");
    	//单一查询方法
    	sb.append("\t<select id=\"getSingleInfo\" resultMap=\"BaseResultMap\" parameterType=\""+EntityPackge+"."+EntityName+"\" > \r\n");
    	sb.append("\t\tselect  <include refid=\"Base_Column_List\" />  from "+SoureEntityName+" where id=#{id} \r\n");
    	sb.append("\t</select> \r\n");
    	//获取总页数方法
    	sb.append("\t<select id=\"getSumCount\" resultType=\"int\" parameterType=\""+EntityPackge+"."+EntityName+"\" > \r\n");
    	sb.append("\t\tselect  count(1)  from "+SoureEntityName+" \r\n");
    	sb.append("\t</select> \r\n");
    	//分页查询
    	sb.append("\t<select id=\"getListPagerInfo\" resultMap=\"BaseResultMap\" parameterType=\"map\"> \r\n");
    	sb.append("\t\tselect <include refid=\"Base_Column_List\" /> from "+SoureEntityName+" order by id desc \r\n");
    	sb.append("\t\tlimit #{limitMax} offset #{limitMin} \r\n");
    	sb.append("\t</select> \r\n");
    	//删除
    	sb.append("\t<delete id=\"delSingleInfo\" parameterType=\""+EntityPackge+"."+EntityName+"\"> \r\n");
    	sb.append("\t\tdelete from "+SoureEntityName+" where id=#{id} \r\n");
    	sb.append("\t</delete> \r\n");
    	//添加 
    	sb.append("\t<insert id=\"addSingleInfo\" parameterType=\""+EntityPackge+"."+EntityName+"\" useGeneratedKeys=\"true\" keyProperty=\"id\"> \r\n");
    	sb.append("\t\tinsert into "+SoureEntityName+"  \r\n");
    	sb.append("\t\t("+getAddSumString(colnames)+") \r\n");
    	sb.append("\t\tvalues ( "+getFormatMapping(colnames)+" )\r\n");
    	sb.append("\t</insert> \r\n");
    	//修改
    	sb.append("\t<update id=\"modSingleInfo\" parameterType=\""+EntityPackge+"."+EntityName+"\">   \r\n");
    	sb.append("\tupdate "+SoureEntityName+" \r\n");
    	sb.append("\t<set> \r\n");
    	sb.append(getFormatIf(colnames));
    	sb.append("\t</set> \r\n");
    	sb.append("\twhere id=#{id} \r\n");
    	sb.append("\t</update> \r\n");
    	sb.append("</mapper> \r\n");
    	return sb.toString();
    }
    /**
     * 解析处理(生成实体类主体代码)
     */
    private String parse(String[] colNames, String[] colTypes, int[] colSizes) {
        StringBuffer sb = new StringBuffer();
        sb.append("package "+EntityPackge+"; \r\n");
        sb.append("\r\nimport java.io.Serializable;\r\n");
        if (importUtil) {
            sb.append("import java.util.Date;\r\n");
        }
        if (importSql) {
            sb.append("import java.sql.*;\r\n\r\n");
        }
        if(importMath){
            sb.append("import java.math.*;\r\n\r\n");
        }
        //表注释
        processColnames(sb);
        sb.append("public class " + EntityName + " implements Serializable {\r\n");
        processAllAttrs(sb);
        processAllMethod(sb);
        sb.append("}\r\n");
        return sb.toString();
 
    }
    /**
     * 构造字符串,用，隔开
     * Author:FENG
     * 2016年5月11日
     */
    private String getSumString(String[] strs){
    	String s="";
    	for(String i:strs){
    		s+=i+",";
    	}
    	s=s.substring(0, s.lastIndexOf(","));
    	return s;
    }
    /**
     * 构造字符串,用，隔开
     * Author:FENG
     * 2016年5月11日
     */
    private String getAddSumString(String[] strs){
    	String s="";
    	Boolean t=true;
    	for(String i:strs){
    		if(t){
    			t=false;
    			continue;
    		}else{
    			s+=i+",";
    		}
    	}
    	if(s.indexOf(",")!=-1){
    		
    		s=s.substring(0, s.lastIndexOf(","));
    	}
    	return s;
    }
    
    /**
     * 构造字符串使用#{}包含
     * Author:FENG
     * 2016年5月11日
     */
    private String getFormatMapping(String[] strs){
    	String s="";
    	Boolean c=true;
    	for(String i:strs){
    		if(c){
    			c=false;
    			continue;
    		}else{
    			String d="#{"+i+"}";
    			s+=d+",";
    		}
    	}
    	s=s.substring(0, s.lastIndexOf(","));
    	return s;
    }
    /**
     * 构造字符串使用if()包含
     * Author:FENG
     * 2016年5月11日
     */
    private String getFormatIf(String[] strs){
//    	<if test="memberId != null">  
//		memberId = #{memberId},  
//	</if>  
    	String s="";
    	Boolean c=true;
    	for(String i:strs){
    		if(c){
    			c=false;
    			continue;
    		}else{
	    		StringBuffer sb=new StringBuffer();
	    		sb.append("\t\t<if test=\""+i+" !=null\"> \r\n");
	    		sb.append("\t\t\t"+i+"=#{"+i+"}"+",\r\n");
	    		sb.append("\t\t</if>\r\n");
	    		s+=sb.toString();
    		}
    	}
    	s=s.substring(0, s.lastIndexOf(","))+s.substring(s.lastIndexOf(",")+1);
    	return s;
    }
    /**
     * 处理列名,把空格下划线'_'去掉,同时把下划线后的首字母大写
     * 要是整个列在3个字符及以内,则去掉'_'后,不把"_"后首字母大写.
     * 同时把数据库列名,列类型写到注释中以便查看,
     * @param sb
     */
    private void processColnames(StringBuffer sb) {
        sb.append("\r\n/** " + EntityName + "\r\n");
        String colsiz="";
        for (int i = 0; i < colnames.length; i++) {
            colsiz = colSizes[i]<=0? "" : (colScale[i]<=0? "("+colSizes[i]+")" : "("+colSizes[i]+","+colScale[i]+")");
            sb.append("\t" + colnames[i].toUpperCase() +"    "+colTypes[i].toUpperCase()+ colsiz+"\r\n");
            char[] ch = colnames[i].toCharArray();
            char c ='a';
            if(ch.length>3){
                for(int j=0;j <ch.length; j++){
                    c = ch[j];
                    if(c == '_'){
                        if (ch[j+1]>= 'a' && ch[j+1] <= 'z') {
                            ch[j+1]=(char) (ch[j+1]-32);
                        }
                    }
                }
            }
            String str = new String(ch);
            colnames[i] = str.replaceAll("_", "");
        }
        sb.append("*/\r\n");
    }
    /**
     * 生成所有的方法
     * 
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic void set" + initMen(colnames[i]) + "("
                    + DataBaseDataType(colTypes[i],colScale[i],colSizes[i]) + " " + colnames[i]
                    + "){\r\n");
            sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
 
            sb.append("\tpublic " + DataBaseDataType(colTypes[i],colScale[i],colSizes[i]) + " get"
                    + initMen(colnames[i]) + "(){\r\n");
            sb.append("\t\treturn " + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
        }
    }
 
    /**
     * 解析输出属性
     * 
     * @return
     */
    private void processAllAttrs(StringBuffer sb) {
        sb.append("\tprivate static final long serialVersionUID = 1L;\r\n");
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tprivate " + DataBaseDataType(colTypes[i],colScale[i],colSizes[i]) + " "
                    + colnames[i] + ";\r\n");
        }
        sb.append("\r\n");
    }
 
    /**
     * 根据规则修改文件名即表名
     * @param str
     * @return
     */
    private String initcap(String str) {
    	//分割字符串
        String[] strs=str.split("_");
        String result="";
        for(String st:strs){
        	 char[] ch = st.toCharArray();
        	 //将首字母变为大写
        	 if (ch[0] >= 'a' && ch[0] <= 'z') {
                 ch[0] = (char) (ch[0] - 32);
             }
        	 String so=new String(ch);
        	 result+=so;
        }
        return  result;
    }
    /**
     * 把输入字符串的首字母改成大写
     * @param str
     * @return
     */
    private String initMen(String str) {
    	char[] ch = str.toCharArray();
    	if (ch[0] >= 'a' && ch[0] <= 'z') {
    		ch[0] = (char) (ch[0] - 32);
    	}
    	return  new String(ch);
    }
 
    /**
     * Oracle
     * @param sqlType
     * @param scale
     * @return
     */
    private String DataBaseDataType(String sqlType, int scale,int size) {
        if (sqlType.equals("int")) {
            return "Integer";
        } else if (sqlType.equals("long")) {
            return "Long";
        } else if(sqlType.equals("bigint") || sqlType.equals("BIGINT") ){
        	return "BigInteger";
        }else if (sqlType.equals("float")
                || sqlType.equals("float precision")
                ) {
            return "Float";
        }else if( sqlType.equals("double")
                || sqlType.equals("double precision")){
        	return "Double";
                	
        }else if (sqlType.equals("number")
                ||sqlType.equals("decimal")
                || sqlType.equals("numeric")
                || sqlType.equals("real")) {
            return scale==0? (size<10? "Integer" : "Long") : "BigDecimal";
        }else if (sqlType.equals("varchar")
                || sqlType.equals("varchar2")
                || sqlType.equals("char")
                || sqlType.equals("nvarchar")
                || sqlType.equals("nchar")) {
            return "String";
        } else if (sqlType.equals("datetime")
                || sqlType.equals("date")
                || sqlType.equals("timestamp")) {
            return "String";
        }
        return null;
    }
 
    /**
     * @param args
     */
    public static void main(String[] args) {
    	BeanUtil t = new BeanUtil();
        try {
			t.tableToEntity();
			System.out.println("完成..");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
}