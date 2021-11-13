package com.wyjackprojects.backendCore;
import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {

    private static List<Map<String, String>> games = Arrays.asList(
            ImmutableMap.of("id", "1",
                    "code", "ABCDE"),
            ImmutableMap.of("id", "2",
                    "code", "FGHIJ")
    );

    private static List<Map<String, String>> teams = Arrays.asList(
            ImmutableMap.of("id", "1",
                    "gameId", "1",
                    "color", "blue"),
            ImmutableMap.of("id", "2",
                    "gameId", "1",
                    "color", "red")
    );

    public DataFetcher getGameByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String gameId = dataFetchingEnvironment.getArgument("id");
            return games
                    .stream()
                    .filter(game -> game.get("id").equals(gameId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getTeamDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String,String> game = dataFetchingEnvironment.getSource();
            String gameId = game.get("id");
            return teams
                    .stream()
                    .filter(team -> team.get("gameId").equals(gameId))
                    .findFirst()
                    .orElse(null);
        };
    }
}