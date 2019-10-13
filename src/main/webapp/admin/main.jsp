<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人博客系统后台管理系统</title>
	<link href="${pageContext.request.contextPath}/favicon.ico" rel="SHORTCUT ICON">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<%--	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
	<script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>--%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

	var url;
	
	function openTab(text,url,iconCls){
		if($("#tabs").tabs("exists",text)){
			$("#tabs").tabs("select",text);
		}else{
			var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/admin/"+url+"'></iframe>";
			$("#tabs").tabs("add",{
				title:text,
				iconCls:iconCls,
				closable:true,
				content:content
			});
		}
	}
	
	/**刷新系统缓存*/
	function refreshSystem(){
		$.post("${pageContext.request.contextPath}/admin/system/refreshSystem.do",{},function(result){
			if(result.success){
				$.messager.alert("系统提示","已成功刷新系统缓存！");
			}else{
				$.messager.alert("系统提示","刷新系统缓存失败！");
			}
		},"json");
	}
	
	/**打开修改密码对话框*/
	function openPasswordModifyDialog(){
		$("#dlg").dialog("open").dialog("setTitle","修改密码");
		url="${pageContext.request.contextPath}/admin/blogger/modifyPassword.do?id=${currentUser.id}";
	}
	
	/**修改密码*/
	function modifyPassword(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				var newPassword = $("#newPassword").val();
				var newPassword2 = $("#newPassword2").val();
				if(!$(this).form("validate")){
					return false;
				}
				if(newPassword!=newPassword2){
					$.messager.alert("系统提示","两次密码输入不一致！");
					return false;
				}
				return true;
			},
			success:function(result){
				var result = eval('('+result+')');
				if(result.success){
					$.messager.alert("系统提示","密码修改成功，下一次登录生效！");
					closePasswordModifyDialog();
				}else{
					$.messager.alert("系统提示","密码修改失败！");
					return;
				}
             }
		});
	}
	
	/**清空俩新密码*/
	function resetValue(){
		$("#newPassword").val("");
		$("#newPassword2").val("");
	}
	
	/**关闭密码修改对话框*/
	function closePasswordModifyDialog(){
		resetValue();
		$("#dlg").dialog("close");
	}
	
	/**退出*/
	function logout(){
		$.messager.confirm("系统提示","您确定要退出系统吗？",function(r){
			if(r){
				window.location.href="${pageContext.request.contextPath}/admin/blogger/logout.do";
			}
		});
	}
	
</script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 60px;background-color: rgba(191,237,255,0.98);overflow:hidden">
	<table style="padding: 5px" width="100%">
		<tr>
			<td style="padding-top: 10px" valign="middle" align="left" width="50%">
				<font size="3" style="color: #3b4249">&nbsp;&nbsp;<strong>个人博客后台管理系统</strong></font>
			</td>
			<td style="padding-top: 10px" valign="middle" align="right" width="50%">
				<font size="3" style="color: rgba(179,168,255,0.98)">&nbsp;&nbsp;<strong>欢迎：</strong>${blogger.userName}</font>
			</td>
		</tr>
	</table>
</div>
<div region="center">
	<div class="easyui-tabs" fit="true" border="false" id="tabs">
		<div title="首页" data-options="iconCls:'icon-home'">
			<div align="center" style="width:100%;height:100%;background: #3b4249">
				</br>
				</br>
					<font style="font-family: 新宋体" size="15"><b>欢迎使用个人博客后台管理系统!</b></font></br>
				</br>
			    <hr/>
				</br>
					<strong style="font-size: 35px;color: red;font-family: 楷体;text-align: center;">该系统分为以下几个模块：</strong>
				</br>
				</br>
				</br>
				</br>
				</br>
				</br>

			<table border="1" style="border-spacing: 10px 20px;" cellspacing="10px">
				<tr style="height: 50px;">
					<td style="font-size: 20px;font-family: 宋体;color: #dea6ee;width: 180px;text-align: center;">博客管理模块：</td>
					<td style="font-size: 18px;font-family: 宋体;color: #16eec1;">博客管理功能分为写博客和博客信息管理。写博客是博主用来发表编写博客的，需要博客标题，然后选择博
						客类型，最后将博客内容填入百度的富文本编辑器中，点击发布博客按钮即可发布博客。</td>
				</tr>
				<tr style="height: 50px;">
					<td style="font-size: 20px;font-family: 宋体;color: #ddee2c;width: 180px;text-align: center;">博客类别管理模块：</td>
					<td style="font-size: 18px;font-family: 宋体;color: #16eec1;">博主类别管理系统可以添加，修改和删除博客类型名称和排序序号。</td>
				</tr>
				<tr style="height: 50px;">
					<td style="font-size: 20px;font-family: 宋体;color: #ee9b3b;width: 180px;text-align: center;">评论信息管理模块：</td>
					<td style="font-size: 18px;font-family: 宋体;color: #16eec1;">评论管理功能分为评论审核和评论信息管理两部分。评论审核是当有游客或自己发表了评论之后，博主需
						要在后台管理系统中审核评论。若想将此评论显示在页面上则点击审核通过，否则点击审核不通过。</td>
				</tr>
				<tr style="height: 50px;">
					<td style="font-size: 20px;font-family: 宋体;color: #ee130d;width: 180px;text-align: center;">个人信息管理模块：</td>
					<td style="font-size: 18px;font-family: 宋体;color: #16eec1;">修改博主的个人信息，可以修改昵称，个性签名，可以添加个人头像，修改个人简介；</td>
				</tr>
				<tr style="height: 50px;">
					<td style="font-size: 20px;font-family: 宋体;color: #fff6f8;width: 180px;text-align: center;">系统管理功能模块：</td>
					<td style="font-size: 18px;font-family: 宋体;color: #16eec1;">友情链接管理，修改密码，刷新系统缓存和安全退出，友情链接管理可以添加，修改，删除友情链接网址；</td>
				</tr>
			</table>
			</div>
		</div>
	</div>
</div>
<div region="west" style="width: 200px" title="导航菜单" split="true">
	<div class="easyui-accordion" data-options="fit:true,border:false">
		<div title="常用操作"  data-options="iconCls:'icon-cycz'" style="padding:10px;">
			<a href="javascript:openTab('写博客','writeBlog.jsp','icon-writeblog')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-writeblog'" style="width: 150px;">写博客</a>
			<a href="javascript:openTab('评论审核','commentReview.jsp','icon-review')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-review'" style="width: 150px">评论审核</a>
		</div>
		
		<div title="博客管理"  data-options="iconCls:'icon-bkgl'" style="padding:10px;">
			<a href="javascript:openTab('写博客','writeBlog.jsp','icon-writeblog')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-writeblog'" style="width: 150px;">写博客</a>
			<a href="javascript:openTab('博客信息管理','blogManage.jsp','icon-bkgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-bkgl'" style="width: 150px;">博客信息管理</a>
		</div>
		<div title="博客类别管理" data-options="iconCls:'icon-bklb'" style="padding:10px">
			<a href="javascript:openTab('博客类别信息管理','blogTypeManage.jsp','icon-bklb')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-bklb'" style="width: 150px;">博客类别信息管理</a>
		</div>
		<div title="评论管理"  data-options="iconCls:'icon-plgl'" style="padding:10px">
			<a href="javascript:openTab('评论审核','commentReview.jsp','icon-review')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-review'" style="width: 150px">评论审核</a>
			<a href="javascript:openTab('评论信息管理','commentManage.jsp','icon-plgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-plgl'" style="width: 150px;">评论信息管理</a>
		</div>
		<div title="个人信息管理"  data-options="iconCls:'icon-grxx'" style="padding:10px">
			<a href="javascript:openTab('修改个人信息','modifyInfo.jsp','icon-grxxxg')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-grxxxg'" style="width: 150px;">修改个人信息</a>
		</div>
		<div title="系统管理"  data-options="iconCls:'icon-system'" style="padding:10px">
		    <a href="javascript:openTab('友情链接管理','linkManage.jsp','icon-link')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-link'" style="width: 150px">友情链接管理</a>
			<a href="javascript:openPasswordModifyDialog()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
			<a href="javascript:refreshSystem()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-refresh'" style="width: 150px;">刷新系统缓存</a>
			<a href="javascript:logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">安全退出</a>
		</div>
	</div>
</div>
<div region="south" style="height: 25px;padding: 5px;background: #a7fef6;" align="center">
	
</div>

<div id="dlg" class="easyui-dialog" style="width:400px;height:200px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
	<form id="fm" method="post">
		<table cellspacing="8px">
			<tr>
				<td>用户名：</td>
				<td><input type="text" id="userName" name="userName" readonly="readonly" value="${currentUser.userName}" style="width:200px"/></td>
			</tr>
			<tr>
				<td>新密码：</td>
				<td><input type="password" id="newPassword" name="newPassword" clas="easyui-validatebox" required="true" style="width:200px"/></td>
			</tr>
			
			<tr>
				<td>确认新密码：</td>
				<td><input type="password" id="newPassword2" name="newPassword2" clas="easyui-validatebox" required="true" style="width:200px"/></td>
			</tr>			
		</table>
	</form>
</div>

<div id="dlg-buttons">
	<a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>