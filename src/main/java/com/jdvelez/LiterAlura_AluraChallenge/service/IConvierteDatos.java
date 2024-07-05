package com.jdvelez.LiterAlura_AluraChallenge.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
