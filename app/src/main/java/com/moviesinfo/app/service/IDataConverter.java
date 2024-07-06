package com.moviesinfo.app.service;

public interface IDataConverter {
    <T> T getData(String json, Class <T> tClass);
}
