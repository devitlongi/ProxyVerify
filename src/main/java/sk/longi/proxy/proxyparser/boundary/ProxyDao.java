package sk.longi.proxy.proxyparser.boundary;


import sk.longi.proxy.proxyparser.entity.ProxyFull;

import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

@Singleton
public class ProxyDao  {

    @PersistenceContext(unitName = "sk.longi.proxy")
    private EntityManager entityManager;

    public void addProxy(ProxyFull proxy) throws Exception {
        entityManager.persist(proxy);


    }

    public void deleteProxy(ProxyFull proxy) throws Exception {
        entityManager.remove(proxy);
    }

//    public List<ProxyFull> getMovies() throws Exception {
//        Query query = entityManager.createQuery("SELECT FROM proxy",ProxyFull.class);
//        return query.getResultList();
//    }


}
