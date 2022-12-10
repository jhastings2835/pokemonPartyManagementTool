package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.MPokemonEntity;
import model.TTrainedPokemonEntity;

public class MPokemonDao {

    private final String selectMPokemon = "SELECT * FROM M_POKEMON ORDER BY NAME ASC";

    private final String selectMPokemonByIds = "SELECT * FROM M_POKEMON WHERE ID = ? AND FORM_ID = ? ORDER BY NAME ASC";

    List<MPokemonEntity> mPokemonList = new ArrayList<MPokemonEntity>();

    public List<MPokemonEntity> selectMPokemon() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager
                    .getConnection("jdbc:sqlite:lib/localDB/localDB.db");
            Statement stmt = con.createStatement();
            // クエリを実行
            ResultSet rs = stmt.executeQuery(selectMPokemon);
            while (rs.next()) {
                // 1レコードずつ値をモデルクラスに設定
                MPokemonEntity mPokemon = new MPokemonEntity();
                // ＩＤ
                mPokemon.setId(rs.getString("ID"));
                // すがたＩＤ
                mPokemon.setFormId(rs.getString("FORM_ID"));
                // 名前
                mPokemon.setName(rs.getString("NAME"));
                // すがたの要項
                mPokemon.setFormRemarks(rs.getString("FORM_REMARKS"));
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
            Connection con = DriverManager
                    .getConnection("jdbc:sqlite:lib/localDB/localDB.db");
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

    public String selectMPokemonByIds(String pokemonId, String pokemonFormId) {
        String pokemonName = null;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager
                    .getConnection("jdbc:sqlite:lib/localDB/localDB.db");

            // select文のセッティング
            PreparedStatement pstmt = con.prepareStatement(selectMPokemonByIds);

            // ワイルドカードに値を設定
            pstmt.setString(1, pokemonId);
            pstmt.setString(2, pokemonFormId);

            // クエリを実行
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // 取得した名前を設定
                pokemonName = rs.getString("NAME");
            }
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 検索結果を返却
        return pokemonName;
    }
}
