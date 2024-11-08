package hibernate.json.regex;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Object[]> getAll(){
        String query = "SELECT id, content::TEXT FROM item";
        Query nativeQuery = entityManager.createNativeQuery(query);
        return nativeQuery.getResultList();
    }

    public List<Object[]> findByReference(String value){
        String query = """
        SELECT id, content::TEXT
        FROM item
        WHERE 1=1
            AND JSONB_PATH_EXISTS(
                      item.content, '$.reference ? (@ == $VALUE )',
                      JSONB_BUILD_OBJECT('VALUE', :value)
            );
        """;
        Query nativeQuery = entityManager.createNativeQuery(query);
        nativeQuery.setParameter("value", value);
        return nativeQuery.getResultList();
    }

    public List<Object[]> findByReferenceRegex(String pattern){
        String query = """
        SELECT id, content::TEXT
        FROM item
        WHERE 1=1
            AND item.content @\\?\\? FORMAT(
                                        '$.reference ? (@ LIKE_REGEX %s)', 
                                        TO_JSON(:pattern) 
                                     )::JSONPATH
        """;
        Query nativeQuery = entityManager.createNativeQuery(query);
        nativeQuery.setParameter("pattern", pattern);
        return nativeQuery.getResultList();
    }

}
