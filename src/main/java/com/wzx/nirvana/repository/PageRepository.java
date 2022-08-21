package com.wzx.nirvana.repository;

import com.wzx.nirvana.model.Page;

public interface PageRepository {

    Page getOne(String name);

    int update(Page page);
}
