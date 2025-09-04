/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.food_ordering_system.common.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.Collections;
import java.util.List;

/**
 * ModelMapper Class.
 * <p>
 * </p>
 *
 * @author
 */

public final class ObjectMapper {



    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    static {
        MODEL_MAPPER.getConfiguration()
                .setSkipNullEnabled(true)                                     // If source field is null, it won’t overwrite the destination field.
                .setFieldMatchingEnabled(true)                                // Allowing mapping directly event don't have getter/setter
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);      // Access private fields without needing public getters/setters.
    }

    public static <I, O> O map(I input, Class<O> outputClass){
        if (input == null) return null;
        return MODEL_MAPPER.map(input, outputClass);
    }

    public static <I, O> List<O> map(List<I> input, Class<O> outputClass){
        if (input == null) return Collections.emptyList();
        return input.stream().map(e -> map(e, outputClass)).toList();
    }


}
