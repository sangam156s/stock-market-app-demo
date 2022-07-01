package com.sse.stock.market.repository;

import com.sse.stock.market.data.Stock;
import com.sse.stock.market.data.StockType;
import com.sse.stock.market.data.Trade;
import com.sse.stock.market.data.TradeType;
import com.sse.stock.market.exception.TradeException;
import com.sse.stock.market.repository.impl.StockRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class StockRepositoryImplTest {

    StockRepositoryImpl stockRepository;

    @BeforeEach
    public void setup() {
        stockRepository = new StockRepositoryImpl("TEA,COMMON,0,0,100|POP,COMMON,8,0,100|ALE,COMMON,23,0,60|GIN,PREFERRED,8,2,100|JOE,COMMON,13,0,250");
    }

    @Test
    public void shouldReturnAllStocks() {
        Set<Stock> sseStocks = stockRepository.getAllStocks();

        Assertions.assertNotNull(sseStocks);
        Assertions.assertEquals(5, sseStocks.size());
    }

    @Test
    public void shouldAddStock() {
        Stock stock = new Stock("TEST", "COMMON", "10d", "10d", "100");
        stockRepository.addStock(stock);
        Set<Stock> sseStocks = stockRepository.getAllStocks();

        Assertions.assertNotNull(sseStocks);
        Assertions.assertEquals(6, sseStocks.size());
        Assertions.assertTrue(sseStocks.contains(stock));
    }

    @Test
    public void shouldReturnStockWithSymbol() {
        Stock teaStock = stockRepository.findBySymbol("TEA").orElse(null);

        Assertions.assertNotNull(teaStock);
        Assertions.assertEquals("TEA", teaStock.getSymbol());
    }

    @Test
    public void shouldAddTradeToStock() throws TradeException {
        Trade trade = new Trade(2, 10d, TradeType.SELL);

        Assertions.assertTrue(stockRepository.addTrade("TEA", trade));
    }

    @Test
    public void shouldRetrieveTradesForStock() throws TradeException {
        Trade trade = new Trade(2, 10d, TradeType.SELL);
        stockRepository.addTrade("TEA", trade);
        Stock teaStock = stockRepository.findBySymbol("TEA").orElse(null);

        List<Trade> teaTrade = stockRepository.getTrades(teaStock);

        Assertions.assertNotNull(teaTrade);
        Assertions.assertEquals(1, teaTrade.size());
        Assertions.assertEquals(trade, teaTrade.get(0));
    }
}
