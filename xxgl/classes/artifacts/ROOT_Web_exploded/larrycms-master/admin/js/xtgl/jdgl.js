var navtab;
var basePath="http://"+window.location.host+window.document.location.pathname.substring(0,window.document.location.pathname.substr(1).indexOf('/')+1);

/**
 * 新增季度
 */
var addQuarterDiv,addQuarter,deleteQuarter;

layui.use([ 'form', 'laydate', 'upload', 'element', 'jquery', 'layer' ], function() {
	var form = layui.form();
	var laydate = layui.laydate();
	var upload = layui.upload();
	var element = layui.element();
	var $ = layui.jquery;
	var searchFormName = 'searchForm';
//	setTimeout(function() {
//		form.render('checkbox');
//	}, 300);
	
	/**
	 * 渲染数据表格
	 */
	function renderDataTable(data){
		var opts = "";
		if(data && data.length > 0) {
			for(var i = 0; i < data.length; i++) {
				opts += "<tr>";
				opts += "<td>"+(i+1)+"</td>";
				opts += "<td>"+data[i].codevalue+"</td>";
				opts += "<td> <a href=\"javascript:deleteQuarter(this,"+data[i].id+")\" class=\"layui-btn layui-btn-small\">删除</a></td>";
				opts += "</tr>";
			}
			$("#jdglBody").html(opts);
		}
	}
	
	deleteQuarter = function(th,id) {
		//询问框
		layer.confirm('请谨慎删除，确定要删除？', {
		  btn: ['确认','取消'] //按钮
		}, function(){
			layui.use([ 'jquery', 'layer' ], function() {
				var $ = layui.jquery;
				var layer = layui.layer;
				$.ajax({
					url : "http://"+window.location.host+"/xtgl/deleteById",
					//async : true, 
//				dataType : "json",
					type : "post",
					data : {id:id},
					success : function(data) {
						console.log(data);
						if (data == 1) {
							layer.msg("操作成功");
							window.location.reload(true);
//						goPage(currpageno);
						} else {
							layer.msg("出错");
						}
					},
					error : function(data) {
						layer.msg("出错");
					}
				});
			});
		}, function(){
		  // 取消
		});
	}
	
	addQuarterDiv = function() {
		if($("#addcodevalue").length==0){
			var opts = "<tr>";
			opts += "<td>&nbsp;</td>";
			opts += "<td><input id=\"addcodevalue\"/></td>";
			opts += "<td><a class=\"layui-btn layui-btn-small\" href=\"javascript:addQuarter()\">提交</a></td>";
			opts += "</tr>";
			$("#jdglBody").append(opts);
			form.render();
		}
	}
	
	addQuarter = function(){
		if(!$("#addcodevalue").val()) {
			layer.msg("季度名称未填写。");
			return false;
		}
		$.ajax({
			url : "/xtgl/addQuarter",
			data : {
				codevalue: $("#addcodevalue").val()
			},
			success : function(data){
				if("repeat" == data) {
					layer.msg("季度名称重复。");
					return;
				}
				window.location.reload(true);
			}
		});
		
	}
	
	
	// 初始化
	$(document).ready(function(){
		// 获取所有季度信息
		$("#jdglBody").html("");
    	$.ajax({
    		url : "http://"+window.location.host+'/xtgl/getQuarters',
    		success:function(data){
    			renderDataTable(data);
    		}
    	});
	});

	//监听提交
	form.on('submit(formDemo)', function(data) {
		layer.load(2);
		$.ajax({
			url : "http://"+window.location.host+"/xtgl/getQuarterByName",
			dataType : "json",
			type : "post",
			data : {
				codevalue : $("#quarterName").val()
			},
			success : function(data) {
				//$("#"+pfId).remove();
				layer.closeAll('loading');
				renderDataTable(data);
//				setTimeout(function() {
//					form.render('checkbox');
//				}, 300);
			},
			error : function(data) {
				layer.closeAll('loading');
				layer.msg("出错");
			}
		});
		return false;
	});

});


