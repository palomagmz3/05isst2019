package es.upm.dit.isst.rgpd.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.*;

import es.upm.dit.isst.rgpd.model.Evaluacion;
import es.upm.dit.isst.rgpd.model.Evaluador;
import es.upm.dit.isst.rgpd.model.Solicitud;
import java.util.Collection;

public class EvaluadorDAOImplementation implements EvaluadorDAO{


	private EvaluadorDAOImplementation() {} //private para que no se puedan crear objetos de esta clase
	
	private static EvaluadorDAOImplementation instancia = null;
	
	
	public static EvaluadorDAOImplementation getInstance() {
		if(null == instancia)
				instancia = new EvaluadorDAOImplementation();
		return instancia;
	}

	@Override
	public Evaluador read(String email) {
		Evaluador evaluador = null;
		Session session = SessionFactoryService.get().openSession();

		try {
			session.beginTransaction();
			evaluador = session.get(Evaluador.class, email);
			session.getTransaction().commit();
		} catch (Exception e) {

		} finally {
			session.close();
		}
		return evaluador;
	}

	@Override
	public void delete(Evaluador evaluador) {
		Session session = SessionFactoryService.get().openSession();
		try {
		session.beginTransaction();
		session.delete(evaluador);
		session.getTransaction().commit();
		} catch(Exception e) {
			session.close();
		} finally {
			session.close();
		}
	}
	
	@Override
	public void update(Evaluador evaluador) {
		Session session =  SessionFactoryService.get().openSession();
		try {
		session.beginTransaction();
		session.saveOrUpdate(evaluador);
		session.getTransaction().commit();
		} catch(Exception e) {
			session.close();
		} finally {
			session.close();
		}	
	}

	@Override

	public void create(Evaluador evaluador) {
		Session session = SessionFactoryService.get().openSession();
		
		try {
		session.beginTransaction();
		session.save(evaluador);
		session.getTransaction().commit();
		} catch(Exception e) {
		session.close();
		} finally {
			session.close();
		}
	}
	
	public Collection<Evaluador> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Collection<Evaluador> evaluadores = session.createQuery("from Evaluador").list();
		session.getTransaction().commit();
		session.close();
		return evaluadores;
	}
}
