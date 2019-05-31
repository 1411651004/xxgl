package com.guochen.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse res = (HttpServletResponse) arg1;
		Object obj = req.getSession().getAttribute("user");
		String servletPath = req.getServletPath();
		if(obj != null){
			arg2.doFilter(arg0, arg1);//
		}else{
			String path = req.getContextPath();
			String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
			if(servletPath.contains("dsna.images")
					||servletPath.contains("login")
					||servletPath.contains("tkxx/tksq")
					||servletPath.contains("xsxx/yz")
					||servletPath.contains("regist")
					||servletPath.contains("front")
					||servletPath.contains("MyJsp.jsp")
					||servletPath.contains("mtender")
					||servletPath.contains("mgetOfferedProjects")
					||servletPath.contains("mgetTargetedProjects")
					||servletPath.contains("mgetVerifyProject")
					||servletPath.contains("mgetVerifyList")
					||servletPath.contains("doBack2Manager")
					||servletPath.contains("comapp/save")//
					||servletPath.contains("sendcode")
					||servletPath.contains("xsxx/add")
					||servletPath.contains("downloadContractFile")
					||servletPath.contains("downloadproject")
					||servletPath.contains("uploadFile")
					||servletPath.contains("mselectComById")
					){
				arg2.doFilter(arg0, arg1);
				return;
			}else{
				if(servletPath.contains("xsxx/eclr")){
					basePath+="xsxx/stulogin";
				}
				if(servletPath.contains(".")){
					int index = servletPath.lastIndexOf(".");
					String suffix = servletPath.substring(index);
					if(".js.html.css.jpg.png".contains(suffix)){
						arg2.doFilter(arg0, arg1);
					}else{//
						res.sendRedirect(basePath);
					}
				}else{
					res.sendRedirect(basePath);
				}
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
