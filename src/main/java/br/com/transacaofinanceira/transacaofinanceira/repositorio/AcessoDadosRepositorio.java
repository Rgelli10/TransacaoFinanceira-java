package br.com.transacaofinanceira.transacaofinanceira.repositorio;

import br.com.transacaofinanceira.transacaofinanceira.model.ContaSaldo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AcessoDadosRepositorio {
    private List<ContaSaldo> tabelaSaldos;

    public AcessoDadosRepositorio() {
        tabelaSaldos = new ArrayList<>();
        tabelaSaldos.add(new ContaSaldo(938485762L, BigDecimal.valueOf(180)));
        tabelaSaldos.add(new ContaSaldo(347586970L, BigDecimal.valueOf(1200)));
        tabelaSaldos.add(new ContaSaldo(2147483649L, BigDecimal.ZERO));
        tabelaSaldos.add(new ContaSaldo(675869708L, BigDecimal.valueOf(4900)));
        tabelaSaldos.add(new ContaSaldo(238596054L, BigDecimal.valueOf(478)));
        tabelaSaldos.add(new ContaSaldo(573659065L, BigDecimal.valueOf(787)));
        tabelaSaldos.add(new ContaSaldo(210385733L, BigDecimal.valueOf(10)));
        tabelaSaldos.add(new ContaSaldo(674038564L, BigDecimal.valueOf(400)));
        tabelaSaldos.add(new ContaSaldo(563856300L, BigDecimal.valueOf(1200)));
    }

    public ContaSaldo getSaldo(long id) {
        return tabelaSaldos.stream()
                .filter(conta -> conta.getConta() == id)
                .findFirst()
                .orElse(null);
    }
}