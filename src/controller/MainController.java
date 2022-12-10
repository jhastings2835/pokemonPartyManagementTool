package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import common.DataBaseUtil;
import dao.MAbilityDao;
import dto.PokemonDetailDto;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.MainService;

public class MainController implements Initializable {

    @FXML
    private Button addTrainedPokemonBtn;

    @FXML
    private TableView<PokemonDetailDto> tableResultList;

    @FXML
    private TableColumn<PokemonDetailDto, String> name;

    @FXML
    private TableColumn<PokemonDetailDto, String> personality;

    @FXML
    private TableColumn<PokemonDetailDto, String> ability;

    @FXML
    private TableColumn<PokemonDetailDto, String> item;

    @FXML
    private TableColumn<PokemonDetailDto, String> teraType;

    @FXML
    private TableColumn<PokemonDetailDto, String> hitPointsEffortValue;

    @FXML
    private TableColumn<PokemonDetailDto, String> attackEffortValue;

    @FXML
    private TableColumn<PokemonDetailDto, String> defenseEffortValue;

    @FXML
    private TableColumn<PokemonDetailDto, String> specialAttackEffortValue;

    @FXML
    private TableColumn<PokemonDetailDto, String> specialDefenseEffortValue;

    @FXML
    private TableColumn<PokemonDetailDto, String> speedEffortValue;

    @FXML
    private TableColumn<PokemonDetailDto, String> move1;

    @FXML
    private TableColumn<PokemonDetailDto, String> move2;

    @FXML
    private TableColumn<PokemonDetailDto, String> move3;

    @FXML
    private TableColumn<PokemonDetailDto, String> move4;

    private final MainService mainService = new MainService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMasterDatas();
        setTableColumns();
        setTabelResultDoubleClickEvent();
    }

    /**
     * マスタデータを設定する。
     */
    private void setMasterDatas() {
        MAbilityDao mAbilityDao = new MAbilityDao();
        DataBaseUtil.mAbilityEntityList = mAbilityDao.selectMAbility();
    }

    /**
     * ダブルクリックイベントを検知できるように設定する
     */
    private void setTabelResultDoubleClickEvent() {
        mainService.setTabelResultDoubleClickEvent(tableResultList);
    }

    @FXML
    void actLoad(ActionEvent event) {
        List<PokemonDetailDto> pokemonDetailDtoList = new ArrayList<>();
        pokemonDetailDtoList = mainService.getPokemonDetailDto();
        tableResultList.getItems().clear();
        tableResultList.getItems().addAll(pokemonDetailDtoList);
    }

    @FXML
    void showAddTrainedPokemon(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/application/TrainedPokemonDetail.fxml"));
        VBox secondPane = (VBox) fxmlLoader.load();

        Stage secondStage = new Stage();

        Scene secondScene = new Scene(secondPane);
        secondStage.setTitle("PokemonPartyManagementTool");
        secondStage.setScene(secondScene);
        secondStage.show();

    }

    // カラムとデータクラスメンバの結び付け
    private void setTableColumns() {
        name.setCellValueFactory(
                new PropertyValueFactory<PokemonDetailDto, String>("name"));
        personality.setCellValueFactory(
                new PropertyValueFactory<PokemonDetailDto, String>(
                        "personalityName"));
        ability.setCellValueFactory(
                new PropertyValueFactory<PokemonDetailDto, String>(
                        "abilityName"));
        item.setCellValueFactory(
                new PropertyValueFactory<PokemonDetailDto, String>("itemName"));
        teraType.setCellValueFactory(
                new PropertyValueFactory<PokemonDetailDto, String>("teraType"));
        hitPointsEffortValue.setCellValueFactory(
                new PropertyValueFactory<PokemonDetailDto, String>(
                        "hitPointsEffortValue"));
        attackEffortValue.setCellValueFactory(
                new PropertyValueFactory<PokemonDetailDto, String>(
                        "attackEffortValue"));
        defenseEffortValue.setCellValueFactory(
                new PropertyValueFactory<PokemonDetailDto, String>(
                        "defenseEffortValue"));
        specialAttackEffortValue.setCellValueFactory(
                new PropertyValueFactory<PokemonDetailDto, String>(
                        "specialAttackEffortValue"));
        specialDefenseEffortValue.setCellValueFactory(
                new PropertyValueFactory<PokemonDetailDto, String>(
                        "specialDefenseEffortValue"));
        speedEffortValue.setCellValueFactory(
                new PropertyValueFactory<PokemonDetailDto, String>(
                        "speedEffortValue"));
        move1.setCellValueFactory(
                new PropertyValueFactory<PokemonDetailDto, String>(
                        "move1Name"));
        move2.setCellValueFactory(
                new PropertyValueFactory<PokemonDetailDto, String>(
                        "move2Name"));
        move3.setCellValueFactory(
                new PropertyValueFactory<PokemonDetailDto, String>(
                        "move3Name"));
        move4.setCellValueFactory(
                new PropertyValueFactory<PokemonDetailDto, String>(
                        "move4Name"));
    }
}
