package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.MPersonalityEntity;

public class MPersonalityDao {

    private final String selectMPersonality = "SELECT * FROM M_PERSONALITY ORDER BY ID";

    public List<MPersonalityEntity> selectMPersonality() {

        List<MPersonalityEntity> mPersonalityEntityList = new ArrayList<MPersonalityEntity>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager
                    .getConnection("jdbc:sqlite:lib/localDB/localDB.db");
            Statement stmt = con.createStatement();
            // クエリを実行
            ResultSet rs = stmt.executeQuery(selectMPersonality);
            while (rs.next()) {
                // 1レコードずつ値をモデルクラスに設定
                MPersonalityEntity mPersonality = new MPersonalityEntity();
                // ＩＤ
                mPersonality.setId(rs.getString("ID"));
                // 名前
                mPersonality.setName(rs.getString("NAME"));
                // 攻撃補正
                mPersonality.setAttackCorrenction(
                        rs.getString("ATTACK_CORRECTION"));
                // 防御補正
                mPersonality.setDefenseCorrenction(
                        rs.getString("DEFENSE_CORRECTION"));
                // 特攻補正
                mPersonality.setSpecialAttackCorrection(
                        rs.getString("SPECIAL_ATTACK_CORRECTION"));
                // 特防補正
                mPersonality.setSpecialDefenseCorrection(
                        rs.getString("SPECIAL_DEFENSE_CORRECTION"));
                // 素早さ補正
                mPersonality
                        .setSpeedCorrenction(rs.getString("SPEED_CORRECTION"));
                // 作成したモデルをリストにつめる
                mPersonalityEntityList.add(mPersonality);
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // リスト返却
        return mPersonalityEntityList;
    }

}
