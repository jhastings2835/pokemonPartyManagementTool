package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.MBasicTypeEntity;

public class MBasicTypeDao {

    private final String selectMBasicType = "SELECT * FROM M_BASIC_TYPE ORDER BY ID";

    private final String selectMBasicTypebyId = "SELECT * FROM M_BASIC_TYPE WHERE ID = ? ORDER BY ID";

    public List<MBasicTypeEntity> selectMBasicType() {

        List<MBasicTypeEntity> mBasicTypeList = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager
                    .getConnection("jdbc:sqlite:lib/localDB/localDB.db");
            Statement stmt = con.createStatement();
            // クエリを実行
            ResultSet rs = stmt.executeQuery(selectMBasicType);
            while (rs.next()) {
                // 1レコードずつ値をモデルクラスに設定
                MBasicTypeEntity mBasicType = new MBasicTypeEntity();
                // ＩＤ
                mBasicType.setId(rs.getString("ID"));
                // 名前
                mBasicType.setName(rs.getString("NAME"));
                // 作成したモデルをリストにつめる
                mBasicTypeList.add(mBasicType);
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // リスト返却
        return mBasicTypeList;
    }

    public String selectMBasicTypeById(String teraType) {
        String basicTypeName = null;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager
                    .getConnection("jdbc:sqlite:lib/localDB/localDB.db");

            // select文のセッティング
            PreparedStatement pstmt = con
                    .prepareStatement(selectMBasicTypebyId);

            // ワイルドカードに値を設定
            pstmt.setString(1, teraType);

            // クエリを実行
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // 取得した名前を設定
                basicTypeName = rs.getString("NAME");
            }
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 検索結果を返却
        return basicTypeName;
    }

}
