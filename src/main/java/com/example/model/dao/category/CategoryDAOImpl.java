package com.example.model.dao.category;

import com.example.model.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CategoryDAOImpl implements CategoryDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            categories = session.createQuery("from Category ",Category.class).list();
        } catch (Exception exception){
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return categories;
    }

    @Override
    public boolean create(Category category) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
            return true;
        } catch (Exception exception){
            session.getTransaction().rollback();
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Category findById(int id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Category.class,id);
        } catch (Exception exception){
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean update(Category category) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(category);
            session.getTransaction().commit();
            return true;
        } catch (Exception exception){
            session.getTransaction().rollback();
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
        } catch (Exception exception){
            session.getTransaction().rollback();
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }
}