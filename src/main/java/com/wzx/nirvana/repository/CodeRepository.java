package com.wzx.nirvana.repository;

import com.wzx.nirvana.model.Code;

public interface CodeRepository {


    Code getOne(String code);

    Code addCode(Code code);

    int updateCode(Code code);

}
