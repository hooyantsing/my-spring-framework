package xyz.hooy.myspringframework.beans.factory;

import xyz.hooy.myspringframework.convert.ConversionService;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    ConversionService getConversionService();

    void setConversionService(ConversionService conversionService);
}
