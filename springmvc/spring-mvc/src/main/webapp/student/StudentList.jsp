<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Student List</title>
<script>
	function showPos(event, data) {
		var el = document.getElementById('viewStudent');
		var x,
			y;
		if (window.event) {
			x = window.event.clientX + document.documentElement.scrollLeft + document.body.scrollLeft;
			y = window.event.clientY + document.documentElement.scrollTop + document.body.scrollTop;
		} else {
			x = event.clientX + window.scrollX;
			y = event.clientY + window.scrollY;
		}
		x -= 2;
		y -= 2;
		y = y + 15;
		el.style.left = x + "px";
		el.style.top = y + "px";
		el.style.display = "block";
		document.getElementById('content').innerHTML = 
			'<img class="avatar" src="/student/avatar/' + data.id + '"/>' +
			'<div class="info"><p>Name: ' + data.name + '</p>' +
			'<p>Age: ' + data.age + '</p></div>';
	}

	function view(id) {
		var xmlHttp = new XMLHttpRequest();
		xmlHttp.open("GET", "json/" + id, true);
		xmlHttp.onload = function() {
			if (this.status != 200) return;
			var student = JSON.parse(this.responseText);
			showPos('onClick', student);
		}
		xmlHttp.send();
	}
</script>
</head>
<body>
	<tiles:insertDefinition name="studentTemplate">
		<tiles:putAttribute name="header">
			<h2>List of Students</h2>
			<a class="btn btn-green" href="/clazz/xml">XML</a>
			<a class="btn btn-green" href="/clazz/json">JSON</a>
			<a class="btn btn-green" href="/clazz/xslt">XSLT</a>
			<a class="btn btn-green" href="/clazz/excel">Excel</a>
			<a class="btn btn-green" href="/clazz/report">Pdf</a>
			<a class="btn btn-green" href="/clazz/pdf">Pdf2</a>
		</tiles:putAttribute>
		<tiles:putAttribute name="body">
			<form method="GET" action="list">
				<table border="1">
					<tr>
						<td colspan="4"><input type="text" name="q" size="30" /></td>
						<td><input type="submit" value="Submit" /></td>
					</tr>
					<tr class="table-header">
						<td>Id</td>
						<td>Name</td>
						<td>Age</td>
					</tr>
					<c:forEach items="${students}" var="student">
						<tr>
							<td>${student.id}</td>
							<td><a href="javascript:view(${student.id})">${student.name}</a></td>
							<td>${student.age}</td>
							<td><a class="btn btn-red" href="delete/${student.id}">Delete</a></td>
							<td><a class="btn btn-green" href="edit?id=${student.id}">Edit</a></td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</tiles:putAttribute>
	</tiles:insertDefinition>

	<div id="viewStudent" style="display: none;">
		<div id="content"></div>
		<button id="hide" onclick="closeDialog()">X</button>
	</div>
	<script type="text/javascript">
		function closeDialog() {
			document.getElementById("viewStudent").style.display = "none";
		}
	</script>
</body>