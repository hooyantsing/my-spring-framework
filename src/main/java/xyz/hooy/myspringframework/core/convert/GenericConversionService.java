package xyz.hooy.myspringframework.core.convert;

import xyz.hooy.myspringframework.core.convert.converter.Converter;
import xyz.hooy.myspringframework.core.convert.converter.ConverterFactory;
import xyz.hooy.myspringframework.core.convert.converter.GenericConverter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GenericConversionService implements ConfigurableConversionService {

    private final Map<GenericConverter.ConvertiblePair, GenericConverter> converters = new ConcurrentHashMap<>();

    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        return Objects.nonNull(getConverter(sourceType, targetType));
    }

    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        Class<?> sourceType = source.getClass();
        GenericConverter converter = getConverter(sourceType, targetType);
        return (T) converter.convert(source, sourceType, targetType);
    }

    @Override
    public void addConverter(Converter<?, ?> converter) {
        GenericConverter.ConvertiblePair requiredTypeInfo = getRequiredTypeInfo(converter);
        ConverterAdapter converterAdapter = new ConverterAdapter(converter, requiredTypeInfo);
        addConverter(converterAdapter);
    }

    @Override
    public void addConverter(GenericConverter converter) {
        for (GenericConverter.ConvertiblePair convertibleType : converter.getConvertibleTypes()) {
            converters.put(convertibleType, converter);
        }
    }

    @Override
    public void addConverterFactory(ConverterFactory<?, ?> factory) {
        GenericConverter.ConvertiblePair requiredTypeInfo = getRequiredTypeInfo(factory);
        ConverterFactoryAdapter converterFactoryAdapter = new ConverterFactoryAdapter(factory, requiredTypeInfo);
        addConverter(converterFactoryAdapter);
    }

    @Override
    public void removeConvertible(Class<?> sourceType, Class<?> targetType) {

    }

    private GenericConverter.ConvertiblePair getRequiredTypeInfo(Object object) {
        Type[] genericInterfaces = object.getClass().getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
        return new GenericConverter.ConvertiblePair((Class<?>) actualTypeArguments[0], (Class<?>) actualTypeArguments[1]);
    }

    protected GenericConverter getConverter(Class<?> sourceType, Class<?> targetType) {
        List<Class<?>> sourceHierarchies = getClassHierarchy(sourceType);
        List<Class<?>> targetHierarchies = getClassHierarchy(targetType);
        for (Class<?> sourceHierarchy : sourceHierarchies) {
            for (Class<?> targetHierarchy : targetHierarchies) {
                GenericConverter.ConvertiblePair convertiblePair = new GenericConverter.ConvertiblePair(sourceHierarchy, targetHierarchy);
                GenericConverter genericConverter = converters.get(convertiblePair);
                if (Objects.nonNull(genericConverter)) {
                    return genericConverter;
                }
            }
        }
        return null;
    }

    private List<Class<?>> getClassHierarchy(Class<?> type) {
        List<Class<?>> hierarchy = new ArrayList<>();
        while (type != null) {
            hierarchy.add(type);
            type = type.getSuperclass();
        }
        return hierarchy;
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

    private final static class ConverterFactoryAdapter implements GenericConverter {

        private final ConverterFactory<Object, Object> converterFactory;
        private final ConvertiblePair convertiblePair;

        public ConverterFactoryAdapter(ConverterFactory<?, ?> factory, ConvertiblePair convertiblePair) {
            this.converterFactory = (ConverterFactory<Object, Object>) factory;
            this.convertiblePair = convertiblePair;
        }

        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(convertiblePair);
        }

        @Override
        public Object convert(Object source, Class<?> sourceType, Class<?> targetType) {
            return converterFactory.getConverter(targetType).convert(source);
        }
    }
}
