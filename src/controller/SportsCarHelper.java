/**
 * @author jacoby - jking11@dmacc.edu
 * CIS175 - Spring 2023
 * Feb 7, 2023
 */
package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.SportsCar;

public class SportsCarHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Week3-JPA");
	
	public void insertCar(SportsCar sc) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(sc);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<SportsCar> showAllCars(){
		EntityManager em = emfactory.createEntityManager();
		List<SportsCar> allCars = em.createQuery("SELECT i FROM SportsCar i").getResultList();
		return allCars;
	}
	
	public void deleteCar(SportsCar toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<SportsCar> typedQuery = em.createQuery("select sc from SportsCar sc where sc.make = :selectedMake and sc.model = :selectedModel and sc.year = :selectedYear", SportsCar.class);
		typedQuery.setParameter("selectedMake", toDelete.getMake());
		typedQuery.setParameter("selectedModel", toDelete.getModel());
		typedQuery.setParameter("selectedYear", toDelete.getYear());
		typedQuery.setMaxResults(1);
		SportsCar result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public List<SportsCar> searchForCarByMake(String makeSelected) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<SportsCar> typedQuery = em.createQuery("select sc from SportsCar sc where sc.make = :selectedMake", SportsCar.class);
		typedQuery.setParameter("selectedMake", makeSelected);
		List<SportsCar> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<SportsCar> searchForCarByModel(String modelSelected) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<SportsCar> typedQuery = em.createQuery("select sc from SportsCar sc where sc.model = :selectedModel", SportsCar.class);
		typedQuery.setParameter("selectedModel", modelSelected);
		List<SportsCar> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<SportsCar> searchForCarByYear(int yearSelected) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<SportsCar> typedQuery = em.createQuery("select sc from SportsCar sc where sc.year = :selectedYear", SportsCar.class);
		typedQuery.setParameter("selectedYear", yearSelected);
		List<SportsCar> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public SportsCar searchForCarById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		SportsCar found = em.find(SportsCar.class, idToEdit);
		em.close();
		return found;
	}
	
	public void updateCar(SportsCar toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public void cleanUp(){
		emfactory.close();
	}
}
