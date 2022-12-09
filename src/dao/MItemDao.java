package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.MItemEntity;
import model.MPokemonEntity;

public class MItemDao {

    private final String selectMItem = "SELECT * FROM M_ITEM ORDER BY ID";

    public List<MItemEntity> selectMItem() {

        List<MItemEntity> mItemList = new ArrayList<MItemEntity>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager
                    .getConnection("jdbc:sqlite:lib/localDB/localDB.db");
            Statement stmt = con.createStatement();
            // クエリを実行
            ResultSet rs = stmt.executeQuery(selectMItem);
            while (rs.next()) {
                // 1レコードずつ値をモデルクラスに設定
                MItemEntity mItem = new MItemEntity();
                // ＩＤ
                mItem.setId(rs.getString("ID"));
                // 名前
                mItem.setName(rs.getString("NAME"));
                // 説明
                mItem.setContent(rs.getString("CONTENT"));
                // 作成したモデルをリストにつめる
                mItemList.add(mItem);
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // リスト返却
        return mItemList;
    }

}
