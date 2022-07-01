/**
 * @author sangam156s
 */
package com.sse.stock.market.exception;

public class TradeException extends  Exception {

    /**
     * Argument Constructor.
     * @param message the {@link String} message to set.
     */
    public TradeException(final String message) {
        super(message);
    }
}
