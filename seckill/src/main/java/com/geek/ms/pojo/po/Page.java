package com.geek.ms.pojo.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable{
	
	private static final long serialVersionUID = -2384687956864824872L;
	// 1.当前页数 从页面获取
    private int page;
    // 2.每页显示数据个数，赋初值或者setter获取
    private int size;
    // 4.总页数，计算得到
    private int total;
    // 5.每页的显示数据，数据库得到
    List<?> records = new ArrayList<>();
	public Page() {
		super();
	}
	public Page(int page, int size) {
		super();
		this.page = page;
		this.size = size;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<?> getRecords() {
		return records;
	}
	public void setRecords(List<?> records) {
		this.records = records;
	}
	@Override
	public String toString() {
		return "Page [page=" + page + ", size=" + size + ", total=" + total + ", records=" + records + "]";
	}
    
}
