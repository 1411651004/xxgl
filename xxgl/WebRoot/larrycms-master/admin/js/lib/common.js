/**
 * larryCMS 自定义公共基础信息提示模块
 * Autor: Larry
 * Date: 17-04-25
 */
layui.define(['layer'],function(exports){
    "use strict";
	var $ = layui.jquery,
	    layer = layui.layer;
       
	var CmsCommon = {
        /**
         * 抛出异常错误信息
         * @param {String} msg
         */
        cmsError: function(msg,title){
			parent.layer.alert(msg, {
				title: title,
				icon: 2,
				time: 0,
				resize: false,
				zIndex: layer.zIndex,
				anim: Math.ceil(Math.random() * 6)
			});
			return;
        },
        cmsInfo: function(msg,title){
            parent.layer.alert(msg, {
                title: title,
                icon: 6,
                time: 0,
                resize: false,
                zIndex: layer.zIndex,
                anim: Math.ceil(Math.random() * 6)
            });
            return;
        },
        cmsConfirm: function(){

        },
        //退出系统
        logOut: function (title, text, url,type, dataType, data, callback) {
            parent.layer.confirm(text, {
                title: title,
                resize: false,
                btn: ['确定', '取消'],
                btnAlign: 'c',
                icon: 3

            }, function () {
            	var basePath="http://"+window.location.host+window.document.location.pathname.substring(0,window.document.location.pathname.substr(1).indexOf('/')+1);
        		$.ajax({
                    type: "POST",
                    url: basePath+"/login/userexit",
                    async: false,
                    dataType: "text",
                    success: function(data){
                    }
                });

			location.href = url;
            }, function () {
                layer.msg('返回系统', {
                    time: 1500,
                    btnAlign: 'c',
                    btn: ['OK']
                });
            });
        }

	};

	exports('common',CmsCommon);
})