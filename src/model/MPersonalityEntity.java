package model;

public class MPersonalityEntity extends DataBaseModel {

	private String id;

	private String name;

	private String attackCorrenction;

	private String defenseCorrenction;

	private String specialAttackCorrection;

	private String specialDefenseCorrection;

	private String speedCorrenction;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAttackCorrenction() {
		return attackCorrenction;
	}

	public void setAttackCorrenction(String attackCorrenction) {
		this.attackCorrenction = attackCorrenction;
	}

	public String getDefenseCorrenction() {
		return defenseCorrenction;
	}

	public void setDefenseCorrenction(String defenseCorrenction) {
		this.defenseCorrenction = defenseCorrenction;
	}

	public String getSpecialAttackCorrection() {
		return specialAttackCorrection;
	}

	public void setSpecialAttackCorrection(String specialAttackCorrection) {
		this.specialAttackCorrection = specialAttackCorrection;
	}

	public String getSpecialDefenseCorrection() {
		return specialDefenseCorrection;
	}

	public void setSpecialDefenseCorrection(String specialDefenseCorrection) {
		this.specialDefenseCorrection = specialDefenseCorrection;
	}

	public String getSpeedCorrenction() {
		return speedCorrenction;
	}

	public void setSpeedCorrenction(String speedCorrenction) {
		this.speedCorrenction = speedCorrenction;
	}

}
