package com.cloud.demo.usecase.hello;

import com.cloud.demo.dao.Pet;

import java.util.List;
import java.util.Map;

public interface Hello {
    String HelloAct(HelloDto param);

    List<Pet> HelloSql(HelloDto param);
}
