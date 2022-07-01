/**
 * @author sangam156s
 */
package com.sse.stock.market.resource;

import com.sse.stock.market.data.Trade;
import com.sse.stock.market.exception.StockMarketException;
import com.sse.stock.market.exception.TradeException;
import com.sse.stock.market.service.StockService;
import com.sse.stock.market.service.TradeService;
import com.sse.stock.market.service.impl.StockServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("sse")
public class SSEResource {

    private static final Logger LOG = LogManager.getLogger(StockServiceImpl.class);
    private final StockService stockService;
    private final TradeService tradeService;

    /**
     * Arg-Constructor
     * @param stockService the {@link StockService} to set.
     * @param tradeService the {@link TradeService} to set.
     */
    @Autowired
    public SSEResource(final StockService stockService,
                        final TradeService tradeService) {
        this.tradeService = tradeService;
        this.stockService = stockService;
    }

    @PostMapping(path = "trade/{symbol}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDividendYield(@PathVariable final String symbol,
                                                  @RequestBody final Trade trade) {
        try {
            tradeService.tradeStock(symbol, trade);
            return ResponseEntity.created(null).build();
        } catch (final TradeException tex) {
            LOG.error("Error while adding trade to stock {}", symbol, tex);
            return ResponseEntity.badRequest().body(tex.getMessage());
        }
    }

    @GetMapping("/{symbol}/dividend-yield")
    public ResponseEntity<String> getDividendYield(@PathVariable final String symbol,
                                                   @RequestParam final String price) {

        try {
            final Double dividendYield = stockService.calculateDividendYield(Double.valueOf(price), symbol);
            return ResponseEntity.ok(String.valueOf(dividendYield));
        } catch (final StockMarketException sme) {
            LOG.error("Unable to get Dividend yield for {}", symbol, sme);
            return ResponseEntity.badRequest().body(sme.getMessage());
        }
    }

    @GetMapping("/{symbol}/pe-ratio")
    public ResponseEntity<String> getPERatio(@PathVariable final String symbol,
                                             @RequestParam final String price) {

        try {
            final Double peRatio = stockService.getPERatio(Double.valueOf(price), symbol);
            return ResponseEntity.ok(String.valueOf(peRatio));
        } catch (final StockMarketException sme) {
            LOG.error("Unable to get P/E ratio for {}", symbol, sme);
            return ResponseEntity.badRequest().body(sme.getMessage());
        }
    }

    @GetMapping("/{symbol}/volume-weight-price")
    public ResponseEntity<String> getVolumeWeightedStockPrice(@PathVariable final String symbol) {
        try{
             final Double stockPrice = stockService.getVolumeWeightedStock(symbol);
             return ResponseEntity.ok(String.valueOf(stockPrice));
        } catch (final StockMarketException sme) {
            LOG.error("Unable to get Volume Weighted Stock price for {}", symbol, sme);
            return ResponseEntity.badRequest().body(sme.getMessage());
        }
    }

    @GetMapping("/all-share-index")
    public ResponseEntity<String> getAllShareIndex() {
        final Double index = stockService.calculateGeometricMean();
        return ResponseEntity.ok(String.valueOf(index));
    }
}
