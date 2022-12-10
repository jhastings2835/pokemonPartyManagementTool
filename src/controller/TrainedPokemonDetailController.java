package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import common.Constants;
import common.DataBaseUtil;
import common.TextUtil;
import dao.MItemDao;
import dao.MPersonalityDao;
import dao.MPokemonAbilityDao;
import dao.MPokemonDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.MItemEntity;
import model.MPersonalityEntity;
import model.MPokemonAbilityEntity;
import model.MPokemonEntity;
import model.TTrainedPokemonEntity;
import service.TrainedPokemonDetailService;

public class TrainedPokemonDetailController implements Initializable {

    @FXML
    private ComboBox<String> pokemonNameList;

    @FXML
    private ComboBox<String> pokemonPersonalityList;

    @FXML
    private ComboBox<String> itemList;

    @FXML
    private ComboBox<String> pokemonAbilityList;

    @FXML
    private ComboBox<String> teraTypeList;

    @FXML
    private TextArea hpEffortValueForm;

    @FXML
    private TextArea attackEffortValueForm;

    @FXML
    private TextArea defenseEffortValueForm;

    @FXML
    private TextArea specialAttackEffortValueForm;

    @FXML
    private TextArea specialDefenseEffortValueForm;

    @FXML
    private TextArea speedEffortValueForm;

    @FXML
    private TextField move1Form;

    @FXML
    private TextField move2Form;

    @FXML
    private TextField move3Form;

    @FXML
    private TextField move4Form;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button saveBtn;

    private final TrainedPokemonDetailService trainedPokemonDetailService = new TrainedPokemonDetailService();

    private List<ComboBox<String>> comboBox = new ArrayList<>();

    private List<TextArea> textArea = new ArrayList<>();

    private List<TextField> textField = new ArrayList<>();

    private Stage trainedPokemonDetailStage = new Stage();

    private int trainedPokemonId = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 一覧データ初期化
        trainedPokemonDetailService.clearGlobalParams();

        // コンボボックス配列にコンボボックスを設定
        this.addComboBoxList();

        // テキストエリア配列にテキストエリアを設定
        this.addTextAreaList();

        // テキストエリア配列にテキストエリアを設定
        this.addTextFiledList();

        // テキストエリアの入力制限を追加
        trainedPokemonDetailService.setTextAreaRestrictions(textArea);

        // コンボボックスの初期表示値を設定
        trainedPokemonDetailService.setComboBoxValues(comboBox);
    }

    /**
     * 初期化に必要な引数をセット
     * 
     * @param secondStage
     * @param id
     */
    public void setInitValue(Stage secondStage, int id) {
        this.trainedPokemonDetailStage = secondStage;
        this.trainedPokemonId = id;

        if (this.trainedPokemonId != -1) {
            // 育成済みポケモンＩＤが存在する場合、値を設定する
            TTrainedPokemonEntity tTrainedPokemonEntity = trainedPokemonDetailService
                    .getInfosById(this.trainedPokemonId);

            // IDからポケモンの名前を取得
            String pokemonName = trainedPokemonDetailService
                    .getPokemonNameByIds(tTrainedPokemonEntity.getPokemonId(),
                            tTrainedPokemonEntity.getPokemonFormId());
            // 取得したポケモンの名前を初期値としてセット
            this.pokemonNameList.getSelectionModel().select(pokemonName);

            // ＩＤから性格を取得
            String personalityName = trainedPokemonDetailService
                    .getPersonalityNameById(
                            tTrainedPokemonEntity.getPersonalityId());
            // 取得した性格の名前を初期値としてセット
            this.pokemonPersonalityList.getSelectionModel()
                    .select(personalityName);

            // ＩＤからアイテムを取得
            String itemName = trainedPokemonDetailService
                    .getItemNameById(tTrainedPokemonEntity.getItemId());
            // 取得したアイテムの名前を初期値としてセット
            this.itemList.getSelectionModel().select(itemName);

            // ＩＤから特性を取得
            String abilityName = trainedPokemonDetailService
                    .getabilityNameById(tTrainedPokemonEntity.getAbilityId());
            // 取得した特性の名前を初期値としてセット
            this.pokemonAbilityList.getSelectionModel().select(abilityName);

            // ＩＤからテラスタイプを取得
            String teraType = trainedPokemonDetailService
                    .getTeraTypeById(tTrainedPokemonEntity.getTeraType());
            // 取得したテラスタイプの名前を初期値としてセット
            this.teraTypeList.getSelectionModel().select(teraType);

            // 取得したHP努力値をテキストエリアに設定
            this.hpEffortValueForm
                    .setText(tTrainedPokemonEntity.getHitPointsEffortValue());

            // 取得した攻撃努力値をテキストエリアに設定
            this.attackEffortValueForm
                    .setText(tTrainedPokemonEntity.getAttackEffortValue());

            // 取得した防御努力値をテキストエリアに設定
            this.defenseEffortValueForm
                    .setText(tTrainedPokemonEntity.getDefenseEffortValue());

            // 取得した特攻努力値をテキストエリアに設定
            this.specialAttackEffortValueForm.setText(
                    tTrainedPokemonEntity.getSpecialAttackEffortValue());

            // 取得した特防努力値をテキストエリアに設定
            this.specialDefenseEffortValueForm.setText(
                    tTrainedPokemonEntity.getSpecialDefenseEffortValue());

            // 取得したすばやさ努力値をテキストエリアに設定
            this.speedEffortValueForm
                    .setText(tTrainedPokemonEntity.getSpeedEffortValue());

            // 取得したわざ1をテキストエリアに設定
            this.move1Form.setText(tTrainedPokemonEntity.getMoveId1());

            // 取得したわざ2をテキストエリアに設定
            this.move2Form.setText(tTrainedPokemonEntity.getMoveId2());

            // 取得したわざ3をテキストエリアに設定
            this.move3Form.setText(tTrainedPokemonEntity.getMoveId3());

            // 取得したわざ4をテキストエリアに設定
            this.move4Form.setText(tTrainedPokemonEntity.getMoveId4());
        }
    }

    /**
     * @param event ポケモンの名前コンボボックスの値が変更された際の処理
     */
    @FXML
    private void changePokemonName(ActionEvent event) {
        // 特性コンボボックスの値を動的に生成
        trainedPokemonDetailService.setPokemonAbilityList(comboBox);
    }

    /**
     * @param event 「save」発火時の処理
     * @throws IOException
     */
    @FXML
    private void save(ActionEvent event) throws IOException {

        Boolean saveComplete = trainedPokemonDetailService.save(comboBox,
                textArea, textField);

        if (saveComplete) {
            // 保存処理が正常終了した場合、画面を閉じる
            saveBtn.getScene().getWindow().hide();
        }
    }

    /**
     * @param event 「delete」発火時の処理
     */
    @FXML
    private void delete(ActionEvent event) {

    }

    private void addComboBoxList() {
        this.comboBox.add(Constants.COMBO_BOX_POKEMON_NAME_LIST,
                pokemonNameList);
        this.comboBox.add(Constants.COMBO_BOX_POKEMON_PERSONALITY_LIST,
                pokemonPersonalityList);
        this.comboBox.add(Constants.COMBO_BOX_ITEM_LIST, itemList);
        this.comboBox.add(Constants.COMBO_BOX_POKEMON_ABILITY_LIST,
                pokemonAbilityList);
        this.comboBox.add(Constants.COMBO_BOX_TERA_TYPE_LIST, teraTypeList);
    }

    private void addTextAreaList() {
        this.textArea.add(Constants.TEXT_AREA_HP_EFFORT_VALUE,
                hpEffortValueForm);
        this.textArea.add(Constants.TEXT_AREA_ATTACK_EFFORT_VALUE,
                attackEffortValueForm);
        this.textArea.add(Constants.TEXT_AREA_DEFENSE_EFFORT_VALUE,
                defenseEffortValueForm);
        this.textArea.add(Constants.TEXT_AREA_SPECIAL_ATTACK_EFFORT_VALUE,
                specialAttackEffortValueForm);
        this.textArea.add(Constants.TEXT_AREA_SPECIAL_DEFENSE_EFFORT_VALUE,
                specialDefenseEffortValueForm);
        this.textArea.add(Constants.TEXT_AREA_SPEED_EFFORT_VALUE,
                speedEffortValueForm);
    }

    private void addTextFiledList() {
        this.textField.add(Constants.TEXT_FIELD_MOVE_1, move1Form);
        this.textField.add(Constants.TEXT_FIELD_MOVE_2, move2Form);
        this.textField.add(Constants.TEXT_FIELD_MOVE_3, move3Form);
        this.textField.add(Constants.TEXT_FIELD_MOVE_4, move4Form);
    }
}
