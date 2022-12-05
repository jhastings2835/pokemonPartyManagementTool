package model;

public class MPokemonAbilityEntity extends DataBaseModel {

	private String pokemonId;

	private String pokemonFormId;

	private String ability1;

	private String ability2;

	private String dreamAbility;

	public String getPokemonId() {
		return pokemonId;
	}

	public void setPokemonId(String pokemonId) {
		this.pokemonId = pokemonId;
	}

	public String getPokemonFormId() {
		return pokemonFormId;
	}

	public void setPokemonFormId(String pokemonFormId) {
		this.pokemonFormId = pokemonFormId;
	}

	public String getAbility1() {
		return ability1;
	}

	public void setAbility1(String ability1) {
		this.ability1 = ability1;
	}

	public String getAbility2() {
		return ability2;
	}

	public void setAbility2(String ability2) {
		this.ability2 = ability2;
	}

	public String getDreamAbility() {
		return dreamAbility;
	}

	public void setDreamAbility(String dreamAbility) {
		this.dreamAbility = dreamAbility;
	}

}
