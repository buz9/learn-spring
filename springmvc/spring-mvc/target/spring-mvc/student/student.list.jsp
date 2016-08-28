<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function view(id) {
		var xmlHttp = new XMLHttpRequest();
		xmlHttp.open("GET", "json/" + id, true);
		xmlHttp.onload = function() {
			if (this.status != 200) return;
			var student = JSON.parse(this.responseText);
			document.getElementById('content').innerHTML = 'Name: ' + student.name;
			var dialog = document.getElementById('viewStudent');
			dialog.show();
		}
		xmlHttp.send();
	}
</script>
</head>
<body>
	<form method="GET" action="list">
		<table border="1">
			<tr>
				<td colspan="4"><input type="text" name="q" size="30" /></td>
				<td><input type="submit" value="Submit" /></td>
			</tr>
			<tr>
				<td>Id</td>
				<td>Name</td>
				<td>Age</td>
			</tr>
			<c:forEach items="${students}" var="student">
				<tr>
					<td>${student.id}</td>
					<td><a href="javascript:view(${student.id})">${student.name}</a></td>
					<td>${student.age}</td>
					<td><a href="delete/${student.id}">Delete</a></td>
					<td><a href="edit?id=${student.id}">Edit</a></td>
				</tr>
			</c:forEach>
		</table>
	</form>

	<dialog id="viewStudent" style="width:50%;border:1px;dotted:black">
	<div id="content"></div>
	<button id="hide">Close</button>
	</dialog>
	<script type="text/javascript">
		(function() {
			var dialog = document.getElementById("viewStudent");
			document.getElementById('hide').onclick = function() {
				diaglog.close();
			};
		})();
	</script>
</body>
</html>