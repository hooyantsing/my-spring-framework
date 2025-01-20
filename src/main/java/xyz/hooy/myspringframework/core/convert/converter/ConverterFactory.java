package xyz.hooy.myspringframework.core.convert.converter;

/**
 * 转换器工厂
 * @param <S> 转换前类型 S
 * @param <R> 转换后类型 T 的父类型 R
 */
public interface ConverterFactory<S, R> {

    <T extends R> Converter<S, T> getConverter(Class<T> targetType);
}
