package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.MAbilityEntity;

public class MAbilityDao {

	private final String selectMAbility = "SELECT * FROM M_ABILITY ORDER BY ID";

	List<MAbilityEntity> mAbilityList = new ArrayList<MAbilityEntity>();

	public List<MAbilityEntity> selectMAbility() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:lib/localDB/localDB.db");
			Statement stmt = con.createStatement();
			// クエリを実行
			ResultSet rs = stmt.executeQuery(selectMAbility);
			while (rs.next()) {
				// 1レコードずつ値をモデルクラスに設定
				MAbilityEntity mAbilityEntity = new MAbilityEntity();
				// ＩＤ
				mAbilityEntity.setId(rs.getString("ID"));
				// 名前
				mAbilityEntity.setName(rs.getString("NAME"));
				// 説明
				mAbilityEntity.setContent(rs.getString("CONTENT"));
				// 作成したモデルをリストにつめる
				mAbilityList.add(mAbilityEntity);
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// リスト返却
		return mAbilityList;
	}

}
