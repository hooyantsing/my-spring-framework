package xyz.hooy.myspringframework.core.convert.converter;

public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumberConverter<>(targetType);
    }

    private final static class StringToNumberConverter<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        private StringToNumberConverter(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (targetType == Integer.class) {
                return (T) Integer.valueOf(source);
            } else if (targetType == Double.class) {
                return (T) Double.valueOf(source);
            } else {
                throw new IllegalArgumentException(
                        "Cannot convert String [" + source + "] to target class [" + targetType.getName() + "]");
            }
        }
    }
}
