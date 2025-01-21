package xyz.hooy.myspringframework.core;

import org.junit.jupiter.api.Test;
import xyz.hooy.myspringframework.convert.DefaultGenericConversionService;

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
