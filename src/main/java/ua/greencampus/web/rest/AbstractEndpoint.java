package ua.greencampus.web.rest;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * @author Mykola Yashchenko
 */
public abstract class AbstractEndpoint {
    private MapperFacade mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();

    protected <S, D> D map(S source, Class<D> tClass) {
        return mapperFacade.map(source, tClass);
    }
}
