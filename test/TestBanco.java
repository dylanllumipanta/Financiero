package com.krakedev.financiero.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestBanco {

    @Test
    public void probarCrearCuenta() {

        Banco banco = new Banco();

        Cliente cliente = new Cliente(
                "1234567890",
                "Dylan",
                "Llumipanta"
        );

        Cuenta cuenta1 = banco.crearCuenta(cliente);
        Cuenta cuenta2 = banco.crearCuenta(cliente);

        assertEquals("1000", cuenta1.getId());
        assertEquals("1001", cuenta2.getId());

        assertEquals(cliente, cuenta1.getPropietario());
        assertEquals(cliente, cuenta2.getPropietario());
    }

    @Test
    public void probarDepositar() {

        Banco banco = new Banco();

        Cliente cliente = new Cliente(
                "123",
                "Dylan",
                "Llumipanta"
        );

        Cuenta cuenta = banco.crearCuenta(cliente);

        boolean resultado = banco.depositar(500, cuenta);

        assertEquals(true, resultado);
        assertEquals(500, cuenta.getSaldoActual());
    }

    @Test
    public void probarDepositoInvalido() {

        Banco banco = new Banco();

        Cliente cliente = new Cliente(
                "123",
                "Dylan",
                "Llumipanta"
        );

        Cuenta cuenta = banco.crearCuenta(cliente);

        boolean resultado = banco.depositar(-100, cuenta);

        assertEquals(false, resultado);
        assertEquals(0, cuenta.getSaldoActual());
    }

    @Test
    public void probarRetirar() {

        Banco banco = new Banco();

        Cliente cliente = new Cliente(
                "123",
                "Dylan",
                "Llumipanta"
        );

        Cuenta cuenta = banco.crearCuenta(cliente);

        banco.depositar(1000, cuenta);

        boolean resultado = banco.retirar(300, cuenta);

        assertEquals(true, resultado);
        assertEquals(700, cuenta.getSaldoActual());
    }

    @Test
    public void probarRetiroSinSaldo() {

        Banco banco = new Banco();

        Cliente cliente = new Cliente(
                "123",
                "Dylan",
                "Llumipanta"
        );

        Cuenta cuenta = banco.crearCuenta(cliente);

        banco.depositar(500, cuenta);

        boolean resultado = banco.retirar(600, cuenta);

        assertEquals(false, resultado);
        assertEquals(500, cuenta.getSaldoActual());
    }

    @Test
    public void probarTransferir() {

        Banco banco = new Banco();

        Cliente cliente = new Cliente(
                "123",
                "Dylan",
                "Llumipanta"
        );

        Cuenta origen = banco.crearCuenta(cliente);
        Cuenta destino = banco.crearCuenta(cliente);

        banco.depositar(1000, origen);

        boolean resultado = banco.transferir(origen, destino, 400);

        assertEquals(true, resultado);
        assertEquals(600, origen.getSaldoActual());
        assertEquals(400, destino.getSaldoActual());
    }

    @Test
    public void probarTransferenciaSinSaldo() {

        Banco banco = new Banco();

        Cliente cliente = new Cliente(
                "123",
                "Dylan",
                "Llumipanta"
        );

        Cuenta origen = banco.crearCuenta(cliente);
        Cuenta destino = banco.crearCuenta(cliente);

        banco.depositar(200, origen);

        boolean resultado = banco.transferir(origen, destino, 500);

        assertEquals(false, resultado);
        assertEquals(200, origen.getSaldoActual());
        assertEquals(0, destino.getSaldoActual());
    }
}