package com.mimduim.calculagorjeta;

/**
 * Created by Mimduim on 24/04/2017.
 */
public class CalculadoraGorjeta {

    private float valorConta;

    public float getValorConta() {
        return valorConta;
    }

    public void setValorConta(float valorConta) {
        this.valorConta = valorConta;
    }

    public float getGorjeta(float porcentagem) {
        return valorConta * porcentagem;
    }
}