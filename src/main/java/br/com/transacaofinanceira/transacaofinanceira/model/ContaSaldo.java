package br.com.transacaofinanceira.transacaofinanceira.model;

import java.math.BigDecimal;

public class ContaSaldo {
    private long conta;
    private BigDecimal saldo;

    public ContaSaldo(long conta, BigDecimal saldo) {
        this.conta = conta;
        this.saldo = saldo;
    }

    public long getConta() {
        return conta;
    }
    public BigDecimal getSaldo() {
        return saldo;
    }
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
