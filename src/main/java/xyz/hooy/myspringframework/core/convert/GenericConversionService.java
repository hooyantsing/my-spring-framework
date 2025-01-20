package xyz.hooy.myspringframework.core.convert;

import xyz.hooy.myspringframework.core.convert.converter.Converter;
import xyz.hooy.myspringframework.core.convert.converter.ConverterFactory;
import xyz.hooy.myspringframework.core.convert.converter.GenericConverter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class GenericConversionService implements ConfigurableConversionService {

    private final Map<GenericConverter.ConvertiblePair, GenericConverter> converters = new ConcurrentHashMap<>();

    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        return false;
    }

    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        return null;
    }

    @Override
    public void addConverter(Converter<?, ?> converter) {
        GenericConverter.ConvertiblePair requiredTypeInfo = getRequiredTypeInfo(converter);
        ConverterAdapter converterAdapter = new ConverterAdapter(converter, requiredTypeInfo);
        converters.put(requiredTypeInfo, converterAdapter);
    }

    @Override
    public void addConverter(GenericConverter converter) {

    }

    @Override
    public void addConverterFactory(ConverterFactory<?, ?> factory) {

    }

    @Override
    public void removeConvertible(Class<?> sourceType, Class<?> targetType) {

    }

    private GenericConverter.ConvertiblePair getRequiredTypeInfo(Object object) {
        Type[] genericInterfaces = object.getClass().getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
        return new GenericConverter.ConvertiblePair((Class<?>) actualTypeArguments[0], (Class<?>) actualTypeArguments[1]);
    }

    private final static class ConverterAdapter implements GenericConverter {

        private final Converter<Object, Object> converter;
        private final ConvertiblePair convertiblePair;

        private ConverterAdapter(Converter<?, ?> converter, ConvertiblePair convertiblePair) {
            this.converter = (Converter<Object, Object>) converter;
            this.convertiblePair = convertiblePair;
        }

        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(convertiblePair);
        }

        @Override
        public Object convert(Object source, Class<?> sourceType, Class<?> targetType) {
            return converter.convert(source);
        }
    }
}
