package com.iamuse.admin.VO;

public class PaginationVO {
	private Integer first;
	private Integer last;
	private Integer next;
	private Integer previous;
	
	public Integer getFirst() {
		return first;
	}
	public void setFirst(Integer first) {
		this.first = first;
	}
	public Integer getLast() {
		return last;
	}
	public void setLast(Integer last) {
		this.last = last;
	}
	public Integer getNext() {
		return next;
	}
	public void setNext(Integer next) {
		this.next = next;
	}
	public Integer getPrevious() {
		return previous;
	}
	public void setPrevious(Integer previous) {
		this.previous = previous;
	}

}
