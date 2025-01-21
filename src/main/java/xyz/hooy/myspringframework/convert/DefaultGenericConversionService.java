package xyz.hooy.myspringframework.convert;

import xyz.hooy.myspringframework.convert.converter.IntegerToStringConverter;
import xyz.hooy.myspringframework.convert.converter.StringToNumberConverterFactory;

public class DefaultGenericConversionService extends GenericConversionService {

    public DefaultGenericConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry registry) {
        registry.addConverter(new IntegerToStringConverter());
        registry.addConverterFactory(new StringToNumberConverterFactory());
    }
}
