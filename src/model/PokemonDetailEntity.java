package model;


public class PokemonDetailEntity extends DataBaseModel{
	private String id;
	
	private String name;
	
	private String personality;
	
	private String ablity;
	
	private String item;
	
	private String totalEffortValue;
	
	private String hitPointsEffortValue;
	
	private String attackEffortValue;
	
	private String defenseEffortValue;
	
	private String specialAttackEffortValue;
	
	private String specialDefenseEffortValue;
	
	private String speedEffortValue;

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

	public String getPersonality() {
		return personality;
	}

	public void setPersonality(String personality) {
		this.personality = personality;
	}

	public String getAblity() {
		return ablity;
	}

	public void setAblity(String ablity) {
		this.ablity = ablity;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getTotalEffortValue() {
		return totalEffortValue;
	}

	public void setTotalEffortValue(String totalEffortValue) {
		this.totalEffortValue = totalEffortValue;
	}

	public String getHitPointsEffortValue() {
		return hitPointsEffortValue;
	}

	public void setHitPointsEffortValue(String hitPointsEffortValue) {
		this.hitPointsEffortValue = hitPointsEffortValue;
	}

	public String getAttackEffortValue() {
		return attackEffortValue;
	}

	public void setAttackEffortValue(String attackEffortValue) {
		this.attackEffortValue = attackEffortValue;
	}

	public String getDefenseEffortValue() {
		return defenseEffortValue;
	}

	public void setDefenseEffortValue(String defenseEffortValue) {
		this.defenseEffortValue = defenseEffortValue;
	}

	public String getSpecialAttackEffortValue() {
		return specialAttackEffortValue;
	}

	public void setSpecialAttackEffortValue(String specialAttackEffortValue) {
		this.specialAttackEffortValue = specialAttackEffortValue;
	}

	public String getSpecialDefenseEffortValue() {
		return specialDefenseEffortValue;
	}

	public void setSpecialDefenseEffortValue(String specialDefenseEffortValue) {
		this.specialDefenseEffortValue = specialDefenseEffortValue;
	}

	public String getSpeedEffortValue() {
		return speedEffortValue;
	}

	public void setSpeedEffortValue(String speedEffortValue) {
		this.speedEffortValue = speedEffortValue;
	}
}
