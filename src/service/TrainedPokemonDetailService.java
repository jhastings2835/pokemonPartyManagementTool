package service;

import java.util.ArrayList;
import java.util.List;

import common.Constants;
import common.DataBaseUtil;
import common.TextUtil;
import dao.MItemDao;
import dao.MPersonalityDao;
import dao.MPokemonAbilityDao;
import dao.MPokemonDao;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import model.MItemEntity;
import model.MPersonalityEntity;
import model.MPokemonAbilityEntity;
import model.MPokemonEntity;

public class TrainedPokemonDetailService {

	// ポケモン基本情報リスト
	private List<MPokemonEntity> globalMPokemonEntityList;

	// ポケモン性格リスト
	private List<MPersonalityEntity> globalMPersonalityEntityList;

	// ポケモン特性リスト
	private List<MPokemonAbilityEntity> globalMPokemonAbilityEntityList;

	// アイテムリスト
	private List<MItemEntity> globalMItemEntityList;

	public void clearGlobalParams() {
		// ポケモン基本情報リストを初期化
		globalMPokemonEntityList = new ArrayList<>();
		// ポケモン性格リストを初期化
		globalMPersonalityEntityList = new ArrayList<>();
		// ポケモン特性リストを初期化
		globalMPokemonAbilityEntityList = new ArrayList<>();
		// アイテムリストを初期化
		globalMItemEntityList = new ArrayList<>();
	}

	public void setTableDropDownListValues(List<ComboBox<String>> comboBox) {
		// ポケモンの名前一覧をドロップダウンリストに設定する
		setPokemonNameList(comboBox.get(Constants.COMBO_BOX_POKEMON_NAME_LIST));

		// ポケモンの性格一覧をドロップダウンリストに設定する
		setPokemonPersonalityList(comboBox.get(Constants.COMBO_BOX_POKEMON_PERSONALITY_LIST));

		// どうぐ一覧をドロップダウンリストに設定する
		setItemList(comboBox.get(Constants.COMBO_BOX_ITEM_LIST));

	}

	private void setPokemonNameList(ComboBox<String> pokemonNameList) {

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

	private void setPokemonPersonalityList(ComboBox<String> pokemonPersonalityList) {

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

	private void setItemList(ComboBox<String> itemList) {

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

	public void setTextAreaRestrictions(List<TextArea> textArea) {
		TextUtil.setTextAreaRestrictions(textArea.get(Constants.TEXT_AREA_HP_EFFORT_VALUE));
		TextUtil.setTextAreaRestrictions(textArea.get(Constants.TEXT_AREA_ATTACK_EFFORT_VALUE));
		TextUtil.setTextAreaRestrictions(textArea.get(Constants.TEXT_AREA_DEFENSE_EFFORT_VALUE));
		TextUtil.setTextAreaRestrictions(textArea.get(Constants.TEXT_AREA_SPECIAL_ATTACK_EFFORT_VALUE));
		TextUtil.setTextAreaRestrictions(textArea.get(Constants.TEXT_AREA_SPECIAL_DEFENSE_EFFORT_VALUE));
		TextUtil.setTextAreaRestrictions(textArea.get(Constants.TEXT_AREA_SPEED_EFFORT_VALUE));
	}

	public void setPokemonAbilityList(List<ComboBox<String>> comboBox) {

		// リストの内容を初期化
		comboBox.get(Constants.COMBO_BOX_POKEMON_ABILITY_LIST).getItems().clear();

		// 選択された名前からIDを取得
		List<String> ids = getPokemonIds(comboBox.get(Constants.COMBO_BOX_POKEMON_NAME_LIST).getValue());

		// ポケモンの名前に紐づく性格をドロップダウンリストに設定する
		setPokemonAbilityList(comboBox, ids.get(Constants.POKEMON_ID), ids.get(Constants.POKEMON_FORM_ID));
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

	private void setPokemonAbilityList(List<ComboBox<String>> comboBox, String id, String formId) {

		// ポケモン特性情報マスタテーブルから取得する
		MPokemonAbilityDao dao = new MPokemonAbilityDao();
		MPokemonAbilityEntity mPokemonAbilityEntity = dao.selectMPokemonAbilityByIds(id, formId);

		// 表示用の特性リストを準備
		List<String> dispPokemonAbilityList = new ArrayList<>();

		if (mPokemonAbilityEntity.getAbility1() != null && !"NULL".equals(mPokemonAbilityEntity.getAbility1())) {
			String ability1 = DataBaseUtil.getAbilityNameById(mPokemonAbilityEntity.getAbility1());
			dispPokemonAbilityList.add(ability1);
		}
		if (mPokemonAbilityEntity.getAbility2() != null && !"NULL".equals(mPokemonAbilityEntity.getAbility2())) {
			String ability2 = DataBaseUtil.getAbilityNameById(mPokemonAbilityEntity.getAbility2());
			dispPokemonAbilityList.add(ability2);
		}
		if (mPokemonAbilityEntity.getDreamAbility() != null
				&& !"NULL".equals(mPokemonAbilityEntity.getDreamAbility())) {
			String dreamAbility = DataBaseUtil.getAbilityNameById(mPokemonAbilityEntity.getDreamAbility());
			dispPokemonAbilityList.add("(夢)" + dreamAbility);
		}
		// 画面のデータリストに格納

		// ポケモンの名前に紐づく特性をドロップダウンリストに設定する
		for (String abilityName : dispPokemonAbilityList) {
			comboBox.get(Constants.COMBO_BOX_POKEMON_ABILITY_LIST).getItems().add(abilityName);
		}

	}

	public void save(List<ComboBox<String>> comboBox, List<TextArea> textArea) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
