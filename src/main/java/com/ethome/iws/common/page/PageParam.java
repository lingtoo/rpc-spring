package com.ethome.iws.common.page;

import java.io.Serializable;

/**
 * @Description: 分页参数
 * abc
 * @date 2016年7月1日
 */
public class PageParam implements Serializable {

	private static final long serialVersionUID = -2733567985075288438L;

	private int pageNum; // 当前页数
	private int numPerPage; // 每页记录数
	
	/**
	 * 默认每页20000条
	 */
	public PageParam() {
		this.pageNum = 1;
		this.numPerPage = 20000;
	}
	
	public PageParam(int pageNum, int numPerPage) {
		super();
		this.pageNum = pageNum;
		this.numPerPage = numPerPage;
	}

	/** 当前页数 */
	public int getPageNum() {
		return pageNum;
	}

	/** 当前页数 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/** 每页记录数 */
	public int getNumPerPage() {
		return numPerPage;
	}

	/** 每页记录数 */
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

}
