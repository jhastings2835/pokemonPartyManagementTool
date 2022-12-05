package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

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
import model.MItemEntity;
import model.MPersonalityEntity;
import model.MPokemonAbilityEntity;
import model.MPokemonEntity;

public class TrainedPokemonDetailController implements Initializable {

	// ポケモン基本情報リスト
	private List<MPokemonEntity> globalMPokemonEntityList;

	// ポケモン性格リスト
	private List<MPersonalityEntity> globalMPersonalityEntityList;

	// ポケモン特性リスト
	private List<MPokemonAbilityEntity> globalMPokemonAbilityEntityList;

	// アイテムリスト
	private List<MItemEntity> globalMItemEntityList;

	private final int POKEMON_ID = 0;

	private final int POKEMON_FORM_ID = 1;

	@FXML
	private ComboBox<String> pokemonNameList;

	@FXML
	private Button deleteBtn;

	@FXML
	private Button saveBtn;

	@FXML
	private ComboBox<String> pokemonAbilityList;

	@FXML
	private ComboBox<String> pokemonPersonalityList;

	@FXML
	private ComboBox<String> itemList;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 一覧データ初期化
		clearGlobalParams();
		// ドロップダウンリストに値を設定する
		setTableDropDownListValues();
	}

	private void clearGlobalParams() {
		// ポケモン基本情報リストを初期化
		globalMPokemonEntityList = new ArrayList<>();
		// ポケモン性格リストを初期化
		globalMPersonalityEntityList = new ArrayList<>();
		// ポケモン特性リストを初期化
		globalMPokemonAbilityEntityList = new ArrayList<>();
		// アイテムリストを初期化
		globalMItemEntityList = new ArrayList<>();
	}

	/**
	 * @param event ポケモンの名前を選択した際、特性のドロップダウンリストを動的に設定する
	 */
	@FXML
	void changePokemonName(ActionEvent event) {
		// リストの内容を初期化
		pokemonAbilityList.getItems().clear();

		// 選択された名前からIDを取得
		List<String> ids = getPokemonIds(pokemonNameList.getValue());

		// ポケモンの名前に紐づく性格をドロップダウンリストに設定する
		setPokemonAbilityList(ids.get(POKEMON_ID), ids.get(POKEMON_FORM_ID));
	}

	private List<String> getPokemonIds(String value) {
		// 返却用リスト生成
		List<String> ids = new ArrayList<>();
		for (MPokemonEntity mPokemonEntity : globalMPokemonEntityList) {
			if (value.equals(mPokemonEntity.getName())) {
				// リストにポケモンIDをセット
				ids.add(POKEMON_ID, mPokemonEntity.getId());
				// リストにポケモンすがたIDをセット
				ids.add(POKEMON_FORM_ID, mPokemonEntity.getFormId());
				// ループを終了
				break;
			}
		}

		// IDのリストを返却
		return ids;
	}

	private void setTableDropDownListValues() {
		// ポケモンの名前一覧をドロップダウンリストに設定する
		setPokemonNameList();

		// ポケモンの性格一覧をドロップダウンリストに設定する
		setPokemonPersonalityList();

		// どうぐ一覧をドロップダウンリストに設定する
		setItemList();
	}

	private void setPokemonNameList() {

		// 初期化
		globalMPokemonEntityList = new ArrayList<>();

		// ポケモン基本情報一覧をマスタテーブルから取得する
		MPokemonDao dao = new MPokemonDao();
		List<MPokemonEntity> mPokemonEntityList = dao.selectMPokemon();

		// ポケモンの名前一覧をドロップダウンリストに設定する
		for (MPokemonEntity mPokemonEntity : mPokemonEntityList) {
			// 表示名初期化
			String pokemonName = null;

			if (mPokemonEntity.getFormRemarks() != null && !mPokemonEntity.getFormRemarks().isEmpty()) {
				// 特殊なすがたがある場合のみ連結
				pokemonName = mPokemonEntity.getName() + mPokemonEntity.getFormRemarks();
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

	private void setPokemonPersonalityList() {

		// 初期化
		globalMPersonalityEntityList = new ArrayList<>();

		// ポケモン性格一覧をマスタテーブルから取得する
		MPersonalityDao dao = new MPersonalityDao();
		List<MPersonalityEntity> mPersonalityEntityList = dao.selectMPersonality();

		// ポケモンの性格一覧をドロップダウンリストに設定する
		for (MPersonalityEntity mPersonalityEntity : mPersonalityEntityList) {
			pokemonPersonalityList.getItems().add(mPersonalityEntity.getName());
		}

		// 画面のデータリストに格納
		globalMPersonalityEntityList = mPersonalityEntityList;
	}

	private void setItemList() {

		// 初期化
		globalMItemEntityList = new ArrayList<>();

		// ポケモン基本情報一覧をマスタテーブルから取得する
		MItemDao dao = new MItemDao();
		List<MItemEntity> mItemEntityList = dao.selectMItem();

		// ポケモンの名前一覧をドロップダウンリストに設定する
		for (MItemEntity mItemEntity : mItemEntityList) {
			itemList.getItems().add(mItemEntity.getName());
		}

		// 画面のデータリストに格納
		globalMItemEntityList = mItemEntityList;
	}

	private void setPokemonAbilityList(String id, String formId) {
//		// ドロップダウンリストの初期化
//		pokemonAbilityList = new ComboBox<String>();

		// ポケモン特性情報マスタテーブルから取得する
		MPokemonAbilityDao dao = new MPokemonAbilityDao();
		MPokemonAbilityEntity mPokemonAbilityEntity = dao.selectMPokemonAbility(id, formId);

		List<String> dispPokemonAbilityList = new ArrayList<>();
		if (mPokemonAbilityEntity.getAbility1() != null && !"NULL".equals(mPokemonAbilityEntity.getAbility1())) {
			dispPokemonAbilityList.add(mPokemonAbilityEntity.getAbility1());
		}
		if (mPokemonAbilityEntity.getAbility2() != null && !"NULL".equals(mPokemonAbilityEntity.getAbility2())) {
			dispPokemonAbilityList.add(mPokemonAbilityEntity.getAbility2());
		}
		if (mPokemonAbilityEntity.getDreamAbility() != null
				&& !"NULL".equals(mPokemonAbilityEntity.getDreamAbility())) {
			dispPokemonAbilityList.add("(夢)" + mPokemonAbilityEntity.getDreamAbility());
		}
		// 画面のデータリストに格納

		// ポケモンの名前に紐づく特性をドロップダウンリストに設定する
		for (String abilityName : dispPokemonAbilityList) {
			pokemonAbilityList.getItems().add(abilityName);
		}
	}
}
