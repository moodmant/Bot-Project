package org.rebootu.tmoody.bo;

import org.rebootu.tmoody.models.Viewer;
import org.rebootu.tmoody.models.ViewerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by taylor on 6/15/15.
 */
@Service("viewerBo")
public class ViewerBoImpl implements ViewerBo {

    @Autowired
    ViewerDao viewerDao;

    @Override
    public void save(Viewer viewer) {
        this.viewerDao.save(viewer);
    }

    @Override
    public void delete(Viewer viewer) {
        this.viewerDao.delete(viewer);
    }

    @Override
    public Viewer findByViewerName(String viewerName) {
        return this.viewerDao.findByViewerName(viewerName);
    }
}
