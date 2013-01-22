package com.spark.base.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.spark.base.common.filter.util.ImageUploader;
import com.spark.cms.common.Constant;

public class SessionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 不过滤的uri
		String[] notFilter = new String[] { "login", "captcha-image" };

		// 请求的uri
		String uri = request.getRequestURI();

		String uristr = uri.replace("\\", "");
		uristr = uristr.replace("/", "");
//		String context = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
//				+ request.getContextPath();
//		System.out.println(context);

		// uri中包含background时才进行过滤

		if (uri.indexOf("app") != -1 || (uri.indexOf("admin") != -1 && !uristr.endsWith("admin"))) {
			// 是否过滤
			boolean doFilter = true;
			for (String s : notFilter) {
				if (uri.indexOf(s) != -1) {
					doFilter = false;
					break;
				}
			}
			if (doFilter) {
				// 执行过滤
				// 从session中获取登录者实体
				Object obj = request.getSession().getAttribute(Constant.LoginAdminUser);
				Object login = request.getSession().getAttribute(Constant.Login);
				if (login == null && null == obj) {
					// 如果session中不存在登录者实体，则弹出框提示重新登录
					// 设置request和response的字符集，防止乱码
					request.setCharacterEncoding("GBK");
					response.setCharacterEncoding("GBK");
					PrintWriter out = response.getWriter();
					String loginPage = request.getContextPath() + "/admin";
					StringBuilder builder = new StringBuilder();
					builder.append("<script type=\"text/javascript\">");
					builder.append("window.top.location.href='");
					builder.append(loginPage);
					builder.append("';");
					builder.append("</script>");
					out.print(builder.toString());
				} else {
					if (uri.equals(request.getContextPath() + "/admin/images/imagesUpload")) {
						new ImageUploader().doUploadImage(request, response);
						return;
					}
					filterChain.doFilter(request, response);
				}
			} else {
				filterChain.doFilter(request, response);
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}

}
