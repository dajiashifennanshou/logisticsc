<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");

	String filePath	= (String)request.getAttribute("filePath");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>4.21 对账文件下载接口</title>
</head>

<body>	
	<br>
	<table width="70%" border="0" align="center" cellpadding="5" cellspacing="0" style="word-break:break-all; border:solid 1px #107929">
		<tr>
			<th align="center" height="30" colspan="5" bgcolor="#6BBE18">
				4.21 对账文件下载接口
			</th>
		</tr>
		
		<tr>
			<td width="20%" align="left">&nbsp;</td>
			<td width="80%" align="left">&nbsp;</td>
		</tr>

		<tr>
			<td width="20%" align="right">&nbsp;文件路径: </td>
			<td width="80%" align="left"> 
					<%=filePath%> 
			</td>
		</tr>

		<tr>
			<td width="20%" align="left">&nbsp;</td>
			<td width="80%" align="left">&nbsp;</td>
		</tr>
	</table>
</body>
</html>
