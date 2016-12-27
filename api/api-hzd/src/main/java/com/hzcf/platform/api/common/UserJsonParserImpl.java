package com.hzcf.platform.api.common;

import com.hzcf.platform.common.util.json.parser.JsonParser;
import com.hzcf.platform.common.util.json.parser.JsonParserException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UserJsonParserImpl
 *
 * @author gavin miao
 * @date 2015/12/29
 */
public class UserJsonParserImpl implements JsonParser {

    private static final Logger log = LoggerFactory.getLogger(JsonParser.class);

    private final ObjectMapper objectMapper;

    public UserJsonParserImpl() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		objectMapper.setDateFormat(dateFormat);
    }

    @Override
    public <T> T getObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("Failed to de-serialize record", e);
            throw new JsonParserException("Failed to de-serialize record", e);
        }
    }

    @Override
    public <T> T getObjectByNode(JsonNode json, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(json, clazz);
        } catch (Exception e) {
            log.error("Failed to de-serialize record", e);
            throw new JsonParserException("Failed to serialized record", e);
        }
    }

    @Override
    public <T> String getJson(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Failed to serialize record", e);
            throw new JsonParserException("Failed to serialize record", e);
        }
    }

    @Override
    public <T> JsonNode toJson(T object) {
        try {
            return objectMapper.valueToTree(object);
        } catch (IllegalArgumentException e) {
            log.error("Failed to de-serialize record", e);
            throw new JsonParserException("Failed to de-serialize record", e);
        }
    }
}
