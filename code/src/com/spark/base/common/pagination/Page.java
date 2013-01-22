package com.spark.base.common.pagination;

import java.util.Collections;
import java.util.List;


/**
 * ��ʾ��ҳ�е�һҳ��
 * 
 * @author  Zhang Kaitao
 */
public class Page<E> {
    private boolean hasPre;//�Ƿ���ҳ
    private boolean hasNext;//�Ƿ�βҳ
    private List<E> items;//��ǰҳ�����ļ�¼�б�
    private int index;//��ǰҳҳ��(��ʼΪ1)
    private IPageContext<E> context;
    
    public IPageContext<E> getContext() {
        return this.context;
    }

    public void setContext(IPageContext<E> context) {
        this.context = context;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isHasPre() {
        return this.hasPre;
    }

    public void setHasPre(boolean hasPre) {
        this.hasPre = hasPre;
    }

    public boolean isHasNext() {
        return this.hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public List<E> getItems() {
        return this.items == null ? Collections.<E>emptyList() : this.items;
    }

    public void setItems(List<E> items) {
        this.items = items;
    }
    
}
