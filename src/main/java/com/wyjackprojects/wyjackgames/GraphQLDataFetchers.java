package com.wyjackprojects.wyjackgames;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {

    private static List<Map<String, String>> games = Arrays.asList(
            ImmutableMap.of("gameId", "ABCDE",
                    "name", "1st Game"),
            ImmutableMap.of("id", "EDCBA",
                    "name", "2nd Game"));

    public DataFetcher getGameByIdDataFetcher() {
        System.out.println("fiewajroifjejfejwa");
        return dataFetchingEnvironment -> {
            String gameId = dataFetchingEnvironment.getArgument("gameId");
            return games
                    .stream()
                    .filter(game -> game.get("gameId").equals(gameId))
                    .findFirst()
                    .orElse(null);
        };
    }
//
//    public DataFetcher getAuthorDataFetcher() {
//        return dataFetchingEnvironment -> {
//            Map<String, String> book = dataFetchingEnvironment.getSource();
//            String authorId = book.get("authorId");
//            return authors
//                    .stream()
//                    .filter(author -> author.get("id").equals(authorId))
//                    .findFirst()
//                    .orElse(null);
//        };
//    }
}