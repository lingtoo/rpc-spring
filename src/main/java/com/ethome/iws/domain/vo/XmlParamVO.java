package com.ethome.iws.domain.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

import com.ethome.iws.common.ReturnCode;

public class XmlParamVO
{

	private static final SimpleDateFormat format_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Element xmlroot;
	
	/**
	 * 消息分隔符
	 */
	public static final String SPLIT ="<<::>>";

	public XmlParamVO(Element element)
	{
		xmlroot = element;
	}

	public XmlParamVO(String base)
	{
		xmlroot = new Element(base);
	}
	public void addBoolValue(String path, boolean value)
	{
		addStringValue(path, String.valueOf(value));
	}

	public void addDateValue(String path, Date date)
	{
		addStringValue(path, format_time.format(date));
	}

	public void addDoubleValue(String path, double value)
	{
		addStringValue(path, String.valueOf(value));
	}

	public void addIntValue(String path, int value)
	{
		addStringValue(path, String.valueOf(value));
	}

	public void addLongValue(String path, long value)
	{
		addStringValue(path, String.valueOf(value));
	}

	public void addShortValue(String path, short value)
	{
		addStringValue(path, String.valueOf(value));
	}

	public void addStringValue(String path, String value) {
		String paths[][] = decodePathString(path);
		Element element = createXmlElement(paths[0], paths[0].length);
		if (element == null) {
			throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("指定路径")).append(path).append("的节点创建失败").toString());
		}
		if (paths[1] != null) {
			element.setAttribute(paths[1][0], value);
		} else {
			List list = element.removeContent();
			element.setText(value);
			for (int idx = 0; idx < list.size(); idx++) {
				if (list.get(idx) instanceof Element) {
					element.addContent((Element) list.get(idx));
				}
			}

		}
	}
	
	public Date getDateValue (String path) throws ParseException
	{
		String value = getStringValue(path);
		return format_time.parse(value);

	}

	public boolean getBoolValue(String path)
	{
		return Boolean.parseBoolean(getStringValue(path));
	}

	public double getDoubleValue(String path)
	{
		return Double.parseDouble(getStringValue(path));
	}

	public int getIntValue(String path)
	{
		return Integer.parseInt(getStringValue(path));
	}

	public long getLongValue(String path)
	{
		return Long.parseLong(getStringValue(path));
	}

	public short getShortValue(String path)
	{
		return Short.parseShort(getStringValue(path));
	}

	public boolean getBoolValue(String path, boolean defvalue)
	{
		return Boolean.parseBoolean(getStringValue(path, String.valueOf(defvalue)));
	}

	public double getDoubleValue(String path, double defvalue)
	{
		return Double.parseDouble(getStringValue(path, String.valueOf(defvalue)));
	}

	public int getIntValue(String path, int defvalue)
	{
		return Integer.parseInt(getStringValue(path, String.valueOf(defvalue)));
	}

	public long getLongValue(String path, long defvalue)
	{
		return Long.parseLong(getStringValue(path, String.valueOf(defvalue)));
	}

	public short getShortValue(String path, short defvalue)
	{
		return Short.parseShort(getStringValue(path, String.valueOf(defvalue)));
	}

	public String getStringValue(String path) {
		String paths[][] = decodePathString(path);
		Element element = findXmlElement(paths[0], paths[0].length);
		if (element == null) {
			throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("指定路径")).append(path).append("的节点未找到").toString());
		}
		if (paths[1] != null) {
			Attribute attr = element.getAttribute(paths[1][0]);
			if (attr != null) {
				return attr.getValue();
			} else {
				throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("当前路径<")).append(path).append(">的属性未定义").toString());
			}
		} else {
			return element.getText();
		}
	}

	public String getStringValue(String path, String defvalue) {
		String paths[][] = decodePathString(path);
		Element element = findXmlElement(paths[0], paths[0].length);
		if (element == null) {
			return defvalue;
		}
		if (paths[1] != null) {
			Attribute attr = element.getAttribute(paths[1][0]);
			if (attr != null) {
				return attr.getValue();
			} else {
				return defvalue;
			}
		} else {
			return element.getText();
		}
	}

	public Hashtable getXmlAttrs(String path)
	{
		String paths[][] = decodePathString(path);
		if (paths[1] != null){
			throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("当前路径<")).append(path).append(">为属性路径").toString());
		}
		String dirs[] = paths[0];
		Element element = findXmlElement(dirs, dirs.length);
		if (element == null){
			throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("指定路径")).append(path).append("的节点未找到").toString());
		}
		Hashtable hash = new Hashtable();
		List attrs = element.getAttributes();
		for (int idx = 0; idx < attrs.size(); idx++){
			hash.put(((Attribute)attrs.get(idx)).getName(), ((Attribute)attrs.get(idx)).getValue());
		}
		return hash;
	}

	public XmlParamVO getXmlNode(String path)
	{
		String paths[][] = decodePathString(path);
		if (paths[1] != null){
			throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("当前路径<")).append(path).append(">为属性路径").toString());
		}
		String dirs[] = paths[0];
		Element element = findXmlElement(dirs, dirs.length);
		if (element == null){
			throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("指定路径")).append(path).append("的节点未找到").toString());
		}
		if (element == xmlroot){
			return this;
		}else{
			return new XmlParamVO(element);
		}
	}

	public List<XmlParamVO> getXmlNodeList(String path)
	{
		String paths[][] = decodePathString(path);
		if (paths[1] != null){
			throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("当前路径<")).append(path).append(">为属性路径").toString());
		}
		String dirs[] = paths[0];
		Element element = findXmlElement(dirs, dirs.length - 1);
		if (element == null){
			throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("指定路径")).append(path).append("的节点未找到").toString());
		}
		List<XmlParamVO> volist = new ArrayList<XmlParamVO>();
		List elmlist = element.getChildren(dirs[dirs.length - 1]);
		for (int idx = 0; idx < elmlist.size(); idx++){
			volist.add(new XmlParamVO((Element)elmlist.get(idx)));
		}
		return volist;
	}

	public int countXmlNodes(String path)
	{
		String paths[][] = decodePathString(path);
		if (paths[1] != null){
			throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("当前路径<")).append(path).append(">为属性路径").toString());
		}
		String dirs[] = paths[0];
		Element element = findXmlElement(dirs, dirs.length - 1);
		if (element == null){
			return 0;
		}else{
			return element.getChildren(dirs[dirs.length - 1]).size();
		}
	}

	public XmlParamVO addXmlNode(String path)
	{
		String paths[][] = decodePathString(path);
		if (paths[1] != null){
			throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("当前路径<")).append(path).append(">为属性路径").toString());
		}
		String dirs[] = paths[0];
		Element element = createXmlElement(dirs, dirs.length);
		if (element == null){
			throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("指定路径")).append(path).append("的节点未创建成功").toString());
		}
		if (element == xmlroot){
			return this;
		}else{
			return new XmlParamVO(element);
		}
	}

	public Element getXmlRoot()
	{
		return getXmlRoot(false);
	}

	public Element getXmlRoot(boolean top)
	{
		if (!top){
			return xmlroot;
		}
		Element tmp;
		for (tmp = xmlroot; tmp.getParentElement() != null; tmp = tmp.getParentElement());
		return tmp;
	}

	public void setBoolValue(String path, boolean value)
	{
		setStringValue(path, String.valueOf(value));
	}

	public void setDateValue(String path, Date date)
	{
		setStringValue(path, format_time.format(date));
	}

	public void setDoubleValue(String path, double value)
	{
		setStringValue(path, String.valueOf(value));
	}

	public void setIntValue(String path, int value)
	{
		setStringValue(path, String.valueOf(value));
	}

	public void setLongValue(String path, long value)
	{
		setStringValue(path, String.valueOf(value));
	}

	public void setShortValue(String path, short value)
	{
		setStringValue(path, String.valueOf(value));
	}

	public void setStringValue(String path, String value)
	{
		String paths[][] = decodePathString(path);
		Element element = findXmlElement(paths[0], paths[0].length);
		if (element == null){
			throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("指定路径")).append(path).append("的节点未找到").toString());
		}
		if (paths[1] != null){
			element.setAttribute(paths[1][0], value);
		} else {
			List list = element.removeContent();
			element.setText(value);
			for (int idx = 0; idx < list.size(); idx++){
				if (list.get(idx) instanceof Element){
					element.addContent((Element)list.get(idx));
				}
			}

		}
	}

	private Element createXmlElement(String pathdir[], int pathlen) {
		Element curnode = xmlroot;
		for (int idx = 0; idx < pathlen; idx++) {
			String iteminfo[] = decodeNodeString(pathdir[idx]);
			int itemidx = Integer.parseInt(iteminfo[0]);
			String itemname = iteminfo[1];
			List subnodes = curnode.getChildren(itemname);
			if (itemidx < 0) {
				if (idx == pathlen - 1) {
					Element element = new Element(itemname);
					curnode.addContent(element);
					curnode = element;
				} else if (subnodes.size() == 0) {
					Element element = new Element(itemname);
					curnode.addContent(element);
					curnode = element;
				} else if (subnodes.size() == 1)
					curnode = (Element) subnodes.get(0);
				else{
					throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("元素节点")).append(pathdir[idx]).append("不明确").toString());
				}
			} else {
				if (subnodes.size() == 0){
					throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("元素节点")).append(itemname).append("未定义").toString());
				}
				if (itemidx >= subnodes.size()){
					throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("元素节点")).append(pathdir[idx]).append("未定义").toString());
				}
				curnode = (Element) subnodes.get(itemidx);
			}
		}

		return curnode;
	}

	private String[] decodeNodeString(String node)
	{
		int pos = node.indexOf("[");
		if (pos != -1){
			return (new String[] {node.substring(pos + 1, node.length() - 1), node.substring(0, pos)});
		}else{
			return (new String[] {"-1", node});
		}
	}

	private String[][] decodePathString(String path)
	{
		String temp[] = fsplit(path.trim(), ".");
		int idx = 0;
		for (int len = temp.length; idx < len; idx++){
			if (idx < len - 1 && temp[idx].indexOf("@") != -1){
				throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("<")).append(path).append(">:中间路径不能够有@符号").toString());
			}
			if (idx == len - 1 && split(temp[idx], "@").length > 2){
				throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("<")).append(path).append(">:最后属性项有且只能有一个@符号").toString());
			}
			if (idx == len - 1 && temp[idx].indexOf("@") != -1 && !temp[idx].startsWith("@")){
				throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("<")).append(path).append(">:最后属性项只能以@开头").toString());
			}
			if (split(temp[idx], "[").length > 2 || split(temp[idx], "]").length > 2){
				throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("<")).append(path).append(">:路径项的子项标志[]有且只能有一对").toString());
			}
			int spos = temp[idx].indexOf("[");
			int epos = temp[idx].indexOf("]");
			if (spos * epos < 0){
				throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("<")).append(path).append(">:路径项的子项标志[]不匹配").toString());
			}
			if (epos != -1 && epos != temp[idx].length() - 1){
				throw new RuntimeException(ReturnCode.XML_FORMAT_ERR+SPLIT +(new StringBuilder("<")).append(path).append(">:路径项的子项标志[]必须以]结尾").toString());
			}
		}

		if (temp[temp.length - 1].startsWith("@")){
			String dirs[] = new String[temp.length - 1];
			System.arraycopy(temp, 0, dirs, 0, dirs.length);
			return (new String[][] {dirs, new String[] {temp[temp.length - 1].substring(1)}});
		} else {
			return (new String[][] {temp, null});
		}
	}

	private Element findXmlElement(String pathdirs[], int pathlen) {
		Element curnode = xmlroot;
		for (int idx = 0; idx < pathlen; idx++) {
			String iteminfo[] = decodeNodeString(pathdirs[idx]);
			int itemidx = Integer.parseInt(iteminfo[0]);
			String itemname = iteminfo[1];
			List subnodes = curnode.getChildren(itemname);
			if (subnodes.size() == 0) {
				return null;
			}
			if (itemidx < 0) {
				if (subnodes.size() > 1) {
					return null;
				}
				curnode = (Element) subnodes.get(0);
			} else {
				if (itemidx >= subnodes.size()) {
					return null;
				}
				curnode = (Element) subnodes.get(itemidx);
			}
		}
		return curnode;
	}
	
	@SuppressWarnings("unchecked")
	private static final String[] fsplit(String source, String match) {
		List splits = new ArrayList();
		if (match != null && match.length() > 0) {
			if (source.indexOf(match) == -1) {
				splits.add(source);
			} else {
				int lpos = 0;
				int len = match.length();
				for (int cpos = 0; (cpos = source.indexOf(match, cpos)) != -1;)
					if (cpos == lpos) {
						cpos += len;
						lpos = cpos;
					} else {
						splits.add(source.substring(lpos, cpos));
						cpos += len;
						lpos = cpos;
					}

				if (lpos != source.length())
					splits.add(source.substring(lpos));
			}
		} else {
			for (int idx = 0; idx < source.length(); idx++) {
				splits.add(source.substring(idx, idx + 1));
			}
		}
		return (String[]) splits.toArray(new String[splits.size()]);
	}
	
	@SuppressWarnings("unchecked")
	private static final String[] split(String source, String match) {
		List splits = new ArrayList();
		if (match != null && match.length() > 0) {
			if (source.indexOf(match) == -1) {
				splits.add(source);
			} else {
				int lpos = 0;
				int len = match.length();
				int cpos;
				for (cpos = 0; (cpos = source.indexOf(match, cpos)) != -1;) {
					splits.add(source.substring(lpos, cpos));
					cpos += len;
					lpos = cpos;
				}

				if (cpos != source.length() - 1) {
					splits.add(source.substring(lpos));
				}
			}
		} else {
			for (int idx = 0; idx < source.length(); idx++) {
				splits.add(source.substring(idx, idx + 1));
			}
		}
		return (String[]) splits.toArray(new String[splits.size()]);
	}

}
