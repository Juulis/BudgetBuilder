package net.petrusha.homebudget.persistence.gae.jdo.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import net.petrusha.homebudget.persistence.gae.jdo.PMF;

public class BaseDAO<E> {
	
	protected Class<E> entityClass;
	
	public BaseDAO(Class<E> entityClass) {
		this.entityClass = entityClass;
	}
	
	protected static interface Command<T> {
		T run(PersistenceManager pm);
	}
	
	public <T> T executeTransactional(Command<T> command) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			T result = command.run(pm);
			tx.commit();
			return result;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
	}
	
	public E save(final E entity) {
		return executeTransactional(new Command<E>() {
			public E run(PersistenceManager pm) {
				pm.makePersistent(entity);
				return entity;
			}
		});
	}
	
	public E findById(final String id) {
		return executeTransactional(new Command<E>() {
			public E run(PersistenceManager pm) {
				return pm.getObjectById(entityClass, id);
			}
		});
	}
	
	public List<E> findAll() {
		return executeTransactional(new Command<List<E>>() {
			@SuppressWarnings("unchecked")
			public List<E> run(PersistenceManager pm) {
				return (List<E>) pm.newQuery(entityClass).execute();
			}
		});
	}
	
	public void delete(final E entity) {
		executeTransactional(new Command<Void>() {
			public Void run(PersistenceManager pm) {
				pm.deletePersistent(entity);
				return null;
			}
		});
	}
	
	public void delete(final List<E> entities) {
		executeTransactional(new Command<Void>() {
			public Void run(PersistenceManager pm) {
				pm.deletePersistentAll(entities);
				return null;
			}
		});
	}
}
