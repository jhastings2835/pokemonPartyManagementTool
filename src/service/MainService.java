package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.TrainedPokemonDetailController;
import dao.TTrainedPokemonDao;
import dto.PokemonDetailDto;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainService {

    public List<PokemonDetailDto> getPokemonDetailDto() {
        List<PokemonDetailDto> pokemonDetailDtoList = new ArrayList<>();
        TTrainedPokemonDao tTrainedPokemonDao = new TTrainedPokemonDao();
        pokemonDetailDtoList = tTrainedPokemonDao.selectDisplayPokemonDetail();
        return pokemonDetailDtoList;
    }

    public void setTabelResultDoubleClickEvent(
            TableView<PokemonDetailDto> tableResultList, Stage stage) {
        tableResultList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boolean doubleClicked = event.getButton().equals(
                        MouseButton.PRIMARY) && event.getClickCount() == 2;
                if (doubleClicked) {

                    FXMLLoader fxmlLoader = new FXMLLoader(
                            getClass().getResource(
                                    "/application/TrainedPokemonDetail.fxml"));
                    VBox secondPane = null;
                    try {
                        secondPane = (VBox) fxmlLoader.load();
                    } catch (IOException e) {
                        // TODO 自動生成された catch ブロック
                        e.printStackTrace();
                    }

                    Stage secondStage = new Stage();

                    // ダブルクリックで選択した値を取得
                    PokemonDetailDto pokemonDetailDto = tableResultList
                            .getSelectionModel().getSelectedItem();
                    int id = pokemonDetailDto.getId();

                    TrainedPokemonDetailController trainedPokemonDetailController = fxmlLoader
                            .getController();

                    // 育成済みポケモンＩＤを渡して表示
                    trainedPokemonDetailController.setInitValue(stage, id);

                    Scene secondScene = new Scene(secondPane);
                    secondStage.setTitle("PokemonPartyManagementTool");
                    secondStage.setScene(secondScene);
                    secondStage.show();
                }
            }
        });
    }
}
