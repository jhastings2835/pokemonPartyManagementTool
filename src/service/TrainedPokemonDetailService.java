package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import common.Constants;
import common.DataBaseUtil;
import common.TextUtil;
import dao.MAbilityDao;
import dao.MItemDao;
import dao.MPersonalityDao;
import dao.MPokemonAbilityDao;
import dao.MPokemonDao;
import dao.MBasicTypeDao;
import dao.TTrainedPokemonDao;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import model.MAbilityEntity;
import model.MBasicTypeEntity;
import model.MItemEntity;
import model.MPersonalityEntity;
import model.MPokemonAbilityEntity;
import model.MPokemonEntity;
import model.TTrainedPokemonEntity;

public class TrainedPokemonDetailService {

    // ポケモン基本情報リスト
    private List<MPokemonEntity> globalMPokemonEntityList;

    // ポケモン性格リスト
    private List<MPersonalityEntity> globalMPersonalityEntityList;

    // 特性一覧リスト
    private List<MAbilityEntity> globalMAbilityEntityList;

    // アイテムリスト
    private List<MItemEntity> globalMItemEntityList;

    // テラスタイプリスト
    private List<MBasicTypeEntity> globalMBasicTypeEntityList;

    // ポケモン基本情報一覧dao
    MPokemonDao mPokemonDao = new MPokemonDao();

    // ポケモン性格一覧情報dao
    MPersonalityDao mPokemonPersonalityDao = new MPersonalityDao();

    // ポケモン特性一覧情報dao
    MPokemonAbilityDao mPokemonAbilityDao = new MPokemonAbilityDao();

    // 特性一覧情報dao
    MAbilityDao mAbilityDao = new MAbilityDao();

    // アイテム一覧情報dao
    MItemDao mItemDao = new MItemDao();

    // アイテム一覧情報dao
    MBasicTypeDao mBasicTypeDao = new MBasicTypeDao();

    // 育成済みポケモンdao
    TTrainedPokemonDao tTrainedPokemonDao = new TTrainedPokemonDao();

    /**
     * 画面で保持する変数を初期化
     */
    public void clearGlobalParams() {
        // ポケモン基本情報リストを初期化
        globalMPokemonEntityList = new ArrayList<>();
        // ポケモン性格リストを初期化
        globalMPersonalityEntityList = new ArrayList<>();
        // アイテムリストを初期化
        globalMItemEntityList = new ArrayList<>();
    }

    /**
     * 値をそれぞれのコンボボックスに設定する
     * 
     * @param comboBox
     */
    public void setComboBoxValues(List<ComboBox<String>> comboBox) {
        // ポケモンの名前一覧をドロップダウンリストに設定する
        setPokemonNameList(comboBox.get(Constants.COMBO_BOX_POKEMON_NAME_LIST));

        // ポケモンの性格一覧をドロップダウンリストに設定する
        setPokemonPersonalityList(
                comboBox.get(Constants.COMBO_BOX_POKEMON_PERSONALITY_LIST));

        // どうぐ一覧をドロップダウンリストに設定する
        setItemList(comboBox.get(Constants.COMBO_BOX_ITEM_LIST));

        // テラタイプ一覧をドロップダウンリストに設定する
        setTeraTypeList(comboBox.get(Constants.COMBO_BOX_TERA_TYPE_LIST));
    }

    /**
     * 育成済みポケモンＩＤが設定されていた場合の値を設定する。
     * 
     * @param trainedPokemonId
     */
    public TTrainedPokemonEntity getInfosById(int trainedPokemonId) {
        // TODO 自動生成されたメソッド・スタブ
        TTrainedPokemonEntity tTrainedPokemonEntity = tTrainedPokemonDao
                .selectTTrainedPokemonById(trainedPokemonId);
        return tTrainedPokemonEntity;
    }

    /**
     * 画面項目値保存
     * 
     * @param ownStage
     * 
     * @param comboBox
     * @param textArea
     * @param textField
     * @throws IOException
     */
    public Boolean save(List<ComboBox<String>> comboBox,
            List<TextArea> textArea, List<TextField> textField)
            throws IOException {
        // 登録前のチェック
        if (validation(comboBox)) {
            // バリデーションに引っかかった場合、アラートを送出する
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setHeaderText("リストの中身を設定してください");
            Optional<ButtonType> result = alert.showAndWait();

            return false;
        }
        // 名前から変換したIDを保存するデータ型を準備
        List<List<String>> idsByName = new ArrayList<>();

        // それぞれのコンボボックスで設定した値からIDを取得
        getIdByName(comboBox, idsByName);

        // ローカルDBに取得した値を設定
        regist(idsByName, textArea, textField);

        return true;
    }

    private void regist(List<List<String>> idsByName, List<TextArea> textArea,
            List<TextField> textField) {
        List<String> insertParams = new ArrayList<>();
        // POKEMON_ID
        insertParams.add(Constants.INSERT_PARAM_POKEMON_ID,
                idsByName.get(Constants.COMBO_BOX_POKEMON_NAME_LIST)
                        .get(Constants.POKEMON_ID));
        // POKEMON_FORM_ID
        insertParams.add(Constants.INSERT_PARAM_POKEMON_FORM_ID,
                idsByName.get(Constants.COMBO_BOX_POKEMON_NAME_LIST)
                        .get(Constants.POKEMON_FORM_ID));
        // PERSONALITY_ID
        insertParams.add(Constants.INSERT_PARAM_PERSONALITY_ID,
                idsByName.get(Constants.COMBO_BOX_POKEMON_PERSONALITY_LIST)
                        .get(Constants.PERSONALITY_ID));

        // ABILITY_ID
        insertParams.add(Constants.INSERT_PARAM_ABILITY_ID,
                idsByName.get(Constants.COMBO_BOX_POKEMON_ABILITY_LIST)
                        .get(Constants.ABILITY_ID));
        // ITEM_ID
        insertParams.add(Constants.INSERT_PARAM_ITEM_ID, idsByName
                .get(Constants.COMBO_BOX_ITEM_LIST).get(Constants.ITEM_ID));

        // HIT_POINTS_EFFORT_VALUE
        String hitPointsEffortValue = textArea
                .get(Constants.TEXT_AREA_HP_EFFORT_VALUE).getText();
        // 登録値がnullの場合、「0」に変換
        if (hitPointsEffortValue == null || hitPointsEffortValue.isEmpty()) {
            hitPointsEffortValue = Constants.EFFORT_ZERO;
        }
        insertParams.add(Constants.INSERT_PARAM_HIT_POINTS_EFFORT_VALUE,
                hitPointsEffortValue);

        // ATTACK_EFFORT_VALUE
        String attackEffortValue = textArea
                .get(Constants.TEXT_AREA_ATTACK_EFFORT_VALUE).getText();
        // 登録値がnullの場合、「0」に変換
        if (attackEffortValue == null || attackEffortValue.isEmpty()) {
            attackEffortValue = Constants.EFFORT_ZERO;
        }
        insertParams.add(Constants.INSERT_PARAM_ATTACK_EFFORT_VALUE,
                attackEffortValue);

        // DEFENSE_EFFORT_VALUE
        String defenseEffortValue = textArea
                .get(Constants.TEXT_AREA_DEFENSE_EFFORT_VALUE).getText();
        // 登録値がnullの場合、「0」に変換
        if (defenseEffortValue == null || defenseEffortValue.isEmpty()) {
            defenseEffortValue = Constants.EFFORT_ZERO;
        }
        insertParams.add(Constants.INSERT_PARAM_DEFENSE_EFFORT_VALUE,
                defenseEffortValue);

        // SPECIAL_ATTACK_EFFORT_VALUE
        String specialAttackEffortValue = textArea
                .get(Constants.TEXT_AREA_SPECIAL_ATTACK_EFFORT_VALUE).getText();
        // 登録値がnullの場合、「0」に変換
        if (specialAttackEffortValue == null
                || specialAttackEffortValue.isEmpty()) {
            specialAttackEffortValue = Constants.EFFORT_ZERO;
        }
        insertParams.add(Constants.INSERT_PARAM_SPECIAL_ATTACK_EFFORT_VALUE,
                specialAttackEffortValue);

        // SPECIAL_DEFENSE_EFFORT_VALUE
        String specialDefenseEffortValue = textArea
                .get(Constants.TEXT_AREA_SPECIAL_DEFENSE_EFFORT_VALUE)
                .getText();
        // 登録値がnullの場合、「0」に変換
        if (specialDefenseEffortValue == null
                || specialDefenseEffortValue.isEmpty()) {
            specialDefenseEffortValue = Constants.EFFORT_ZERO;
        }
        insertParams.add(Constants.INSERT_PARAM_SPECIAL_DEFENSE_EFFORT_VALUE,
                specialDefenseEffortValue);

        // SPEED_EFFORT_VALUE
        String speedEffortValue = textArea
                .get(Constants.TEXT_AREA_SPEED_EFFORT_VALUE).getText();
        // 登録値がnullの場合、「0」に変換
        if (speedEffortValue == null || speedEffortValue.isEmpty()) {
            speedEffortValue = Constants.EFFORT_ZERO;
        }
        insertParams.add(Constants.INSERT_PARAM_SPEED_EFFORT_VALUE,
                speedEffortValue);

        // TOTAL_EFFORT_VALUE
        // 努力値合計を計算
        Integer intTotalEffortValue = Integer.parseInt(hitPointsEffortValue)
                + Integer.parseInt(attackEffortValue)
                + Integer.parseInt(defenseEffortValue)
                + Integer.parseInt(specialAttackEffortValue)
                + Integer.parseInt(specialDefenseEffortValue)
                + Integer.parseInt(speedEffortValue);
        // Stringに変換
        String StringTotalEffortValue = intTotalEffortValue.toString();
        insertParams.add(Constants.INSERT_PARAM_TOTAL_EFFORT_VALUE,
                StringTotalEffortValue);

        // MOVE_ID_1
        String moveId1 = textField.get(Constants.TEXT_FIELD_MOVE_1).getText();
        // 登録値がnullの場合、空文字に変換
        if (moveId1 == null || moveId1.isEmpty()) {
            moveId1 = Constants.EMPTY;
        }
        insertParams.add(Constants.INSERT_PARAM_MOVE_ID_1, moveId1);

        // MOVE_ID_2
        String moveId2 = textField.get(Constants.TEXT_FIELD_MOVE_2).getText();
        // 登録値がnullの場合、空文字に変換
        if (moveId2 == null || moveId2.isEmpty()) {
            moveId2 = Constants.EMPTY;
        }
        insertParams.add(Constants.INSERT_PARAM_MOVE_ID_2, moveId2);

        // MOVE_ID_3
        String moveId3 = textField.get(Constants.TEXT_FIELD_MOVE_3).getText();
        // 登録値がnullの場合、空文字に変換
        if (moveId3 == null || moveId3.isEmpty()) {
            moveId3 = Constants.EMPTY;
        }
        insertParams.add(Constants.INSERT_PARAM_MOVE_ID_3, moveId3);

        // MOVE_ID_4
        String moveId4 = textField.get(Constants.TEXT_FIELD_MOVE_4).getText();
        // 登録値がnullの場合、空文字に変換
        if (moveId4 == null || moveId4.isEmpty()) {
            moveId4 = Constants.EMPTY;
        }
        insertParams.add(Constants.INSERT_PARAM_MOVE_ID_4, moveId4);

        // TERA_TYPE
        insertParams.add(Constants.INSERT_PARAM_TERA_TYPE,
                idsByName.get(Constants.COMBO_BOX_TERA_TYPE_LIST)
                        .get(Constants.ITEM_ID));

        tTrainedPokemonDao.insertTTrainedPokemon(insertParams);
    }

    /**
     * 保存時の登録値チェック
     * 
     * 
     * @param comboBox
     * @throws IOException
     */
    private boolean validation(List<ComboBox<String>> comboBox)
            throws IOException {

        // コンボボックスの中身が一つでも空なら処理終了
        if (comboBox.get(Constants.COMBO_BOX_POKEMON_NAME_LIST)
                .getValue() == null
                || comboBox.get(Constants.COMBO_BOX_POKEMON_NAME_LIST)
                        .getValue() == null
                || comboBox.get(Constants.COMBO_BOX_ITEM_LIST)
                        .getValue() == null
                || comboBox.get(Constants.COMBO_BOX_POKEMON_ABILITY_LIST)
                        .getValue() == null
                || comboBox.get(Constants.COMBO_BOX_TERA_TYPE_LIST)
                        .getValue() == null) {
            return true;
        }
        return false;
    }

    /**
     * 画面で設定されたコンボボックスの値からIDを取得する
     * 
     * @param comboBox
     * @param idsByName
     * @throws IOException
     */
    private void getIdByName(List<ComboBox<String>> comboBox,
            List<List<String>> idsByName) throws IOException {
        // 画面で設定したポケモン名からIDを取得
        getIdByPokemonName(
                comboBox.get(Constants.COMBO_BOX_POKEMON_NAME_LIST).getValue(),
                idsByName);

        // 画面で設定した性格名からIDを取得
        getIdByPersonalityName(comboBox
                .get(Constants.COMBO_BOX_POKEMON_PERSONALITY_LIST).getValue(),
                idsByName);

        // 画面で設定したアイテム名からIDを取得
        getIdByItemName(comboBox.get(Constants.COMBO_BOX_ITEM_LIST).getValue(),
                idsByName);

        // 画面で設定した特性名からIDを取得
        getIdByPokemonAbility(comboBox
                .get(Constants.COMBO_BOX_POKEMON_ABILITY_LIST).getValue(),
                idsByName);

        // 画面で設定したテラスタイプ名からIDを取得
        getIdByTeraType(
                comboBox.get(Constants.COMBO_BOX_TERA_TYPE_LIST).getValue(),
                idsByName);
    }

    /**
     * 画面で設定されたポケモン名からIDを取得する
     * 
     * @param pokemonName 画面で設定されたポケモン名
     * @param idsByName   取得したIDを保持する変数
     */
    private void getIdByPokemonName(String pokemonName,
            List<List<String>> idsByName) {

        // 名前から変換したIDを保存するデータ型を準備
        List<String> pokemonIds = new ArrayList<>();

        for (MPokemonEntity globalMPokemonEntity : globalMPokemonEntityList) {
            if (pokemonName.equals(globalMPokemonEntity.getName())) {
                // 名前が一致した場合、IDを取得してループを終える
                pokemonIds.add(Constants.POKEMON_ID,
                        globalMPokemonEntity.getId());
                pokemonIds.add(Constants.POKEMON_FORM_ID,
                        globalMPokemonEntity.getFormId());
                break;
            }
        }
        // IDリストに取得したIDをセット
        idsByName.add(Constants.COMBO_BOX_POKEMON_NAME_LIST, pokemonIds);
    }

    /**
     * 画面で設定された性格名からIDを取得する
     * 
     * @param personalityName 画面で設定された性格名
     * @param idsByName       取得したIDを保持する変数
     */
    private void getIdByPersonalityName(String personalityName,
            List<List<String>> idsByName) {
        // 名前から変換したIDを保存するデータ型を準備
        List<String> personalityId = new ArrayList<>();

        for (MPersonalityEntity globalMPersonalityEntity : globalMPersonalityEntityList) {
            if (personalityName.equals(globalMPersonalityEntity.getName())) {
                // 名前が一致した場合、IDを取得してループを終える
                personalityId.add(globalMPersonalityEntity.getId());
                break;
            }
        }
        // IDリストに取得したIDをセット
        idsByName.add(Constants.COMBO_BOX_POKEMON_PERSONALITY_LIST,
                personalityId);
    }

    /**
     * 画面で設定したアイテム名からIDを取得する
     * 
     * @param itemName  画面で設定したアイテム名
     * @param idsByName 取得したIDを保持する変数
     */
    private void getIdByItemName(String itemName,
            List<List<String>> idsByName) {

        // 名前から変換したIDを保存するデータ型を準備
        List<String> itemId = new ArrayList<>();

        for (MItemEntity globalMItemEntity : globalMItemEntityList) {
            if (itemName.equals(globalMItemEntity.getName())) {
                itemId.add(globalMItemEntity.getId());
                break;
            }
        }
        // IDリストに取得したIDをセット
        idsByName.add(Constants.COMBO_BOX_ITEM_LIST, itemId);
    }

    /**
     * 画面で設定した特性名からIDを取得する
     * 
     * @param abilityName 画面で設定した特性名
     * @param idsByName   取得したIDを保持する変数
     */
    private void getIdByPokemonAbility(String abilityName,
            List<List<String>> idsByName) {
        // 夢特性だった場合、余分な文字をトリミング
        if (abilityName.contains("(夢)")) {
            abilityName = abilityName.substring(3);
        }

        // 名前から変換したIDを保存するデータ型を準備
        List<String> abilityId = new ArrayList<>();

        for (MAbilityEntity globalMAbilityEntity : globalMAbilityEntityList) {
            if (abilityName.equals(globalMAbilityEntity.getName())) {
                // 名前が一致した場合、IDを取得してループを終える
                abilityId.add(globalMAbilityEntity.getId());
                break;
            }
        }
        // IDリストに取得したIDをセット
        idsByName.add(Constants.COMBO_BOX_POKEMON_ABILITY_LIST, abilityId);
    }

    /**
     * 画面で設定したテラスタイプ名からIDを取得する
     * 
     * @param abilityName 画面で設定したテラスタイプ名
     * @param idsByName   取得したIDを保持する変数
     */
    private void getIdByTeraType(String teraTypeName,
            List<List<String>> idsByName) {

        // 名前から変換したIDを保存するデータ型を準備
        List<String> basicTypeId = new ArrayList<>();

        for (MBasicTypeEntity globalMBasicTypeEntity : globalMBasicTypeEntityList) {
            if (teraTypeName.equals(globalMBasicTypeEntity.getName())) {
                // 名前が一致した場合、IDを取得してループを終える
                basicTypeId.add(globalMBasicTypeEntity.getId());
                break;
            }
        }
        // IDリストに取得したIDをセット
        idsByName.add(Constants.COMBO_BOX_TERA_TYPE_LIST, basicTypeId);
    }

    /**
     * ポケモンの名前を選択するコンボボックスの内容を設定する
     * 
     * @param pokemonNameList
     */
    private void setPokemonNameList(ComboBox<String> pokemonNameList) {

        // 初期化
        globalMPokemonEntityList = new ArrayList<>();

        // ポケモン基本情報一覧をマスタテーブルから取得する
        List<MPokemonEntity> mPokemonEntityList = mPokemonDao.selectMPokemon();

        // ポケモンの名前一覧をドロップダウンリストに設定する
        for (MPokemonEntity mPokemonEntity : mPokemonEntityList) {
            // 表示名初期化
            String pokemonName = null;

            if (mPokemonEntity.getFormRemarks() != null
                    && !mPokemonEntity.getFormRemarks().isEmpty()) {
                // 特殊なすがたがある場合のみ連結
                pokemonName = mPokemonEntity.getName()
                        + mPokemonEntity.getFormRemarks();
            } else {
                // そうでない場合、名前のみ
                pokemonName = mPokemonEntity.getName();
            }
            // Entityの中身を操作（よくない）
            mPokemonEntity.setName(pokemonName);
            // リストに値を設定
            pokemonNameList.getItems().add(pokemonName);
        }

        // 画面のデータリストに格納
        globalMPokemonEntityList = mPokemonEntityList;
    }

    /**
     * ポケモンの性格一覧を選択するコンボボックスの内容を設定する。
     * 
     * @param pokemonPersonalityList
     */
    private void setPokemonPersonalityList(
            ComboBox<String> pokemonPersonalityList) {

        // 初期化
        globalMPersonalityEntityList = new ArrayList<>();

        // ポケモン性格一覧をマスタテーブルから取得する
        List<MPersonalityEntity> mPersonalityEntityList = mPokemonPersonalityDao
                .selectMPersonality();

        // ポケモンの性格一覧をドロップダウンリストに設定する
        for (MPersonalityEntity mPersonalityEntity : mPersonalityEntityList) {
            pokemonPersonalityList.getItems().add(mPersonalityEntity.getName());
        }

        // 画面のデータリストに格納
        globalMPersonalityEntityList = mPersonalityEntityList;
    }

    /**
     * アイテム一覧を選択するコンボボックスの内容を設定する。
     * 
     * @param itemList
     */
    private void setItemList(ComboBox<String> itemList) {

        // 初期化
        globalMItemEntityList = new ArrayList<>();

        // アイテム一覧をマスタテーブルから取得する
        List<MItemEntity> mItemEntityList = mItemDao.selectMItem();

        // ポケモンの名前一覧をドロップダウンリストに設定する
        for (MItemEntity mItemEntity : mItemEntityList) {
            itemList.getItems().add(mItemEntity.getName());
        }

        // 画面のデータリストに格納
        globalMItemEntityList = mItemEntityList;
    }

    /**
     * テラスタイプ一覧を選択するコンボボックスの内容を設定する。
     * 
     * @param teraTypeList
     */
    private void setTeraTypeList(ComboBox<String> teraTypeList) {

        // 初期化
        globalMBasicTypeEntityList = new ArrayList<>();

        // ポケモン性格一覧をマスタテーブルから取得する
        List<MBasicTypeEntity> mBasicTypeEntityList = mBasicTypeDao
                .selectMBasicType();

        // ポケモンの性格一覧をドロップダウンリストに設定する
        for (MBasicTypeEntity mBasictTypeEntity : mBasicTypeEntityList) {
            teraTypeList.getItems().add(mBasictTypeEntity.getName());
        }

        // 画面のデータリストに格納
        globalMBasicTypeEntityList = mBasicTypeEntityList;
    }

    /**
     * テキストエリアの入力制限を設定する
     * 
     * @param textArea
     */
    public void setTextAreaRestrictions(List<TextArea> textArea) {
        TextUtil.setTextAreaRestrictions(
                textArea.get(Constants.TEXT_AREA_HP_EFFORT_VALUE));
        TextUtil.setTextAreaRestrictions(
                textArea.get(Constants.TEXT_AREA_ATTACK_EFFORT_VALUE));
        TextUtil.setTextAreaRestrictions(
                textArea.get(Constants.TEXT_AREA_DEFENSE_EFFORT_VALUE));
        TextUtil.setTextAreaRestrictions(
                textArea.get(Constants.TEXT_AREA_SPECIAL_ATTACK_EFFORT_VALUE));
        TextUtil.setTextAreaRestrictions(
                textArea.get(Constants.TEXT_AREA_SPECIAL_DEFENSE_EFFORT_VALUE));
        TextUtil.setTextAreaRestrictions(
                textArea.get(Constants.TEXT_AREA_SPEED_EFFORT_VALUE));
    }

    /**
     * ポケモンの特性一覧を選択するコンボボックスの内容を設定する。
     * 
     * @param comboBox
     */
    public void setPokemonAbilityList(List<ComboBox<String>> comboBox) {

        // リストの内容を初期化
        comboBox.get(Constants.COMBO_BOX_POKEMON_ABILITY_LIST).getItems()
                .clear();

        // 選択された名前からIDを取得
        List<String> ids = getPokemonIds(
                comboBox.get(Constants.COMBO_BOX_POKEMON_NAME_LIST).getValue());

        // ポケモンの名前に紐づく性格をドロップダウンリストに設定する
        setPokemonAbilityList(comboBox, ids.get(Constants.POKEMON_ID),
                ids.get(Constants.POKEMON_FORM_ID));
    }

    private List<String> getPokemonIds(String value) {
        // 返却用リスト生成
        List<String> ids = new ArrayList<>();
        for (MPokemonEntity mPokemonEntity : globalMPokemonEntityList) {
            if (value.equals(mPokemonEntity.getName())) {
                // リストにポケモンIDをセット
                ids.add(Constants.POKEMON_ID, mPokemonEntity.getId());
                // リストにポケモンすがたIDをセット
                ids.add(Constants.POKEMON_FORM_ID, mPokemonEntity.getFormId());
                // ループを終了
                break;
            }
        }
        // IDのリストを返却
        return ids;
    }

    private void setPokemonAbilityList(List<ComboBox<String>> comboBox,
            String id, String formId) {

        // ポケモン特性情報マスタテーブルから取得する
        MPokemonAbilityEntity mPokemonAbilityEntity = mPokemonAbilityDao
                .selectMPokemonAbilityByIds(id, formId);

        // 表示用の特性リストを準備
        List<String> dispPokemonAbilityList = new ArrayList<>();

        if (mPokemonAbilityEntity.getAbility1() != null
                && !"NULL".equals(mPokemonAbilityEntity.getAbility1())) {
            String ability1 = this
                    .getAbilityNameById(mPokemonAbilityEntity.getAbility1());
            dispPokemonAbilityList.add(ability1);
        }
        if (mPokemonAbilityEntity.getAbility2() != null
                && !"NULL".equals(mPokemonAbilityEntity.getAbility2())) {
            String ability2 = this
                    .getAbilityNameById(mPokemonAbilityEntity.getAbility2());
            dispPokemonAbilityList.add(ability2);
        }
        if (mPokemonAbilityEntity.getDreamAbility() != null
                && !"NULL".equals(mPokemonAbilityEntity.getDreamAbility())) {
            String dreamAbility = this.getAbilityNameById(
                    mPokemonAbilityEntity.getDreamAbility());
            dispPokemonAbilityList.add("(夢)" + dreamAbility);
        }

        // ポケモンの名前に紐づく特性をドロップダウンリストに設定する
        for (String abilityName : dispPokemonAbilityList) {
            comboBox.get(Constants.COMBO_BOX_POKEMON_ABILITY_LIST).getItems()
                    .add(abilityName);
        }

    }

    public String getAbilityNameById(String id) {

        globalMAbilityEntityList = new ArrayList<>();

        globalMAbilityEntityList = mAbilityDao.selectMAbility();

        String name = null;

        for (MAbilityEntity globalMAbilityEntity : globalMAbilityEntityList) {
            if (globalMAbilityEntity != null
                    && id.equals(globalMAbilityEntity.getId())) {
                name = globalMAbilityEntity.getName();
                break;
            }
        }

        return name;
    }

    public String getPokemonNameByIds(String pokemonId, String pokemonFormId) {
        return mPokemonDao.selectMPokemonByIds(pokemonId, pokemonFormId);
    }

    public String getPersonalityNameById(String personalityId) {
        return mPokemonPersonalityDao.selectMPersonalityById(personalityId);
    }

    public String getItemNameById(String itemId) {
        return mItemDao.selectMItemById(itemId);
    }

    public String getabilityNameById(String abilityId) {
        return mAbilityDao.selectMAbilityById(abilityId);
    }

    public String getTeraTypeById(String teraType) {
        return mBasicTypeDao.selectMBasicTypeById(teraType);
    }
}
