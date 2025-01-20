package xyz.hooy.myspringframework.core.convert;

import xyz.hooy.myspringframework.core.convert.converter.IntegerToStringConverter;
import xyz.hooy.myspringframework.core.convert.converter.StringToNumberConverterFactory;

public class DefaultGenericConversionService extends GenericConversionService {

    public DefaultGenericConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry registry) {
        registry.addConverter(new IntegerToStringConverter());
        registry.addConverterFactory(new StringToNumberConverterFactory());
    }
}
