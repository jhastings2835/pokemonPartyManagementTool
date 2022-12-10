package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            TableView<PokemonDetailDto> tableResultList) {
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

                    Scene secondScene = new Scene(secondPane);
                    secondStage.setTitle("PokemonPartyManagementTool");
                    secondStage.setScene(secondScene);
                    secondStage.show();
                }
            }
        });
    }
}
