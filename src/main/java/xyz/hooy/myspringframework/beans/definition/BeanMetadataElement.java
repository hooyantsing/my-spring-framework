package xyz.hooy.myspringframework.beans.definition;

/**
 * 元数据来源
 */
public interface BeanMetadataElement {

    default Object getSource() {
        return null;
    }
}
