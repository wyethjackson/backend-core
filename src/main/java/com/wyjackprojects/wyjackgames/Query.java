package com.wyjackprojects.wyjackgames;

import java.util.List;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.wyjackprojects.wyjackgames.punderdome.Game;
import com.wyjackprojects.wyjackgames.punderdome.GameRepository;

public class Query implements GraphQLQueryResolver {

    private final GameRepository gameRepository;

    public Query(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> allGames() {
        return gameRepository.getAllGames();
    }
}