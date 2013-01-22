/**
 * 
 */
package com.spark.front.manager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.spark.cms.services.channel.ChannelService;

/**
 * @author Jideas
 * 
 */
public abstract class JSPServericeProvider {

	@SuppressWarnings("unchecked")
	public final static PageManager getPageManager(HttpServletRequest request) {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession()
				.getServletContext());
		List<Object> pmlist = new ArrayList(context.getBeansOfType(PageManager.class).values());
		if (pmlist.isEmpty()) {
			return null;
		}
		return (PageManager) pmlist.get(0);
	}
	
	public final static ChannelService getChannelService(HttpServletRequest request) {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession()
				.getServletContext());
		List<Object> pmlist = new ArrayList<Object>(context.getBeansOfType(ChannelService.class).values());
		if (pmlist.isEmpty()) {
			return null;
		}
		return (ChannelService) pmlist.get(0);
	}
}
