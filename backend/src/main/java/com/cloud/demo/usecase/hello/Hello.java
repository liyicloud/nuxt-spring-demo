package com.cloud.demo.usecase.hello;

import java.util.List;
import java.util.Map;

public interface Hello {
    String HelloAct(HelloDto param);

    List<Map<String,Object>> HelloSql(HelloDto param);
}
