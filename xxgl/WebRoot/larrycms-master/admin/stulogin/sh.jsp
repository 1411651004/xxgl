<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="<%=basePath %>static/css/stylesheet.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/bootstrap/css/bootstrap.min.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/css/global.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/admin/css/common.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/admin/css/personal.css" media="all">
    <style>
        .table .table-cell-box ul.three li { font-size: 14px; }
        .layui-form-label {
        	width: 140px;
        }
        .layui-input-inline{
        	width : 310px;
        }
        .layui-input-inline{
        	width : 450px;
        }
    </style>
</head>

<body>

    <div class="table">
        <section class="larry-grid table-cell">
			<div class="larry-personal">
				<header class="larry-personal-tit">
					<span>学生信息</span>
				</header><!-- /header -->
				<div class="larry-personal-body clearfix">
					<form class="layui-form layui-form-pane col-lg-4 col-md-5 col-sm-6 " method="post" action="" id="searchForm" name="searchForm">
						<!-- 表单区域 -->
							<div class="layui-collapse" style="width:1000px;">
							  <div class="layui-colla-item">
							    <h4 class="layui-colla-title">基本信息</h4>
							    <input type="hidden" name="id" value="${record.id }">
							    <div class="layui-colla-content layui-show">
									<div class="layui-form-item">
									  <div class="layui-inline">
									    <label class="layui-form-label">中文姓名</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="xm" value="${record.xm }" id="xm" lay-verify="required" placeholder="请输入中文姓名" autocomplete="off" class="layui-input">
									    </div>
									  </div>
									  <div class="layui-inline">
									    <label class="layui-form-label">英文姓名</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="ywxm" value="${record.ywxm }" id="ywxm" lay-verify="required" placeholder="请输入英文姓名" autocomplete="off" class="layui-input">
									    </div>
									  </div>
									  <div class="layui-inline">
									    <label class="layui-form-label">日文姓名</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="rwxm" value="${record.rwxm }" id="rwxm" lay-verify="required" placeholder="フリガナ" autocomplete="off" class="layui-input">
									    </div>
									  </div>
									</div>
									
									<div class="layui-form-item">
									  <div class="layui-inline">
									    <label class="layui-form-label">性别</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="xb" value="${record.xb }" id="xb" lay-verify="required" placeholder="请输入性别" autocomplete="off" class="layui-input">
									    </div>
									  </div>
									  <div class="layui-inline">
									    <label class="layui-form-label">民族</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="mz" value="${record.mz }" name="mz" lay-verify="required" placeholder="请输入民族" autocomplete="off" class="layui-input">
									    </div>
									  </div>
									  <div class="layui-inline">
									    <label class="layui-form-label">学校</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input id="xxTemp" value="${record.xx }" hidden="hidden"/>
									      <select name="xx" id="xx" lay-verify="required" lay-search="">
									        <option value="">直接选择或搜索选择学校</option>
									      	<c:forEach items="${qyxxlist}" var="qyxx" varStatus="sta">
										      	<option value="${qyxx.mc }">${qyxx.mc }</option>
									      	</c:forEach>
									      </select>
									    </div>
									  </div>
									</div>
									
									<div class="layui-form-item">
									  <div class="layui-inline">
									    <label class="layui-form-label">专业</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="zy" value="${record.zy }" id="zy" lay-verify="required" placeholder="请输入专业" autocomplete="off" class="layui-input">
									    </div>
									  </div>
									  <div class="layui-inline">
									    <label class="layui-form-label">身份证号码</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="sfz" value="${record.sfz}" id="sfz" lay-verify="required|identity" placeholder="请输入身份证号码" autocomplete="off" class="layui-input">
									    </div>
									  </div>
									  <div class="layui-inline">
									    <label class="layui-form-label">电话</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="dh" value="${record.dh }" id="dh" lay-verify="required|phone" placeholder="请输入电话" autocomplete="off" class="layui-input">
									    </div>
									  </div>
									</div>
								</div>
							  </div>
							  
							  <div class="layui-colla-item">
							    <h4 class="layui-colla-title">护照信息 （如果为空，请填写无）</h4>
								    <div class="layui-colla-content layui-show">
								    	<div class="layui-form-item">
								    		<div class="layui-inline">
											    <label class="layui-form-label">是否有护照</label>
											    <div class="layui-input-inline" style="width: 200px;">
											      <input id="sfyhzTemp" value="${record.sfyhz }" hidden="hidden"/>
											      <select name="sfyhz"  lay-filter="sfyhz" id="sfyhz" lay-verify="required" lay-search="">
											      	<option value="">请选择</option>
											      	<option value="是">是</option>
											      	<option value="否">否</option>
											      </select>
											    </div>
											  </div>
											  <div class="layui-inline">
											    <label class="layui-form-label">婚姻情况</label>
											    <div class="layui-input-inline" style="width: 200px;">
											      <input id="hyqkTemp" value="${record.hyqk }" hidden="hidden"/>
											      <select name="hyqk" id="hyqk" lay-verify="required" lay-search="">
											      	<option value="">请选择</option>
											      	<option value="未婚">未婚</option>
											      	<option value="既婚">既婚</option>
											      </select>
											    </div>
											  </div>
											  <div class="layui-inline" id="myhz" style="display:none">
											    <label class="layui-form-label">取得预定日</label>
											    <div class="layui-input-inline" style="width: 200px;">
											      <input type="text" name="qdydr" value="${record.qdydr }" id="qdydr" placeholder="请选择取得预定日" autocomplete="off" class="layui-input">
											    </div>
											  </div>
								    	</div>
								    	<!-- 是否有护照选择是 -->
								    	<div class="layui-form-item" id="cyhz" style="display:none">
								    		<div class="layui-inline">
											    <label class="layui-form-label">护照号码</label>
											    <div class="layui-input-inline" style="width: 200px;">
											      <input type="text" name="hzhm" value="${record.hzhm }" id="hzhm" placeholder="请输入护照号码" autocomplete="off" class="layui-input">
											    </div>
											  </div>
											  <div class="layui-inline">
											    <label class="layui-form-label">有效期</label>
											    <div class="layui-input-inline" style="width: 200px;">
											      <input type="text" name="hzyxq" value="${record.hzyxq }" id="hzyxq" placeholder="请输入护照有效期" autocomplete="off" class="layui-input">
											    </div>
											  </div>
								    	</div>
								    	<div class="layui-form-item">
								    		<div class="layui-inline">
											    <label class="layui-form-label">是否去过日本</label>
											    <div class="layui-input-inline" style="width: 200px;">
											      <input id="sfqgrbTemp" value="${record.sfqgrb }" hidden="hidden"/>
											      <select name="sfqgrb" lay-filter="sfqgrb" id="sfqgrb" lay-verify="required" lay-search="">
											      	<option value="">请选择</option>
											      	<option value="是">是</option>
											      	<option value="否">否</option>
											      </select>
											    </div>
											  </div>
											  <div class="layui-inline" id="cs" style="display:none">
											    <label class="layui-form-label">去日本的次数</label>
											    <div class="layui-input-inline" style="width: 200px;">
											      <input type="text" name="qrbcs" value="${record.qrbcs }" id="qrbcs" placeholder="请输入去日本的次数" autocomplete="off" class="layui-input">
											    </div>
											  </div>
											  <div class="layui-inline" id="zhsj" style="display:none">
											    <label class="layui-form-label" style="height: 50px;">最近一次去<br>日本的时间</label>
											    <div class="layui-input-inline" style="width: 200px; height: 50px;">
											      <input style="height: 50px;" type="text" name="zhfrsj" value="${record.zhfrsj }" id="zhfrsj" placeholder="请选择最后赴日时间" autocomplete="off" class="layui-input">
											    </div>
											  </div>
								    	</div>
								    	
								    </div>
							   </div>
							  
							  <div class="layui-colla-item">
							    <h4 class="layui-colla-title">学籍信息 （如果为空，请填写无）</h4>
							    <div class="layui-colla-content layui-show">
							    	<div class="layui-form-item">
									  <div class="layui-inline">
									    <label class="layui-form-label">入学年份</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="rxnf" value="${record.rxnf }" lay-verify="required" id="rxnf" placeholder="请选择入学年份" autocomplete="off" class="layui-input">
									    </div>
									  </div>
									  <div class="layui-inline">
									    <label class="layui-form-label">年级</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <select name="nj" id="nj" lay-verify="required" lay-filter="nj" lay-search="">
			        
			                              </select>
									    </div>
									  </div>
									  <div class="layui-inline">
									    <label class="layui-form-label">学制</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input id="xzTemp" value="${record.xz }" hidden="hidden"/>
									      <select name="xz" id="xz" lay-verify="required" lay-search="">
									        <option value="">直接选择或搜索选择</option>
									      	<option value="2">两年制</option>
									      	<option value="3">三年制</option>
									      	<option value="4">四年制</option>
									      </select>
									    </div>
									  </div>
									</div>
									<div class="layui-form-item">
									  <div class="layui-inline">
									    <label class="layui-form-label">学历</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input id="sfyjsTemp" value="${record.sfyjs }" hidden="hidden"/>
									      <select name="sfyjs" id="sfyjs" lay-verify="required" lay-filter="sfyjs" lay-search="">
									        <option value="">直接选择或搜索选择</option>
									      	<option value="专科">专科</option>
									      	<option value="本科">本科</option>
									      	<option value="研究生">研究生</option>
									      	<option value="博士">博士</option>
									      </select>
									    </div>
									  </div>
									  <div class="layui-inline">
									    <label class="layui-form-label">季度</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input id="jdTemp" value="${record.jd }" hidden="hidden"/>
									      <select name="jd" id="jd" lay-verify="required" lay-search="">
									        <option value="">直接选择或搜索选择</option>
									        <c:forEach items="${jdList}" var="jd" varStatus="sta">
										      	<option value="${jd.id }">${jd.codevalue }</option>
									      	</c:forEach>
									      </select>
									    </div>
									  </div>
									  <div class="layui-inline">
									    <label class="layui-form-label">实习时间</label>
									    <div class="layui-input-inline" style="width: 200px;">
									       <input id="sxsjTemp" value="${record.sxsj }" hidden="hidden"/>
									       <select name="sxsj" id="sxsj" lay-verify="required" lay-search="">
									        <option value="">直接选择或搜索选择</option>
									      	<option value="三个月">三个月</option>
									      	<option value="六个月">六个月</option>
									      	<option value="九个月">九个月</option>
									      </select>
									    </div>
									  </div>
									</div>
							        <div class="layui-form-item">
										<label class="layui-form-label" style="width:287px!important">是否参加日语等级考试</label>
										<div class="layui-input-block" style="width:519px;margin-left:287px!important;">
										    <input id="ryksTemp" value="${record.ryks }" hidden="hidden"/>
											<select name="ryks" id="ryks" lay-verify="required" lay-filter="ryks">
											        <option value="1">否</option>
											        <option value="0">是</option>
											      </select>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label" style="width:287px!important">是否参加英语四六级考试</label>
										<div class="layui-input-block" style="width:519px;margin-left:287px!important;">
										    <input id="yyksTemp" value="${record.yyks }" hidden="hidden"/>
											<select name="yyks" id="yyks" lay-verify="required">
											        <option value="1">否</option>
											        <option value="0">是</option>
											      </select>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label" style="width:287px!important">是否参加研究生考试(仅限应届毕业生)</label>
										<div class="layui-input-block" style="width:519px;margin-left:287px!important;">
										    <input id="yjsksTemp" value="${record.yjsks }" hidden="hidden"/>
											<select name="yjsks" id="yjsks" lay-verify="required">
											        <option value="1">否</option>
											        <option value="0">是</option>
											      </select>
										</div>
									</div>
							    </div>
							  </div>
							  <%-- 报名表
							  <div class="layui-colla-item">
							    <h4 class="layui-colla-title">报名表（请先下载报名表模板进行填写）</h4>
							    <div class="layui-colla-content layui-show">
							    	<a href="<%=basePath %>uploadFile/template/赴日社会実践报名表(需证件照）.doc" class="layui-btn">报名表模板</a>
							    	<c:if test="${!empty  record.bmb }">
							    	<a href="<%=basePath %>uploadFile/${record.bmb}" class="layui-btn">报名表</a>下载后请去掉文件名的后六位<br>
							    	</c:if>
							    	<br>
							    	<div class="layui-upload-drag" id="scbmb" style="margin-top:10px">
									  <i class="layui-icon"></i>
									  <p id="wa">点击上传，或将文件拖拽到此处</p>
									</div>
									<input type="hidden" name="bmb" id="bmb" value="${record.bmb }">
									<input type="hidden" name="flag" id="flag" value="2">
									
							    </div>
						    </div>
							   
							   --%>
							  
							  <div class="layui-colla-item">
							    <h4 class="layui-colla-title">家庭信息 （如果为空，请填写无）</h4>
							    <div class="layui-colla-content layui-show">
							    	<div class="layui-form-item">
									  <div class="layui-inline">
									    <label class="layui-form-label">父亲姓名</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="fqxm" value="${record.fqxm }" id="fqxm" lay-verify="required" placeholder="若为单亲家庭，请填写无" autocomplete="off" class="layui-input">
									    </div>
									  </div>
									  <div class="layui-inline">
									    <label class="layui-form-label">父亲联系电话</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="fqlxdh" value="${record.fqlxdh }" id="fqlxdh" lay-verify="fatherNumber" placeholder="请输入父亲联系电话" autocomplete="off" class="layui-input">
									    </div>
									  </div>
									</div>
									<div class="layui-form-item">
									  <div class="layui-inline">
									    <label class="layui-form-label">母亲姓名</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="mqxm" value="${record.mqxm }" id="mqxm" autocomplete="off" lay-verify="required" placeholder="若为单亲家庭，请填写无" class="layui-input">
									    </div>
									  </div>
									  <div class="layui-inline">
									    <label class="layui-form-label">母亲联系电话</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="mqlxdh" value="${record.mqlxdh }" id="mqlxdh" autocomplete="off" lay-verify="motherNumber" placeholder="请输入母亲联系电话" class="layui-input">
									    </div>
									  </div>
									</div>
									<div class="layui-form-item">
									    <label class="layui-form-label">家庭地址</label>
									    <div class="layui-input-block">
									      <input type="text" value="${record.jtdz }" name="jtdz" id="jtdz" style="width: 574px;" lay-verify="required" placeholder="请输入家庭地址" autocomplete="off" class="layui-input">
									    </div>
									  </div>
							    </div>
							  </div>
							  <div class="layui-colla-item">
							    <h4 class="layui-colla-title">个人信息 （如果为空，请填写无）</h4>
							    <div class="layui-colla-content layui-show">
							    	<div class="layui-form-item">
									  <div class="layui-inline">
									    <label class="layui-form-label">身高</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="sg" value="${record.sg }" id="sg" lay-verify="required|number" placeholder="单位:cm" autocomplete="off" class="layui-input">
									    </div>
									  </div>
									  <div class="layui-inline">
									    <label class="layui-form-label">体重</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="tz" value="${record.tz }" id="tz" lay-verify="required|number" placeholder="单位:kg" autocomplete="off" class="layui-input">
									    </div>
									  </div>
									  <div class="layui-inline">
									    <label class="layui-form-label">胸围</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="xw" value="${record.xw }" id="xw" lay-verify="required|number" placeholder="单位:cm" autocomplete="off" class="layui-input">
									    </div>
									  </div>
									</div>
									<div class="layui-form-item">
									  <div class="layui-inline">
									    <label class="layui-form-label">腰围</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="yw" value="${record.yw }" id="yw" lay-verify="required|number" placeholder="单位:cm" autocomplete="off" class="layui-input">
									    </div>
									  </div>
									  <div class="layui-inline">
									    <label class="layui-form-label">臀围</label>
									    <div class="layui-input-inline" style="width: 200px;">
									      <input type="text" name="tw" id="tw" value="${record.tw }" lay-verify="required|number" placeholder="单位:cm" autocomplete="off" class="layui-input">
									    </div>
									  </div>
									  <div class="layui-inline">
									    <label class="layui-form-label">鞋号</label>
									    <div class="layui-input-inline" style="width: 200px;">
									    <input id="xhTemp" value="${record.xh }" hidden="hidden"/>
									      <select name="xh" id="xh" lay-verify="required" lay-search="">
									        <option value="">直接选择或搜索选择</option>
									      	<option value="S">35号---22.5cm</option>
									      	<option value="M">36号---23cm</option>
									      	<option value="L">37号---23.5cm</option>
									      	<option value="XL">38号---24cm</option>
									      	<option value="XXL">39号---24.5cm</option>
									      	<option value="XXXL">40号---25cm</option>
									      	<option value="XXXL">41号---25.5cm</option>
									      	<option value="XXXL">42号---26cm</option>
									      </select>
									    </div>
									  </div>
									</div>
									<div class="layui-form-item">
									  <div class="layui-inline">
									    <label class="layui-form-label">衣服尺寸</label>
									    <div class="layui-input-inline" style="width: 200px;">
									    <input id="yfccTemp" value="${record.yfcc }" hidden="hidden"/>
									      <select name="yfcc" id="yfcc"  lay-verify="required" lay-search="">
									        <option value="">直接选择或搜索选择</option>
									      	<option value="S">S</option>
									      	<option value="M">M</option>
									      	<option value="L">L</option>
									      	<option value="XL">XL</option>
									      	<option value="XXL">XXL</option>
									      	<option value="XXXL">XXXL</option>
									      </select>
									    </div>
									  </div>
									</div>
									
							    </div>
							  </div>
							  
							  <div class="layui-colla-item">
							    <h4 class="layui-colla-title">附件</h4>
							    <div class="layui-colla-content layui-show">
									<div class="layui-form-item">
										<label class="layui-form-label">附件</label>
										<div class="layui-input-block" style="margin-left:120px">&nbsp;&nbsp;
											<a class="layui-btn layui-btn-small" href="javascript:window.open('<%=basePath%>uploadFile/bmb/${record.bmb }');">报名表</a>
											<%--
											<a class="layui-btn layui-btn-small" href="javascript:window.open('<%=basePath%>uploadFile/${record.zlzg }');">在留资格</a>
											<c:if test="${empty record.hzzp}">
												<a class="layui-btn layui-btn-small" href="javascript:alert('暂无护照照片');">护照照片</a>
											</c:if>
											<c:if test="${!empty record.hzzp}">
												<a class="layui-btn layui-btn-small" href="javascript:window.open('<%=basePath%>uploadFile/${record.hzzp }');">护照照片</a>
											</c:if>
										     --%>
										</div>
									</div>
								</div>
							  </div>
							  <div class="layui-colla-item">
							    <h4 class="layui-colla-title">其他信息</h4>
							    <div class="layui-colla-content layui-show">
							    	<div class="layui-form-item">
										<label class="layui-form-label">企业</label>
										<div class="layui-input-block" style="margin-left:120px">
											<input type="text" name="byzd2" value="${record.byzd2 }" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-inline" style="margin-right:0px!important">
											<label class="layui-form-label">窗口</label>
											<div class="layui-input-inline" style="width:282px;margin-right:0px!important">
												<input type="text" name="byzd3" value="${record.byzd3 }" class="layui-input">
											</div>
										</div>
										<div class="layui-inline" style="margin-right:0px!important">
											<label class="layui-form-label">学生番号</label>
											<div class="layui-input-inline" style="width:282px;margin-right:0px!important">
												<input type="text" name="byzd4" value="${record.byzd4 }" class="layui-input">
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-inline" style="margin-right:0px!important" style="display:none !important;">
											<label class="layui-form-label">氏名</label>
											<div class="layui-input-inline" style="width:282px;margin-right:0px!important">
												<input type="text" name="byzd5" value="${record.byzd5 }" class="layui-input">
											</div>
										</div>
										<div class="layui-inline" style="margin-right:0px!important">
											<label class="layui-form-label">在留批复</label>
											<div class="layui-input-inline" style="width:282px;margin-right:0px!important">
												<input type="text" name="zlpf" value="${record.zlpf }" class="layui-input">
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">缴费时间</label>
										<div class="layui-input-block" style="margin-left:120px">
											<input type="text" name="jfsj" value="${record.jfsj }" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">参加项目</label>
										<div class="layui-input-block" style="margin-left:120px">
											<input type="text" name="cjxm" value="${record.cjxm }" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">启程航班</label>
										<div class="layui-input-block" style="margin-left:120px">
											<input type="text" name="qchb" value="${record.qchb }" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">到达航班</label>
										<div class="layui-input-block" style="margin-left:120px">
											<input type="text" name="ddhb" value="${record.ddhb }" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">回程航班</label>
										<div class="layui-input-block" style="margin-left:120px">
											<input type="text" name="hchb1" value="${record.hchb1 }" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">回程航班</label>
										<div class="layui-input-block" style="margin-left:120px">
											<input type="text" name="hchb2" value="${record.hchb2 }" class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-inline" style="margin-right:0px!important">
											<label class="layui-form-label">到达日本日期</label>
											<div class="layui-input-inline" style="width:282px;margin-right:0px!important">
												<input type="text" name="ddrq" value="${record.ddrq }" class="layui-input">
											</div>
										</div>
										<div class="layui-inline" style="margin-right:0px!important">
											<label class="layui-form-label">归国时间</label>
											<div class="layui-input-inline" style="width:282px;margin-right:0px!important">
												<input type="text" name="ggsj" value="${record.ggsj }" class="layui-input">
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-inline" style="margin-right:0px!important">
											<label class="layui-form-label">机票费</label>
											<div class="layui-input-inline" style="width:282px;margin-right:0px!important">
												<input type="text" name="jpf" value="${record.jpf }" class="layui-input">
											</div>
										</div>
										<div class="layui-inline" style="margin-right:0px!important">
											<label class="layui-form-label">签证费</label>
											<div class="layui-input-inline" style="width:282px;margin-right:0px!important">
												<input type="text" name="qzf" value="${record.qzf }" class="layui-input">
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-inline" style="margin-right:0px!important">
											<label class="layui-form-label">住宿费</label>
											<div class="layui-input-inline" style="width:282px;margin-right:0px!important">
												<input type="text" name="zsf" value="${record.zsf }" class="layui-input">
											</div>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">备注</label>
										<div class="layui-input-block" style="margin-left:120px;width: 400px;">
											<textarea rows="" cols="" name="qtbz" class="layui-textarea">${record.qtbz }</textarea>
										</div>
									</div>
								</div>
							  </div>
							</div>
					</form>
				</div>
			</div>
		</section>
        
        <div class="btn-group" style="text-align: center;">
            <input style="margin-left:400px;" type="submit" class="table-btn" onclick="tg('${record.id}')" value="通过">
            <input type="submit" class="table-btn" onclick="xg('${record.dh}','${record.id}')" value="通知修改">
        </div>

    </div>
<div id="tanchu1" style="display:none">
	<textarea  id="remindCon1" rows="7" style="font-size:16px;" cols="40"></textarea>
</div>
</body>

<script type="text/javascript" src="<%=basePath %>larrycms-master/layui/layui.js"></script>
<script type="text/javascript">
layui.config({
  dir: '<%=basePath %>larrycms-master/layui/' //layui.js 所在路径（注意，如果是script单独引入layui.js，无需设定该参数。），一般情况下可以无视
  ,version: false //一般用于更新组件缓存，默认不开启。设为true即让浏览器不缓存。也可以设为一个固定的值，如：201610
  ,debug: false //用于开启调试模式，默认false，如果设为true，则JS模块的节点会保留在页面
  ,base: '<%=basePath %>larrycms-master/layui/lay/modules/' //设定扩展的Layui组件的所在目录，一般用于外部组件扩展
});


layui.use(['jquery','layer','form','element','laydate','upload'], function(){
	var $ = layui.jquery;
	var form = layui.form;
	var element = layui.element;
	var layer = layui.layer;
	var laydate = layui.laydate;
	
	form.verify({
	    fatherNumber : function(value, item){
	    	if("无" == $("#fqxm").val()) {
	    		item.value = "";
	    	} else {
		    	if(!value){
		    		return "请填写父亲电话号码";
		    	}
		        if(!(/^1[34578][0-9]\d{8}$/.test(value))) {
		        	return "请填写正确的电话号码";
		        }
	    	}
	    },
	    motherNumber : function(value, item){
	    	if("无" == $("#mqxm").val()) {
	    		item.value = "";
	    	} else {
		    	if(!value){
		    		return "请填写母亲电话号码";
		    	}
		        if(!(/^1[34578][0-9]\d{8}$/.test(value))) {
		        	return "请填写正确的电话号码";
		        }
	    	}
	    }
	});
	
	
	
	var sfyjs="${record.sfyjs }";
	
	function sfyjsSelect(data) {
	      var njVal = $("#nj").val();
		  if(data.value == "研究生") {
			  $("#nj").html("<option value=\"\">直接选择或搜索选择</option><option value=\"1\">大学院1年生</option><option value=\"2\">大学院2年生</option><option value=\"3\">大学院3年生</option><option value=\"4\">大学院4年生</option>");
		  } else {
			  $("#nj").html("<option value=\"\">直接选择或搜索选择</option><option value=\"1\">1年生</option><option value=\"2\">2年生</option><option value=\"3\">3年生</option><option value=\"4\">4年生</option>");
		  }
		  if(njVal) {
		      $("#nj").val(njVal);
		  }
		  form.render();
	  }
	  sfyjsSelect({value:sfyjs});
	 //是否为研究生
	 form.on('select(sfyjs)', sfyjsSelect);
	 $("#nj").val('${record.nj}');
	
	
	$("#ryks").val("${record.ryks}");
	$("#yyks").val("${record.yyks}");
	$("#yjsks").val("${record.yjsks}");
	if(sfyjs!="0"){
		$("#sfyjs").prop("checked","true");
	}
	
	// 初始化select
 	$("select").each(function(){
 		var select = $(this);
 		var name = select.attr("name");
 		var value = $("#"+name+"Temp").val();
 		select.find("option[value='" + value + "']").attr("selected","selected");
 	});
	
 	if($("#sfyhzTemp").val()=='是'){
  		$("#cyhz").css("display","block");
  		$("#myhz").css("display","none");
  	}else if($("#sfyhzTemp").val()=='否'){
  		$("#cyhz").css("display","none");
  		$("#myhz").css("display","");
  	}else if($("#sfqgrbTemp").val()==''){
  		$("#cyhz").css("display","none");
  		$("#myhz").css("display","none");
  	}
    if($("#sfqgrbTemp").val()=='是'){
  		$("#cs").css("display","");
  		$("#zhsj").css("display","");
  	}else if($("#sfqgrbTemp").val()=='否'){
  		$("#cs").css("display","none");
  		$("#zhsj").css("display","none");
  	}else if($("#sfqgrbTemp").val()==''){
  		$("#cs").css("display","none");
  		$("#zhsj").css("display","none");
  	}
	laydate.render({
	   elem: '#rxnf',
	   type: 'year',
	   position: 'fixed',
	   format: 'yyyy年' //可任意组合
	});
	//常规用法
	laydate.render({
	  elem: '#qdydr',
	  position: 'fixed',
	  format: 'yyyy年MM月dd日' //可任意组合
	});
	//常规用法
	//日期时间范围选择
	laydate.render({ 
	  elem: '#zhfrsj',
	  position: 'fixed',
	  range: true //或 range: '~' 来自定义分割字符
	});
	//常规用法
	laydate.render({
	  elem:'#hzyxq',
	  position: 'fixed',
	  format: 'yyyy年MM月dd日' //可任意组合
	});
	form.render();
 

	form.on('select(sfyhz)', function(data){
		if(data.value=='是'){
			$("#cyhz").css("display","block");
			$("#myhz").css("display","none");
		}else if(data.value=='否'){
			$("#cyhz").css("display","none");
			$("#myhz").css("display","");
		}else if(data.value==''){
			$("#cyhz").css("display","none");
			$("#myhz").css("display","none");
		}
	});
	
    form.on('select(sfqgrb)', function(data){
	  	if(data.value=='是'){
	  		$("#cs").css("display","");
	  		$("#zhsj").css("display","");
	  	}else if(data.value=='否'){
	  		$("#cs").css("display","none");
	  		$("#zhsj").css("display","none");
	  	}else if(data.value==''){
	  		$("#cs").css("display","none");
	  		$("#zhsj").css("display","none");
	  	}
	});
    
 });
function tg(obj){
layui.use(['jquery','layer'], function(){
			 var $=layui.jquery;
			 var layer = layui.layer;
	$.ajax({
		url:"<%=basePath%>xsxx/updatesta",
		type:"post",
		async:true,
		dataType:"text",
		data:{
			id:obj
		},
		success:function(data){
			layer.closeAll();
			if(data=='1'){
				layer.closeAll();
				layer.msg("审核成功");
				setTimeout("location.reload();",1500);
			}else{
				layer.msg("审核失败，请联系管理员");
			}
	    }
	});
	});
	
}

</script>
</html>