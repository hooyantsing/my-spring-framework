package xyz.hooy.myspringframework.core.convert;

import org.junit.jupiter.api.Test;

public class ConvertTest {

    @Test
    void convert() {
        DefaultGenericConversionService defaultGenericConversionService = new DefaultGenericConversionService();
        String integerToString = defaultGenericConversionService.convert(new Integer(100), String.class);
        System.out.println(integerToString);
        Double stringToNumber = defaultGenericConversionService.convert("200.0", Double.class);
        System.out.println(stringToNumber);
    }
}
