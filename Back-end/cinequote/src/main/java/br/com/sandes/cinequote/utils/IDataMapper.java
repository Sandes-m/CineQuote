package br.com.sandes.cinequote.utils;

public interface IDataMapper {

    <T> T obterDados(String json, Class<T> classe);

}
