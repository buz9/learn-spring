<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%-- <tiles:insertAttribute name="title" ignore="true" /> --%></title>
<style>
table {
	text-align: center;	
}
table table, table input {
	width: 100%;
}
.table-header {
	font-size: 20px;
}
button#hide {
	position: absolute;
	cursor: pointer;
	top: 5px;
	right: 5px;
	border: 0;
	font-size: 18px;
	padding: 2px 5px;
}
#viewStudent {
	background-color: #bdc3c7;
	position: absolute;
	left: 100px;
	top: 50px;
	padding: 10px;
	font-size: 12px;
	border: 2px solid black;
	width: 50%;
}
#viewStudent #content {
	padding-top: 30px;
}
#viewStudent .avatar {
	width: 200px;
	vertical-align: middle;
}
#viewStudent .info {
	display: inline-block;
	padding-left: 30px;
	font-size: 25px;
}
.btn {
	margin: 5px;
	border: none;
	border-radius: 5px;
	color: white;
	padding: 8px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
}
.btn.btn-green {
	background-color: #4CAF50;
}
.btn.btn-red {
	background-color: #f44336;
}
.btn:hover {
	background-color: white;
    color: black;
    border: 2px solid; 
    -webkit-transition-duration: 0.4s;
    transition-duration: 0.4s;
}
.btn.btn-green:hover {
	 border-color: #4CAF50;
}
.btn.btn-red:hover {
	 border-color: #f44336;
}
</style>
</head>
<body>
	<table border="1" cellpadding="2" cellspacing="2" align="center">
		<tr>
			<td height="30" colspan="2"><tiles:insertAttribute name="header" />
			</td>
		</tr>
		<tr>
			<td height="350"><tiles:insertAttribute name="menu" /></td>
			<td width="550"><tiles:insertAttribute name="body" /></td>
		</tr>
		<tr>
			<td height="30" colspan="2"><tiles:insertAttribute name="footer" />
			</td>
		</tr>
	</table>
</body>
</html>