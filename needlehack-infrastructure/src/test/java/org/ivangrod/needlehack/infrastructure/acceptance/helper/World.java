package org.ivangrod.needlehack.infrastructure.acceptance.helper;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class World {

  private ResponseEntity response;

  public void setResponse(ResponseEntity response) {
    this.response = response;
  }

  public ResponseEntity getResponse() {
    return response;
  }
}
