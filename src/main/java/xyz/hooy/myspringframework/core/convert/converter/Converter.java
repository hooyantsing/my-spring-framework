package xyz.hooy.myspringframework.core.convert.converter;

/**
 * 转换器
 * @param <S> 转换前类型
 * @param <T> 转换后类型
 */
@FunctionalInterface
public interface Converter<S, T> {

    T convert(S source);
}
