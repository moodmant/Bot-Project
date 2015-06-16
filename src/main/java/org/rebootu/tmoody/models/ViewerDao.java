package org.rebootu.tmoody.models;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by taylor on 6/15/15.
 */
@Transactional
@Repository
public interface ViewerDao extends CrudRepository<Viewer, Integer> {
    Viewer findByViewerName(String viewerName);
}
