package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.Constants;
import model.MItemEntity;
import model.MPokemonAbilityEntity;

public class TTrainedPokemonDao {

    private final String insertTTrainedPokemon = "INSERT INTO T_TRAINED_POKEMON (POKEMON_ID,POKEMON_FORM_ID,PERSONALITY_ID,ABILITY_ID,ITEM_ID,HIT_POINTS_EFFORT_VALUE,ATTACK_EFFORT_VALUE,DEFENSE_EFFORT_VALUE,SPECIAL_ATTACK_EFFORT_VALUE,SPECIAL_DEFENSE_EFFORT_VALUE,SPEED_EFFORT_VALUE,TOTAL_EFFORT_VALUE,MOVE_ID_1,MOVE_ID_2,MOVE_ID_3,MOVE_ID_4) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    List<MItemEntity> mItemList = new ArrayList<MItemEntity>();

    public void insertTTrainedPokemon(List<String> insertParams) {
        try {
            // localDBと接続
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager
                    .getConnection("jdbc:sqlite:lib/localDB/localDB.db");

            // select文のセッティング
            PreparedStatement pstmt = con
                    .prepareStatement(insertTTrainedPokemon);

            // ワイルドカードに値を設定
            pstmt.setString(Constants.INSERT_PARAM_POKEMON_ID + 1,
                    insertParams.get(Constants.INSERT_PARAM_POKEMON_ID));
            pstmt.setString(Constants.INSERT_PARAM_POKEMON_FORM_ID + 1,
                    insertParams.get(Constants.INSERT_PARAM_POKEMON_FORM_ID));
            pstmt.setString(Constants.INSERT_PARAM_PERSONALITY_ID + 1,
                    insertParams.get(Constants.INSERT_PARAM_PERSONALITY_ID));
            pstmt.setString(Constants.INSERT_PARAM_ITEM_ID + 1,
                    insertParams.get(Constants.INSERT_PARAM_ITEM_ID));
            pstmt.setString(Constants.INSERT_PARAM_ABILITY_ID + 1,
                    insertParams.get(Constants.INSERT_PARAM_ABILITY_ID));
            pstmt.setString(Constants.INSERT_PARAM_HIT_POINTS_EFFORT_VALUE + 1,
                    insertParams.get(
                            Constants.INSERT_PARAM_HIT_POINTS_EFFORT_VALUE));
            pstmt.setString(Constants.INSERT_PARAM_ATTACK_EFFORT_VALUE + 1,
                    insertParams
                            .get(Constants.INSERT_PARAM_ATTACK_EFFORT_VALUE));
            pstmt.setString(Constants.INSERT_PARAM_DEFENSE_EFFORT_VALUE + 1,
                    insertParams
                            .get(Constants.INSERT_PARAM_DEFENSE_EFFORT_VALUE));
            pstmt.setString(
                    Constants.INSERT_PARAM_SPECIAL_ATTACK_EFFORT_VALUE + 1,
                    insertParams.get(
                            Constants.INSERT_PARAM_SPECIAL_ATTACK_EFFORT_VALUE));
            pstmt.setString(
                    Constants.INSERT_PARAM_SPECIAL_DEFENSE_EFFORT_VALUE + 1,
                    insertParams.get(
                            Constants.INSERT_PARAM_SPECIAL_DEFENSE_EFFORT_VALUE));
            pstmt.setString(Constants.INSERT_PARAM_SPEED_EFFORT_VALUE + 1,
                    insertParams
                            .get(Constants.INSERT_PARAM_SPEED_EFFORT_VALUE));
            pstmt.setString(Constants.INSERT_PARAM_TOTAL_EFFORT_VALUE + 1,
                    insertParams
                            .get(Constants.INSERT_PARAM_TOTAL_EFFORT_VALUE));
            pstmt.setString(Constants.INSERT_PARAM_MOVE_ID_1 + 1,
                    insertParams.get(Constants.INSERT_PARAM_MOVE_ID_1));
            pstmt.setString(Constants.INSERT_PARAM_MOVE_ID_2 + 1,
                    insertParams.get(Constants.INSERT_PARAM_MOVE_ID_2));
            pstmt.setString(Constants.INSERT_PARAM_MOVE_ID_2 + 1,
                    insertParams.get(Constants.INSERT_PARAM_MOVE_ID_2));
            pstmt.setString(Constants.INSERT_PARAM_MOVE_ID_4 + 1,
                    insertParams.get(Constants.INSERT_PARAM_MOVE_ID_4));

            // クエリを実行
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
