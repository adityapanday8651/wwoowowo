package com.wowment.logging.api.layouts;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;
import org.apache.logging.log4j.core.lookup.ResourceBundleLookup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wowment.logging.api.utils.AppUtils;

@Plugin(name = "CustomStringLayout", category = "Core", elementType = "layout", printObject = true)
public class LogMessageLayout extends AbstractStringLayout {
	
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

	private final Gson gson = new GsonBuilder().create();

	protected LogMessageLayout(Charset charset) {
		super(charset);
	}

	@PluginFactory
	public static LogMessageLayout createLayout(@PluginAttribute("locationInfo") boolean locationInfo,
			@PluginAttribute("properties") boolean properties, @PluginAttribute("complete") boolean complete,
			@PluginAttribute(value = "charset", defaultString = "UTF-8") Charset charset) {
		return new LogMessageLayout(charset);

	}

	public String toSerializable(LogEvent event) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("appName",new ResourceBundleLookup().lookup(event, "logging:appName"));	
		map.put("message", event.getMessage().toString());
		map.put("datetime", AppUtils.dateToString(new Date(),DATE_TIME_FORMAT));
		map.put("Level", event.getLevel().toString());
		map.put("threadId", event.getThreadId());
		map.put("loggerName", event.getLoggerName());
		if(event.getThrown() != null) {
			map.put("exception", event.getThrown().getMessage());
		}
		return gson.toJson(map) + "\n";
	}
}
