package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.MPokemonEntity;

public class MPokemonDao {

	private final String selectMPokemon = "SELECT * FROM M_POKEMON";

	private final String selectMPokemon = "SELECT * FROM M_POKEMON WHERE";

	List<MPokemonEntity> mPokemonList = new ArrayList<MPokemonEntity>();

	public List<MPokemonEntity> selectMPokemon() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:lib/localDB/localDB.db");
			Statement stmt = con.createStatement();
			// クエリを実行
			ResultSet rs = stmt.executeQuery(selectMPokemon);
			while (rs.next()) {
				// 1レコードずつ値をモデルクラスに設定
				MPokemonEntity mPokemon = new MPokemonEntity();
				// ＩＤ
				mPokemon.setId(rs.getString("ID"));
				// すがたＩＤ
				mPokemon.setId(rs.getString("FORM_ID"));
				// 名前
				mPokemon.setName(rs.getString("NAME"));
				// すがたの要項
				mPokemon.setId(rs.getString("FORM_REMARKS"));
				// 作成したモデルをリストにつめる
				mPokemonList.add(mPokemon);
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// リスト返却
		return mPokemonList;
	}

	public List<MPokemonEntity> selectMPokemonByName() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:lib/localDB/localDB.db");
			Statement stmt = con.createStatement();
			// クエリを実行
			ResultSet rs = stmt.executeQuery(selectMPokemon);
			while (rs.next()) {
				// 1レコードずつ値をモデルクラスに設定
				MPokemonEntity mPokemon = new MPokemonEntity();
				// ＩＤ
				mPokemon.setId(rs.getString("ID"));
				// すがたＩＤ
				mPokemon.setId(rs.getString("FORM_ID"));
				// 名前
				mPokemon.setName(rs.getString("NAME"));
				// すがたの要項
				mPokemon.setId(rs.getString("FORM_REMARKS"));
				// 作成したモデルをリストにつめる
				mPokemonList.add(mPokemon);
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// リスト返却
		return mPokemonList;
	}
}
