package br.com.equiplano.seguradora.core.util;

public class Paginador {

	private long offset = 0;
	private int pageNumber = 1;
	int pageSize = 10;
	private boolean sortDesc;
	private boolean sortAsc;
	

	public Paginador(long offset, int pageNumber, int pageSize, boolean sortDesc, boolean sortAsc) {
		super();
		this.offset = offset;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.sortDesc = sortDesc;
		this.sortAsc = sortAsc;
	}
	
	public Paginador() {
		super();
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		if(pageSize == 0) {
			return 10;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isSortDesc() {
		return sortDesc;
	}

	public void setSortDesc(boolean sortDesc) {
		this.sortDesc = sortDesc;
	}

	public boolean isSortAsc() {
		return sortAsc;
	}

	public void setSortAsc(boolean sortAsc) {
		this.sortAsc = sortAsc;
	}

}
