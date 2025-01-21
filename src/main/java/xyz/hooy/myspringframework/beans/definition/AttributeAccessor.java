package xyz.hooy.myspringframework.beans.definition;

/**
 * 对象提供 set 和 get 元数据的能力。
 */
public interface AttributeAccessor {

    void setAttribute(String name, Object value);

    Object getAttribute(String name);

    Object removeAttribute(String name);

    boolean hasAttribute(String name);

    String[] attributeNames();
}
