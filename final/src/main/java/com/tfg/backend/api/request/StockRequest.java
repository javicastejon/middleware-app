package com.tfg.backend.api.request;

import com.tfg.backend.data.ErrorMessages.ErrorMessageRequests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record StockRequest(
    
    @NotNull(message = ErrorMessageRequests.USER_NULL)
    Integer FkUserRq,

    @NotNull(message = ErrorMessageRequests.BOARDGAME_NULL)
    Integer FkBoardgameRq,

    @NotNull(message = ErrorMessageRequests.STOCK_UNITS_NULL)
    @Min(value = 0, message = ErrorMessageRequests.STOCK_UNITS_NOT_VALID)
    Integer unitsRq

) {}
