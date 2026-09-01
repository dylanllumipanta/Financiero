package com.krakedev.financiero.test;

import com.krakedev.financiero.entidades.Cuenta;

public class TestCuenta {

    public static void main(String[] args) {

        Cuenta cuenta = new Cuenta("001");

        cuenta.imprimir();
    }
}