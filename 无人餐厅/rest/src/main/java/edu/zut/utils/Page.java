package edu.zut.utils;

//辅助分页工具类
public class Page {
	private int pageStart;
	private int pageSize=3;
	private int pageNum;
	private int pageMax;
	private int totalLine;
	
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Page(int pageStart, int pageSize, int pageNum, int pageMax) {
		super();
		this.pageStart = pageStart;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.pageMax = pageMax;
	}
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * 设置当前页
	 * @param pageNum
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		this.pageMax = totalLine%pageSize==0?totalLine/pageSize:(totalLine/pageSize)+1; //一并设置最大页数
		this.pageStart = (pageNum-1)*pageSize;//每页起始
	}
	
	public int getTotalLine() {
		return totalLine;
	}

	/**
	 * 设置总页数,一定要在setpagenum之情调用
	 * @param totalLine
	 */
	public void setTotalLine(int totalLine) {
		this.totalLine = totalLine;
	}

	@Override
	public String toString() {
		return "Page{" +
				"pageStart=" + pageStart +
				", pageSize=" + pageSize +
				", pageNum=" + pageNum +
				", pageMax=" + pageMax +
				", totalLine=" + totalLine +
				'}';
	}

	public int getPageStart() {
		return pageStart;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getPageMax() {
		return pageMax;
	}

	
	
	
	
}
