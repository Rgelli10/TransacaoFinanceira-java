package br.com.transacaofinanceira.transacaofinanceira.model;

import java.math.BigDecimal;

public class Transacao {
    private int correlationId;
    private String datetime;
    private long contaOrigem;
    private long contaDestino;
    private BigDecimal valor;

    public Transacao(int correlationId, String datetime, long contaOrigem, long contaDestino, BigDecimal valor) {
        this.correlationId = correlationId;
        this.datetime = datetime;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
    }

    public int getCorrelationId() {
        return correlationId;
    }
    public long getContaOrigem() {
        return contaOrigem;
    }
    public long getContaDestino() {
        return contaDestino;
    }
    public BigDecimal getValor() {
        return valor;
    }

}
