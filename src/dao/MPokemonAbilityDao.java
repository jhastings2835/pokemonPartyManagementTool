package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.MPokemonAbilityEntity;
import model.MPersonalityEntity;

public class MPokemonAbilityDao {

	private final String selectMPokemonAbility = "SELECT * FROM M_POKEMON_ABILITY WHERE POKEMON_ID = ? AND POKEMON_FORM_ID = ?";

	List<MPokemonAbilityEntity> mPokemonAbilityEntityList = new ArrayList<MPokemonAbilityEntity>();

	/**
	 * @param pokemonId
	 * @param pokemonFormId
	 * @return
	 */
	public List<MPokemonAbilityEntity> selectMPokemonAbility(String pokemonId, String pokemonFormId) {
		try {
			// localDBと接続
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:lib/localDB/localDB.db");

			// select文のセッティング
			PreparedStatement pstmt = con.prepareStatement(selectMPokemonAbility);

			// ワイルドカードに値を設定
			pstmt.setString(1, pokemonId);
			pstmt.setString(2, pokemonId);

			// クエリを実行
			ResultSet rs = pstmt.executeQuery(selectMPokemonAbility);
			while (rs.next()) {
				// 1レコードずつ値をモデルクラスに設定
				MPokemonAbilityEntity mPokemonAbilityEntity = new MPokemonAbilityEntity();
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
				// 作成したモデルをリストにつめる
				mPokemonAbilityEntityList.add(mPokemonAbilityEntity);
			}
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// リスト返却
		return mPokemonAbilityEntityList;
	}

}
