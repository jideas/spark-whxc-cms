package com.spark.base.common.pagination;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spark.base.common.utils.KeySynchronizer;


/**
 * @author  Zhang Kaitao
 * @version 1.0, Sep 17, 2010
 * @since 1.0
 */
public class PageUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PageUtil.class);
    /**
     * ��ȡ����ʱ����
     */
    private static Map<Class<?>, Field> classPKMap = new WeakHashMap<Class<?>, Field>();
    
    /**
     * �������ܼ�¼��
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public static int getPageStart(int pageNumber, int pageSize) {
        return (pageNumber - 1) * pageSize;
    }
    
    /**
     * �����ҳ��ȡ����ʱ�α����ʼλ��
     * 
     * @param totalCount ���м�¼�ܺ�
     * @param pageNumber ҳ��,��1��ʼ
     * @return
     */
    public static int getPageStart(int totalCount, int pageNumber, int pageSize) {
        int start = (pageNumber - 1) * pageSize;
        if (start >= totalCount) {
            start = 0;
        }

        return start;
    }

    /**
     * �����ҳ����
     * 
     * @param totalCount �������������м�¼�ܺ�
     * @param pageNumber ���η�ҳ��ҳ��
     * @param items
     * @return
     */
    public static <E> Page<E> getPage(int totalCount, int pageNumber, List<E> items, int pageSize) {
        IPageContext<E> pageContext = new QuickPageContext<E>(totalCount, pageSize, items);
        return pageContext.getPage(pageNumber);
    }
    
    public static Field getPkField(Class<?> cls) {
        Field pkField = classPKMap.get(cls);
        if(pkField == null) {
            synchronized (KeySynchronizer.acquire(cls)) {
                Field[] fields = cls.getDeclaredFields();
                for(Field field : fields) {
                    if(field.isAnnotationPresent(Id.class)) {
                        pkField = field;
                        pkField.setAccessible(true);
                        classPKMap.put(cls, pkField);
                    }
                }
            }
        }
        if(pkField == null) {
            LOGGER.error("page error,{} : pk null", cls);
        }
        return pkField;
    }
    
    public static <T> String getIdValue(T obj) {
        if(obj == null) {
            return "";
        }
        String retVal = "";
        Field pkField = getPkField(obj.getClass());
        try {
            retVal = pkField.get(obj).toString();
        } catch (Exception e) {
            LOGGER.error("page error,{} : get id value", obj);
        }
        return retVal;
    }
    public static <T> String getIdName(T obj) {
        if(obj == null) {
            return "";
        }
        String retVal = "";
        Field pkField = getPkField(obj.getClass());
        try {
            retVal = pkField.getName();
        } catch (Exception e) {
            LOGGER.error("page error,{} : get id name", obj);
        }
        return retVal;
    }
}
