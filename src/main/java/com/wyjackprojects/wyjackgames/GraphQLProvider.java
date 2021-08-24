package com.wyjackprojects.wyjackgames;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.http.MediaType;

import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@Controller
@EnableAutoConfiguration
public class GraphQLProvider {

    private static final Logger log = LoggerFactory.getLogger(GraphQLProvider.class);

//    @PostConstruct
//    public void init() throws IOException {
//        System.out.println("WE ARE HERER THOOOOO");
//
//    }

    @RequestMapping(value = "/graphql", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object executeOperation(@RequestBody Map body) {
        String query = (String) body.get("query");
        Map<String, Object> variables = (Map<String, Object>) body.get("variables");
        if (variables == null) {
            variables = new LinkedHashMap<>();
        }
        String schema = "type Query{hello: String}";

        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

        RuntimeWiring runtimeWiring = newRuntimeWiring()
                .type("Query", builder -> builder.dataFetcher("hello", new StaticDataFetcher("world")))
                .build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

        GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
        ExecutionResult executionResult = build.execute(query);
//        ExecutionResult executionResult = build.execute(query, (Object) null, variables);
        Map<String, Object> result = new LinkedHashMap<>();
        if (executionResult.getErrors().size() > 0) {
            result.put("errors", executionResult.getErrors());
            log.error("Errors: {}", executionResult.getErrors());
        }
        result.put("data", executionResult.getData());
        return result;
    }

//    private GraphQLSchema buildSchema(String sdl) {
//        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
//        RuntimeWiring runtimeWiring = buildWiring();
//        SchemaGenerator schemaGenerator = new SchemaGenerator();
//        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
//    }
//
//    private RuntimeWiring buildWiring() {
//        return RuntimeWiring.newRuntimeWiring()
//          .type(
//            newTypeWiring("Query").dataFetcher("gameById", graphQLDataFetchers.getGameByIdDataFetcher())
//          ).build();
//    }

//    @Bean
//    public GraphQL graphQL() {
//        System.out.println("GRAPHQL WHAAAT");
//        return graphQL;
//    }
}