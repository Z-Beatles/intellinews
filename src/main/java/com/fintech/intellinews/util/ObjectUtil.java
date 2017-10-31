package com.fintech.intellinews.util;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;
import org.w3c.dom.NodeList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class ObjectUtil {
	private static final Logger logger = LoggerFactory.getLogger(ObjectUtil.class);

	public static boolean isListSupport(Object obj) {
		if (obj instanceof JsonNode) {
			return ((JsonNode) obj).isArray();
		} else if (obj instanceof Iterator) {
			return true;
		} else if (obj instanceof Iterable) {
			return true;
		} else if (obj instanceof Object[]) {
			return true;
		} else if (obj instanceof Enumeration) {
			return true;
		} else if (obj instanceof NodeList) {
			return true;
		} else if (obj.getClass().isArray()) {
			return true;
		}
		try {
			Method method = obj.getClass().getMethod("iterator", (Class[]) null);
			if (Iterator.class.isAssignableFrom(method.getReturnType())) {
				return true;
			}
		} catch (NoSuchMethodException | SecurityException e) {
		}
		return false;
	}

	public static List<?> toDataList(Object obj, String... candidates) {
		if (ArrayUtils.isEmpty(candidates)) {
			return (List<?>) toPrimitiveList(obj);
		} else {
			return (List<?>) toMapList(obj, candidates);
		}
	}

	public static Object toDataObject(Object obj, String... candidates) {
		if (ArrayUtils.isEmpty(candidates)) {
			return toPrimitiveObject(obj);
		} else {
			return toMapObject(obj, candidates);
		}
	}

	private static List<Map<String, Object>> toMapList(Object obj, String... candidates) {
		List<Map<String, Object>> result = new ArrayList<>();
		if (obj == null) {
			return null;
		} else if (isListSupport(obj)) {
			IteratorUtils.getIterator(obj).forEachRemaining(o -> {
				result.add(toMapObject(o, candidates));
			});
		} else {
			throw new RuntimeException(MessageFormat.format("对象[{0}]不能转换为List", obj));
		}
		return result;
	}

	private static Map<String, Object> toMapObject(Object obj, String... candidates) {
		if (obj == null) {
			return null;
		}
		Function<String, Object> getter = toInternalGetter(obj);
		Map<String, Object> result = new HashMap<>();
		Map<String, List<String>> labelMap = new HashMap<String, List<String>>();
		for (String candidate : candidates) {
			if (candidate.indexOf('.') >= 0) {
				String[] arr = StringUtils.split(candidate, ".", 2);
				List<String> labelList = labelMap.get(arr[0]);
				if (labelList == null) {
					labelList = new ArrayList<>();
					labelMap.put(arr[0], labelList);
				}
				labelList.add(arr[1]);
			} else {
				result.put(candidate, toPrimitiveObject(getter.apply(candidate)));
			}
		}

		for (Map.Entry<String, List<String>> entry : labelMap.entrySet()) {
			Object data = getter.apply(entry.getKey());
			if (data == null) {
				result.put(entry.getKey(), null);
			} else if (isListSupport(data)) {
				result.put(entry.getKey(), toMapList(data, entry.getValue().toArray(new String[entry.getValue().size()])));
			} else {
				result.put(entry.getKey(), toMapObject(data, entry.getValue().toArray(new String[entry.getValue().size()])));
			}
		}
		return result;
	}

	private static List<Object> toPrimitiveList(Object obj) {
		List<Object> result = new ArrayList<>();
		if (obj == null) {
			return null;
		} else if (isListSupport(obj)) {
			IteratorUtils.getIterator(obj).forEachRemaining(o -> result.add(toPrimitiveObject(o)));
		} else {
			throw new RuntimeException(MessageFormat.format("对象[{0}]不能转换为List", obj));
		}
		return result;
	}

	private static Object toPrimitiveObject(Object obj) {
		if (obj == null) {
			return null;
		} else if (isListSupport(obj)) {
			List<Object> list = new ArrayList<>();
			IteratorUtils.getIterator(obj).forEachRemaining(o -> list.add(toPrimitiveObject(o)));
			return list;
		} else {
			return toInternalFormatter(obj);
		}
	}

	private static final Map<String, Function<Object, Object>> FORMATTER_FUNCTION = new HashMap<>();
	static {
		FORMATTER_FUNCTION.put(Boolean.class.getName(), x -> x);
		FORMATTER_FUNCTION.put(Integer.class.getName(), x -> x);
		FORMATTER_FUNCTION.put(Long.class.getName(), x -> x);
		FORMATTER_FUNCTION.put(Float.class.getName(), x -> x);
		FORMATTER_FUNCTION.put(Double.class.getName(), x -> x);
		FORMATTER_FUNCTION.put(String.class.getName(), x -> x);
		FORMATTER_FUNCTION.put(LocalDateTime.class.getName(), x -> DateUtil.toStringFromLocalDateTime((LocalDateTime) x));

		FORMATTER_FUNCTION.put(TextNode.class.getName(), x -> ((TextNode) x).asText());
		FORMATTER_FUNCTION.put(BooleanNode.class.getName(), x -> ((BooleanNode) x).asBoolean());
		FORMATTER_FUNCTION.put(IntNode.class.getName(), x -> ((IntNode) x).intValue());
		FORMATTER_FUNCTION.put(DoubleNode.class.getName(), x -> ((DoubleNode) x).doubleValue());
	}

	private static Object toInternalFormatter(Object obj) {
		if (obj == null) {
			return null;
		} else if (obj.getClass().isPrimitive()) {
			return obj;
		} else if (FORMATTER_FUNCTION.containsKey(obj.getClass().getName())) {
			return FORMATTER_FUNCTION.get(obj.getClass().getName()).apply(obj);
		}
		logger.warn("无法格式化" + obj.getClass().getName() + "类型");
		return obj.toString();
	}

	private static Function<String, Object> toInternalGetter(Object obj) {
		if (obj == null) {
			return null;
		} else if (isListSupport(obj)) {
			throw new RuntimeException(String.format("对象[%s]为列表类型，不能获取指定属性", obj));
		} else if (obj instanceof ObjectNode) {
			return x -> ((ObjectNode) obj).get(x);
		} else if (obj instanceof Map) {
			return x -> ((Map<?, ?>) obj).get(x);
		} else {
			BeanMap map = BeanMap.create(obj);
			return x -> map.get(x);
		}
	}

	public static Long toLong(String str) {
		return StringUtils.isNotBlank(str) ? Long.valueOf(str) : null;
	}

	public static Integer toInteger(String str) {
		return StringUtils.isNotBlank(str) ? Integer.valueOf(str) : null;
	}

	public static Boolean toBoolean(String str) {
		return StringUtils.isNotBlank(str) ? Boolean.valueOf(str) : null;
	}

}
