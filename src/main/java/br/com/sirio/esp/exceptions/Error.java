package br.com.sirio.esp.exceptions;

import br.com.sirio.esp.domain.dto.response.FieldErrorResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

  private Integer httpStatusCode;
  private String internalCode;
  private String errorMessage;
  private List<FieldErrorResponse> errors;
}

