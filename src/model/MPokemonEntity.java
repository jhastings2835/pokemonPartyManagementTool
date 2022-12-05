package model;

public class MPokemonEntity extends DataBaseModel {
	private String id;

	private String formId;

	private String Name;

	private String formRemarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getFormRemarks() {
		return formRemarks;
	}

	public void setFormRemarks(String formRemarks) {
		this.formRemarks = formRemarks;
	}
}
