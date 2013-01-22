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

		// �����˵�uri
		String[] notFilter = new String[] { "login", "captcha-image" };

		// �����uri
		String uri = request.getRequestURI();

		String uristr = uri.replace("\\", "");
		uristr = uristr.replace("/", "");
//		String context = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
//				+ request.getContextPath();
//		System.out.println(context);

		// uri�а���backgroundʱ�Ž��й���

		if (uri.indexOf("app") != -1 || (uri.indexOf("admin") != -1 && !uristr.endsWith("admin"))) {
			// �Ƿ����
			boolean doFilter = true;
			for (String s : notFilter) {
				if (uri.indexOf(s) != -1) {
					doFilter = false;
					break;
				}
			}
			if (doFilter) {
				// ִ�й���
				// ��session�л�ȡ��¼��ʵ��
				Object obj = request.getSession().getAttribute(Constant.LoginAdminUser);
				Object login = request.getSession().getAttribute(Constant.Login);
				if (login == null && null == obj) {
					// ���session�в����ڵ�¼��ʵ�壬�򵯳�����ʾ���µ�¼
					// ����request��response���ַ�������ֹ����
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
