package org.zerock.domain;

public class BoardAttachVO {
	private String uuid;
	private	String uploadpath;
	private String filename;
	private boolean filetype;
	private int bno;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUploadpath() {
		return uploadpath;
	}
	public void setUploadpath(String uploadpath) {
		this.uploadpath = uploadpath;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public boolean isFiletype() {
		return filetype;
	}
	public void setFiletype(boolean filetype) {
		this.filetype = filetype;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	
	@Override
	public String toString() {
		return "BoardAttachVO [uuid=" + uuid + ", uploadpath=" + uploadpath + ", filename=" + filename + ", filetype="
				+ filetype + ", bno=" + bno + "]";
	}	
	
}
