package com.github.fge;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.avro.Avro2JsonSchemaProcessor;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.DevNullProcessingReport;
import com.github.fge.jsonschema.core.tree.JsonTree;
import com.github.fge.jsonschema.core.tree.SchemaTree;
import com.github.fge.jsonschema.core.tree.SimpleJsonTree;
import com.github.fge.jsonschema.core.util.ValueHolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;


public class Main {
    public static void main(String[] args) throws IOException, ProcessingException {
//        final JsonNode schema = JsonLoader.fromResource("/shop_order_006.avsc");
        final JsonNode schema = JsonLoader.fromResource(args[0]);
        final JsonTree tree = new SimpleJsonTree(schema);
        final ValueHolder<JsonTree> input = ValueHolder.hold("avroSchema", tree);

        Avro2JsonSchemaProcessor processor = new com.github.fge.avro.Avro2JsonSchemaProcessor();
        final ValueHolder<SchemaTree> output = processor.process(new DevNullProcessingReport(), input);
        final String outputString = output.getValue().getNode().toPrettyString();

//        Files.write(Paths.get("./shop_order_006.json"), outputString.getBytes(UTF_8));
        Files.write(Paths.get(args[1]), outputString.getBytes(UTF_8));
    }
}
