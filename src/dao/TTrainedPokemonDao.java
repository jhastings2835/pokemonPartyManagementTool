package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.Constants;
import dto.PokemonDetailDto;
import model.TTrainedPokemonEntity;

public class TTrainedPokemonDao {

    private final String insertTTrainedPokemon = "INSERT INTO T_TRAINED_POKEMON (POKEMON_ID,POKEMON_FORM_ID,PERSONALITY_ID,ABILITY_ID,ITEM_ID,HIT_POINTS_EFFORT_VALUE,ATTACK_EFFORT_VALUE,DEFENSE_EFFORT_VALUE,SPECIAL_ATTACK_EFFORT_VALUE,SPECIAL_DEFENSE_EFFORT_VALUE,SPEED_EFFORT_VALUE,TOTAL_EFFORT_VALUE,MOVE_ID_1,MOVE_ID_2,MOVE_ID_3,MOVE_ID_4,TERA_TYPE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private final String selectTrainedPokemon = "SELECT * FROM T_TRAINED_POKEMON";

    private final String selectTTrainedPokemonById = "SELECT * FROM T_TRAINED_POKEMON WHERE ID = ?";

    private final String selectDisplayPokemonDetail = "select trained.id id, mpokemon.NAME name, personality.NAME personality, ability.NAME ability, item.NAME item, type.NAME teraType, trained.HIT_POINTS_EFFORT_VALUE hitPointsEffortValue, trained.ATTACK_EFFORT_VALUE attackEffortValue, trained.DEFENSE_EFFORT_VALUE defenseEffortValue, trained.SPECIAL_ATTACK_EFFORT_VALUE specialAttackEffortValue, trained.SPECIAL_DEFENSE_EFFORT_VALUE specialDefenseEffortValue, trained.SPEED_EFFORT_VALUE speedEffortValue, trained.MOVE_ID_1 move1Name, trained.MOVE_ID_2 move2Name, trained.MOVE_ID_3 move3Name, trained.MOVE_ID_4 move4Name from T_TRAINED_POKEMON trained INNER JOIN M_POKEMON mpokemon on trained.POKEMON_ID = mpokemon.ID and trained.POKEMON_FORM_ID = mpokemon.FORM_ID INNER JOIN M_PERSONALITY personality ON trained.PERSONALITY_ID = personality.ID INNER JOIN M_ABILITY ability ON trained.ABILITY_ID = ability.ID INNER JOIN M_ITEM item ON trained.ITEM_ID = item.ID INNER JOIN M_BASIC_TYPE type ON trained.TERA_TYPE = type.ID";

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
            pstmt.setString(Constants.INSERT_PARAM_MOVE_ID_3 + 1,
                    insertParams.get(Constants.INSERT_PARAM_MOVE_ID_3));
            pstmt.setString(Constants.INSERT_PARAM_MOVE_ID_4 + 1,
                    insertParams.get(Constants.INSERT_PARAM_MOVE_ID_4));
            pstmt.setString(Constants.INSERT_PARAM_TERA_TYPE + 1,
                    insertParams.get(Constants.INSERT_PARAM_TERA_TYPE));

            // クエリを実行
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TTrainedPokemonEntity> selectTTrainedPokemon() {

        List<TTrainedPokemonEntity> tTrainedPokemonEntityList = new ArrayList<TTrainedPokemonEntity>();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager
                    .getConnection("jdbc:sqlite:lib/localDB/localDB.db");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectTrainedPokemon);
            while (rs.next()) {
                TTrainedPokemonEntity tTrainedPokemonEntity = new TTrainedPokemonEntity();
                tTrainedPokemonEntity.setId(rs.getInt("ID"));
                tTrainedPokemonEntity.setPokemonId(rs.getString("POKEMON_ID"));
                tTrainedPokemonEntity
                        .setPokemonFormId(rs.getString("POKEMON_FORM_ID"));
                tTrainedPokemonEntity
                        .setPersonalityId(rs.getString("PERSONALITY_ID"));
                tTrainedPokemonEntity.setAbilityId(rs.getString("ABILITY_ID"));
                tTrainedPokemonEntity.setItemId(rs.getString("ITEM_ID"));
                tTrainedPokemonEntity.setHitPointsEffortValue(
                        rs.getString("HIT_POINTS_EFFORT_VALUE"));
                tTrainedPokemonEntity.setAttackEffortValue(
                        rs.getString("ATTACK_EFFORT_VALUE"));
                tTrainedPokemonEntity.setDefenseEffortValue(
                        rs.getString("DEFENSE_EFFORT_VALUE"));
                tTrainedPokemonEntity.setSpecialAttackEffortValue(
                        rs.getString("SPECIAL_ATTACK_EFFORT_VALUE"));
                tTrainedPokemonEntity.setSpecialDefenseEffortValue(
                        rs.getString("SPECIAL_DEFENSE_EFFORT_VALUE"));
                tTrainedPokemonEntity.setSpeedEffortValue(
                        rs.getString("SPEED_EFFORT_VALUE"));
                tTrainedPokemonEntity.setTotalEffortValue(
                        rs.getString("TOTAL_EFFORT_VALUE"));
                tTrainedPokemonEntity.setMoveId1(rs.getString("MOVE_ID_1"));
                tTrainedPokemonEntity.setMoveId2(rs.getString("MOVE_ID_2"));
                tTrainedPokemonEntity.setMoveId3(rs.getString("MOVE_ID_3"));
                tTrainedPokemonEntity.setMoveId4(rs.getString("MOVE_ID_4"));
                tTrainedPokemonEntity.setTeraType(rs.getString("TERA_TYPE"));
                tTrainedPokemonEntityList.add(tTrainedPokemonEntity);
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tTrainedPokemonEntityList;
    }

    public List<PokemonDetailDto> selectDisplayPokemonDetail() {

        List<PokemonDetailDto> pokemonDetailDtoList = new ArrayList<PokemonDetailDto>();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager
                    .getConnection("jdbc:sqlite:lib/localDB/localDB.db");
            Statement stmt = con.createStatement();
            // クエリを実行
            ResultSet rs = stmt.executeQuery(selectDisplayPokemonDetail);
            while (rs.next()) {
                PokemonDetailDto pokemonDetailDto = new PokemonDetailDto();
                pokemonDetailDto.setId(rs.getInt("id"));
                pokemonDetailDto.setName(rs.getString("name"));
                pokemonDetailDto
                        .setPersonalityName(rs.getString("personality"));
                pokemonDetailDto.setAbilityName(rs.getString("ability"));
                pokemonDetailDto.setItemName(rs.getString("item"));
                pokemonDetailDto.setHitPointsEffortValue(
                        rs.getString("hitPointsEffortValue"));
                pokemonDetailDto.setAttackEffortValue(
                        rs.getString("attackEffortValue"));
                pokemonDetailDto.setDefenseEffortValue(
                        rs.getString("defenseEffortValue"));
                pokemonDetailDto.setSpecialAttackEffortValue(
                        rs.getString("specialAttackEffortValue"));
                pokemonDetailDto.setSpecialDefenseEffortValue(
                        rs.getString("specialDefenseEffortValue"));
                pokemonDetailDto
                        .setSpeedEffortValue(rs.getString("speedEffortValue"));
                pokemonDetailDto.setMove1Name(rs.getString("move1Name"));
                pokemonDetailDto.setMove2Name(rs.getString("move2Name"));
                pokemonDetailDto.setMove3Name(rs.getString("move3Name"));
                pokemonDetailDto.setMove4Name(rs.getString("move4Name"));
                pokemonDetailDto.setTeraType(rs.getString("teraType"));
                pokemonDetailDtoList.add(pokemonDetailDto);
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pokemonDetailDtoList;
    }

    public TTrainedPokemonEntity selectTTrainedPokemonById(
            int trainedPokemonId) {
        TTrainedPokemonEntity tTrainedPokemonEntity = new TTrainedPokemonEntity();
        try {
            // localDBと接続
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager
                    .getConnection("jdbc:sqlite:lib/localDB/localDB.db");

            // select文のセッティング
            PreparedStatement pstmt = con
                    .prepareStatement(selectTTrainedPokemonById);

            // ワイルドカードに値を設定
            pstmt.setInt(1, trainedPokemonId);

            // クエリを実行
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                tTrainedPokemonEntity.setId(rs.getInt("ID"));
                tTrainedPokemonEntity.setPokemonId(rs.getString("POKEMON_ID"));
                tTrainedPokemonEntity
                        .setPokemonFormId(rs.getString("POKEMON_FORM_ID"));
                tTrainedPokemonEntity
                        .setPersonalityId(rs.getString("PERSONALITY_ID"));
                tTrainedPokemonEntity.setAbilityId(rs.getString("ABILITY_ID"));
                tTrainedPokemonEntity.setItemId(rs.getString("ITEM_ID"));
                tTrainedPokemonEntity.setHitPointsEffortValue(
                        rs.getString("HIT_POINTS_EFFORT_VALUE"));
                tTrainedPokemonEntity.setAttackEffortValue(
                        rs.getString("ATTACK_EFFORT_VALUE"));
                tTrainedPokemonEntity.setDefenseEffortValue(
                        rs.getString("DEFENSE_EFFORT_VALUE"));
                tTrainedPokemonEntity.setSpecialAttackEffortValue(
                        rs.getString("SPECIAL_ATTACK_EFFORT_VALUE"));
                tTrainedPokemonEntity.setSpecialDefenseEffortValue(
                        rs.getString("SPECIAL_DEFENSE_EFFORT_VALUE"));
                tTrainedPokemonEntity.setSpeedEffortValue(
                        rs.getString("SPEED_EFFORT_VALUE"));
                tTrainedPokemonEntity.setTotalEffortValue(
                        rs.getString("TOTAL_EFFORT_VALUE"));
                tTrainedPokemonEntity.setMoveId1(rs.getString("MOVE_ID_1"));
                tTrainedPokemonEntity.setMoveId2(rs.getString("MOVE_ID_2"));
                tTrainedPokemonEntity.setMoveId3(rs.getString("MOVE_ID_3"));
                tTrainedPokemonEntity.setMoveId4(rs.getString("MOVE_ID_4"));
                tTrainedPokemonEntity.setTeraType(rs.getString("TERA_TYPE"));
            }
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tTrainedPokemonEntity;
    }

}
