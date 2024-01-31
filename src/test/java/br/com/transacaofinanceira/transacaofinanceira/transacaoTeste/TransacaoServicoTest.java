package br.com.transacaofinanceira.transacaofinanceira.transacaoTeste;

import br.com.transacaofinanceira.transacaofinanceira.model.ContaSaldo;
import br.com.transacaofinanceira.transacaofinanceira.servico.TransacaoServico;
import br.com.transacaofinanceira.transacaofinanceira.model.Transacao;
import br.com.transacaofinanceira.transacaofinanceira.repositorio.AcessoDadosRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransacaoServicoTest {

    @Mock
    private AcessoDadosRepositorio acessoDadosRepositorio;

    @InjectMocks
    private TransacaoServico transacaoServico;

    @Test
    public void transferirComSaldoSuficiente() {
        when(acessoDadosRepositorio.getSaldo(938485762L)).thenReturn(new ContaSaldo(938485762L, BigDecimal.valueOf(180)));
        when(acessoDadosRepositorio.getSaldo(2147483649L)).thenReturn(new ContaSaldo(2147483649L, BigDecimal.valueOf(50)));

        Transacao transacao = new Transacao(1, "09/09/2023 14:15:00", 938485762L, 2147483649L, BigDecimal.valueOf(50));
        transacaoServico.transferir(transacao);

        assertEquals(BigDecimal.valueOf(130), transacaoServico.getSaldoConta(938485762L));
        assertEquals(BigDecimal.valueOf(100), transacaoServico.getSaldoConta(2147483649L));
    }

    @Test
    public void transferirComSaldoInsuficiente() {
        when(acessoDadosRepositorio.getSaldo(938485762L)).thenReturn(new ContaSaldo(938485762L, BigDecimal.valueOf(180)));
        when(acessoDadosRepositorio.getSaldo(2147483649L)).thenReturn(new ContaSaldo(2147483649L, BigDecimal.valueOf(0)));

        Transacao transacao = new Transacao(1, "09/09/2023 14:15:00", 938485762L, 2147483649L, BigDecimal.valueOf(200));
        transacaoServico.transferir(transacao);

        assertEquals(BigDecimal.valueOf(180), transacaoServico.getSaldoConta(938485762L));
        assertEquals(BigDecimal.valueOf(0), transacaoServico.getSaldoConta(2147483649L));
    }

    @Test
    public void transferirComSaldoInvalido() {
        when(acessoDadosRepositorio.getSaldo(938485762L)).thenThrow(new IllegalArgumentException("Saldo inválido"));

        Transacao transacao = new Transacao(1, "09/09/2023 14:15:00", 938485762L, 2147483649L, BigDecimal.valueOf(-50));

        Exception exception = assertThrows(Exception.class, () -> transacaoServico.transferir(transacao));
        assertEquals("Saldo inválido", exception.getMessage());
    }

}
