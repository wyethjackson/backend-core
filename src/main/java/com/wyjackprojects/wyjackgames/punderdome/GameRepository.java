package com.wyjackprojects.wyjackgames.punderdome;

import java.util.ArrayList;
import java.util.List;

public class GameRepository {
    private final List<Game> games;

    public GameRepository() {
        games = new ArrayList<>();
        games.add(new Game("ABCDE", "WyJackGame"));
    }

    public List<Game> getAllGames() {
        return games;
    }

    public void saveGame(Game game) {
        games.add(game);
    }
}
