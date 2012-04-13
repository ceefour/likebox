package com.hendyirawan.likebox.common;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JacksonUtils {
	private static transient Logger log = LoggerFactory.getLogger(JacksonUtils.class);

	private static ObjectMapper mapper;
	
	static {
		mapper = new ObjectMapper();
		mapper.configure(Feature.INDENT_OUTPUT, true);
		mapper.configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
	}
	
	public static String asJson(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			log.error("Cannot map " + obj.getClass() + " to JSON", e);
			return null;
		}
	}
}
