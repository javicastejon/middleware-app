package com.tfg.backend.api.request;

import com.tfg.backend.data.ErrorMessages.ErrorMessageRequests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecoveryRequest (

    @NotBlank(message = ErrorMessageRequests.USER_EMAIL_NULL)
    @Email(message = ErrorMessageRequests.USER_EMAIL_VALID)
    String emailRecoveryRq

) {}
