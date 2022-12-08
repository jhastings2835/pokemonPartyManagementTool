package common;

public class Constants {

    // コンボボックス配列の添字
    public static final int COMBO_BOX_POKEMON_NAME_LIST = 0;

    public static final int COMBO_BOX_POKEMON_PERSONALITY_LIST = 1;

    public static final int COMBO_BOX_ITEM_LIST = 2;

    public static final int COMBO_BOX_POKEMON_ABILITY_LIST = 3;

    // テキストエリア配列の添字
    public static final int TEXT_AREA_HP_EFFORT_VALUE = 0;

    public static final int TEXT_AREA_ATTACK_EFFORT_VALUE = 1;

    public static final int TEXT_AREA_DEFENSE_EFFORT_VALUE = 2;

    public static final int TEXT_AREA_SPECIAL_ATTACK_EFFORT_VALUE = 3;

    public static final int TEXT_AREA_SPECIAL_DEFENSE_EFFORT_VALUE = 4;

    public static final int TEXT_AREA_SPEED_EFFORT_VALUE = 5;

    // テキストフィールド配列の添字
    public static final int TEXT_FIELD_MOVE_1 = 0;

    public static final int TEXT_FIELD_MOVE_2 = 1;

    public static final int TEXT_FIELD_MOVE_3 = 2;

    public static final int TEXT_FIELD_MOVE_4 = 3;

    // テキストエリア上限桁数
    public static final int EFFORT_VALUE_MAX_LENGTH = 3;

    // DBカラム指定添字
    public static final int POKEMON_ID = 0;

    public static final int POKEMON_FORM_ID = 1;

    // IDリストに使用する添字、配列の先頭
    public static final int PERSONALITY_ID = 0;

    public static final int ABILITY_ID = 0;

    public static final int ITEM_ID = 0;

    // ポケモン詳細テーブルとのIOに使用する添字
    public static final int INSERT_PARAM_POKEMON_ID = 0;

    public static final int INSERT_PARAM_POKEMON_FORM_ID = 1;

    public static final int INSERT_PARAM_PERSONALITY_ID = 2;

    public static final int INSERT_PARAM_ITEM_ID = 3;

    public static final int INSERT_PARAM_ABILITY_ID = 4;

    public static final int INSERT_PARAM_HIT_POINTS_EFFORT_VALUE = 5;

    public static final int INSERT_PARAM_ATTACK_EFFORT_VALUE = 6;

    public static final int INSERT_PARAM_DEFENSE_EFFORT_VALUE = 7;

    public static final int INSERT_PARAM_SPECIAL_ATTACK_EFFORT_VALUE = 8;

    public static final int INSERT_PARAM_SPECIAL_DEFENSE_EFFORT_VALUE = 9;

    public static final int INSERT_PARAM_SPEED_EFFORT_VALUE = 10;

    public static final int INSERT_PARAM_TOTAL_EFFORT_VALUE = 11;

    public static final int INSERT_PARAM_MOVE_ID_1 = 12;

    public static final int INSERT_PARAM_MOVE_ID_2 = 13;

    public static final int INSERT_PARAM_MOVE_ID_3 = 14;

    public static final int INSERT_PARAM_MOVE_ID_4 = 15;

    // null値に変えて登録を行うための定数
    public static final String EFFORT_ZERO = "0";

    public static final String EMPTY = "";
}
