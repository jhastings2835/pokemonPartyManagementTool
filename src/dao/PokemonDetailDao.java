package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.PokemonDetailEntity;

public class PokemonDetailDao {

	private final String selectBasicType = "SELECT * FROM M_BASIC_TYPE";

	private final String selectTrainedPokemon = "SELECT * FROM T_TRAINED_POKEMON";

	private final String selectDispPokemonDetail = "SELECT trained.id ,name.NAME ,personality.NAME ,ability.NAME ,item.NAME ,trained.TOTAL_EFFORT_VALUE ,trained.ATTACK_EFFORT_VALUE ,trained.DEFENSE_EFFORT_VALUE ,trained.SPECIAL_ATTACK_EFFORT_VALUE ,trained.SPECIAL_DEFENSE_EFFORT_VALUE ,trained.SPEED_EFFORT_VALUE FROM T_TRAINED_POKEMON trained inner join M_POKEMON name on trained.POKEMON_ID = name.ID and trained.POKEMON_FORM_ID = name.FORM_ID inner join M_PERSONALITY personality on trained.PERSONALITY_ID = personality.ID inner join M_ABILITY ability on trained.ABILITY_ID = ability.ID inner join M_ITEM item on trained.ITEM_ID = item.ID";

	public List<PokemonDetailEntity> selectMBasictType() {

		List<PokemonDetailEntity> PokemonDetailList = new ArrayList<PokemonDetailEntity>();

		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:lib/localDB/localDB.db");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(selectBasicType);
			while (rs.next()) {
				PokemonDetailEntity pokemonDetail = new PokemonDetailEntity();
				pokemonDetail.setId(rs.getString("id"));
				pokemonDetail.setName(rs.getString("name"));
				PokemonDetailList.add(pokemonDetail);
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return PokemonDetailList;
	}

	public List<PokemonDetailEntity> selectTTrainedPokemon() {

		List<PokemonDetailEntity> PokemonDetailList = new ArrayList<PokemonDetailEntity>();

		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:lib/localDB/localDB.db");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(selectTrainedPokemon);
			while (rs.next()) {
				PokemonDetailEntity pokemonDetail = new PokemonDetailEntity();
				pokemonDetail.setId(rs.getString("ID"));
				pokemonDetail.setName(rs.getString("POKEMON_ID"));
				pokemonDetail.setName(rs.getString("POKEMON_FORM_ID"));
				pokemonDetail.setPersonality(rs.getString("PERSONALITY_ID"));
				pokemonDetail.setAblity(rs.getString("ABILITY_ID"));
				pokemonDetail.setItem(rs.getString("ITEM_ID"));
				pokemonDetail.setHitPointsEffortValue(rs.getString("HIT_POINTS_EFFORT_VALUE"));
				pokemonDetail.setAttackEffortValue(rs.getString("ATTACK_EFFORT_VALUE"));
				pokemonDetail.setDefenseEffortValue(rs.getString("DEFENSE_EFFORT_VALUE"));
				pokemonDetail.setSpecialAttackEffortValue(rs.getString("SPECIAL_ATTACK_EFFORT_VALUE"));
				pokemonDetail.setSpecialDefenseEffortValue(rs.getString("SPECIAL_DEFENSE_EFFORT_VALUE"));
				pokemonDetail.setSpeedEffortValue(rs.getString("SPEED_EFFORT_VALUE"));
				pokemonDetail.setTotalEffortValue(rs.getString("TOTAL_EFFORT_VALUE"));
				PokemonDetailList.add(pokemonDetail);
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return PokemonDetailList;
	}

}
