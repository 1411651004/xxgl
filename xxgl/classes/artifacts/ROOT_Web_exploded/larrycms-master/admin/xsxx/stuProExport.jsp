<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'list.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>larrycms-master/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>larrycms-master/admin/css/font.css">
<script type="text/javascript"
	src="<%=basePath%>larrycms-master/layui/layui.js"></script>
	<style type="text/css">
	.layui-inline{
		width:250px;
	}
	</style>
</head>
<body>
	<form class="layui-form" action="">

		<div class="layui-collapse" style="width:840px;" id="proContent">
			<div class="layui-colla-item">
				<h4 class="layui-colla-title">基本信息</h4>
				<div class="layui-colla-content layui-show">
					<div class="layui-form-item">
						<div class="layui-inline">
							<input type="checkbox" name="xm" title="中文姓名" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="ywxm" title="英文姓名" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="rwxm" title="日文姓名" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="xb" title="性别" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="mz" title="民族" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="xx" title="学校" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="zy" title="专业" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="sfz" title="身份证号码" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="dh" title="电话" />
						</div>
						
						护照信息
						<div class="layui-inline">
							<input type="checkbox" name="sfyhz" title="护照" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="hyqk" title="婚姻情况" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="qdydr" title="取得预定日" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="hzhm" title="护照号码" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="hzyxq" title="有效期" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="sfqgrb" title="是否去过日本" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="qrbcs" title="去日本次数" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="zhfrsj" title="最近去日本时间" />
						</div>
						
						
						学籍信息
						<div class="layui-inline">
							<input type="checkbox" name="rxnf" title="入学年份" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="nj" title="年级" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="ywxm" title="学校" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="ywxm" title="学校" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="ywxm" title="学校" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="ywxm" title="学校" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="ywxm" title="学校" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="ywxm" title="学校" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="ywxm" title="学校" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="ywxm" title="学校" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="ywxm" title="学校" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="ywxm" title="学校" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="ywxm" title="学校" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="ywxm" title="学校" />
						</div>
						<div class="layui-inline">
							<input type="checkbox" name="ywxm" title="学校" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="proForm">立即提交</button>
			</div>
		</div>
	</form>
	<input style="display:none;" id="stuProNotShows"
		value="${stuProNotShows.codevalue }" />
	<input style="display:none;" id="notShowId"
		value="${stuProNotShows.id }" />
	<input style="display:none;" id="notShowCode"
		value="${stuProNotShows.code }" />
	<script>
		layui.config({
			dir : '<%=basePath%>larrycms-master/layui/', //layui.js 所在路径（注意，如果是script单独引入layui.js，无需设定该参数。），一般情况下可以无视
			version : false, //一般用于更新组件缓存，默认不开启。设为true即让浏览器不缓存。也可以设为一个固定的值，如：201610
			debug : false, //用于开启调试模式，默认false，如果设为true，则JS模块的节点会保留在页面
			base : '<%=basePath%>larrycms-master/layui/lay/modules/' //设定扩展的Layui组件的所在目录，一般用于外部组件扩展
		});
		
		var proData = [
			{
				title : "基本信息",
				items : [{
					name : "xm",
					text : "中文姓名"
				},{
					name : "ywxm",
					text : "英文姓名"
				},{
					name : "rwxm",
					text : "日文姓名"
				},{
					name : "xb",
					text : "性别"
				},{
					name : "mz",
					text : "民族"
				},{
					name : "xx",
					text : "学校"
				},{
					name : "zy",
					text : "专业"
				},{
					name : "sfz",
					text : "身份证号码"
				},{
					name : "dh",
					text : "电话"
				}]
			},{
				title : "护照信息",
				items : [{
					name : "sfyhz",
					text : "是否有护照"
				},{
					name : "hyqk",
					text : "婚姻情况"
				},{
					name : "qdydr",
					text : "取得预定日"
				},{
					name : "hzhm",
					text : "护照号码"
				},{
					name : "hzyxq",
					text : "有效期"
				},{
					name : "sfqgrb",
					text : "是否去过日本"
				},{
					name : "qrbcs",
					text : "去日本次数"
				},{
					name : "zhfrsj",
					text : "最近去日本时间"
				}]
			},{
				title : "学籍信息",
				items : [{
					name : "rxnf",
					text : "入学年份"
				},{
					name : "nj",
					text : "年级"
				},{
					name : "xz",
					text : "学制"
				},{
					name : "sfyjs",
					text : "学历"
				},{
					name : "jd",
					text : "季度"
				},{
					name : "sxsj",
					text : "实习时间"
				},{
					name : "ryks",
					text : "是否参加日语等级考试"
				},{
					name : "yyks",
					text : "是否参加英语四六级考试"
				},{
					name : "yjsks",
					text : "是否参加研究生考试"
				}]
			},{
				title : "报名表",
				items : [{
					name : "bmb",
					text : "报名表"
				}]
			},{
				title : "家庭信息",
				items : [{
					name : "fqxm",
					text : "父亲姓名"
				},{
					name : "fqlxdh",
					text : "父亲联系电话"
				},{
					name : "mqxm",
					text : "母亲姓名"
				},{
					name : "mqlxdh",
					text : "母亲联系电话"
				},{
					name : "jtdz",
					text : "家庭地址"
				}]
			},{
				title : "个人信息",
				items : [{
					name : "sg",
					text : "身高"
				},{
					name : "tz",
					text : "体重"
				},{
					name : "xw",
					text : "胸围"
				},{
					name : "yw",
					text : "腰围"
				},{
					name : "xh",
					text : "鞋号"
				},{
					name : "yfcc",
					text : "衣服尺寸"
				}]
			},{
				title : "其他信息",
				items : [{
					name : "byzd3",
					text : "窗口"
				},{
					name : "byzd4",
					text : "学生番号"
				},{
					name : "byzd5",
					text : "姓氏"
				},{
					name : "zlpf",
					text : "再留批复"
				},{
					name : "jfsj",
					text : "缴费时间"
				},{
					name : "cjxm",
					text : "参加项目"
				},{
					name : "qchb",
					text : "启程航班"
				},{
					name : "ddhb",
					text : "到达航班"
				},{
					name : "hchb1",
					text : "回程航班1"
				},{
					name : "hchb2",
					text : "回程航班2"
				},{
					name : "ddrq",
					text : "到达日本日期"
				},{
					name : "ggsj",
					text : "归国时间"
				},{
					name : "jpf",
					text : "机票费"
				},{
					name : "qzf",
					text : "签证费"
				},{
					name : "zsf",
					text : "住宿费"
				},{
					name : "qtbz",
					text : "备注"
				}]
			}
		];
	
	
		//Demo
		layui.use([ 'layer', 'form', 'jquery' ], function() {
			var form = layui.form;
			var $ = layui.jquery;
			var layer = layui.layer;
			
			var html = "";
			for(var i = 0; i < proData.length; i++) {
				html += "<div class=\"layui-colla-item\">";
				html += "<h4 class=\"layui-colla-title\">"+proData[i].title+"</h4>";
				html += "<div class=\"layui-colla-content layui-show\">";
				var j = 0;
				for(; j < proData[i].items.length; j++) {
					if(j%3 == 0) {
						html += "<div class=\"layui-form-item\">";
					}
					
					html += "<div class=\"layui-inline\">";
					html += "<input type=\"checkbox\" name=\"" + proData[i].items[j].name+"\" title=\""+proData[i].items[j].text+"\" />";
					html += "</div>";
					
					
					if(j%3 == 2) {
						html += "</div>";
					}
				}
				if(j%3 != 2) {
					html += "</div>";
				}
				html += "</div></div>";
			}
			$("#proContent").html(html);
	
			var stuProNotShows = $("#stuProNotShows").val();
			if (stuProNotShows) {
				$("input[type='checkbox']").each(function() {
					if (stuProNotShows == this.name 
							|| stuProNotShows.startsWith(this.name+",")
							|| stuProNotShows.indexOf("," + this.name + ",") > -1 
							|| stuProNotShows.endsWith(","+this.name)) {
						// off
						$(this).attr("checked", false);
					} else {
						// on
						$(this).attr("checked", true);
					}
				});
				form.render();
			} else {
				$("input[type='checkbox']").each(function() {
					// on
					$(this).attr("checked", true);
				});
				form.render();
			}
		
			//监听提交
			form.on('submit(proForm)', function(data) {
				var pros = data.form;
				var notShow = {
					id : $("#notShowId").val(),
					code : $("#notShowCode").val(),
					codevalue : ""
				}
				for(var i = 0; i < pros.length; i++) {
					if(pros[i].localName == "input" && !pros[i].checked) {
						notShow.codevalue += pros[i].name+",";
					}
				}
				if(notShow.codevalue.length>0) {
					notShow.codevalue=notShow.codevalue.substring(0,notShow.codevalue.length-1);
				}
				$.ajax({
					url : "/xsxx/proExportFile",
					data : notShow,
					dataType:"json",
					success : function(data){
						if(data && data.status == "success") {
							layer.msg("学生信息文件生成成功");
							window.open("<%=basePath%>uploadFile/tkxx/"+data.fileName);
						} else {
							alert(data.msg);
						}
					},
					error : function(data){
						console.log(data);
					}
				});
				return false;
			});
		
		});
		
	</script>
</body>
</html>
