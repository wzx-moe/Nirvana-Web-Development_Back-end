package com.wzx.nirvana.repository;

import com.wzx.nirvana.model.Location;

public interface LocationRepository {

    Location getLocation();

    int updateLocation(Location location);
}
