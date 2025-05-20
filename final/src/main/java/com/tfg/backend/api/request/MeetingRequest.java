package com.tfg.backend.api.request;

import com.tfg.backend.data.ErrorMessages.ErrorMessageRequests;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MeetingRequest(
    @NotNull(message = ErrorMessageRequests.SESSION_NULL)
    Integer FkSessionRq,
    
    @NotNull(message = ErrorMessageRequests.BOARDGAME_NULL)
    Integer FkBoardgameRq,
    
    @NotNull(message = ErrorMessageRequests.MEETING_DURATION_NULL)
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$", 
             message = ErrorMessageRequests.MEETING_DURATION_NOT_VALID)
    String meetingDurationRq
) {}
