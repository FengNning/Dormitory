<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var managerName=document.getElementById("managerName").value;
		var password=document.getElementById("password").value;
		var repassword=document.getElementById("repassword").value;
		//宿舍楼名称
		var dormBuildId=document.getElementById("dormBuildId").value;
		var sex=document.getElementById("sex").value;
		var tel=document.getElementById("tel").value;
		if(managerName==""||password==""||repassword==""||dormBuildId==""||sex==""||tel==""){
			document.getElementById("error").innerHTML="信息填写不完整！";
			return false;
		} else if(password!=repassword){
			document.getElementById("error").innerHTML="密码填写不一致！";
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		$("ul li:eq(1)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${dormManager.dormManId!=null }">
				修改管理员
			</c:when>
			<c:otherwise>
				添加管理员
			</c:otherwise>
		</c:choose>
		</div>
		<form action="dormManager?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="dormManId" name="dormManId" value="${dormManager.dormManId }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>管理员姓名：</td>
							<td><input type="text" id="managerName"  name="managerName" value="${dormManager.managerName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>密码：</td>
							<td><input type="password" id="password"  name="password" value="${dormManager.password }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>重复密码：</td>
							<td><input type="password" id="repassword"  name="repassword" value="${dormManager.password }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>性别：</td>
							<td>
								<select id="sex" name="sex" style="width: 90px;">
									<option value="">请选择...</option>
									<option value="男" ${dormManager.sex eq "男"?'selected':'' }>男</option>
									<option value="女" ${dormManager.sex eq "女"?'selected':'' }>女</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><font color="red">*</font>联系电话：</td>
							<td><input type="text" id="tel"  name="tel" value="${dormManager.tel }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>宿舍楼名称：</td>
							<td>
								<select id="dormBuildId" name="dormBuildId" style="width: 90px;">
									<option value="">请选择...</option>
									<c:forEach items="${dormBuildList}" var="dormBuild">
										<c:choose>
											<c:when test="${dormBuild.dormBuildId==dormManager.dormBuildId}">
												<option value="${dormBuild.dormBuildId}" selected>${dormBuild.dormBuildName}</option>
											</c:when>
											<c:otherwise>
												<option value="${dormBuild.dormBuildId}">${dormBuild.dormBuildName}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
					</div>
					<div align="center">
						<font id="error" color="red">${error }</font>
					</div>
			</div>
		</form>
</div>