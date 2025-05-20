package com.tfg.backend.api.request;

import com.tfg.backend.data.ErrorMessages.ErrorMessageRequests;
import com.tfg.backend.exceptions.exceptions.ConflictException;

import jakarta.validation.constraints.NotNull;

public record UserAssociateRequest(

    @NotNull (message = ErrorMessageRequests.USER_NULL)
    Integer fkHostUserId,

    @NotNull (message = ErrorMessageRequests.USER_NULL)
    Integer fkAssociatedUserId

) {
    public UserAssociateRequest {
        if (fkHostUserId.equals(fkAssociatedUserId)) {
            throw new ConflictException(ErrorMessageRequests.ASSOCIATE_NOT_VALID);
        }
    }
}
