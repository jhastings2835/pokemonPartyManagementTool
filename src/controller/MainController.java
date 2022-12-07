package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.DataBaseUtil;
import dao.MAbilityDao;
import dao.PokemonDetailDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.PokemonDetailEntity;

public class MainController implements Initializable {

	@FXML
	private Button addTrainedPokemonBtn;

	@FXML
	private TableView<PokemonDetailEntity> tableResultList;

	@FXML
	private TableColumn<PokemonDetailEntity, String> id;

	@FXML
	private TableColumn<PokemonDetailEntity, String> name;

	@FXML
	private TableColumn<PokemonDetailEntity, String> personality;

	@FXML
	private TableColumn<PokemonDetailEntity, String> ablity;

	@FXML
	private TableColumn<PokemonDetailEntity, String> item;

	@FXML
	private TableColumn<PokemonDetailEntity, String> totalEffortValue;

	@FXML
	private TableColumn<PokemonDetailEntity, String> hitPointsEffortValue;

	@FXML
	private TableColumn<PokemonDetailEntity, String> attackEffortValue;

	@FXML
	private TableColumn<PokemonDetailEntity, String> defenseEffortValue;

	@FXML
	private TableColumn<PokemonDetailEntity, String> specialAttackEffortValue;

	@FXML
	private TableColumn<PokemonDetailEntity, String> specialDefenseEffortValue;

	@FXML
	private TableColumn<PokemonDetailEntity, String> speedEffortValue;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setMasterDatas();
		setTableColumns();
	}

	/**
	 * マスタデータを設定する。
	 */
	private void setMasterDatas() {
		// TODO 自動生成されたメソッド・スタブ
		MAbilityDao mAbilityDao = new MAbilityDao();
		DataBaseUtil.mAbilityEntityList = mAbilityDao.selectMAbility();
	}

	@FXML
	void actLoad(ActionEvent event) {
		PokemonDetailDao dao = new PokemonDetailDao();
		tableResultList.getItems().clear();
		tableResultList.getItems().addAll(dao.selectTTrainedPokemon());
	}

	@FXML
	void showAddTrainedPokemon(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/TrainedPokemonDetail.fxml"));
		VBox secondPane = (VBox) fxmlLoader.load();

		Stage secondStage = new Stage();

		Scene secondScene = new Scene(secondPane);
		secondStage.setTitle("PokemonPartyManagementTool");
		secondStage.setScene(secondScene);
		secondStage.show();

	}

	// カラムとデータクラスメンバの結び付け
	private void setTableColumns() {
		id.setCellValueFactory(new PropertyValueFactory<PokemonDetailEntity, String>("id"));
		name.setCellValueFactory(new PropertyValueFactory<PokemonDetailEntity, String>("name"));
		personality.setCellValueFactory(new PropertyValueFactory<PokemonDetailEntity, String>("personality"));
		ablity.setCellValueFactory(new PropertyValueFactory<PokemonDetailEntity, String>("ablity"));
		item.setCellValueFactory(new PropertyValueFactory<PokemonDetailEntity, String>("item"));
		totalEffortValue.setCellValueFactory(new PropertyValueFactory<PokemonDetailEntity, String>("totalEffortValue"));
		hitPointsEffortValue
				.setCellValueFactory(new PropertyValueFactory<PokemonDetailEntity, String>("hitPointsEffortValue"));
		attackEffortValue
				.setCellValueFactory(new PropertyValueFactory<PokemonDetailEntity, String>("attackEffortValue"));
		defenseEffortValue
				.setCellValueFactory(new PropertyValueFactory<PokemonDetailEntity, String>("defenseEffortValue"));
		specialAttackEffortValue
				.setCellValueFactory(new PropertyValueFactory<PokemonDetailEntity, String>("specialAttackEffortValue"));
		specialDefenseEffortValue.setCellValueFactory(
				new PropertyValueFactory<PokemonDetailEntity, String>("specialDefenseEffortValue"));
		speedEffortValue.setCellValueFactory(new PropertyValueFactory<PokemonDetailEntity, String>("speedEffortValue"));
	}

}
