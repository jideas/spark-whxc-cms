package com.spark.base.common.pagination;

import java.util.List;

/**
 * ��̬��ҳʵ��.ÿ�β�ѯ����һҳ��¼�������ʹ��.
 *
 * @version 1.0, 2010-4-22
 */
public class QuickPageContext<E> implements IPageContext<E>{
    private List<E> items;
    private int totalCount;//�ܼ�¼��
    private int pageSize;  //ÿҳ��ʾ��¼��
    
    /**
     * 
     * @param totalCount
     * @param pageSize
     * @param items
     */
    public QuickPageContext(int totalCount, int pageSize, List<E> items) {
        this.totalCount = totalCount;
        this.pageSize = pageSize == 0 ? 10 : pageSize;
        if (items != null) {
            this.items = items;
        }
    }
    
    public Page<E> getPage(int index) {
        Page<E> page = new Page<E>();
        page.setContext(this);
        int index2 = index > getPageCount() ? 1 : index;
        page.setHasNext(index2 < getPageCount());
        page.setHasPre(index2 > 1);
        page.setIndex(index2);
        page.setItems(items);
        return page;
    }

    /**
     * ������ҳ��.
     * 
     * @return
     */
    public int getPageCount() {
        int div = totalCount / pageSize;
        int result = (totalCount % pageSize == 0) ? div : div + 1;
        
        return result;
    }
    
    public int getTotal() {
        return this.totalCount;
    }
    
    public int getPageSize() {
        return this.pageSize;
    }
}
