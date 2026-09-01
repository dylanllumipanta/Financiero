package com.krakedev.financiero.servicios;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;

public class Banco {

    private int ultimoCodigo;

    public Banco() {
        ultimoCodigo = 1000;
    }

    public int getUltimoCodigo() {
        return ultimoCodigo;
    }

    public void setUltimoCodigo(int ultimoCodigo) {
        this.ultimoCodigo = ultimoCodigo;
    }

    public Cuenta crearCuenta(Cliente cliente) {

        String codigoStr = ultimoCodigo + "";

        ultimoCodigo++;

        Cuenta cuenta = new Cuenta(codigoStr);

        cuenta.setPropietario(cliente);

        return cuenta;
    }

    public boolean depositar(double monto, Cuenta cuenta) {

        if (monto > 0) {
            double saldoActual = cuenta.getSaldoActual();
            cuenta.setSaldoActual(saldoActual + monto);

            return true;
        }

        return false;
    }

    public boolean retirar(double monto, Cuenta cuenta) {

        if (monto > 0 && monto <= cuenta.getSaldoActual()) {

            double saldoActual = cuenta.getSaldoActual();

            cuenta.setSaldoActual(saldoActual - monto);

            return true;
        }

        return false;
    }

    public boolean transferir(Cuenta origen, Cuenta destino, double monto) {

        boolean retiroExitoso = retirar(monto, origen);

        if (retiroExitoso) {

            boolean depositoExitoso = depositar(monto, destino);

            if (depositoExitoso) {
                return true;
            }
        }

        return false;
    }
}