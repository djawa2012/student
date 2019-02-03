package com.edu.student.dao;

import io.jsondb.JsonDBTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentDAO {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JsonDBTemplate jsonDBTemplate;

    public List<StudentDO> search (String first, String last) {
        String jxQuery = String.format("/.[contains(" +
                "translate(@first,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'), '%s') " +
                "and contains(" +
                "translate(@last,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'), '%s')]", first.toUpperCase(), last.toUpperCase());
        return jsonDBTemplate.find(jxQuery, StudentDO.class);
    }

    public List<StudentDO> searchByFirst (String first) {
        String jxQuery = String.format("/.[contains(" +
                "translate(@first,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'), '%s')]", first.toUpperCase());
        return jsonDBTemplate.find(jxQuery, StudentDO.class);
    }

    public List<StudentDO> searchByLast (String last) {
        String jxQuery = String.format("/.[contains(" +
                "translate(@last,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'), '%s')]", last.toUpperCase());
        return jsonDBTemplate.find(jxQuery, StudentDO.class);
    }

    public Optional<StudentDO> getStudent (String first, String last) {
        if ( null == first || null == last )
            return Optional.empty();

        String jxQuery = String.format("/.[translate(@first,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='%s'" +
                " and " +
                "translate(@last,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='%s']", first.toUpperCase(), last.toUpperCase());
        List<StudentDO> list = jsonDBTemplate.find(jxQuery, StudentDO.class);

        if ( list.size() > 1 ) {
            logger.info(String.format("Found more than one students with the name: %s %s", first, last));
            return Optional.empty();
        } else if ( list.size() == 0 ) {
            logger.info(String.format("No student with the name: %s %s", first, last));
            return Optional.empty();
        } else {
            return Optional.of( list.get(0) );
        }
    }
}
