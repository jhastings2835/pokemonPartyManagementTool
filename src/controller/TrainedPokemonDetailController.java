package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.MPokemonAbilityDao;
import dao.MItemDao;
import dao.MPersonalityDao;
import dao.MPokemonDao;
import dao.PokemonDetailDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import model.DataBaseModel;
import model.MItemEntity;
import model.MPersonalityEntity;
import model.MPokemonEntity;

public class TrainedPokemonDetailController implements Initializable {

	List<List<? extends DataBaseModel>> dataBaseModelList = new ArrayList<>();

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
		dataBaseModelList = new ArrayList<>();
		// ドロップダウンリストに値を設定する
		setTableDropDownListValues();
	}

	/**
	 * @param event ポケモンの名前を選択した際、特性のドロップダウンリストを動的に設定する
	 */
	@FXML
	void changePokemonName(ActionEvent event) {
		pokemonNameList.getValue();

		// ポケモンの名前に紐づく性格をドロップダウンリストに設定する
		setPokemonAbilityList();
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

		// ポケモン基本情報一覧をマスタテーブルから取得する
		MPokemonDao dao = new MPokemonDao();
		List<MPokemonEntity> mPokemonEntityList = dao.selectMPokemon();

		// 画面のデータリストに格納
		dataBaseModelList.add(mPokemonEntityList);

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
			// リストに値を設定
			pokemonNameList.getItems().add(pokemonName);
		}
	}

	private void setPokemonPersonalityList() {

		// ポケモン性格一覧をマスタテーブルから取得する
		MPersonalityDao dao = new MPersonalityDao();
		List<MPersonalityEntity> mPersonalityEntityList = dao.selectMPersonality();

		// 画面のデータリストに格納
		dataBaseModelList.add(mPersonalityEntityList);

		// ポケモンの性格一覧をドロップダウンリストに設定する
		for (MPersonalityEntity mPersonalityEntity : mPersonalityEntityList) {
			pokemonPersonalityList.getItems().add(mPersonalityEntity.getName());
		}
	}

	private void setItemList() {

		// ポケモン基本情報一覧をマスタテーブルから取得する
		MItemDao dao = new MItemDao();
		List<MItemEntity> mItemEntityList = dao.selectMItem();

		// 画面のデータリストに格納
		dataBaseModelList.add(mItemEntityList);

		// ポケモンの名前一覧をドロップダウンリストに設定する
		for (MItemEntity mItemEntity : mItemEntityList) {
			itemList.getItems().add(mItemEntity.getName());
		}
	}

	private void setPokemonAbilityList() {
		// ドロップダウンリストの初期化
		pokemonAbilityList = new ComboBox<String>();

		// ポケモン特性情報マスタテーブルから取得する
		MPokemonAbilityDao dao = new MPokemonAbilityDao();
		List<MPokemonAbilityEntity> mPokemonAbilityEntityList = dao.selectMPokemonAbility();

//		// 画面のデータリストに格納
//		dataBaseModelList.add(mPokemonAbilityEntityList);

		// ポケモンの名前に紐づく特性をドロップダウンリストに設定する
		for (MPokemonAbilityEntity mPokemonAbilityEntity : mPokemonAbilityEntityList) {
			pokemonAbilityList.getItems().add(mPokemonAbilityEntity.getName());
		}
	}
}
