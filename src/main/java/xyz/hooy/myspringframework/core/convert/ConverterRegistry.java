package xyz.hooy.myspringframework.core.convert;

import xyz.hooy.myspringframework.core.convert.converter.Converter;
import xyz.hooy.myspringframework.core.convert.converter.ConverterFactory;
import xyz.hooy.myspringframework.core.convert.converter.GenericConverter;

/**
 * 用于存放多个转换器
 */
public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);

    void addConverter(GenericConverter converter);

    void addConverterFactory(ConverterFactory<?, ?> factory);

    void removeConvertible(Class<?> sourceType, Class<?> targetType);
}
