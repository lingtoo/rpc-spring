package com.ethome.iws.common.json;

import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PropertyFilter;

public interface PropFilter extends PropertyFilter, NameFilter {

	boolean apply(Object object, String name, Object value);
	
	String process(Object object, String name, Object value);
}
