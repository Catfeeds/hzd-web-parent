package com.hzcf.platform.api.user.common;

import com.hzcf.platform.common.util.json.parser.JsonParser;
import com.hzcf.platform.common.util.json.parser.JsonParserFactory;

/**
 * UserJsonParseFactory
 *
 * @author gavin miao
 * @date 2016/12/2
 */
public class UserJsonParseFactory extends JsonParserFactory{
    private static final JsonParser USER_JSON_PARSER = new UserJsonParserImpl();
    public static JsonParser getUserParser(){
        return USER_JSON_PARSER;
    }
}
