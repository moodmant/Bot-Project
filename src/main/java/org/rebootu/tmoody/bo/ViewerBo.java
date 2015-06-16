package org.rebootu.tmoody.bo;

import org.rebootu.tmoody.models.Viewer;

/**
 * Created by taylor on 6/15/15.
 */
public interface ViewerBo {

    void save(Viewer viewer);
    void delete(Viewer viewer);
    Viewer findByViewerName(String viewerName);

}
