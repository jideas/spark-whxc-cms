package com.spark.base.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.hibernate.type.Type;



public interface GenericDAO{
    
	public Session getSession();
	
	/**
	 * ����pojo
	 */
    public <M,PK> PK save(M model);

    /**
     *�������
     * @param model
     */
    public <M> void saveOrUpdate(M model);
    
    /**
     * ����pojo
     * @param model
     */
    public  <M> void update(M model);
    
    
    
    public  <M> void merge(M model);
    
    /**
     * ɾ��
     * @param id
     */
    public <M, PK extends Serializable> void delete(Class<M> clazz,PK pk);

    /**
     * ɾ��
     * @param model
     */
    public <M> void deleteObject(M model);

    /**
     * ��ȡ
     * @param id
     * @return
     */
    public <M,PK extends Serializable> M get(Class<M> clazz,PK id);
    
    /**
     * ��ȡ
     * @param id
     * @return
     */
    public <M,PK extends Serializable> M load(Class<M> clazz,PK id);
    
    /**
     * ������
     * @return
     */
    public <M> int countAll(Class<M> clazz);

    /**
     * ���м�¼
     * @return
     */
    public <M> List<M> listAll(Class<M> clazz);

    /**
     * ��ȡ��������
     */
    public int getGenericCountByHql(final String hsql,final Object ...paramlist);
    
    /**
     * HSQL��ѯ
     */
    public <T>List<T> getGenericByHql(final String hsql,final Object ...paramlist);
    
    /**
     * hsql��ҳ��ѯ
     * @param <T>
     * @param hsql
     * @param position
     * @param pageSize
     * @param paramlist
     * @return
     */
    public <T>List<T> getGenericByPosition(final String hsql,final int position,final int pageSize,final Object ...paramlist);
    
    /**
     * �Ƿ����
     * @param id
     * @return
     */
    public <M,PK extends Serializable> boolean  exists(Class<M> clazz,PK id);
    
    public void flush();
    
    public void clear();
    
    /**
     * ִ��Hsql��insert;update;delete;
     */
    public int execteBulk(final String hql, final Object... paramlist);
    
    /**
     * ִ��ԭ��SQL��insert;update;delete;
     * @param natvieSQL
     * @param paramlist
     * @return
     */
    public int execteNativeBulk(final String natvieSQL, final Object... paramlist) ;
    
    /**
     * ִ��ԭ��SQL��ѯ
     */
    public <T> List<T> query(final String nativeSQL,final Class<T> entity,final Object... paramlist);
    
    
    /**
     * ִ��ԭ��SQL��ѯ
     */
    public <T> List<T> query(final String nativeSQL,final Object... paramlist);

}
