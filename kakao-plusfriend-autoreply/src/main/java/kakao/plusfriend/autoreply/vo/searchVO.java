package kakao.plusfriend.autoreply.vo;


/** 
 * 
 * @author crane76
 * DB에 검색하기 위한 VO
 * id, content, regular, type, classpath, method, createdt, modifydt
 */
public class searchVO {

	private String id;
	private String content;
	private String regular;
	private String type;
	private String classpath;
	private String method;
	private String createdt;
	private String modifydt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegular() {
		return regular;
	}
	public void setRegular(String regular) {
		this.regular = regular;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getClasspath() {
		return classpath;
	}
	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getCreatedt() {
		return createdt;
	}
	public void setCreatedt(String createdt) {
		this.createdt = createdt;
	}
	public String getModifydt() {
		return modifydt;
	}
	public void setModifydt(String modifydt) {
		this.modifydt = modifydt;
	}
}
