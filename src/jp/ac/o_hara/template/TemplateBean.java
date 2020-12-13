package jp.ac.o_hara.template;
import java.io.Serializable;

public class TemplateBean implements Serializable {
	private String test = "";
	
	public TemplateBean() {}
	public TemplateBean(String test) {
		this.test = test;
	}
	
	public void setTest(String test) { this.test = test; }
	public String getTest() { return this.test; }
}