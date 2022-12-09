package service;

import java.util.ArrayList;
import java.util.List;

import dao.TTrainedPokemonDao;
import dto.PokemonDetailDto;

public class MainService {

    public List<PokemonDetailDto> getPokemonDetailDto() {
        // TODO 自動生成されたメソッド・スタブ
        List<PokemonDetailDto> pokemonDetailDtoList = new ArrayList<>();
        TTrainedPokemonDao tTrainedPokemonDao = new TTrainedPokemonDao();
        pokemonDetailDtoList = tTrainedPokemonDao.selectDisplayPokemonDetail();
        return pokemonDetailDtoList;
    }

}
