package ru.aao.eventservice.common.exception.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Error {

    @JsonProperty(value = "Код ошибки")
    @JsonPropertyDescription("code")
    private int code;

    @JsonProperty(value = "Расширенный код ошибки")
    @JsonPropertyDescription("compositeCode")
    private String compositeCode;

    @JsonProperty(value = "Текст ошибки")
    @JsonPropertyDescription("message")
    private String message;

    @JsonProperty(value = "Расширенный текст ошибки")
    @JsonPropertyDescription("detailMessage")
    private String detailMessage;

    public Error(ERROR_DETAIL err, String detailMessage) {
        this.compositeCode = null;
        switch(err) {
            case Unknown_error:
                this.code = HttpServletResponse.SC_BAD_REQUEST;
                this.message = "Unknown error";
                break;
            case Inner_error:
                this.code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                this.message = "Inner error";
                break;
            case External_service_error:
                this.code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                this.message = "External error";
                break;
            case Parse_error:
                this.code = HttpServletResponse.SC_BAD_REQUEST;
                this.message = "Parse error";
                break;
            default:
                this.code = HttpServletResponse.SC_BAD_REQUEST;
                this.message = "Unknown error";
                break;
        }
        this.detailMessage = detailMessage;
    }

    public String toString() {
        return "Error(code=" + this.getCode() + ", compositeCode=" + this.getCompositeCode() + ", message=" + this.getMessage() + ", detailMessage=" + this.getDetailMessage() + ")";
    }
}
