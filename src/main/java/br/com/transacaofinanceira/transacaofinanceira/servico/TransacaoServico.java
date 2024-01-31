package br.com.transacaofinanceira.transacaofinanceira.servico;

import br.com.transacaofinanceira.transacaofinanceira.model.ContaSaldo;
import br.com.transacaofinanceira.transacaofinanceira.model.Transacao;
import br.com.transacaofinanceira.transacaofinanceira.repositorio.AcessoDadosRepositorio;

import java.math.BigDecimal;

public class TransacaoServico {
    private AcessoDadosRepositorio acessoDadosRepositorio;

    public TransacaoServico(AcessoDadosRepositorio acessoDadosRepositorio) {
        this.acessoDadosRepositorio = acessoDadosRepositorio;
    }

    public BigDecimal getSaldoConta(long conta) {
        return acessoDadosRepositorio.getSaldo(conta).getSaldo();
    }

    public void transferir(Transacao transacao) {
        ContaSaldo contaSaldoOrigem = acessoDadosRepositorio.getSaldo(transacao.getContaOrigem());

        if (contaSaldoOrigem == null) {
            System.out.println("Conta origem não encontrada para a transacao " +
                    transacao.getCorrelationId());
            return;
        }

        BigDecimal valorTransacao = transacao.getValor();
        BigDecimal saldoOrigem = contaSaldoOrigem.getSaldo();

        if (saldoOrigem.compareTo(valorTransacao) > 0) {
            ContaSaldo contaSaldoDestino = acessoDadosRepositorio.getSaldo(transacao.getContaDestino());

            if (contaSaldoDestino == null ) {
                System.out.println("Conta destino não encontrada para a transacao " +
                        transacao.getCorrelationId());
                return;
            }

            BigDecimal novoSaldoOrigem = saldoOrigem.subtract(valorTransacao);
            BigDecimal novoSaldoDestino = contaSaldoDestino.getSaldo().add(valorTransacao);

            contaSaldoOrigem.setSaldo(novoSaldoOrigem);
            contaSaldoDestino.setSaldo(novoSaldoDestino);

            System.out.println("Transacao numero " + transacao.getCorrelationId() +
                    " foi efetivada com sucesso! Novos saldos: Conta Origem:" +
                    novoSaldoOrigem + " | Conta Destino: " + novoSaldoDestino);
        } else {
            System.out.println("Transacao numero " +
                    transacao.getCorrelationId() +
                    " foi cancelada. Saldo insuficiente.");
        }
    }
}
