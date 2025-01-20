package xyz.hooy.myspringframework.core.convert.converter;

public class IntegerToStringConverter implements Converter<Integer, String> {

    @Override
    public String convert(Integer source) {
        return String.valueOf(source);
    }
}
