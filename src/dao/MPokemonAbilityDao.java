package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.MPersonalityEntity;
import model.MPokemonAbilityEntity;

public class MPokemonAbilityDao {

	private final String selectMPokemonAbilityByIds = "SELECT * FROM M_POKEMON_ABILITY WHERE POKEMON_ID = ? AND POKEMON_FORM_ID = ?";

	private final int POKEMON_ID = 1;

	private final int POKEMON_FORM_ID = 2;

	List<MPokemonAbilityEntity> mPokemonAbilityEntityList = new ArrayList<MPokemonAbilityEntity>();

	/**
	 * @param pokemonId
	 * @param pokemonFormId
	 * @return
	 */
	public MPokemonAbilityEntity selectMPokemonAbilityByIds(String pokemonId, String pokemonFormId) {

		// 1レコードずつ値をモデルクラスに設定
		MPokemonAbilityEntity mPokemonAbilityEntity = new MPokemonAbilityEntity();

		try {
			// localDBと接続
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:lib/localDB/localDB.db");

			// select文のセッティング
			PreparedStatement pstmt = con.prepareStatement(selectMPokemonAbilityByIds);

			// ワイルドカードに値を設定
			pstmt.setString(POKEMON_ID, pokemonId);
			pstmt.setString(POKEMON_FORM_ID, pokemonFormId);

			// クエリを実行
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// ポケモンＩＤ
				mPokemonAbilityEntity.setPokemonId(rs.getString("POKEMON_ID"));
				// ポケモンすがたID
				mPokemonAbilityEntity.setPokemonFormId(rs.getString("POKEMON_FORM_ID"));
				// 特性1
				mPokemonAbilityEntity.setAbility1(rs.getString("ABILITY_1"));
				// 特性2
				mPokemonAbilityEntity.setAbility2(rs.getString("ABILITY_2"));
				// 夢特性
				mPokemonAbilityEntity.setDreamAbility(rs.getString("DREAM_ABILITY"));
			}
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// リスト返却
		return mPokemonAbilityEntity;
	}

}
