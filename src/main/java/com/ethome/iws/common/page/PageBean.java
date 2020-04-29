package com.ethome.iws.common.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ethome.iws.common.Result;
import com.ethome.iws.domain.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @Description: 分页bean
 * abc
 * @date 2016年7月1日
 */
@JsonIgnoreProperties(value = { "pageCount","beginPageIndex","endPageIndex" })
public class PageBean implements Serializable {

	private static final long serialVersionUID = 5336898393045835469L;

	// 指定的或是页面参数
	private int currentPage; // 当前页
	private int numPerPage; // 每页显示多少条

	// 查询数据库
	private int totalCount; //总记录数
	private List<Object> recordList; //本页的数据列表

	// 计算
	private int pageCount; //总页数
	private int beginPageIndex; // 页码列表的开始索引（包含）
	private int endPageIndex; // 页码列表的结束索引（包含）

	private Map<String, Object> countResultMap; // 当前分页条件下的统计结果

	public PageBean() {
	}

	/**
	 * 只接受前4个必要的属性，会自动的计算出其他3个属生的值
	 * 
	 * @param currentPage
	 * @param numPerPage
	 * @param totalCount
	 * @param recordList
	 */
	public PageBean(int currentPage, int numPerPage, int totalCount, List<Object> recordList) {
		this.currentPage = currentPage;
		this.numPerPage = numPerPage;
		this.totalCount = totalCount;
		this.recordList = recordList;

		// 计算总页码
		pageCount = (totalCount + numPerPage - 1) / numPerPage;

		// 计算 beginPageIndex 和 endPageIndex
		// >> 总页数不多于10页，则全部显示
		if (pageCount <= 10) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		// >> 总页数多于10页，则显示当前页附近的共10个页码
		else {
			// 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
			beginPageIndex = currentPage - 4;
			endPageIndex = currentPage + 5;
			// 当前面的页码不足4个时，则显示前10个页码
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			// 当后面的页码不足5个时，则显示后10个页码
			if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 10 + 1;
			}
		}
	}

	/**
	 * 只接受前5个必要的属性，会自动的计算出其他3个属生的值
	 * 
	 * @param currentPage
	 * @param numPerPage
	 * @param totalCount
	 * @param recordList
	 */
	public PageBean(int currentPage, int numPerPage, int totalCount, List<Object> recordList,
			Map<String, Object> countResultMap) {
		this.currentPage = currentPage;
		this.numPerPage = numPerPage;
		this.totalCount = totalCount;
		this.recordList = recordList;
		this.countResultMap = countResultMap;

		// 计算总页码
		pageCount = (totalCount + numPerPage - 1) / numPerPage;

		// 计算 beginPageIndex 和 endPageIndex
		// >> 总页数不多于10页，则全部显示
		if (pageCount <= 10) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		// >> 总页数多于10页，则显示当前页附近的共10个页码
		else {
			// 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
			beginPageIndex = currentPage - 4;
			endPageIndex = currentPage + 5;
			// 当前面的页码不足4个时，则显示前10个页码
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			// 当后面的页码不足5个时，则显示后10个页码
			if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 10 + 1;
			}
		}
	}

	private String pageInfo;// 分页信息
	
	public String getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(String pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getPage(String url, BaseEntity queryCondition) {
		url += queryCondition.queryCondition(queryCondition);
		StringBuffer sb = new StringBuffer();
		if (totalCount > 0 && currentPage > 0 && url != null && !url.trim().equals("")) {
			// 获得总页数
			long count = totalCount <= numPerPage ? 1 : totalCount / numPerPage;
			if (totalCount > numPerPage && totalCount % numPerPage != 0) {
				count += 1;
			}
			String temp = url.indexOf("?") != -1 ? "&" : "?";
			if (numPerPage == 20) {
				sb.append("显示<a href='" + url + temp + "pageParam.pageNum=1&pageParam.numPerPage=20' class='accut' >20</a> ");
				sb.append(" <a href='" + url + temp + "pageParam.pageNum=1&pageParam.numPerPage=50' >50</a> ");
				sb.append(" <a href='" + url + temp + "pageParam.pageNum=1&pageParam.numPerPage=100' >100</a> 行 ");

			} else if (numPerPage == 50) {
				sb.append("显示<a href='" + url + temp + "pageParam.pageNum=1&pageParam.numPerPage=20' >20</a> ");
				sb.append(" <a href='" + url + temp + "pageParam.pageNum=1&pageParam.numPerPage=50' class='accut' >50</a> ");
				sb.append(" <a href='" + url + temp + "pageParam.pageNum=1&pageParam.numPerPage=100' >100</a> 行 ");
			} else {
				sb.append("显示<a href='" + url + temp + "pageParam.pageNum=1&pageParam.numPerPage=20' >20</a> ");
				sb.append(" <a href='" + url + temp + "pageParam.pageNum=1&pageParam.numPerPage=50' >50</a> ");
				sb.append(" <a href='" + url + temp + "pageParam.pageNum=1&pageParam.numPerPage=100' class='accut'>100</a>  行");
			}
			sb.append(
					"<input type='hidden' name='jspUtil.numPerPage' id='numPerPage' value='" + numPerPage + "'>");
			if (count != 1) {
				sb.append("<a href='" + url + temp + "pageParam.pageNum=1&pageParam.numPerPage=" + numPerPage + "'><<</a>");

				if (count < 9) {
					for (int i = 1; i <= count; i++) {
						if (i != currentPage) {
							sb.append("<a href='" + url + temp + "pageParam.pageNum=" + i + "&pageParam.numPerPage=" + numPerPage + "'>"
									+ i + "</a>");
						} else {
							sb.append("<a href='" + url + temp + "pageParam.pageNum=" + i + "&pageParam.numPerPage=" + numPerPage
									+ "' class='accut'>" + i + "</a>");
						}
					}

					count = currentPage + 1 > count ? count : currentPage + 1;
					sb.append("<a href='" + url + temp + "pageParam.pageNum=" + count + "&pageParam.numPerPage=" + numPerPage
							+ "'>></a>");
				} else {
					if (currentPage < 4) {
						int leng = 8;
						if (currentPage != 1) {
							leng = 7;
							sb.append("<a href='" + url + temp + "pageParam.pageNum=" + (currentPage - 1) + "&pageParam.numPerPage="
									+ numPerPage + "'><</a>");
						}
						for (int i = 1; i < leng; i++) {
							if (i == currentPage) {
								sb.append("<a href='" + url + temp + "pageParam.pageNum=" + i + "&pageParam.numPerPage=" + numPerPage
										+ "' class='accut'>" + i + "</a>");
							} else {
								sb.append("<a href='" + url + temp + "pageParam.pageNum=" + i + "&pageParam.numPerPage=" + numPerPage
										+ "'>" + i + "</a>");
							}
						}
						sb.append("<a href='" + url + temp + "pageParam.pageNum=" + count + "&pageParam.numPerPage=" + numPerPage
								+ "'>..." + count + "</a>");
						count = currentPage + 1 > count ? count : currentPage + 1;
						sb.append("<a href='" + url + temp + "pageParam.pageNum=" + count + "&pageParam.numPerPage=" + numPerPage
								+ "'>></a>");
					} else if (currentPage >= 4 && currentPage != count) {
						sb.append("<a href='" + url + temp + "pageParam.pageNum=1&pageParam.numPerPage=" + numPerPage + "'>1...</a>");
						sb.append("<a href='" + url + temp + "pageParam.pageNum=" + (currentPage - 1) + "&pageParam.numPerPage="
								+ numPerPage + "'><</a>");
						long j = currentPage - 2 + 5;
						j = j > count ? count : j;
						int i = currentPage - 2;
						if ((j - i) < 5) {
							i -= 5 - (j - i);
						}
						for (; i < j; i++) {
							if (i == currentPage) {
								sb.append("<a href='" + url + temp + "pageParam.pageNum=" + i + "&pageParam.numPerPage=" + numPerPage
										+ "' class='accut'>" + i + "</a>");
							} else {
								sb.append("<a href='" + url + temp + "pageParam.pageNum=" + i + "&pageParam.numPerPage=" + numPerPage
										+ "'>" + i + "</a>");
							}
						}
						sb.append("<a href='" + url + temp + "pageParam.pageNum=" + count + "&pageParam.numPerPage=" + numPerPage
								+ "'>..." + count + "</a>");
						count = currentPage + 1 > count ? count : currentPage + 1;
						sb.append("<a href='" + url + temp + "pageParam.pageNum=" + count + "&pageParam.numPerPage=" + numPerPage
								+ "'>></a>");
					} else {
						sb.append("<a href='" + url + temp + "pageParam.pageNum=1&pageParam.numPerPage=" + numPerPage + "'>1...</a>");
						sb.append("<a href='" + url + temp + "pageParam.pageNum=" + (currentPage - 1) + "&pageParam.numPerPage="
								+ numPerPage + "'><</a>");
						for (long i = count - 6; i < count; i++) {
							if (i == currentPage) {
								sb.append("<a href='" + url + temp + "pageParam.pageNum=" + i + "&pageParam.numPerPage=" + numPerPage
										+ "' class='accut'>" + i + "</a>");
							} else {
								sb.append("<a href='" + url + temp + "pageParam.pageNum=" + i + "&pageParam.numPerPage=" + numPerPage
										+ "'>" + i + "</a>");
							}
						}
						sb.append("<a href='" + url + temp + "pageParam.pageNum=" + count + "&pageParam.numPerPage=" + numPerPage
								+ "' class='accut'>" + count + "</a>");
					}
				}
			}
			pageInfo = sb.toString();
		}
		return pageInfo;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<Object> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Object> recordList) {
		this.recordList = recordList;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public Map<String, Object> getCountResultMap() {
		return countResultMap;
	}

	public void setCountResultMap(Map<String, Object> countResultMap) {
		this.countResultMap = countResultMap;
	}

	public static void main(String[] args){
//		List<Object> recordList = new ArrayList<>();
//		Device device = new Device();
//		device.setDeviceName("流量流速计");
//		device.setActualDeviceTypeId("0310");
//		device.setStatus("on");
//		device.setMatchStatus("ok");
//		device.setProperty1("100");
//		device.setProperty2("10");
//		device.setProperty3("50");
//		device.setProperty4("10");
//		recordList.add(device);
//		PageBean page = new PageBean(1, 10, 1, recordList);
//		
//		System.out.println(Result.success(page).writeValueAsString());
	}
}
