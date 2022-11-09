package com.mugigraphql.controller;

import com.mugigraphql.entity.Person;
import com.mugigraphql.service.PersonService;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Mugi
 */
@RestController
@RequestMapping(value = "/Person")
@Slf4j
public class PersonController {
    private final PersonService personService;

    @Value("classpath:person.graphqls")
    private Resource schemaResource;

    private GraphQL graphQL;
    private Long id;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostConstruct
    public void loadSchema() throws IOException {
        File schemaFile = schemaResource.getFile();
        TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildWiring() {
        DataFetcher<List<Person>> fetcher1 = data -> {
            return (List<Person>) personService.listPersons();
        };

        DataFetcher<Person> fetcher2 = data -> {
            return personService.findByEmail(data.getArgument("email"));
        };

        return RuntimeWiring.newRuntimeWiring().type("Query",
                        typeWriting -> typeWriting
                                .dataFetcher("getAllPerson", fetcher1)
                                .dataFetcher("findPerson", fetcher2))
                .build();

    }

    @PostMapping
    public String addPerson(@RequestBody Person person) {
        log.info("newPerson:-" + person);

        return personService.addPerson(person);
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") Long id) {
 
        return personService.getPerson(id);
    }


    @PutMapping
    public String updatePerson(Person person) {
        return personService.updatePerson(person);
    }

    @GetMapping("/List")
    public List<Person> listPersons() {
        return personService.listPersons();
    }

    @PostMapping("/getAll")
    public ResponseEntity<Object> getAll(@RequestBody String query) {
        ExecutionResult result = graphQL.execute(query);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @PostMapping("/getPersonByEmail")
    public ResponseEntity<Object> getPersonByEmail(@RequestBody String query) {
        ExecutionResult result = graphQL.execute(query);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }
}
