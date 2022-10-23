package com.cdsoft.dialogflowserver.mappers;

public interface Mapper <T, S>{

    T map(S s);
}
